//package neu.edu.lab08;
//
//import java.text.DateFormat;
//import java.util.Date;
//import java.util.Locale;
//
//import neu.edu.lab08.dao.UserDao;
//import neu.edu.lab08.model.User;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.Validator;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.WebDataBinder;
//import org.springframework.web.bind.annotation.InitBinder;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
///**
// * Handles requests for the application home page.
// */
//@Controller
//public class HomeController {
//	
//	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
//	
//	/*
//	 * Specify this useValidate will be injected
//	 */
//	@Autowired
//	@Qualifier("userValidator")
//	private Validator validator;
//	
//	@Autowired
//	private UserDao userDao;
//	
//	/*
//	 * This is to initialize webDataBinder,set its
//	 * validator as we specify.
//	 */
//	@InitBinder
//	private void initBinder (WebDataBinder binder){
//		binder.setValidator(validator);
//	}
//	
//	
//	/**
//	 * Simply selects the home view to render by returning its name.
//	 */
//	@RequestMapping(value = "/", method = RequestMethod.GET)
//	public String initUserLoginForm(Model model) {
//		User user = new User();
//		model.addAttribute("user", user);
//		return "home";
//	}
//	
//	/*
//	 * Process From request
//	 */
//	@RequestMapping(value="/" ,method=RequestMethod.POST)
//	public String submitForm(Model model, @Validated User user, BindingResult result){
//		model.addAttribute("user",user);
//		String returnVal = "userHomePage";
//		if (result.hasErrors()){
//			
//			return "home";
//		}else{
//			try {
//				User u = userDao.queryUserByNameAndPassword(user.getName(), user.getPassword());
//				if (u != null){
//					model.addAttribute("user", u);
//					return returnVal;		
//				}
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//		}
//		
//		return "home";
//		
//	}
//	
//}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package neu.edu.lab08;




import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import neu.edu.lab08.dao.HibernateUtil;
import neu.edu.lab08.dao.InventoryDAO;
import neu.edu.lab08.dao.PatientDAO;
import neu.edu.lab08.dao.RequestDAO;
import neu.edu.lab08.dao.UserDao;
import neu.edu.lab08.dao.VaccineDAO;
import neu.edu.lab08.model.InsuredPatient;
import neu.edu.lab08.model.Inventory;
import neu.edu.lab08.model.Request;
import neu.edu.lab08.model.UninsuredPatient;
import neu.edu.lab08.model.User;
import neu.edu.lab08.model.Vaccine;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@Scope("session")
public class RequestController {

private static final Logger logger = LoggerFactory.getLogger(RequestController.class);
	
	/*
	 * Specify this useValidate will be injected
	 */

	
	@Autowired
	private UserDao userDao;
	@Autowired
	private VaccineDAO vaccineDao;
	@Autowired
	private PatientDAO patientDao;
	@Autowired
	private RequestDAO requestDao;
	@Autowired
	private InventoryDAO inventoryDao;

	
	
	@RequestMapping(value = "/requestforvaccine", method = RequestMethod.GET)
	public String request(Model model, @RequestParam("vaccine") int vaccineid, HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
        String username = (String)session.getAttribute("username");
//        HttpSession useridsession = request.getSession();
//        Integer userid = (Integer)useridsession.getAttribute("userid");
        Vaccine vaccine = vaccineDao.listVaccineByVaccineid(vaccineid);
        model.addAttribute("vaccine", vaccine);
		model.addAttribute("hospitalusername", username);
//		model.addAttribute("userid", userid);
		return "requestvaccine";
	}
	
	@RequestMapping(value = "/requestvaccine", method = RequestMethod.POST)
	public String requestvaccine(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Integer id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		Integer price = Integer.parseInt(request.getParameter("price"));
		Integer availability = Integer.parseInt(request.getParameter("availability"));
		String expiredate = request.getParameter("expiredate");
		Integer requestQuantity = Integer.parseInt(request.getParameter("requestQuantity"));
		Vaccine vaccine = vaccineDao.listVaccineByVaccineid(id);
		Integer quantity = vaccine.getAvailability();
		System.out.print("------quantity-------"+quantity+"---------------");
		System.out.print("------requestQuantity-------"+requestQuantity+"---------------");
		if(requestQuantity > quantity){
			return "hospitalMenu";
		}
		
		HttpSession session = request.getSession();
        String username = (String)session.getAttribute("username");
		
		Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
		hibernateSession.beginTransaction();
		
		Request vr = new Request();
		
			vr.setVaccineid(id);
			vr.setVaccinename(name);
			vr.setAvailability(availability);
			vr.setExpiredate(expiredate);
			vr.setQuantity(requestQuantity);
			vr.setPrice(price);
			vr.setTotalprice(requestQuantity * price);
			vr.setStatus("Not Approved");
			vr.setUsername(username);
			
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			Date date = new Date();
			String s = dateFormat.format(date);
			
			hibernateSession.save(vr);
			hibernateSession.getTransaction().commit();
			
			requestDao.updateRequestAvailability((availability-requestQuantity), id);
			requestDao.updateVaccineAvailability((availability-requestQuantity), id);
		return "hospitalMenu";
	}
	
	
	@RequestMapping(value = "/vaccineList", method = RequestMethod.GET)
	public String gernerateXmlResult(Model model, @RequestParam("vaccinename") String vaccinename) throws Exception {
		String[] vaccineArray = {"Adenovirus", "Anthrax", "Hepatitis", "Measles", "Rubella", "Varicella", "TB"};
		for(int i = 0; i < vaccineArray.length; i++){
			if(vaccinename.equals("")){
		        model.addAttribute("checkVaccineName", "please input a name");
			
			}
			else{
				boolean contains = Arrays.asList(vaccineArray).contains(vaccinename);
				if(contains){
					model.addAttribute("checkVaccineName", vaccinename + " is valid name");
				}
				else{
					model.addAttribute("checkVaccineName", vaccinename + " is not valid name");
				}
			}
			
			}
		
		return "checkVaccine";
	}
	
	
}

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




import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

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
import neu.edu.lab08.model.Patient;
import neu.edu.lab08.model.Request;
import neu.edu.lab08.model.UninsuredPatient;
import neu.edu.lab08.model.UsedVaccine;
import neu.edu.lab08.model.User;
import neu.edu.lab08.model.Vaccine;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.servlet.*;
import org.apache.commons.io.output.*;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
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



@Controller
@Scope("session")
public class HomeController {

private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/*
	 * Specify this useValidate will be injected
	 */
	@Autowired
	@Qualifier("userValidator")
	private Validator validator;
	
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
	/*
	 * This is to initialize webDataBinder,set its
	 * validator as we specify.
	 */
	@InitBinder
	private void initBinder (WebDataBinder binder){
		binder.setValidator(validator);
	}
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws Exception 
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String initUserLoginForm(Model model, HttpServletRequest request) throws Exception {
		Cookie[] cookies = request.getCookies();
		String usernameCookie = "";
		String passwordCookie = "";
		for(Cookie cookie : cookies){
			if(cookie.getName().equals("usernameCookie")){
				usernameCookie = cookie.getValue();
			}
			if(cookie.getName().equals("passwordCookie")){
				passwordCookie = cookie.getValue();
			}
		}
		User u = userDao.queryUserByNameAndPassword(usernameCookie, passwordCookie);
		if(u != null){
			try{
				if(u.getRole().equals("admin")){
					model.addAttribute("user", u);
					HttpSession session = request.getSession();
		            session.setAttribute("username", u.getUsername());
					return "createUser";
				}
				else if(u.getRole().equals("CDC")){
					HttpSession session = request.getSession();
		            session.setAttribute("username", u.getUsername());
					model.addAttribute("user", u);
					ArrayList<Vaccine> vaccineList = vaccineDao.listVaccineByUsername(u.getUsername());
					ArrayList<Request> requestList = requestDao.listRequest();
					model.addAttribute("producedvaccineList", vaccineList);
					model.addAttribute("requestList", requestList);
					return "cdcMenu";
				}
				else if(u.getRole().equals("Hospital")){
					HttpSession session = request.getSession();
		            session.setAttribute("username", u.getUsername());
					model.addAttribute("user", u);
					ArrayList<Vaccine> vaccineList = vaccineDao.listVaccine();
					ArrayList<InsuredPatient> insuredPatientList = patientDao.listInsuredPatientByUsername(u.getUsername());
					ArrayList<UninsuredPatient> uninsuredPatientList = patientDao.listUninsuredPatientByUsername(u.getUsername());
					ArrayList<Inventory> inventoryList = inventoryDao.listInventoryByUser(u.getUsername());
					model.addAttribute("insuredPatientList", insuredPatientList);
					model.addAttribute("uninsuredPatientList", uninsuredPatientList);
					model.addAttribute("vaccineList", vaccineList);
					model.addAttribute("inventoryList", inventoryList);
					HttpSession inventorysession = request.getSession();
					inventorysession.setAttribute("inventoryList", inventoryList);
					ArrayList<UsedVaccine> usedvaccineList = vaccineDao.listUsedVaccineByUsername(u.getUsername());
					model.addAttribute("usedvaccineList", usedvaccineList);
					return "hospitalMenu";
				}
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		else{
			User user = new User();
			model.addAttribute("user", user);
			
		}
		return "home";
	}
	
	/*
	 * Process From request
	 */
	@RequestMapping(value="/login" ,method=RequestMethod.POST)
	public String submitForm(Model model, @Validated User user, BindingResult result, HttpServletRequest request, HttpServletResponse response){
		model.addAttribute("user",user);
		String returnVal = "menu";
		String remember = request.getParameter("remember");
		if (result.hasErrors()){
			
			return "home";
		}else{
			try {
				User u = userDao.queryUserByNameAndPassword(user.getUsername(), user.getPassword());
//				ArrayList<Message> messageList = messageDao.listMessagesByUsername(u.getUsername());
//				ArrayList<Contacts> contactsList = contactsDao.listContactsByUsername(u.getUsername());
//				int messageSize = messageList.size();
//				int contactsSize = contactsList.size();
				if (u != null){
					if(remember != null){
		                Cookie usernameCookie = new Cookie("usernameCookie", u.getUsername());
		                Cookie passwordCookie = new Cookie("passwordCookie", u.getPassword());
		                usernameCookie.setMaxAge(604000);
		                passwordCookie.setMaxAge(604000);
		                response.addCookie(usernameCookie);
		                response.addCookie(passwordCookie);
		            }
					
						if(u.getRole().equals("admin")){
							model.addAttribute("user", u);
							HttpSession session = request.getSession();
				            session.setAttribute("username", u.getUsername());
//				            HttpSession useridsession = request.getSession();
//				            useridsession.setAttribute("userid", u.getId());
							return "createUser";
						}
						else if(u.getRole().equals("CDC")){
							HttpSession session = request.getSession();
				            session.setAttribute("username", u.getUsername());
//				            HttpSession useridsession = request.getSession();
//				            useridsession.setAttribute("userid", u.getId());
							model.addAttribute("user", u);
							ArrayList<Vaccine> vaccineList = vaccineDao.listVaccineByUsername(u.getUsername());
							ArrayList<Request> requestList = requestDao.listRequest();
							model.addAttribute("producedvaccineList", vaccineList);
							model.addAttribute("requestList", requestList);
							return "cdcMenu";
						}
						else if(u.getRole().equals("Hospital")){
							HttpSession session = request.getSession();
				            session.setAttribute("username", u.getUsername());
//				            HttpSession useridsession = request.getSession();
//				            useridsession.setAttribute("userid", u.getId());
							model.addAttribute("user", u);
							ArrayList<Vaccine> vaccineList = vaccineDao.listVaccine();
							ArrayList<InsuredPatient> insuredPatientList = patientDao.listInsuredPatientByUsername(u.getUsername());
							ArrayList<UninsuredPatient> uninsuredPatientList = patientDao.listUninsuredPatientByUsername(u.getUsername());
							ArrayList<Inventory> inventoryList = inventoryDao.listInventoryByUser(u.getUsername());
							model.addAttribute("insuredPatientList", insuredPatientList);
							model.addAttribute("uninsuredPatientList", uninsuredPatientList);
							model.addAttribute("vaccineList", vaccineList);
							model.addAttribute("inventoryList", inventoryList);
							HttpSession inventorysession = request.getSession();
							inventorysession.setAttribute("inventoryList", inventoryList);
							ArrayList<UsedVaccine> usedvaccineList = vaccineDao.listUsedVaccineByUsername(u.getUsername());
							model.addAttribute("usedvaccineList", usedvaccineList);
							return "hospitalMenu";
						}
					}		
				}
			 catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return "home";
		
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Cookie[] cookies = request.getCookies();
		if(cookies != null){
			for(int i = 0; i < cookies.length; i++){
				cookies[i].setValue("");
				cookies[i].setPath("/");
				cookies[i].setMaxAge(0);
				response.addCookie(cookies[i]);
			}
		}
//		model.addAttribute("user", null);
//		model.addAttribute("messageList", null);
//		model.addAttribute("contactsList", null);
//		HttpSession session = request.getSession();
//        session.setAttribute("username", null);
		return initUserLoginForm(model, request);
	}
	
	@RequestMapping(value = "/createuser", method = RequestMethod.POST)
	public String createUser(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String from = request.getParameter("from");
		String role = request.getParameter("role");
		
		Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
		hibernateSession.beginTransaction();
		
		User user = new User();
		
			user.setUsername(username);
			user.setPassword(password);
			user.setFrom(from);
			user.setRole(role);
			hibernateSession.save(user);
			
			hibernateSession.getTransaction().commit();
		return "createUser";
	}
	
	@RequestMapping(value = "/createvaccine", method = RequestMethod.POST)
	public String createvaccine(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String vaccinename = request.getParameter("vaccinename");
		Integer price = Integer.parseInt(request.getParameter("price"));
		Integer availability = Integer.parseInt(request.getParameter("availability"));
		String expiredate = request.getParameter("expiredate");
		
		HttpSession session = request.getSession();
        String username = (String)session.getAttribute("username");
		
		Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
		hibernateSession.beginTransaction();
		
		Vaccine vaccine = new Vaccine();
		
			vaccine.setName(vaccinename);
			vaccine.setPrice(price);
			vaccine.setAvailability(availability);
			vaccine.setManufacture(username);
			
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			Date date = new Date();
			String s = dateFormat.format(date);
			vaccine.setProducedate(s);
			vaccine.setExpiredate(expiredate);
			vaccine.setStatus(1);
			
			hibernateSession.save(vaccine);
			hibernateSession.getTransaction().commit();
		return "cdcMenu";
	}
	
	@RequestMapping(value = "/createpatientanduploadpicture", method = RequestMethod.POST)
	public String createpatientanduploadpicture(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String name = request.getParameter("name");
		String gender = (request.getParameter("gender"));
		String dob = request.getParameter("dob");
		String insurance= request.getParameter("insurance");
		Integer amount = Integer.parseInt(request.getParameter("amount"));
		
		HttpSession session = request.getSession();
        session.setAttribute("name", name);
        session.setAttribute("gender", gender);
        session.setAttribute("dob", dob);
        session.setAttribute("insurance", insurance);
        session.setAttribute("amount", amount);
        
		return "uploadPicture";
	}
	
	@RequestMapping(value = "/createpatient", method = RequestMethod.POST)
	public String createpatient(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
//		String name = request.getParameter("name");
//		String gender = (request.getParameter("gender"));
//		String dob = request.getParameter("dob");
//		String insurance= request.getParameter("insurance");
//		Integer amount = Integer.parseInt(request.getParameter("amount"));
		
		HttpSession session = request.getSession();
        String username = (String)session.getAttribute("username");
        String name = (String)session.getAttribute("name");
        String gender = (String)session.getAttribute("gender");
        String dob = (String)session.getAttribute("dob");
        String insurance = (String)session.getAttribute("insurance");
        Integer amount = (Integer)session.getAttribute("amount");
        
        Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
		hibernateSession.beginTransaction();
		
		String fileName = null;
		
		File file ;
		   int maxFileSize = 5000 * 1024;
		   int maxMemSize = 5000 * 1024;

		   String filePath = "/Users/mengqingwang/Downloads/lab08/src/main/webapp/resources/picture";

		   // 验证上传内容了类型
		   String contentType = request.getContentType();
		   if ((contentType.indexOf("multipart/form-data") >= 0)) {

		      DiskFileItemFactory factory = new DiskFileItemFactory();
		      // 设置内存中存储文件的最大值
		      factory.setSizeThreshold(maxMemSize);
		      // 本地存储的数据大于 maxMemSize.
		      factory.setRepository(new File("c:\\temp"));

		      // 创建一个新的文件上传处理程序
		      ServletFileUpload upload = new ServletFileUpload(factory);
		      // 设置最大上传的文件大小
		      upload.setSizeMax( maxFileSize );
		      try{ 
		         // 解析获取的文件
		         List fileItems = upload.parseRequest(request);

		         // 处理上传的文件
		         Iterator i = fileItems.iterator();
		         
		         while ( i.hasNext () ) 
		         {
		            FileItem fi = (FileItem)i.next();
		            if ( !fi.isFormField () )	
		            {
		            // 获取上传文件的参数
		            String fieldName = fi.getFieldName();
		            fileName=fi.getName();
		            //String fileNamePath = "\\images\\"+fileName;
		            
		            boolean isInMemory = fi.isInMemory();
		            long sizeInBytes = fi.getSize();
		            // 写入文件
		            if( fileName.lastIndexOf("\\") >= 0 ){
		            file = new File( filePath , 
		            fileName.substring( fileName.lastIndexOf("\\"))) ;
		            }else{
		            file = new File( filePath ,
		            fileName.substring(fileName.lastIndexOf("\\")+1)) ;
		            }
		            fi.write( file ) ;
		            }
		         }
		      }
		      catch(Exception ex) {
			         System.out.println(ex);
			      }
		if(insurance.equals("Insured")){
			InsuredPatient ip = new InsuredPatient();
			
			ip.setName(name);
			ip.setGender(gender);
			ip.setDob(dob);
			ip.setPatienttype(insurance);
			ip.setPicture(fileName);
			ip.setHospital(username);
			ip.setInsuredamount(amount);
			ip.setStatus(1);
			hibernateSession.save(ip);
			hibernateSession.getTransaction().commit();
		}
		else if(insurance.equals("Uninsured")){
			UninsuredPatient up = new UninsuredPatient();
			
			up.setName(name);
			up.setGender(gender);
			up.setDob(dob);
			up.setPatienttype(insurance);
			up.setPicture(fileName);
			up.setHospital(username);
			up.setAccount(amount);
			up.setStatus(1);
			hibernateSession.save(up);
			hibernateSession.getTransaction().commit();
		}
		
		   }
		   return "hospitalMenu";
	}
	@RequestMapping(value = "/deletevaccine", method = RequestMethod.POST)
	public String deleteVaccine(Model model, HttpServletRequest request) throws Exception {
		String vaccineIdArr[] = request.getParameterValues("deletevaccine");
		if(vaccineIdArr != null){
			for(int i = 0; i < vaccineIdArr.length; i++){
//            vaccineDao.deleteVaccine(vaccineIdArr[i]);
				vaccineDao.updateVaccineStatus(vaccineIdArr[i], 2);
			}
		}
		return "deleteVaccine";
	}
	
	@RequestMapping(value = "/deleteinsuredpatient", method = RequestMethod.POST)
	public String deleteInsuredpatient(Model model, HttpServletRequest request) throws Exception {
		String insuredpatientIdArr[] = request.getParameterValues("delete");
		if(insuredpatientIdArr != null){
			for(int i = 0; i < insuredpatientIdArr.length; i++){
//            patientDao.deleteinsuredpatient(insuredpatientIdArr[i]);
				patientDao.updateInsuredpatientStatus(insuredpatientIdArr[i]);
			}
		}
		return "deletepatient";
	}
	
	@RequestMapping(value = "/deleteuninsuredpatient", method = RequestMethod.POST)
	public String deleteUninsuredpatient(Model model, HttpServletRequest request) throws Exception {
		String uninsuredpatientIdArr[] = request.getParameterValues("delete");
		if(uninsuredpatientIdArr != null){
			for(int i = 0; i < uninsuredpatientIdArr.length; i++){
//            patientDao.deleteuninsuredpatient(uninsuredpatientIdArr[i]);
				patientDao.updateUninsuredpatientStatus(uninsuredpatientIdArr[i]);
			}
		}
		return "deletepatient";
	}
	
	@RequestMapping(value = "/vaccine", method = RequestMethod.GET)
	public String assignVaccineip(Model model, @RequestParam("patientid") String patientid, HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
        String username = (String)session.getAttribute("username");
        ArrayList<Inventory> inventoryList = inventoryDao.listInventoryByUser(username);
        model.addAttribute("inventoryList", inventoryList);
		model.addAttribute("patientid", patientid);
		model.addAttribute("username", username);
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		String s = dateFormat.format(date);
		model.addAttribute("date", s);
		
		return "assignvaccine";
	}
	
	@RequestMapping(value = "/assignvaccine", method = RequestMethod.POST)
	public String vaccine(Model model, HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
        String username = (String)session.getAttribute("username");
        Integer patientid = Integer.parseInt(request.getParameter("patientid"));
        String inventoryIdArr[] = request.getParameterValues("assignvaccine");
		if(inventoryIdArr != null){
			for(int i = 0; i < inventoryIdArr.length; i++){
				
				Inventory inventory = inventoryDao.listInventoryById(inventoryIdArr[i]);
				Integer vaccineid = inventory.getVaccineid();
				System.out.print("-------id---------"+vaccineid+"--------------------");
				Vaccine vaccine = vaccineDao.listVaccineByVaccineid(vaccineid);
				Integer vaccineprice = vaccine.getPrice();
				System.out.print("-------price---------"+vaccineprice+"--------------------");
				Integer inventoryQuantity = inventory.getQuantity();
				Integer newquantity = inventoryQuantity - 1 ;
				inventoryDao.updateInventory(newquantity, inventoryIdArr[i]);
				
				Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
	    		hibernateSession.beginTransaction();
	    		
				UsedVaccine uv = new UsedVaccine();
				uv.setVaccineid(inventory.getVaccineid());
				uv.setVaccinename(inventory.getVaccinename());
				uv.setPatientid(patientid);
				System.out.println(inventory.getVaccine());
				uv.setVaccineprice(vaccineprice);
//				System.out.print(inventory.getVaccine().getPrice());
				DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
				Date date = new Date();
				String s = dateFormat.format(date);
				uv.setDate(s);
				uv.setHospital(username);
				uv.setPayamount(vaccineprice);
				hibernateSession.save(uv);
				hibernateSession.getTransaction().commit();
				
				Patient patient = patientDao.patientById(patientid);
				String patienttype = patient.getPatienttype();
				if(patienttype.equals("Insured")){
					InsuredPatient insuredpatient = patientDao.insuredpatientById(patientid);
					Integer insuredamount = insuredpatient.getInsuredamount();
					Integer newamount = insuredamount - vaccineprice;
					patientDao.updateInsuredpatient(newamount, patientid);
				}
				else if(patienttype.equals("Uninsured")){
					UninsuredPatient uninsuredpatient = patientDao.uninsuredpatientById(patientid);
					Integer account = uninsuredpatient.getAccount();
					Integer newaccount = account - vaccineprice;
					patientDao.updateUninsuredpatient(newaccount, patientid);
				}
			}
		}
		return "vaccine";
	}
	
//	@RequestMapping(value = "/requestforvaccine", method = RequestMethod.GET)
//	public String request(Model model, @RequestParam("vaccine") int vaccineid, HttpServletRequest request) throws Exception {
//		HttpSession session = request.getSession();
//        String username = (String)session.getAttribute("username");
////        HttpSession useridsession = request.getSession();
////        Integer userid = (Integer)useridsession.getAttribute("userid");
//        Vaccine vaccine = vaccineDao.listVaccineByVaccineid(vaccineid);
//        model.addAttribute("vaccine", vaccine);
//		model.addAttribute("username", username);
////		model.addAttribute("userid", userid);
//		return "request";
//	}
	
//	@RequestMapping(value = "/requestvaccine", method = RequestMethod.POST)
//	public String requestvaccine(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
//		Integer id = Integer.parseInt(request.getParameter("id"));
//		String name = request.getParameter("name");
//		double price = Double.parseDouble(request.getParameter("price"));
//		Integer availability = Integer.parseInt(request.getParameter("availability"));
//		String expiredate = request.getParameter("expiredate");
//		Integer requestQuantity = Integer.parseInt(request.getParameter("requestQuantity"));
//		Integer userid = Integer.parseInt(request.getParameter("userid"));
//		
//		HttpSession session = request.getSession();
//        String username = (String)session.getAttribute("username");
//		
//		Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
//		hibernateSession.beginTransaction();
//		
//		Request vr = new Request();
//		
//			vr.setVaccineid(id);
//			vr.setVaccinename(name);
//			vr.setAvailability(availability);
//			vr.setQuantity(requestQuantity);
//			vr.setPrice(price);
//			vr.setTotalprice(requestQuantity * price);
//			vr.setStatus("Not Approved");
//			vr.setUsername(username);
//			
//			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
//			Date date = new Date();
//			String s = dateFormat.format(date);
//			
//			hibernateSession.save(vr);
//			
//		return "hospitalMenu";
//	}
	
	@RequestMapping(value = "/approverequest", method = RequestMethod.POST)
	public String approverequest(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
//		Integer vaccineid = Integer.parseInt(request.getParameter("vaccineid"));
//		String vaccinename = request.getParameter("vaccinename");
//		Integer quantity = Integer.parseInt(request.getParameter("quantity"));
//		String user = request.getParameter("user");
		String requestIdArr[] = request.getParameterValues("approve");
		if(requestIdArr != null){
			for(int i = 0; i < requestIdArr.length; i++){
            Request approvedRequest = requestDao.requestByRequestid(requestIdArr[i]);
            String status = approvedRequest.getStatus();
            
            if(status.equals("Not Approved")){
            	Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
        		hibernateSession.beginTransaction();
        		
        		Inventory inventory = new Inventory();
        		
    			inventory.setVaccineid(approvedRequest.getVaccineid());
    			inventory.setVaccinename(approvedRequest.getVaccinename());
    			inventory.setExpiredate(approvedRequest.getExpiredate());
    			inventory.setQuantity(approvedRequest.getQuantity());
    			inventory.setUser(approvedRequest.getUsername());
    			
    			hibernateSession.save(inventory);
    			hibernateSession.getTransaction().commit();
                
    			requestDao.updateRequest("Approved", approvedRequest.getVaccineid());
            }
            else if(status.equals("Approved")){
            	System.out.print("Request has been aproved already");
            }
            
			}
		}
		
		HttpSession session = request.getSession();
        String username = (String)session.getAttribute("username");
		
		
		
		
//		Vaccine vaccine = vaccineDao.listVaccineByVaccineid(vaccineid);
//		Integer newAvailability = (vaccine.getAvailability() - quantity);
//		vaccineDao.updateVaccine(vaccineid, newAvailability);
		
		
		return "cdcMenu";
	}
	
}

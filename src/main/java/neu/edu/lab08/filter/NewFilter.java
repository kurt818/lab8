package neu.edu.lab08.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class NewFilter
 */
public class NewFilter implements Filter {

	private static final boolean debug = true;
    /**
     * Default constructor. 
     */
	private FilterConfig filterConfig = null;
	
	private final String number="[^0-9]";
    
    private final String letterandnumber="[^A-Za-z0-9]";
	
    public NewFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	
	public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }
	
	public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

	
	
	private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("BookFilter:DoBeforeProcessing");
        }
	}
        
    private void doAfterProcessing(ServletRequest request, ServletResponse response)
                throws IOException, ServletException {
            if (debug) {
                log("BookFilter:DoAfterProcessing");
            }
        
    }
        
	private static boolean isLong(String in){
	  boolean flag=true;
	  try{
		  Long l=Long.parseLong(in);
	  }
	catch(Exception e){
		flag=false;
	}
	return flag;	
	}
	
	private static boolean isInt(Integer age){
		  boolean flag=true;
		  try{
			  int l=age;
		  }
		catch(Exception e){
			flag=false;
		}
		return flag;	
		}
		
	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		String createtype = request.getParameter("create");

		HttpServletRequest servletrequest = (HttpServletRequest) request;
		HttpServletResponse servletresponse = (HttpServletResponse) response;
		XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper(
				(HttpServletRequest) request);
		boolean status = false;
		if (createtype != null) {
			if(createtype.equals("vaccine")){
                HttpServletRequest req = (HttpServletRequest) request;
                HttpServletResponse res = (HttpServletResponse) response; 
            
                String vaccinename=request.getParameter("vaccinename");
                String price=request.getParameter("price");
                String availability=request.getParameter("availability");
                String expiredate=request.getParameter("expiredate");
                
                if (!(regexChecker(vaccinename, letterandnumber)&&regexChecker(price, number)&&regexChecker(availability, number)))
                    {
                        PrintWriter out = res.getWriter();
                        out.print("<script language='javascript'>alert(\"please input number\");" + "window.history.go(-1);</script>");
                    }else
                {
                    chain.doFilter(request, response);
                }
            }
			else if(createtype.equals("patient")){
                HttpServletRequest req = (HttpServletRequest) request;
                HttpServletResponse res = (HttpServletResponse) response; 
                String amount = request.getParameter("amount");
                if (!regexChecker(amount, number)){
                    PrintWriter out = res.getWriter();
                    out.print("<script language='javascript'>alert(\"please input number\");" + "window.history.go(-1);</script>");
                }
                else{
                    chain.doFilter(request, response);
                }
            }
			else if(createtype.equals("request")){
                HttpServletRequest req = (HttpServletRequest) request;
                HttpServletResponse res = (HttpServletResponse) response; 
                String requestQuantity = request.getParameter("requestQuantity");
                if (!regexChecker(requestQuantity, number)){
                    PrintWriter out = res.getWriter();
                    out.print("<script language='javascript'>alert(\"please input number\");" + "window.history.go(-1);</script>");
                }
                else{
                    chain.doFilter(request, response);
                }
            }
		}
		else{
            chain.doFilter(request, response);
        }

//			if (createtype.equals("vaccine")) {
//				Integer price = Integer.parseInt(request.getParameter("price"));
//				Integer availability = Integer.parseInt(request
//						.getParameter("availability"));
//				if (isInt(price) && isInt(availability)) {
//					status = true;
//				}
//			} else if (createtype.equals("patient")) {
//				Integer amount = Integer.parseInt(request
//						.getParameter("amount"));
//				if (isInt(amount)) {
//					status = true;
//				}
//
//			} else if (createtype.equals("request")) {
//				Integer requestQuantity = Integer.parseInt(request
//						.getParameter("requestQuantity"));
//				if (isInt(requestQuantity)) {
//					status = true;
//				}
//
//			} else {
//				status = true;
//			}
//		} else {
//			status = true;
//		}
//		if (!status) {
//			PrintWriter out = servletresponse.getWriter();
//			out.print("<script language='javascript'>alert(\"Please enter number\");"
//					// + servletrequest.getRequestURL()
//					+ "window.history.go(-1);</script>");
//		} else
//			chain.doFilter(xssRequest, response);
//
//		// pass the request along the filter chain

	}

	public void log(String msg) {
        filterConfig.getServletContext().log(msg);        
    }
    
	@Override
    public String toString() {
        if (filterConfig == null) {
            return ("BookFilter()");
        }
        StringBuffer sb = new StringBuffer("BookFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		 this.filterConfig = filterConfig;
	        if (filterConfig != null) {
	            if (debug) {                
	                log("BookFilter:Initializing filter");
	            }
	        }
	}
	public boolean regexChecker(String x, String reg) {
        Pattern checkRegex = Pattern.compile(reg);
        Matcher regexMatcher = checkRegex.matcher(x);
        if (regexMatcher.find()) {
            return false;
        }
        return true;

    }

}

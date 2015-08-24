package neu.edu.lab08.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.*;


/**
 * Servlet Filter implementation class searchFilter
 */
@WebFilter(filterName = "SqlInjectFilter", urlPatterns = {"/*"})
public class sqlInjectionFilter implements Filter {

	private static List<String> invalidsql = new ArrayList<String>(); 
    private static String error = "/error.jsp"; 
    
    private static final boolean debug = true;
    
    private FilterConfig filterConfig = null;
    /**
     * Default constructor. 
     */
    public sqlInjectionFilter() {
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
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		if(debug){ 
            System.out.println("prevent sql inject filter works"); 
        } 
        HttpServletRequest request = (HttpServletRequest)req; 
        HttpServletResponse response = (HttpServletResponse)res; 
        Map<String, String[]> params = request.getParameterMap(); 
        Set<String> keys = params.keySet(); 
        for(String key : keys){ 
            String value = request.getParameter(key); 
            if(debug){ 
                System.out.println("process params <key, value>: <"+key+", "+value+">"); 
            } 
            for(String word : invalidsql){ 
                if(word.equalsIgnoreCase(value) || value.contains(word)){ 
                    if(value.contains("<")){ 
                        value = value.replace("<", "<"); 
                    } 
                    if(value.contains(">")){ 
                        value = value.replace(">", ">"); 
                    } 
                    request.getSession().setAttribute("sqlInjectError", "the request parameter \""+value+"\" contains keyword: \""+word+"\""); 
                    response.sendRedirect(request.getContextPath()+error); 
                    return; 
                } 
            } 
        } 
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

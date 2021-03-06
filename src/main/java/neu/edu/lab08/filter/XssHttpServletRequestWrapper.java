package neu.edu.lab08.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper{

	HttpServletRequest orgRequest = null;
	
	public XssHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
		orgRequest=request;
		// TODO Auto-generated constructor stub
	}

	public String getParameter(String name) {  
        String value = super.getParameter(xssEncode(name));  
        if (value != null) {  
            value = xssEncode(value);  
        }  
        return value;  
    } 
	
	@Override  
    public String getHeader(String name) {  
  
        String value = super.getHeader(xssEncode(name));  
        if (value != null) {  
            value = xssEncode(value);  
        }  
        return value;  
    }  
    
    private static String xssEncode(String s) {  
        if (s == null || "".equals(s)) {  
            return s;  
        }  
        StringBuilder sb = new StringBuilder(s.length() + 16);  
        for (int i = 0; i < s.length(); i++) {  
            char c = s.charAt(i);  
            switch (c) {  
            case '>':  
                sb.append('＞');//全角大于号  
                break;  
            case '<':  
                sb.append('＜');//全角小于号  
                break;  
            case '\'':  
                sb.append('‘');//全角单引号  
                break;  
            case '\"':  
                sb.append('“');//全角双引号  
                break;  
            case '&':  
                sb.append('＆');//全角  
                break;  
            case '\\':  
                sb.append('＼');//全角斜线  
                break;  
            case '#':  
                sb.append('＃');//全角井号  
                break;  
            default:  
                sb.append(c);  
                break;  
            }  
        }  
        return sb.toString();  
    }  
    
     public HttpServletRequest getOrgRequest() {  
        return orgRequest;  
    } 
     
     public static HttpServletRequest getOrgRequest(HttpServletRequest req) {  
        if (req instanceof XssHttpServletRequestWrapper) {  
            return ((XssHttpServletRequestWrapper) req).getOrgRequest();  
        }  
  
        return req;  
    }  
    
}

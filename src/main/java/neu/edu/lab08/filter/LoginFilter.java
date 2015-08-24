//package neu.edu.lab08.filter;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//@WebFilter(urlPatterns = {"/*"})
//public class LoginFilter implements Filter{
//
//	public void doFilter(ServletRequest request, ServletResponse response,
//			FilterChain chain) throws ServletException, IOException {
//		HttpServletRequest servletRequest = (HttpServletRequest) request;
//		HttpServletResponse servletResponse = (HttpServletResponse) response;
//
//		HttpSession session = servletRequest.getSession();
//
//		String path = servletRequest.getRequestURI();
//		System.out.println(path);
//
//		if (path.equals("/lab08/")||path.equals("/lab08/logout")
//		 ) {
//			chain.doFilter(servletRequest, servletResponse);
//			System.out.println("dddd");
//			return;
//
//		} else {
//
//			if (session.getAttribute("username") == null) {
//
//				servletResponse.sendRedirect("/lab08/logout");
//
//				PrintWriter out = servletResponse.getWriter();
//				out.print("<script language='javascript'>alert(\"Please log in before shopping\");"
//						// + servletrequest.getRequestURL()
//						+ "window.history.go(-1);</script>");
//				System.out.println("no user");
//			} else {
//				System.out.println("ccc");
//				chain.doFilter(request, response);
//			}
//		}
//		// pass the request along the filter chain
//
//	}
//
//	@Override
//	public void destroy() {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void init(FilterConfig arg0) throws ServletException {
//		// TODO Auto-generated method stub
//		
//	}
//}

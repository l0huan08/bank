package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest servletRequest = (HttpServletRequest)request;
		HttpServletResponse serlvetResponse = (HttpServletResponse)response;
		HttpSession session = servletRequest.getSession();
		
		//Get the request path
		String path = servletRequest.getRequestURI();
		
		//Get username from session
		String username = (String)session.getAttribute("username");
		
		//No need to filter login page and register page
		if(path.indexOf("/login.jsp") > -1){
			chain.doFilter(servletRequest, serlvetResponse);
			return;
		}
		if(path.indexOf("/register.jsp") > -1){
			chain.doFilter(servletRequest, serlvetResponse);
			return;
		}
		
		//If logged in
		if(username == null || "".equals(username)){
			serlvetResponse.sendRedirect("login.jsp");
		} else{
			chain.doFilter(servletRequest, serlvetResponse);
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
}

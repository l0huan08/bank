package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(description = "right", urlPatterns = { "*.jsp" })
public class AuthorityFilter  implements Filter{

	@Override
	public void destroy(){
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest servletRequest = (HttpServletRequest)request;
		HttpServletResponse serlvetResponse = (HttpServletResponse)response;
		HttpSession session = servletRequest.getSession();
		
		String path = servletRequest.getRequestURI();
		
		String status = (String)session.getAttribute("status");
		
		if(path.indexOf("/login.jsp") > -1){
			chain.doFilter(servletRequest, serlvetResponse);
			return;
		}
		if(path.indexOf("/register.jsp") > -1){
			chain.doFilter(servletRequest, serlvetResponse);
			return;
		}
		
		if(status == "admin"){
			if(path.indexOf("clientMain.jsp") > -1 || path.indexOf("clientTop.jsp") > -1 || path.indexOf("clientleft.jsp") > -1 || path.indexOf("clientIndex.jsp") > -1 || path.indexOf("clientInfo.jsp") > -1 || path.indexOf("openAccount.jsp") > -1 || path.indexOf("accountDetail.jsp") > -1 || path.indexOf("depositCheck.jsp") > -1 || path.indexOf("viewStatement.jsp") > -1){
				serlvetResponse.sendRedirect("error.jsp");
			} else{
				chain.doFilter(servletRequest, serlvetResponse);
			}
		} else{
			if(path.indexOf("adminMain.jsp") > -1 || path.indexOf("adminTop.jsp") > -1 || path.indexOf("adminLeft.jsp") > -1 || path.indexOf("adminIndex.jsp") > -1 || path.indexOf("adminClientInfo.jsp") > -1 || path.indexOf("editClientInfo.jsp") > -1){
				serlvetResponse.sendRedirect("error.jsp");
			} else{
				chain.doFilter(servletRequest, serlvetResponse);
			}
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
}
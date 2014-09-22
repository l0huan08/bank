package bank.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bank.dao.ClientDao;
import bank.dao.AdminDao;;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(description = "LoginServlet", urlPatterns = { "/LoginServlet" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		ClientDao client = new ClientDao();
		AdminDao admin = new AdminDao();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String status = request.getParameter("status");
		username = username.trim();
		password = password.trim();
		if(username.length() == 0){
			out.print("<head><title>Error</title></head>");
			out.print("Please enter username.");
			response.addHeader("REFRESH", "2;URL=login.jsp");
		} else{
			if(password.length() == 0){
				out.print("<head><title>Error</title></head>");
				out.print("Please enter password.");
				response.addHeader("REFRESH", "2;URL=login.jsp");
			} else{
				if(status.equals("client")){
					if(client.loginClient(username, password)){
						response.sendRedirect("clientMain.jsp");
						session.setAttribute("username", username);
						session.setAttribute("status", "client");
					} else{
						out.print("<head><title>Error</title></head>");
						out.print("<script>alert('Wrong username or password.');</script>");
						response.addHeader("REFRESH", "0.1;URL=login.jsp");
					}
				} else if(status.equals("admin")){
					if(admin.loginAdmin(username, password)){
						response.sendRedirect("adminMain.jsp");
						session.setAttribute("username", username);
						session.setAttribute("status", "admin");
					} else{
						out.print("<head><title>Error</title></head>");
						out.print("<script>alert('Wrong username or password.');</script>");
						response.addHeader("REFRESH", "0.1;URL=login.jsp");
					}
				}
			}
		}
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

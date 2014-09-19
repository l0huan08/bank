package bank.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.entity.Account;
import bank.dao.AccountDao;

/**
 * Servlet implementation class OpenAccountServlet
 */
@WebServlet(description = "OpenAccountServlet", urlPatterns = { "/OpenAccountServlet" })
public class OpenAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OpenAccountServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		Account account = null;
		AccountDao accountDao = new AccountDao();
		int accountType = Integer.parseInt(request.getParameter("accountType"));
		String username = (String)session.getAttribute("username");
		PrintWriter out = response.getWriter();
		account = accountDao.openAccount(username, accountType);
		out.print("<head><title>Processing...</title></head>");
		if(account != null){
			out.print("<script>alert('Account opened successfully.');</script>");
		} else{
			out.print("<script>alert('Account opened failed. Please call our customer service.');</script>");
		}
		response.addHeader("REFRESH", "0.1;URL=openAccount.jsp");
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

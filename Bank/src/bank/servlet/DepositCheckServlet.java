package bank.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.dao.AccountDao;

/**
 * Servlet implementation class DepositCheckServlet
 */
@WebServlet(description = "DepositCheckServlet", urlPatterns = { "/DepositCheckServlet" })
public class DepositCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DepositCheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
			PrintWriter out = response.getWriter();
			AccountDao accountDao = new AccountDao();
			String account = request.getParameter("account");
			String amount = request.getParameter("amount");
			amount = amount.trim();
			if(amount.length() == 0){
				out.print("<title>Error</title>");
				out.print("<script>alert('Please enter the deposit amount.'</script>");
				response.addHeader("REFRESH", "0.1;URL=depositCheck.jsp");
			} else{
				double money = Double.parseDouble(amount);
				boolean flag = accountDao.deposit(account, money);
				if(flag){
					out.print("<title>Success</title>");
					out.print("<script>alert('Deposit success.');</script>");
					response.addHeader("REFRESH", "0.1;URL=depositCheck.jsp");
				} else{
					out.print("<title>Error</title>");
					out.print("<script>alert('Deposit failed. Please contact customer service');</script>");
					response.addHeader("REFRESH", "0.1;URL=depositCheck.jsp");
				}
			}
		} catch(NumberFormatException e){
			PrintWriter out = response.getWriter();
			out.print("<title>Error</title>");
			out.print("<script>alert('Amount must be a number.');</script>");
			response.addHeader("REFRESH", "0.1;URL=depositCheck.jsp");
		} catch(Exception e){
			response.addHeader("REFRESH", "0.1;URL=errorPages/500.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

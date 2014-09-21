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
 * Servlet implementation class MakeTransferServlet
 */
@WebServlet(description = "MakeTransferServlet", urlPatterns = { "/MakeTransferServlet" })
public class MakeTransferServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MakeTransferServlet() {
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
			String id = request.getParameter("id");
			String recipient = request.getParameter("recipient");
			recipient = recipient.trim();
			String amount = request.getParameter("amount");
			amount = amount.trim();
			String des = request.getParameter("description");
			if(recipient.length() == 0){
				out.print("<title>Error</title>");
				out.print("<script>alert('Please enter the recipient's account number.'</script>");
				response.addHeader("REFRESH", "0.1;URL=makeTransfer.jsp");
			} else{
				if(amount.length() == 0){
					out.print("<title>Error</title>");
					out.print("<script>alert('Please enter the transfer amount.'</script>");
					response.addHeader("REFRESH", "0.1;URL=makeTransfer.jsp");
				} else{
					double money = Double.parseDouble(amount);
					boolean flag = accountDao.makeTransfer(id, recipient, money);
					if(flag){
						out.print("<title>Success</title>");
						out.print("<script>alert('Transfer success.');</script>");
						response.addHeader("REFRESH", "0.1;URL=makeTransfer.jsp");
					} else{
						out.print("<title>Transfer failed</title>");
						out.print("<script>alert('Transfer failed. It is either you do not have enough money in your account or you entered the wrong information. Please try again or contact customer service.');</script>");
						response.addHeader("REFRESH", "0.1;URL=makeTransfer.jsp");
					}
				}
			}
		} catch(NumberFormatException e){
			PrintWriter out = response.getWriter();
			out.print("<title>Error</title>");
			out.print("<script>alert('Amount must be a number.');</script>");
			response.addHeader("REFRESH", "0.1;URL=makeTransfer.jsp");
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

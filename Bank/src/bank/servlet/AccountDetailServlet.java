package bank.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.dao.AccountDao;
import bank.entity.Account;

/**
 * Servlet implementation class AccountDetailServlet
 */
@WebServlet(description = "AccountDetailServlet", urlPatterns = { "/AccountDetailServlet" })
public class AccountDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		String id = request.getParameter("id");
		Account account = new Account();
		AccountDao accountDao = new AccountDao();
		account = accountDao.getAccount(id);
		String status = null;
		if(account.isActive() == true){
			status = "Active";
		} else {
			status = "Frozen";
		}
		DecimalFormat format = new DecimalFormat("#.00");
		StringBuffer selectJSON = new StringBuffer("[");
		selectJSON.append("{" + "\"" + "accountNumber" + "\"" + ":" + "\"" + "").append(account.getAccountNumber()).append("" + "\"" + "," + "\""+"accountType"+"\""+":"+"\""+"").append(account.getAccountType().getTypeName()).append("" + "\"" + "," + "\""+"balance"+"\""+":"+"\""+"").append(format.format(account.getBalance())).append("" + "\"" + "," + "\""+"accountStatus"+"\""+":"+"\""+"").append(status).append(""+"\""+"},");
		selectJSON.deleteCharAt(selectJSON.length() - 1);
      	selectJSON.append("]");
		out.print(selectJSON);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

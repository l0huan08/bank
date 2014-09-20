package bank.servlet.ajax;

import java.util.*;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.entity.Account;
import bank.dao.AccountDao;

/**
 * Servlet implementation class ClientInfoServlet
 */
@WebServlet(description = "AccountInfo", urlPatterns = { "/AccountInfo" })
public class AccountInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		String name = request.getParameter("username");
		AccountDao accountDao = new AccountDao();
		List<Account> list = accountDao.getAccountsByClient(name);
		StringBuffer selectJSON = new StringBuffer("[");
		for(int i = 0;i < list.size();i++){
			selectJSON.append("{" + "\"" + "accountID" + "\"" + ":" + "\"" + "").append(list.get(i).getAccountId()).append("" + "\"" + "," + "\""+"accountName"+"\""+":"+"\""+""). append(list.get(i).getAccountNumber()).append(""+"\""+"},");
		}
		selectJSON.deleteCharAt(selectJSON.length() - 1);
      	selectJSON.append("]");
		out.print(selectJSON);
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
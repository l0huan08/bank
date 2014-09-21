package bank.servlet.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.dao.AccountDao;
import bank.entity.Account;

/**
 * Servlet implementation class AdminMainServlet
 */
@WebServlet(description = "findAccountServlet", urlPatterns = { "/findAccountServlet" })
public class findAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public findAccountServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		AccountDao accountDao = new AccountDao();
		String username = request.getParameter("name");
		List<Account> list = accountDao.getAccountsByClient(username);
		StringBuffer selectJSON = new StringBuffer("[");
		if(list == null){
			selectJSON .append("{"+"\""+"accountID"+"\""+":"+"\""+"").append("null").append(""+"\""+", "+"\""+"accountNumber"+"\""+":"+"\""+"").append("null").append(""+"\""+"},");
		} else{
			for(int i = 0;i < list.size();i++){
				selectJSON .append("{"+"\""+"accountID"+"\""+":"+"\""+"").append(list.get(i).getAccountId()).append(""+"\""+", "+"\""+"accountNumber"+"\""+":"+"\""+"").append(list.get(i).getAccountNumber()).append(""+"\""+"},");
			}
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
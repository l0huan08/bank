package bank.servlet.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.dao.AccountDao;
import bank.entity.Account;

/**
 * Servlet implementation class findAccountsServlet
 */
@WebServlet(description = "findAccountsServlet", urlPatterns = { "/findAccountsServlet" })
public class findAccountsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public findAccountsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
		AccountDao accountDao = new AccountDao();
		List<Account> list = accountDao.getAccountsByClient(username);
		StringBuffer selectJSON = new StringBuffer("[");
		if(list == null){
			selectJSON.append("{" + "\"" + "accountNumber" + "\"" + ":" + "\"" + "").append("null").append("" + "\"" + "," + "\""+"accountType"+"\""+":"+"\""+"").append("null").append("" + "\"" + "," + "\""+"balance"+"\""+":"+"\""+"").append("null").append("" + "\"" + "," + "\""+"accountStatus"+"\""+":"+"\""+"").append("null").append(""+"\""+"},");
		} else{
			String status = null;
			DecimalFormat format = new DecimalFormat("#.00");
			for(int i = 0;i < list.size();i++){
				System.out.println(list.get(i).isActive());
				if(list.get(i).isActive() == true){
					status = "Active";
				} else {
					status = "Frozen";
				}
				selectJSON.append("{" + "\"" + "accountNumber" + "\"" + ":" + "\"" + "").append(list.get(i).getAccountNumber()).append("" + "\"" + "," + "\""+"accountType"+"\""+":"+"\""+"").append(list.get(i).getAccountType().getTypeName()).append("" + "\"" + "," + "\""+"balance"+"\""+":"+"\""+"").append(format.format(list.get(i).getBalance())).append("" + "\"" + "," + "\""+"accountStatus"+"\""+":"+"\""+"").append(status).append(""+"\""+"},");
			}
		}
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
package bank.servlet.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.dao.ClientDao;

/**
 * Servlet implementation class resetPasswordServlet
 */
@WebServlet(description = "resetPasswordServlet", urlPatterns = { "/resetPasswordServlet" })
public class resetPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public resetPasswordServlet() {
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
		ClientDao clientDao = new ClientDao();
		boolean flag = clientDao.resetClientPassword(username);
		StringBuffer selectJSON = new StringBuffer("[");
		if(flag)
			selectJSON .append("{"+"\""+"result"+"\""+":"+"\""+"").append("success").append(""+"\""+"},");
		else
			selectJSON .append("{"+"\""+"result"+"\""+":"+"\""+"").append("fail").append(""+"\""+"},");
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
package bank.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.entity.Client;
import bank.dao.ClientDao;

/**
 * Servlet implementation class AdminClientInfoServlet
 */
@WebServlet(description = "AdminClientInfoServlet", urlPatterns = { "/AdminClientInfoServlet" })
public class AdminClientInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminClientInfoServlet() {
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
		StringBuffer selectJSON = new StringBuffer("[");
		List<Client> list = clientDao.searchClient(username);
		if(list == null){
			selectJSON.append("{" + "\"" + "clientID" + "\"" + ":" + "\"" + "").append("").append("" + "\"" + "," + "\""+"userName"+"\""+":"+"\""+""). append("").append(""+"\""+"},");
		} else{
			for(int i = 0;i < list.size();i++){
				selectJSON.append("{" + "\"" + "clientID" + "\"" + ":" + "\"" + "").append(list.get(i).getClientId()).append("" + "\"" + "," + "\""+"userName"+"\""+":"+"\""+""). append(list.get(i).getUsername()).append("" + "\"" + "," + "\""+"firstName"+"\""+":"+"\""+""). append(list.get(i).getFirstName()).append("" + "\"" + "," + "\""+"lastName"+"\""+":"+"\""+"").append(list.get(i).getLastName()).append(""+"\""+"},");
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

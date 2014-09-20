package bank.servlet.ajax;

import java.util.*;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.entity.Client;
import bank.dao.ClientDao;

/**
 * Servlet implementation class ClientInfoServlet
 */
@WebServlet(description = "ClientInfo", urlPatterns = { "/ClientInfo" })
public class ClientInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientInfo() {
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
		Client client = null;
		ClientDao clientDao = new ClientDao();
		client = clientDao.getClient(name);
		StringBuffer selectJSON = new StringBuffer("[");
		String firstName = client.getFirstName();
		String middleName = client.getMiddleName();
		if(middleName == null)
			middleName = "";
		String lastName = client.getLastName();
		String gender = client.getGender();
		Date birthday = client.getBirthday();
		String telephone = client.getTel();
		String address1 = client.getAdd1();
		String address2 = client.getAdd2();
		if(address2 == null)
			address2 = "";
		String zip = client.getZip();
		String email = client.getEmail();
		String username = client.getUsername();
		String password = client.getPassword();
		selectJSON .append("{"+"\""+"firstName"+"\""+":"+"\""+"").append(firstName).append(""+"\""+", "+"\""+"middleName"+"\""+":"+"\""+"").append(middleName). append(""+"\""+", "+"\""+"lastName"+"\""+":"+"\""+"").append(lastName). append(""+"\""+", "+"\""+"gender"+"\""+":"+"\""+"").append(gender). append(""+"\""+", "+"\""+"birthday"+"\""+":"+"\""+"").append(birthday). append(""+"\""+", "+"\""+"telephone"+"\""+":"+"\""+"").append(telephone). append(""+"\""+", "+"\""+"address1"+"\""+":"+"\""+"").append(address1). append(""+"\""+", "+"\""+"address2"+"\""+":"+"\""+"").append(address2). append(""+"\""+", "+"\""+"zip"+"\""+":"+"\""+"").append(zip).append(""+"\""+", "+"\""+"email"+"\""+":"+"\""+"").append(email).append(""+"\""+", "+"\""+"userName"+"\""+":"+"\""+"").append(username).append(""+"\""+", "+"\""+"password"+"\""+":"+"\""+"").append(password) .append(""+"\""+"},");
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

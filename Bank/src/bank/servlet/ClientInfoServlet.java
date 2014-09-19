package bank.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bank.entity.Client;
import bank.dao.ClientDao;

/**
 * Servlet implementation class ClientInfoServlet
 */
@WebServlet(description = "ClientInfoServlet", urlPatterns = { "/ClientInfoServlet" })
public class ClientInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientInfoServlet() {
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
			Client client = new Client();
			ClientDao clientDao = new ClientDao();
			HttpSession session = request.getSession();
			String fname = request.getParameter("fname");
			fname = fname.trim();
			String mname = request.getParameter("mname");
			if(mname == null)
				mname = "";
			String lname = request.getParameter("lname");
			lname = lname.trim();
			String gender = request.getParameter("gender");
			String dob = request.getParameter("dob");
			dob = dob.trim();
			String tel = request.getParameter("tel");
			tel = tel.trim();
			String add1 = request.getParameter("add1");
			String add2 = request.getParameter("add2");
			if(add2 == null)
				add2 = "";
			String zip = request.getParameter("zip");
			String email = request.getParameter("email");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String userName = (String)session.getAttribute("username");
			client.setFirstName(fname);
			client.setMiddleName(mname);
			client.setLastName(lname);
			client.setGender(gender);
			DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd"); 
			Date birthday = new java.sql.Date(format1.parse(dob).getTime());
			client.setBirthday(birthday);
			client.setTel(tel);
			client.setAdd1(add1);
			client.setAdd2(add2);
			client.setZip(zip);
			client.setEmail(email);
			client.setUsername(username);
			client.setPassword(password);
			clientDao.updateClientProfile(userName, client);
		}catch(Exception e){
			e.printStackTrace();
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

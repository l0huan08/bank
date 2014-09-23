package bank.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bank.dao.ClientDao;
import bank.entity.Client;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet(description = "Register Servlet", urlPatterns = { "/RegisterServlet" })
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
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
			HttpSession session = request.getSession();
			ClientDao clientDao = new ClientDao();
			Client client = new Client();
			String fname = request.getParameter("fname");
			String mname = request.getParameter("mname");
			String lname = request.getParameter("lname");
			String gender = request.getParameter("gender");
			String dob = request.getParameter("dob");
			String tel = request.getParameter("tel");
			String add1 = request.getParameter("add1");
			String add2 = request.getParameter("add2");
			String email = request.getParameter("email");
			String zip = request.getParameter("zip");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			if(mname == null){
				mname = "";
			}
			if(add2 == null){
				add2 = "";
			}
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
			client.setEmail(email);
			client.setZip(zip);
			client.setUsername(username);
			client.setPassword(password);
			boolean flag = clientDao.registerClient(client);
			if(flag){
				out.print("<title>Success</title>");
				out.print("<script>alert('Register success. Welcome to our bank.');</script>");
				session.setAttribute("username", username);
				session.setAttribute("status", "client");
				response.addHeader("REFRESH", "0.1;URL=clientMain.jsp");
			} else{
				out.print("<title>Error</title>");
				out.print("<script>alert('Register failed. Please try again or contact customer service.');</script>");
				response.addHeader("REFRESH", "0.1;URL=register.jsp");
			}
		} catch(Exception e){
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

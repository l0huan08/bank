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
			add1 = add1.trim();
			String add2 = request.getParameter("add2");
			if(add2 == null)
				add2 = "";
			String zip = request.getParameter("zip");
			zip = zip.trim();
			String email = request.getParameter("email");
			email = email.trim();
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			password = password.trim();
			String userName = (String)session.getAttribute("username");
			if(userName == null){
				out.print("<title>Error</title>");
				out.print("<script>alert('You need to login first.');</script>");
				response.addHeader("REFRESH", "1;URL=login.jsp");
			}
			if(fname.length() == 0){
				out.print("<title>Error</title>");
				out.print("<script>alert('Please enter your first name.');</script>");
				response.addHeader("REFRESH", "1;URL=clientInfo.jsp");
			}
			if(lname.length() == 0){
				out.print("<title>Error</title>");
				out.print("<script>alert('Please enter your last name.');</script>");
				response.addHeader("REFRESH", "1;URL=clientInfo.jsp");
			}
			if(dob.length() == 0){
				out.print("<title>Error</title>");
				out.print("<script>alert('Please enter your date of birth.');</script>");
				response.addHeader("REFRESH", "1;URL=clientInfo.jsp");
			}
			if(tel.length() == 0){
				out.print("<title>Error</title>");
				out.print("<script>alert('Please enter your telephone number.');</script>");
				response.addHeader("REFRESH", "1;URL=clientInfo.jsp");
			}
			if(add1.length() == 0){
				out.print("<title>Error</title>");
				out.print("<script>alert('Please enter your address.');</script>");
				response.addHeader("REFRESH", "1;URL=clientInfo.jsp");
			}
			if(zip.length() == 0){
				out.print("<title>Error</title>");
				out.print("<script>alert('Please enter your zip code.');</script>");
				response.addHeader("REFRESH", "1;URL=clientInfo.jsp");
			}
			if(email.length() == 0){
				out.print("<title>Error</title>");
				out.print("<script>alert('Please enter your email.');</script>");
				response.addHeader("REFRESH", "1;URL=clientInfo.jsp");
			}
			if(password.length() == 0){
				out.print("<title>Error</title>");
				out.print("<script>alert('Please enter your password.');</script>");
				response.addHeader("REFRESH", "1;URL=clientInfo.jsp");
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
			client.setZip(zip);
			client.setEmail(email);
			client.setUsername(username);
			client.setPassword(password);
			boolean flag = clientDao.updateClientProfile(userName, client);
			if(flag){
				out.print("<title>Success</title>");
				out.print("<script>alert('Update success.');</script>");
				response.addHeader("REFRESH", "1;URL=clientInfo.jsp");
			} else{
				out.print("<title>Error</title>");
				out.print("<script>alert('Update fail. Please contact our customer service.');</script>");
				response.addHeader("REFRESH", "1;URL=clientInfo.jsp");
			}
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

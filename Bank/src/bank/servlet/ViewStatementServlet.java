package bank.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.dao.TransactionDao;
import bank.entity.Transaction;
/**
 * Servlet implementation class ViewStatementServlet
 */
@WebServlet(description = "ViewStatementServlet", urlPatterns = { "/ViewStatementServlet" })
public class ViewStatementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewStatementServlet() {
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
			DecimalFormat format = new DecimalFormat("#.00");
			String id = request.getParameter("id");
			String start = request.getParameter("start");
			start = start.trim();
			String end = request.getParameter("end");
			end = end.trim();
			if(start.length() == 0){
				out.print("<head><title>Error</title></head>");
				out.print("<script>alert('Please select a start date.');</script>");
				response.addHeader("REFRESH", "0.1;URL=viewStatement.jsp");
			}
			if(end.length() == 0){
				out.print("<head><title>Error</title></head>");
				out.print("<script>alert('Please select an end date.');</script>");
				response.addHeader("REFRESH", "0.1;URL=viewStatement.jsp");
			}
			if(start.length() != 0 && end.length() != 0) {
				DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd"); 
				Date from = new java.sql.Date(format1.parse(start).getTime());
				Date to = new java.sql.Date(format1.parse(end).getTime());
				TransactionDao transactionDao = new TransactionDao();
				List<Transaction> list = transactionDao.getTransactionRecords(id, from, to);
				StringBuffer selectJSON = new StringBuffer("[");
				if(list == null){
					selectJSON.append("{" + "\"" + "amount" + "\"" + ":" + "\"" + "").append("null").append("" + "\"" + "," + "\""+"type"+"\""+":"+"\""+"").append("null").append("" + "\"" + "," + "\""+"description"+"\""+":"+"\""+"").append("null").append(""+"\""+"},");
				} else{
					for(int i = 0;i < list.size();i++){
						selectJSON.append("{" + "\"" + "amount" + "\"" + ":" + "\"" + "").append(format.format(list.get(i).getAmount())).append("" + "\"" + "," + "\""+"type"+"\""+":"+"\""+"").append(list.get(i).getTransactionType().getTypeName()).append("" + "\"" + "," + "\""+"description"+"\""+":"+"\""+"").append(list.get(i).getDescription()).append(""+"\""+"},");
					}
				}
				selectJSON.deleteCharAt(selectJSON.length() - 1);
			    selectJSON.append("]");
				out.print(selectJSON);
				out.flush();
				out.close();
			}
		} catch(java.text.ParseException e){
			PrintWriter out = response.getWriter();
			out.print("<head><title>Error</title></head>");
			out.print("<script>alert('Please enter a valid date. Format: yyyy-mm-dd');</script>");
			response.addHeader("REFRESH", "0.1;URL=viewStatement.jsp");
			out.flush();
			out.close();
		} catch(Exception e){
			PrintWriter out = response.getWriter();
			out.print("<script>parent.window.location='errorPages/500.jsp';</script>");
			out.flush();
			out.close();
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

package bank.dao;

import bank.entity.*;

import java.sql.*;


/**
 * Operate the Client table
 * @author huang li
 * 2014.9.18
 */
public class ClientDao {
	private DBConnector dbConnector = new DBConnector();
	private Connection conn = null;
	
	/**
	 * Register a new client.
	 * Set its attributes from front page's textboxes
	 * @return  if the client exist, do not register, return false.
	 *  if success, add a new client record into Client table, and return true. 
	 */
	public boolean registerClient(Client client) {
		if (client==null)
			return false;
		
		if (client.getUsername().trim().isEmpty())
			return false; //not allow username==""
		
		
		
		
		try{
			conn = dbConnector.getConnection();
			if (conn==null) //cannot connect to DB
				return false;
			
			PreparedStatement st;
			ResultSet rs;
			
			// check does this client's username exist?
			st = conn.prepareStatement(
					"select * from tbClient where username=? ");
			st.setString(1, client.getUsername());
			rs = st.executeQuery();
			
			// if exist, return false
			if (rs.next()){
				return false; //exist a client in the table has same username
			}
			
			
			// if not, add a new row.
			st = conn.prepareStatement(
					"insert into tbClient(fname,mname,lname,gender,birthday,tel,"
					+ "add1,add2,zip,email,username,pw) "
					+ "values(?,?,?,?,?,?,?,?,?,?,?,?)");
			st.setString(1, client.getFirstName());
			st.setString(2, client.getMiddleName());
			st.setString(3, client.getLastName());
			st.setString(4, client.getGender());
			
			st.setDate(5, DaoUtility.dateToSqlDate(client.getBirthday()));
			st.setString(6, client.getTel());
			st.setString(7, client.getAdd1());
			st.setString(8, client.getAdd2());
			st.setString(9, client.getZip());
			st.setString(10, client.getEmail());
			
			st.setString(11, client.getUsername());
			st.setString(12, client.getPassword());
			
			int nInsertedRow = st.executeUpdate(); //the number of rows inserted
			return (nInsertedRow>0); //means a row inserted
		}
		catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return false;
	}
	
	/**
	 * Client Login
	 * @param username
	 * @param password
	 * @return if username and password is correct, client can login. return true.
	 */
	public boolean loginClient(String username, String password) {
		return true;
		//TODO: implemented need
	}
}

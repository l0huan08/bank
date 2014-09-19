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
	
	/**
	 * Register a new client.
	 * Set its attributes from front page's textboxes
	 * @return  if the client exist, do not register, return false.
	 *  if success, add a new client record into Client table, and return true. 
	 */
	public boolean registerClient(Client client) {
		
		if (!DaoUtility.isUsernameValid(client.getUsername()))
			return false;

		Connection conn=null;
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
			
			st.setDate(5, client.getBirthday());
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
				if (conn!=null)
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
		if (!DaoUtility.isUsernameValid(username))
			return false;

		Connection conn=null;
		try{
			conn = dbConnector.getConnection();
			if (conn==null) //cannot connect to DB
				return false;
			
			PreparedStatement st;
			ResultSet rs;
			
			// check does this client's username with password exist?
			st = conn.prepareStatement(
					"select * from tbClient where username=? and pw=?");
			st.setString(1, username);
			st.setString(2, password);
			rs = st.executeQuery();
			
			// if exist, return true
			if (rs.next()){
				return true;
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if (conn!=null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return false;
	}
	
	/**
	 * Get a client object by username
	 * @param username
	 * @return the client object if exist
	 */
	public Client getClient(String username) {
		if (!DaoUtility.isUsernameValid(username))
			return null;

		Connection conn=null;
		try{
			conn = dbConnector.getConnection();
			if (conn==null) //cannot connect to DB
				return null;
			
			PreparedStatement st;
			ResultSet rs;
			
			// check does this client's username exist?
			st = conn.prepareStatement(
					"select * from tbClient where username=? ");
			st.setString(1, username);
			rs = st.executeQuery();
			
			// if exist, return the Client object
			if (rs.next()){

				int cid = rs.getInt("cid");
				String fname = rs.getString("fname");
				String mname = rs.getString("mname");
				String lname = rs.getString("lname");
				String gender = rs.getString("gender");
				Date birthday = rs.getDate("birthday");
				String tel = rs.getString("tel");
				String add1 = rs.getString("add1");
				String add2 = rs.getString("add2");
				String zip = rs.getString("zip");
				String email = rs.getString("email");
				String usname = rs.getString("username");
				String pw = rs.getString("pw");
					
				Client client = new Client(cid, fname, mname,
						lname, gender, birthday, tel,
						 add1,  add2,  zip,  email,
						usname, pw);
		
				return client; //exist a client in the table
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if (conn!=null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return null;
		
	}
	
	
	/**
	 * Update Client's profile. 
	 * Do not change cid and username
	 * @param username Client username
	 * @param newClientProfile  including client's new profile
	 * @return true if success updated.
	 */
	boolean updateClientProfile(String username, Client newClient) {
		if (!DaoUtility.isUsernameValid(username))
			return false;

		Connection conn=null;
		try{
			conn = dbConnector.getConnection();
			if (conn==null) //cannot connect to DB
				return false;
			
			PreparedStatement st;
			
			// update the client with username='username'.
			// do not update cid and username
			st = conn.prepareStatement(
					"update tbClient "
					+ " set fname=?, mname=?, lname=? ,gender=?,birthday=?,tel=?,"
					+ "add1=?,add2=?,zip=?,email=?,pw=?) "
					+ "where username=? ");
			st.setString(1, newClient.getFirstName());
			st.setString(2, newClient.getMiddleName());
			st.setString(3, newClient.getLastName());
			st.setString(4, newClient.getGender());
			
			st.setDate(5, newClient.getBirthday());
			st.setString(6, newClient.getTel());
			st.setString(7, newClient.getAdd1());
			st.setString(8, newClient.getAdd2());
			st.setString(9, newClient.getZip());
			st.setString(10, newClient.getEmail());
			
			st.setString(11, newClient.getPassword());
			st.setString(12, newClient.getPassword());
			
			int nInsertedRow = st.executeUpdate(); //the number of rows inserted
			return (nInsertedRow>0); //means a row inserted
		}
		catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if (conn!=null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return false;		
	}
}

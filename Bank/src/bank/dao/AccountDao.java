package bank.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import bank.entity.Account;

/**
 * Operate Account Table in DB
 * @author huang li
 * 2014.9.18
 */
public class AccountDao {
	
	private DBConnector dbConnector = new DBConnector();
	
	public List<Account> getAccountsByClient(String username) {
		
		Connection conn = dbConnector.getConnection();
		if (conn==null)
			return null;
		
		try {
			
			// select tbAccount.* from tbAccount, tbClient 
			//    where tbAccount.cid=tbClient.cid 
			//      and tbClient.username= 'username'
			// TODO: need finish
			//PreparedStatement st = conn
			return null;
		}catch(Exception e){
			
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
	}
}

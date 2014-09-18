package bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bank.entity.Account;
import bank.entity.AccountType;

/**
 * Operate Account Table in DB
 * @author huang li
 * 2014.9.18
 */
public class AccountDao {
	
	private DBConnector dbConnector = new DBConnector();
	
	/**
	 * Get all account of a client
	 * @param username client username
	 * @return the list of accounts belong to this client
	 */
	public List<Account> getAccountsByClient(String username) {
		if (username==null)
			return null;
		if (username.trim().isEmpty())
			return null;
		
		Connection conn = dbConnector.getConnection();
		if (conn==null)
			return null;
		
		try {
			PreparedStatement st;
			ResultSet rs;
			
			st = conn.prepareStatement(
					"select tbAccount.*, trtname from tbAccount, tbClient, tbTransactionType "+ 
					"    where tbAccount.cid=tbClient.cid " +
					"      and trtype=trtid " +
					"      and tbClient.username= ?" );
			st.setString(1, username);
			rs = st.executeQuery();
			
			ArrayList<Account> lst = new ArrayList<Account>();
			while (rs.next()){
				int aid = rs.getInt("aid");
				int cid = rs.getInt("cid");
				int typeid = rs.getInt("typeid");
				String typeName = rs.getString("trtname");
				AccountType acType = new AccountType();
				acType.setTypeId(typeid);
				acType.setTypeName(typeName);
				
				double balance = rs.getDouble("balance");
				String acnumber = rs.getString("acnumber");
				boolean isactive = rs.getBoolean("isactive");
				
				Account account = new Account(aid,cid,acType,balance,acnumber,isactive);
				lst.add(account);
			}
			
			if (lst.size()<=0)
				return null;
			return lst;
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
}

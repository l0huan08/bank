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
		
		Connection conn = null;
		
		try {
			PreparedStatement st;
			ResultSet rs;
			
			conn=dbConnector.getConnection();
			if (conn==null)
				return null;
			
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
				if (conn!=null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	/**
	 * Open a new account
	 * @param username  new account's username
	 * @param accountTypeId  type of the account
	 * @return this new account if successful open. else return null.
	 */
	public Account openAccount(String username, int accountTypeId){
		if (!DaoUtility.isUsernameValid(username))
			return null;

		Connection conn = null;

		try {
			PreparedStatement st;
			ResultSet rs;
			int cid=0; //clientId
			
			conn=dbConnector.getConnection();
			if (conn==null)
				return null;
			
			// find the cid by username
			st = conn.prepareStatement("select cid from tbClient"
					+ " where username=? ");
			st.setString(1, username);
			rs = st.executeQuery();
			if (rs.next()) {
				cid = rs.getInt("cid");
			} else {
				return null; // not found this client, return null object
			}
			
			// generate accountNumber
			String acNumber = genNewAccountNumber(cid);
			
			// insert the account into tbAccount
			st = conn.prepareStatement("insert into tbAccount(cid,typeid,acnumber)"
					+ " values(?,?,?)");
			st.setInt(1, cid);
			st.setInt(2, accountTypeId);
			st.setString(3, acNumber);
			int rowInserted = st.executeUpdate(); //
			if (rowInserted<=0) { 
				return null; //inserted failed
			}
			
			// get the inserted account object
			st = conn.prepareStatement("select tbAccount.*, typename  from tbAccount,tbAccountType"
					+ " where acnumber=? and tbAccountType.typeid=tbAccount.typeid ");
			st.setString(1, acNumber);
			rs = st.executeQuery();
			if (rs.next()) {
				int aid = rs.getInt("aid");
				String acTypeName = rs.getString("typename");
				double balance = rs.getDouble("balance");
				boolean isactive = rs.getBoolean("isactive");
				AccountType acType = new AccountType(accountTypeId,acTypeName);
				
				Account account=new Account(aid,cid,acType,balance,acNumber,isactive);
				return account; //the new account
			} else {
				return null;// not found account
			}
		} catch (Exception e){
			e.printStackTrace();
		} finally {
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
	 * Generate a new Account Number.
	 * @param username Client's username
	 * @return the new account number = clientId*1000+random(900)
	 */
	private String genNewAccountNumber(int clientId) {
		// keep 4 digits
		int acNumber = clientId*1000+(int)(Math.random()*900);
		return String.valueOf(acNumber);
	}
}

package bank.entity;

public class Account {
	private int accountId=0;
	private int clientId=0;
	private AccountType accountType=null;
	private double balance=0;
	private String accountNumber=null;
	
	
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	
	
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	
	
	public Account(){
	}
	
	public Account(int accountId,int clientId,AccountType accountType,double balance,
			String accountNumber){
		this.accountId= accountId;
		this.clientId=clientId;
		this.accountType=accountType;
		this.balance=balance;
		this.accountNumber=accountNumber;
	}
	public AccountType getAccountType() {
		return accountType;
	}
	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}
}

package org.soft.base.model;

public class BankUser {

	  private int bankUserId  ;
	  private String  bankUserNo  ;
	  private String  bankUserPass ;
	  private String  bankUserName  ;
	  private double  bankBalance  ;
	  private int  bankId  ;
	  
	public BankUser() {
		
	}
	  
	public BankUser(int bankUserId, String bankUserNo, String bankUserPass, String bankUserName, double bankBalance,
			int bankId) {
		super();
		this.bankUserId = bankUserId;
		this.bankUserNo = bankUserNo;
		this.bankUserPass = bankUserPass;
		this.bankUserName = bankUserName;
		this.bankBalance = bankBalance;
		this.bankId = bankId;
	}
	public int getBankUserId() {
		return bankUserId;
	}
	public void setBankUserId(int bankUserId) {
		this.bankUserId = bankUserId;
	}
	public String getBankUserNo() {
		return bankUserNo;
	}
	public void setBankUserNo(String bankUserNo) {
		this.bankUserNo = bankUserNo;
	}
	public String getBankUserPass() {
		return bankUserPass;
	}
	public void setBankUserPass(String bankUserPass) {
		this.bankUserPass = bankUserPass;
	}
	public String getBankUserName() {
		return bankUserName;
	}
	public void setBankUserName(String bankUserName) {
		this.bankUserName = bankUserName;
	}
	public double getBankBalance() {
		return bankBalance;
	}
	public void setBankBalance(double bankBalance) {
		this.bankBalance = bankBalance;
	}
	public int getBankId() {
		return bankId;
	}
	public void setBankId(int bankId) {
		this.bankId = bankId;
	}
	  
	  

}

package org.soft.base.model;

public class Bank {
	
	private int bankId ;
	private String bankName ;
	
	public Bank() {
		
	}
	
	public Bank(int bankId, String bankName) {
		super();
		this.bankId = bankId;
		this.bankName = bankName;
	}
	public int getBankId() {
		return bankId;
	}
	public void setBankId(int bankId) {
		this.bankId = bankId;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	
	//重写Object的toString 方法
	public String toString() {
		return bankId+" "+bankName ;
	}
	
	

}

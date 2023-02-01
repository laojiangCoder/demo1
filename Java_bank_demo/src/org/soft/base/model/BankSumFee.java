package org.soft.base.model;

//没有表对应
public class BankSumFee {
	
	private String bankName ;
	private double sumFee ;
	
	public BankSumFee() {
		
	}
	
	public BankSumFee(String bankName, double sumFee) {
		super();
		this.bankName = bankName;
		this.sumFee = sumFee;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public double getSumFee() {
		return sumFee;
	}
	public void setSumFee(double sumFee) {
		this.sumFee = sumFee;
	}
	
	

}

package org.soft.base.model;

import java.util.Date;

public class BankFee {
     private int bankFeeId ;
     private int bankId ;
     private double fee ;
     private Date feeTime ;
     
     public BankFee() {
    	 
     }
     
	public BankFee(int bankFeeId, int bankId, double fee, Date feeTime) {
		super();
		this.bankFeeId = bankFeeId;
		this.bankId = bankId;
		this.fee = fee;
		this.feeTime = feeTime;
	}
	public int getBankFeeId() {
		return bankFeeId;
	}
	public void setBankFeeId(int bankFeeId) {
		this.bankFeeId = bankFeeId;
	}
	public int getBankId() {
		return bankId;
	}
	public void setBankId(int bankId) {
		this.bankId = bankId;
	}
	public double getFee() {
		return fee;
	}
	public void setFee(double fee) {
		this.fee = fee;
	}

	public Date getFeeTime() {
		return this.feeTime;
	}

	public void setFeeTime(Date feeTime) {
		this.feeTime = feeTime;
	}
    
     
     
}

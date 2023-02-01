package org.soft.base.model;

public class Admin {
	
	private int adminId ;
	private String adminNo ;
	private String adminPass ;
	private String adminName ;
	private String adminPhone ;
	private String adminEmail ;
	
	public Admin() {
		
	}
	
	public Admin(int adminId, String adminNo, String adminPass, String adminName, String adminPhone,
			String adminEmail) {
		super();
		this.adminId = adminId;
		this.adminNo = adminNo;
		this.adminPass = adminPass;
		this.adminName = adminName;
		this.adminPhone = adminPhone;
		this.adminEmail = adminEmail;
	}
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	public String getAdminNo() {
		return adminNo;
	}
	public void setAdminNo(String adminNo) {
		this.adminNo = adminNo;
	}
	public String getAdminPass() {
		return adminPass;
	}
	public void setAdminPass(String adminPass) {
		this.adminPass = adminPass;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getAdminPhone() {
		return adminPhone;
	}
	public void setAdminPhone(String adminPhone) {
		this.adminPhone = adminPhone;
	}
	public String getAdminEmail() {
		return adminEmail;
	}
	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}
	
	

}

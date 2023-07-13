package com.nagarro.productCom.entity;

public class JwtRequest {
	private String userEmail;
	private String userPassword;
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public JwtRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public JwtRequest(String userEmail, String userPassword) {
		super();
		this.userEmail = userEmail;
		this.userPassword = userPassword;
	}
	@Override
	public String toString() {
		return "JwtRequest [userEmail=" + userEmail + ", userPassword=" + userPassword + "]";
	}
	
	
}

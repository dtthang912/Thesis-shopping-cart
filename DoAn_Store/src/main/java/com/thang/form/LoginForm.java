package com.thang.form;

public class LoginForm {
	private String userName = "";
	private String pass = "";

	public LoginForm() {
		
	}

	public LoginForm(String userName, String pass) {
		super();
		this.userName = userName;
		this.pass = pass;
	}

	public String getUserName() {
		return userName;
	}

	public String getPass() {
		return pass;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

}

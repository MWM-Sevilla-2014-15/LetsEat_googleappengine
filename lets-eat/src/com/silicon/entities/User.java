package com.silicon.entities;

import java.util.Date;

public class User {
	private Date signupDate;
	private String email;
	private String name;
	private String pass;
	
	public User (String email, String name, String pass) {
		this.signupDate = new Date();
		this.email = email;
		this.name = name;
		this.pass = pass;
	}
	
	public User (String name, String pass) {
		this.name = name;
		this.pass = pass;
	}
	
	public User (String email) {
		this.email = email;
	}

	public Date getSignupDate() {
		return signupDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	
}

package com.silicon.entities;

import java.util.Date;

public class User {
	private Date createDate;
	private int isActived;
	private String email;
	private String name;
	private String pass;
	
	public User (String email, String name, String pass) {
		this.createDate = new Date();
		this.isActived = 1;
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getIsActived() {
		return isActived;
	}

	public void setIsActived(int isActived) {
		this.isActived = isActived;
	}
	
}

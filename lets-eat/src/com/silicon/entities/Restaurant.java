package com.silicon.entities;

import java.util.Date;

public class Restaurant {
	private Date signUpDate;
	//Description
	private String name;
	private String url;
	private String desc;
	private Date open_at;
	private Date close_at;
	private String avg_price;
	private String score;
	private int totalTables;
	//State
	private int bookTables;
	//Contact and Location
	private float lat;
	private float lon;
	private String prov;
	private String telf;
	
	public Date getSignUpDate() {
		return signUpDate;
	}
	public void setSignUpDate(Date signUpDate) {
		this.signUpDate = signUpDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Date getOpen_at() {
		return open_at;
	}
	public void setOpen_at(Date open_at) {
		this.open_at = open_at;
	}
	public Date getClose_at() {
		return close_at;
	}
	public void setClose_at(Date close_at) {
		this.close_at = close_at;
	}
	public String getAvg_price() {
		return avg_price;
	}
	public void setAvg_price(String avg_price) {
		this.avg_price = avg_price;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public int getTotalTables() {
		return totalTables;
	}
	public void setTotalTables(int totalTables) {
		this.totalTables = totalTables;
	}
	public int getBookTables() {
		return bookTables;
	}
	public void setBookTables(int bookTables) {
		this.bookTables = bookTables;
	}
	public float getLat() {
		return lat;
	}
	public void setLat(float lat) {
		this.lat = lat;
	}
	public float getLon() {
		return lon;
	}
	public void setLon(float lon) {
		this.lon = lon;
	}
	public String getProv() {
		return prov;
	}
	public void setProv(String prov) {
		this.prov = prov;
	}
	public String getTelf() {
		return telf;
	}
	public void setTelf(String telf) {
		this.telf = telf;
	}
	
}

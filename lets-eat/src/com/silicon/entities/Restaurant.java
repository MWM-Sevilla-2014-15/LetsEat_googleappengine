package com.silicon.entities;

import java.util.Date;

public class Restaurant {
	// Audit
	private long id;
	private Date createDate;
	private int isActived;
	// Description
	private String name;
	private String type;
	private String url;
	private String desc;
	private String m_t_open;
	private String m_t_close;
	private String t_t_open;
	private String t_t_close;
	private int avg_price;
	private float score;
	private int totalTables;
	// State
	private int bookTables;
	// Contact and Location
	private float lat;
	private float lon;
	private String prov;
	private String telf;
	
	/**
	 * Constructor - Create Restaurant 
	 * @param name
	 * @param type
	 * @param desc
	 * @param telf
	 * @param m_t_open
	 * @param m_t_close
	 * @param t_t_open
	 * @param t_t_close
	 * @param avg_price
	 * @param score
	 * @param totalTables
	 * @param lat
	 * @param lon
	 */
	public Restaurant(String name, String type, String desc, String telf, String m_t_open,
			String m_t_close, String t_t_open, String t_t_close, 
			int avg_price,float score,int totalTables,float lat,float lon) {
		this.createDate = new Date();
		this.isActived = 1;
		this.name = name;
		this.type = type;
		this.url = "";
		this.desc = desc;
		this.telf = telf;
		this.m_t_open = m_t_open;
		this.m_t_close = m_t_close;
		this.t_t_open = t_t_open;
		this.t_t_close = t_t_close;
		this.setAvg_price(avg_price);
		this.setScore(score);
		this.totalTables=totalTables;
		this.bookTables = 0;
		this.prov = "";
		this.lat=lat;
		this.lon=lon;
	}
	/**
	 * Constructor - Get Restaurant 
	 * @param id
	 * @param name
	 * @param type
	 * @param desc
	 * @param telf
	 * @param m_t_open
	 * @param m_t_close
	 * @param t_t_open
	 * @param t_t_close
	 * @param avg_price
	 * @param score
	 * @param totalTables
	 * @param bookTables
	 * @param lat
	 * @param lon
	 */
	public Restaurant(long id, String name, String type, String desc, String telf, String m_t_open,
			String m_t_close, String t_t_open, String t_t_close, 
			int avg_price, float score,int totalTables,int bookTables,float lat,float lon) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.url = "";
		this.desc = desc;
		this.telf = telf;
		this.m_t_open = m_t_open;
		this.m_t_close = m_t_close;
		this.t_t_open = t_t_open;
		this.t_t_close = t_t_close;
		this.setAvg_price(avg_price);
		this.setScore(score);
		this.totalTables=totalTables;
		this.bookTables = bookTables;
		this.prov = "";
		this.lat=lat;
		this.lon=lon;
	}

	public Restaurant(long id, int bookTables) {
		this.id = id;
		this.bookTables = bookTables;
	}
	
	public Restaurant() {
		
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

	public String getM_t_open() {
		return m_t_open;
	}

	public void setM_t_open(String m_t_open) {
		this.m_t_open = m_t_open;
	}

	public String getM_t_close() {
		return m_t_close;
	}

	public void setM_t_close(String m_t_close) {
		this.m_t_close = m_t_close;
	}

	public String getT_t_open() {
		return t_t_open;
	}

	public void setT_t_open(String t_t_open) {
		this.t_t_open = t_t_open;
	}

	public String getT_t_close() {
		return t_t_close;
	}

	public void setT_t_close(String t_t_close) {
		this.t_t_close = t_t_close;
	}


	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public int getAvg_price() {
		return avg_price;
	}

	public void setAvg_price(int avg_price) {
		this.avg_price = avg_price;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
}
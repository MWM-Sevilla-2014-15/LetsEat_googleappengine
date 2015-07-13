package com.silicon.dao;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;


public class RestaurantDao {
	//Restaurant Entity
	private final static String REST_KEY ="Restaurant";
	//Auditing
	private final static String REST_FIELD_DATE ="signUpDate";
	//Description
	private final static String REST_FIELD_NAME ="name";
	private final static String REST_FIELD_URLIMAGE ="url";
	private final static String REST_FIELD_DESCRIP ="desc";
	private final static String REST_FIELD_OPENAT ="open_at";
	private final static String REST_FIELD_CLOSEAT ="close_at";
	private final static String REST_FIELD_AVGPRICE ="avg_price";
	private final static String REST_FIELD_SCORE ="score";
	private final static String REST_FIELD_TOTALTABLES ="totalTables";
	//State
	private final static String REST_FIELD_BOOKTABLES ="bookTables";
	//Contact and Location
	private final static String REST_FIELD_LAT ="lat";
	private final static String REST_FIELD_LONG ="lon";
	private final static String REST_FIELD_PROV ="prov";
	private final static String REST_FIELD_TELF ="telf";

	
	
}

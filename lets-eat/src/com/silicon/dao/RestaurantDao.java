package com.silicon.dao;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.silicom.utils.PropUtil;
import com.silicon.entities.Restaurant;
import com.silicon.entities.User;


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
	private final static String REST_FIELD_LON ="lon";
	private final static String REST_FIELD_PROV ="prov";
	private final static String REST_FIELD_TELF ="telf";

	public static String createRestaurant(Restaurant restaurant){	
		String msg=PropUtil.RR_OK;
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();	
		Key	userKey	= KeyFactory.createKey(REST_KEY, restaurant.getName());
		try	{
			// First check if that entity already exists in the database
			datastore.get(userKey);
			msg=PropUtil.RR_E_RD;
		} catch	(EntityNotFoundException e)	{
				Transaction txn = datastore.beginTransaction();
				try {
					Entity entryEntity =	new	Entity(REST_KEY, restaurant.getName());
					entryEntity.setProperty(REST_FIELD_DATE, restaurant.getSignUpDate());
					entryEntity.setProperty(REST_FIELD_NAME, restaurant.getName());
					entryEntity.setProperty(REST_FIELD_URLIMAGE, "");
					entryEntity.setProperty(REST_FIELD_DESCRIP, restaurant.getDesc());
					entryEntity.setProperty(REST_FIELD_OPENAT, restaurant.getOpen_at());
					entryEntity.setProperty(REST_FIELD_CLOSEAT, restaurant.getClose_at());
					entryEntity.setProperty(REST_FIELD_AVGPRICE, restaurant.getAvg_price());
					entryEntity.setProperty(REST_FIELD_SCORE, restaurant.getScore());
					entryEntity.setProperty(REST_FIELD_TOTALTABLES, restaurant.getTotalTables());
					entryEntity.setProperty(REST_FIELD_LAT, restaurant.getLat());
					entryEntity.setProperty(REST_FIELD_LON, restaurant.getLon());
					entryEntity.setProperty(REST_FIELD_PROV, restaurant.getProv());
					entryEntity.setProperty(REST_FIELD_TELF, restaurant.getTelf());
					datastore.put(entryEntity);
					txn.commit();
				}finally{
					if (txn.isActive()) {
						txn.rollback();
					}
				}
		}
		return msg;	
	}
	
}

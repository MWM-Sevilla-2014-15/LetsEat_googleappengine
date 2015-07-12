package com.silicon.dao;

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
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.appengine.api.datastore.Transaction;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Query.CompositeFilter;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.PreparedQuery;


import com.silicon.entities.User;

public class UserDao {
	//User Entity
	private final static String USER_KEY ="User";
	private final static String USER_FIELD_DATE ="signUpDate";	
	private final static String USER_FIELD_EMAIL ="email";
	private final static String USER_FIELD_NAME ="name";
	private final static String USER_FIELD_PASS ="pass";
	//Messages Codes (see description at com.silicom.util.PropUtil.java)
	private final static String SU_OK ="SU_OK";
	private final static String SU_E_UD ="SU_E_UD";
	private final static String SU_E_ED ="SU_E_ED";
	private final static String SI_OK ="SI_OK";
	private final static String SI_E_UPE ="SI_E_UPE";
	private final static String SI_E_UNR ="SI_E_UNR";
	private final static String GP_OK ="GP_OK";
	private final static String GP_E_ENE ="GP_E_ENE";
	
	public static String createUser(User user){	
		String msg=SU_OK;
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();	
		Key	userKey	= KeyFactory.createKey(USER_KEY, user.getName());	
		try	{
			// First check if that entity already exists in the database
			datastore.get(userKey);
			msg=SU_E_UD;
		} catch	(EntityNotFoundException e)	{
			if (existEmail(user).equals(GP_E_ENE)){
				Transaction txn = datastore.beginTransaction();
				try {
					Entity entryEntity =	new	Entity(USER_KEY, user.getName());
					entryEntity.setProperty(USER_FIELD_DATE, user.getSignupDate());
					entryEntity.setProperty(USER_FIELD_EMAIL, user.getEmail());
					entryEntity.setProperty(USER_FIELD_NAME, user.getName());
					entryEntity.setProperty(USER_FIELD_PASS, user.getPass());
					datastore.put(entryEntity);
					txn.commit();
				}finally{
					if (txn.isActive()) {
						txn.rollback();
					}
				}
			}else{
				msg=SU_E_ED;
			}
		}
		return msg;	
	}
	
	public static String authenticateUser(User user){	
		String msg =SI_OK;
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();	
		Key	userKey	= KeyFactory.createKey(USER_KEY, user.getName());	
		try	{
			// First check if that entity already exists in the database
			Entity entryEntity = datastore.get(userKey);	
			if (!entryEntity.getProperty(USER_FIELD_PASS).toString().equals(user.getPass())){
				msg=SI_E_UPE;
			}
		} catch	(EntityNotFoundException e)	{	
			msg =SI_E_UNR;
		}
		return msg;	
	}
	
	public static String existEmail(User user){
		String msg =GP_E_ENE;
		DatastoreService datastore	= DatastoreServiceFactory.getDatastoreService();
		Filter propertyFilter = new FilterPredicate(USER_FIELD_EMAIL, 
													FilterOperator.EQUAL,
													user.getEmail());
		Query q = new Query(USER_KEY).setFilter(propertyFilter);
		// Use PreparedQuery interface to retrieve results
		PreparedQuery pq = datastore.prepare(q);
		for (Entity result : pq.asIterable()) {
		  msg =GP_OK;
		}
		return msg;
	}
	
}

package com.silicon.dao;

import java.io.UnsupportedEncodingException;
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
import com.silicom.utils.EmailSender;
import com.silicom.utils.PropUtil;
import com.silicon.entities.User;

public class UserDao implements InterfDao {
	//User Entity
	private final static String USER_KEY ="User";
	private final static String USER_FIELD_EMAIL ="email";
	private final static String USER_FIELD_NAME ="name";
	private final static String USER_FIELD_PASS ="pass";
	
	public static String create(User user){	
		String msg=PropUtil.SU_OK;
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();	
		Key	userKey	= KeyFactory.createKey(USER_KEY, user.getName());	
		try	{
			// First check if that entity already exists in the database
			datastore.get(userKey);
			msg=PropUtil.SU_E_UD;
		} catch	(EntityNotFoundException e)	{
			if (isEmailDuplicated(user).equals(PropUtil.GP_E_ENE)){
				Transaction txn = datastore.beginTransaction();
				try {
					Entity entryEntity =	new	Entity(USER_KEY, user.getName());
					entryEntity.setProperty(FIELD_DATE, user.getCreateDate());
					entryEntity.setProperty(FIELD_ACTIVE, user.getIsActived());
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
				msg=PropUtil.SU_E_ED;
			}
		}
		return msg;	
	}
	
	public static String authenticate(User user){	
		String msg =PropUtil.SI_OK;
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();	
		Key	userKey	= KeyFactory.createKey(USER_KEY, user.getName());	
		try	{
			// First check if that entity already exists in the database
			Entity entryEntity = datastore.get(userKey);	
			if (!entryEntity.getProperty(USER_FIELD_PASS).toString().equals(user.getPass())){
				msg=PropUtil.SI_E_UPE;
			}
		} catch	(EntityNotFoundException e)	{	
			msg =PropUtil.SI_E_UNR;
		}
		return msg;	
	}
	
	public static String isEmailDuplicated(User user){
		String msg =PropUtil.GP_E_ENE;
		DatastoreService datastore	= DatastoreServiceFactory.getDatastoreService();
		Filter propertyFilter = new FilterPredicate(USER_FIELD_EMAIL, 
													FilterOperator.EQUAL,
													user.getEmail());
		Query q = new Query(USER_KEY).setFilter(propertyFilter);
		// Use PreparedQuery interface to retrieve results
		PreparedQuery pq = datastore.prepare(q);
		for (Entity result : pq.asIterable()) {
			 try {
				user.setPass(result.getProperty(USER_FIELD_PASS).toString());
				user.setName(result.getProperty(USER_FIELD_NAME).toString());
				msg = EmailSender.sendEmail(user);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return msg;
	}
	
	public static String isAdmin(User user, String emailAdmin){	
		String msg =PropUtil.IA_OK;
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();	
		Key	userKey	= KeyFactory.createKey(USER_KEY, user.getName());	
		try	{
			Entity entryEntity = datastore.get(userKey);	
			if (!entryEntity.getProperty(USER_FIELD_PASS).toString().equals(user.getPass())){
				msg=PropUtil.IA_E;
			}else{
				if (!entryEntity.getProperty(USER_FIELD_EMAIL).toString().equals(emailAdmin)){
					msg=PropUtil.IA_E;
				}
			}
		} catch	(EntityNotFoundException e)	{	
			msg =PropUtil.IA_E;
		}
		return msg;	
	}
	
}

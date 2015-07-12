package com.silicon.dao;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.silicon.entities.User;

public class UserDao {
	
	public static String createUser(User user){	
		String msg ="SU_OK";
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();	
		Key	userKey	= KeyFactory.createKey("User", user.getName());	
		try	{
			// First check if that entity already exists in the database
			datastore.get(userKey);	
			msg ="SU_E_UD";
		} catch	(EntityNotFoundException e)	{	
			Entity entryEntity =	new	Entity("User", user.getName());
			entryEntity.setProperty("signUpDate", user.getSignupDate());
			entryEntity.setProperty("email", user.getEmail());
			entryEntity.setProperty("name", user.getName());
			entryEntity.setProperty("pass", user.getPass());
			datastore.put(entryEntity);
		}
		return msg;	
	}
	
	public static String authenticateUser(User user){	
		String msg ="SI_OK";
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();	
		Key	userKey	= KeyFactory.createKey("User", user.getName());	
		try	{
			// First check if that entity already exists in the database
			Entity entryEntity = datastore.get(userKey);	
			if (!entryEntity.getProperty("pass").toString().equals(user.getPass())){
				msg ="SI_E_UPE";
			}
		} catch	(EntityNotFoundException e)	{	
			msg ="SI_E_UNR";
		}
		return msg;	
	}
	
	public static String existEmail(User user){	
		String msg ="EM_OK";
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();	
		Key	userKey	= KeyFactory.createKey("User", user.getName());	
		try	{
			// First check if that entity already exists in the database
			Entity entryEntity = datastore.get(userKey);	
			if (!entryEntity.getProperty("pass").toString().equals(user.getPass())){
				msg ="SI_E_UPE";
			}
		} catch	(EntityNotFoundException e)	{	
			msg ="SI_E_UNR";
		}
		return msg;
	}
	
}

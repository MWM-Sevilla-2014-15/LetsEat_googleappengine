package com.silicon.dao;

import java.io.IOException;
import java.util.ArrayList;
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

public class RestaurantDao implements InterfDao {
	// Restaurant Entity
	private final static String REST_KEY = "Restaurant";
	// Description
	private final static String REST_ID = "id";
	private final static String REST_FIELD_NAME = "name";
	private final static String REST_FIELD_TYPE = "tipo";
	private final static String REST_FIELD_URLIMAGE = "url";
	private final static String REST_FIELD_DESCRIP = "desc";
	private final static String REST_FIELD_M_T_OPEN = "m_t_open";
	private final static String REST_FIELD_M_T_CLOSE = "m_t_close";
	private final static String REST_FIELD_T_T_OPEN = "t_t_open";
	private final static String REST_FIELD_T_T_CLOSE = "t_t_close";
	private final static String REST_FIELD_AVGPRICE = "avg_price";
	private final static String REST_FIELD_SCORE = "score";
	private final static String REST_FIELD_TOTALTABLES = "totalTables";
	// State
	private final static String REST_FIELD_BOOKTABLES = "bookTables";
	// Contact and Location
	private final static String REST_FIELD_LAT = "lat";
	private final static String REST_FIELD_LON = "lon";
	private final static String REST_FIELD_PROV = "prov";
	private final static String REST_FIELD_TELF = "telf";

	public static String create(Restaurant restaurant) {
		String msg = PropUtil.RR_OK;
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Transaction txn = datastore.beginTransaction();
		try {
			Entity entryEntity = new Entity(REST_KEY, getNextId());
			entryEntity.setProperty(FIELD_DATE, restaurant.getCreateDate());
			entryEntity.setProperty(FIELD_ACTIVE, restaurant.getIsActived());
			entryEntity.setProperty(REST_FIELD_NAME, restaurant.getName());
			entryEntity.setProperty(REST_FIELD_TYPE, restaurant.getType());
			entryEntity.setProperty(REST_FIELD_URLIMAGE, restaurant.getUrl());
			entryEntity.setProperty(REST_FIELD_DESCRIP, restaurant.getDesc());
			entryEntity.setProperty(REST_FIELD_M_T_OPEN,
					restaurant.getM_t_open());
			entryEntity.setProperty(REST_FIELD_M_T_CLOSE,
					restaurant.getM_t_close());
			entryEntity.setProperty(REST_FIELD_T_T_OPEN,
					restaurant.getT_t_open());
			entryEntity.setProperty(REST_FIELD_T_T_CLOSE,
					restaurant.getT_t_close());
			entryEntity.setProperty(REST_FIELD_AVGPRICE,
					restaurant.getAvg_price());
			entryEntity.setProperty(REST_FIELD_SCORE, restaurant.getScore());
			entryEntity.setProperty(REST_FIELD_TOTALTABLES,
					restaurant.getTotalTables());
			entryEntity.setProperty(REST_FIELD_BOOKTABLES,
					restaurant.getBookTables());
			entryEntity.setProperty(REST_FIELD_LAT, restaurant.getLat());
			entryEntity.setProperty(REST_FIELD_LON, restaurant.getLon());
			entryEntity.setProperty(REST_FIELD_PROV, restaurant.getProv());
			entryEntity.setProperty(REST_FIELD_TELF, restaurant.getTelf());
			datastore.put(entryEntity);
			txn.commit();
		} finally {
			if (txn.isActive()) {
				msg = PropUtil.RR_E_U;
				txn.rollback();
			}
		}
		return msg;
	}

	public static String book(Restaurant restaurant) {
		String msg = PropUtil.BR_OK;
		int tablesTotal=0, tablesBooked=0, tables2Book=0;
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Key userKey = KeyFactory.createKey(REST_KEY, restaurant.getId());
		try {
			Entity entryEntity = datastore.get(userKey);
			tablesTotal = (int) ((long) entryEntity.getProperty(REST_FIELD_TOTALTABLES));
			tablesBooked = (int) ((long) entryEntity.getProperty(REST_FIELD_BOOKTABLES));
			tables2Book = restaurant.getBookTables();
			if (tablesTotal>=(tablesBooked+tables2Book)){
				Transaction txn = datastore.beginTransaction();
				try {
					entryEntity.setProperty(REST_FIELD_BOOKTABLES,tablesBooked+tables2Book);
					datastore.put(entryEntity);
					txn.commit();
				} finally {
					if (txn.isActive()) {
						msg = PropUtil.BR_E_U;
						txn.rollback();
					}
				}
			} else { // (tablesTotal < (tablesBooked+tables2Book))
				msg = PropUtil.BR_E_RF;
			}
		} catch (EntityNotFoundException e) {
			msg=PropUtil.BR_E_RNF;
		}
		return msg;
	}

	public static ArrayList<Restaurant> fetch() {
		Restaurant restauranteItem;
		ArrayList<Restaurant> restauranteList = new ArrayList<Restaurant>();
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query entryQuery = new Query(REST_KEY);
		List<Entity> entriesResult = datastore.prepare(
				entryQuery.addSort(FIELD_DATE, SortDirection.ASCENDING))
				.asList(FetchOptions.Builder.withDefaults());
		for (Entity entryEntity : entriesResult) {
			restauranteItem = new Restaurant(
					(long) entryEntity.getKey().getId(),
					(String) entryEntity.getProperty(REST_FIELD_NAME),
					(String) entryEntity.getProperty(REST_FIELD_TYPE),
					(String) entryEntity.getProperty(REST_FIELD_DESCRIP),
					(String) entryEntity.getProperty(REST_FIELD_TELF),
					(String) entryEntity.getProperty(REST_FIELD_M_T_OPEN),
					(String) entryEntity.getProperty(REST_FIELD_M_T_CLOSE),
					(String) entryEntity.getProperty(REST_FIELD_T_T_OPEN),
					(String) entryEntity.getProperty(REST_FIELD_T_T_CLOSE),
					(int) ((long) entryEntity.getProperty(REST_FIELD_AVGPRICE)),
					(float) ((double) entryEntity.getProperty(REST_FIELD_SCORE)),
					(int) ((long) entryEntity
							.getProperty(REST_FIELD_TOTALTABLES)),
					(int) ((long) entryEntity
							.getProperty(REST_FIELD_BOOKTABLES)),
					(float) ((double) entryEntity.getProperty(REST_FIELD_LAT)),
					(float) ((double) entryEntity.getProperty(REST_FIELD_LON)));

			restauranteList.add(restauranteItem);
		}
		return restauranteList;
	}

	private static long getNextId() {
		long currentId = 0, nextId;
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query entryQuery = new Query(REST_KEY);
		List<Entity> entriesResult = datastore.prepare(
				entryQuery.addSort(FIELD_DATE, SortDirection.ASCENDING))
				.asList(FetchOptions.Builder.withDefaults());
		for (Entity entryEntity : entriesResult) {
			long idItem = entryEntity.getKey().getId();
			if (idItem > currentId) {
				currentId = idItem;
			}
		}
		nextId = ++currentId;
		return nextId;
	}

}

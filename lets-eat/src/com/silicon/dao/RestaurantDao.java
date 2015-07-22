package com.silicon.dao;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.silicom.cron.ResetBookedTables;
import com.silicom.utils.EmailSender;
import com.silicom.utils.PropUtil;
import com.silicon.entities.Restaurant;

public class RestaurantDao implements InterfDao {
	private static final Logger _logger = Logger
			.getLogger(RestaurantDao.class.getName());
	
	// Restaurant Entity
	private final static String REST_KEY = "Restaurant";
	// Description
	private final static String REST_FIELD_TYPE = "d_tipo";
	private final static String REST_FIELD_URLIMAGE = "d_url";
	private final static String REST_FIELD_DESCRIP = "d_desc";
	private final static String REST_FIELD_AVGPRICE = "d_avg_price";
	private final static String REST_FIELD_DISCOUNT = "d_discount";
	private final static String REST_FIELD_SCORE = "d_score";
	private final static String REST_FIELD_TOTALTABLES = "d_totalTables";
	// State
	private final static String REST_FIELD_BOOKTABLES = "s_bookTables";
	// Contact
	private final static String REST_FIELD_NAME = "c_name";
	private final static String REST_FIELD_M_T_OPEN = "c_m_open";
	private final static String REST_FIELD_M_T_CLOSE = "c_m_close";
	private final static String REST_FIELD_T_T_OPEN = "c_t_open";
	private final static String REST_FIELD_T_T_CLOSE = "c_t_close";
	private final static String REST_FIELD_LAT = "c_lat";
	private final static String REST_FIELD_LON = "c_lon";
	private final static String REST_FIELD_PROV = "c_prov";
	private final static String REST_FIELD_TELF = "c_telf";

	public static String create(Restaurant restaurant) {
		String msg = PropUtil.RR_OK;
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Transaction txn = datastore.beginTransaction();
		try {
			Entity entryEntity = new Entity(REST_KEY, getNextId());
			entryEntity.setProperty(FIELD_CREATEDATE, restaurant.getCreateDate());
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
			entryEntity.setProperty(REST_FIELD_DISCOUNT,
					restaurant.getDiscount());
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
				_logger.severe(PropUtil.getPropierties().get(msg));
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
					entryEntity.setProperty(FIELD_MODIFIEDDATE,restaurant.getModifDate());
					datastore.put(entryEntity);
					txn.commit();
				} finally {
					if (txn.isActive()) {
						msg = PropUtil.BR_E_U;
						txn.rollback();
						_logger.severe(PropUtil.getPropierties().get(msg));
					}
				}
			} else { // (tablesTotal < (tablesBooked+tables2Book))
				msg = PropUtil.BR_E_RF;
			}
		} catch (EntityNotFoundException e) {
			msg=PropUtil.BR_E_RNF;
			_logger.warning(PropUtil.getPropierties().get(msg));
		}
		return msg;
	}

	public static void resetBookedTables() {
		DatastoreService datastore	= DatastoreServiceFactory.getDatastoreService();
		Filter propertyFilter = new FilterPredicate(REST_FIELD_BOOKTABLES, 
													FilterOperator.GREATER_THAN,
													0);
		Query q = new Query(REST_KEY).setFilter(propertyFilter);
		// Use PreparedQuery interface to retrieve results
		PreparedQuery pq = datastore.prepare(q);
		for (Entity entity : pq.asIterable()) {
			Transaction txn = datastore.beginTransaction();
			try {
				entity.setProperty(REST_FIELD_BOOKTABLES,0);
				entity.setProperty(FIELD_MODIFIEDDATE,new Date());
				datastore.put(entity);
				txn.commit();
				_logger.info(PropUtil.getPropierties().get(PropUtil.RT_E_U));
			} finally {
				if (txn.isActive()) {
					txn.rollback();
					_logger.severe(PropUtil.getPropierties().get(PropUtil.RT_E_U));
				}
			}
		}
	}
	
	public static ArrayList<Restaurant> fetch() {
		Restaurant restauranteItem;
		ArrayList<Restaurant> restauranteList = new ArrayList<Restaurant>();
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query entryQuery = new Query(REST_KEY);
		List<Entity> entriesResult = datastore.prepare(
				entryQuery.addSort(FIELD_CREATEDATE, SortDirection.ASCENDING))
				.asList(FetchOptions.Builder.withDefaults());
		if (entriesResult.size()>0){
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
						(int) ((long) entryEntity.getProperty(REST_FIELD_DISCOUNT)),
						(float) ((double) entryEntity.getProperty(REST_FIELD_SCORE)),
						(int) ((long) entryEntity
								.getProperty(REST_FIELD_TOTALTABLES)),
						(int) ((long) entryEntity
								.getProperty(REST_FIELD_BOOKTABLES)),
						(float) ((double) entryEntity.getProperty(REST_FIELD_LAT)),
						(float) ((double) entryEntity.getProperty(REST_FIELD_LON)));
				restauranteList.add(restauranteItem);
			}
		}else{
			_logger.warning(PropUtil.getPropierties().get(PropUtil.GR_I_EMP));
		}
		return restauranteList;
	}

	private static long getNextId() {
		long currentId = 0, nextId;
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query entryQuery = new Query(REST_KEY);
		List<Entity> entriesResult = datastore.prepare(
				entryQuery.addSort(FIELD_CREATEDATE, SortDirection.ASCENDING))
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

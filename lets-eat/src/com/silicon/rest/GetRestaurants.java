package com.silicon.rest;

import java.util.ArrayList;
import java.util.Arrays;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;
import org.restlet.data.Status;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Post;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.gson.Gson;
import com.silicom.utils.PropUtil;
import com.silicon.entities.Restaurant;
import com.silicon.entities.User;
import com.silicon.dao.RestaurantDao;
import com.silicon.dao.UserDao;

public class GetRestaurants extends ServerResource {

	@SuppressWarnings("finally")
	@Post("json:json")
	public Representation acceptJson(JsonRepresentation represent)
			throws ResourceException {

		Representation rep = null;
		JSONStringer jsReply = null;
		String result;
		String jsonRestaurantList = null;
		try {
			ArrayList <Restaurant> restauranteList = RestaurantDao.fetch();
			if (!(restauranteList.size()>0)){
				jsReply = new JSONStringer();
				jsReply.object();
				jsReply.key("code").value(PropUtil.GR_I_EMP);
				jsReply.key("desc").value(PropUtil.getPropierties().get(PropUtil.GR_I_EMP));
				jsReply.endObject();
			}else {
				Gson gson = new Gson(); 
				jsonRestaurantList = gson.toJson(restauranteList);
			}
			getResponse().setStatus(Status.SUCCESS_OK);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				jsReply = new JSONStringer();
				jsReply.object();
				jsReply.key("code").value("ERROR");
				jsReply.key("desc").value(e.getMessage());
				jsReply.endObject();
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
			getResponse().setStatus(Status.SERVER_ERROR_INTERNAL);
		} finally {
			if (jsReply!=null){
				rep = new JsonRepresentation(jsReply);
			} else {
				rep = new JsonRepresentation(jsonRestaurantList);
			}
			return rep;
		}
	}
}

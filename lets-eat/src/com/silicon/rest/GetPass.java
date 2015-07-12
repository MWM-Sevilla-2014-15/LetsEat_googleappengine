package com.silicon.rest;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;
import org.restlet.data.Status;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Post;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import com.silicom.utils.PropUtil;
import com.silicon.entities.User;
import com.silicon.dao.UserDao;
/**
 * Resource which has only one representation.
 *
 */
public class GetPass extends ServerResource {

	@SuppressWarnings("finally")
	@Post("json:json")
	public Representation acceptJson(JsonRepresentation represent)
			throws ResourceException {

		Representation rep = null;
		JSONStringer jsReply = null;
		String result;
		
		try {
			JSONObject jsonobject = represent.getJsonObject();
			String requestString = jsonobject.getString("request");
			JSONObject json = new JSONObject(requestString);
			User user = new User 		(json.getString("email"));
			result = UserDao.existEmail(user);
			jsReply = new JSONStringer();
			jsReply.object();
			jsReply.key("code").value(result);
			jsReply.key("desc").value(PropUtil.getPropierties().get(result));
			jsReply.endObject();
			getResponse().setStatus(Status.SUCCESS_OK);
		} catch (Exception e) {
			e.printStackTrace();
			jsReply = new JSONStringer();
			try {
				jsReply.object();
				jsReply.key("code").value("ERROR");
				jsReply.key("desc").value(e.getMessage());
				jsReply.endObject();
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
			getResponse().setStatus(Status.SERVER_ERROR_INTERNAL);
		} finally {
			rep = new JsonRepresentation(jsReply);
			return rep;
		}
	}

}

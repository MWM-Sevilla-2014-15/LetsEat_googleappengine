package com.silicon.rest;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;
import org.restlet.data.Status;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Post;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

/**
 * Resource which has only one representation.
 *
 */
public class SingUp extends ServerResource {

	@SuppressWarnings("finally")
	@Post("json:json")
	public Representation acceptJson(JsonRepresentation represent)
			throws ResourceException {

		Representation rep = null;
		JSONStringer jsReply = null;
		
		try {
			/*
			 * Representation entity = getRequestEntity(); JsonRepresentation
			 * represent = new JsonRepresentation(entity);
			 */

			JSONObject jsonobject = represent.getJsonObject();
			String requestString = jsonobject.getString("request");
			JSONObject json = new JSONObject(requestString);
			String user = json.getString("user_email");
			String login = json.getString("login");
			// double score = json.getDouble(“score”);

			getResponse().setStatus(Status.SUCCESS_ACCEPTED);
			jsReply = new JSONStringer();
			jsReply.object();

			jsReply.key("code").value("SUCCESS");
			jsReply.key("desc").value("Process Sucessfully");

			jsReply.endObject();

			getResponse().setStatus(Status.SUCCESS_OK);

		} catch (Exception e) {

			e.printStackTrace();

			jsReply = new JSONStringer();

			try {

				jsReply.object();

				jsReply.key("CODE").value("Error");

				jsReply.key("DESC").value(e.getMessage());

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

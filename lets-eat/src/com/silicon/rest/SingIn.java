package com.silicon.rest;

import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

/**
 * Resource which has only one representation.
 *
 */
public class SingIn extends ServerResource {

	@Get("json")
	public String handleGet() {
		try {
			JSONObject json = new JSONObject();
			json.put("id-service", "sign in");
			JsonRepresentation jsonRep = new JsonRepresentation(json);
			return jsonRep.getText();
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}

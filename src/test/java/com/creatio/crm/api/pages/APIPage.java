package com.creatio.crm.api.pages;

import org.json.JSONObject;

public class APIPage {
	
	public static String createRepoBody(String name, String description, boolean isPrivate) {
		JSONObject body = new JSONObject();
		body.put("name", name);
		body.put("description", description);
		body.put("private", isPrivate);	
		return body.toString();
	}
	
	public static String updateRepoBody(boolean isPrivate) {
		JSONObject body = new JSONObject();
		body.put("private", isPrivate);	
		return body.toString();
	}

}

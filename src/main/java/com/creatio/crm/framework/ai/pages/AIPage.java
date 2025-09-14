package com.creatio.crm.framework.ai.pages;

import org.json.JSONObject;

public class AIPage {
	
	public static String simpleRequestBody(String model, String prompt) {
		JSONObject requestBody = new JSONObject();
		requestBody.put("model", model);
		requestBody.put("prompt", prompt);
		requestBody.put("stream", false);
		return requestBody.toString();
	}
	
	public static String structuredRequestBody(String model, String prompt) {
		JSONObject requestBody = new JSONObject();
		requestBody.put("model", model);
		requestBody.put("prompt", prompt);
		requestBody.put("stream", false);
		
		// Create  format object
		JSONObject format = new JSONObject();
		format.put("type", "integer");
		
		// Create properties object
		JSONObject properties = new JSONObject();
		
		// Create output object
		JSONObject output = new JSONObject();
		output.put("type", "object");
		
		properties.put("output", output);		
		format.put("properties", properties);		
		format.put("required", new String[] {"output"});
		
		//Check the body
		System.out.println(requestBody.toString());		
		
		return requestBody.toString();
	}

}

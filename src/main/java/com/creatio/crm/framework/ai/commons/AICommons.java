package com.creatio.crm.framework.ai.commons;

import java.util.Properties;

import com.creatio.crm.framework.utilities.PropUtil;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class AICommons {
	
	//This class will contain all the common methods to connect with LLMs to get the AI solutions.
	public static Properties prop = PropUtil.readData("Config.properties");
	
	// method to get the AI response based on the model, prompt
	public static Response getAIResponse(String requestBody) {
		RestAssured.baseURI = prop.getProperty("OLLAMA_URL");
		String endPoint =prop.getProperty("OLLAMA_ENDPOINT");
		Response response = RestAssured.given().headers("Content-Type", "application/json").body(requestBody).when().post(endPoint);		
		return response;
	}
}

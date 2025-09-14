package com.creatio.crm.framework.api.commons;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;

import com.creatio.crm.framework.utilities.PropUtil;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class APICommons {

	// This class will contain all the common methods to automate API functional
	// testing.

	public static Response response = null;
	public static Properties prop = PropUtil.readData("Config.properties");

	// method to get the API Response based on the request type, end point and body
	public static void getAPIResponse(String requestType, String endPoint, String body) {
		RestAssured.baseURI = prop.getProperty("BASE_URL");
		String token = prop.getProperty("TOKEN");
		requestType = requestType.toUpperCase();
		if (requestType.equals("GET")) {
			response = RestAssured.given().headers("Authorization", token).headers("Accept", "application/vnd.github+json").when().get(endPoint);
		} else if (requestType.equals("POST")) {
			response = RestAssured.given().headers("Authorization", token).headers("Accept", "application/vnd.github+json").body(body).when().post(endPoint);
		} else if (requestType.equals("PATCH")) {
			response = RestAssured.given().headers("Authorization", token).headers("Accept", "application/vnd.github+json").body(body).when().patch(endPoint);
		} else if (requestType.equals("PUT")) {
			response = RestAssured.given().headers("Authorization", token).headers("Accept", "application/vnd.github+json").body(body).when().put(endPoint);
		} else if (requestType.equals("DELETE")) {
			response = RestAssured.given().headers("Authorization", token).headers("Accept", "application/vnd.github+json").when().delete(endPoint);
		}
		System.out.println("RESPONSE : "+response.asPrettyString());
		wait(3);
	}
	
	// method to verify the status code from the response
	public static void verifyStatusCode(int expectedStatusCode) {
		int actualStatusCode = response.getStatusCode();
		Assert.assertEquals(actualStatusCode, expectedStatusCode, "Status code does not match!");
	}
	
	// method to verify the status message from the response
	public static void verifyStatusMessage(String expectedStatusMessage) {
		String actualStatusLine = response.getStatusLine();
		Assert.assertTrue(actualStatusLine.contains(expectedStatusMessage), "Status message does not match!");
	}
	
	// method to verify the response time
	public static void verifyResponseTime(long expectedResponseTime) {
		long actualResponseTime = response.getTimeIn(TimeUnit.SECONDS);
		Assert.assertTrue(actualResponseTime <= expectedResponseTime, "Response time is greater than expected!");
	}
		
	// method to verify the response body key and value
	public static void verifyResponseBodyKeyValue(String key, String expectedValue) {
		String actualValue = response.jsonPath().getString(key);
		Assert.assertEquals(actualValue, expectedValue, "Response body value does not match for key: " + key);
	}
	
	// method to verify the response header key and value
	public static void verifyResponseHeaderKeyValue(String key, String expectedValue) {
		String actualValue = response.getHeader(key);
		Assert.assertEquals(actualValue, expectedValue, "Response header value does not match for key: " + key);
	}	
	
	// method to wait for some time
	public static void wait(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

package com.creatio.crm.ai.tests;

import org.testng.annotations.Test;

import com.creatio.crm.framework.ai.commons.AICommons;
import com.creatio.crm.framework.ai.pages.AIPage;

import io.restassured.response.Response;

public class AITest {
	
	@Test
	public void AITest() {
		//Simple Response
		String requestBody = AIPage.simpleRequestBody("gemma3:1b", "Location of Charminar?");
		Response response = AICommons.getAIResponse(requestBody);
		System.out.println("RESPONSE : "+response.jsonPath().getString("response"));
		
		//Structured Response
		requestBody = AIPage.structuredRequestBody("gemma3:1b", "What is the Square Root Of 81? Respond with just the number.");
		response = AICommons.getAIResponse(requestBody);
		System.out.println("RESPONSE : "+response.jsonPath().getString("response"));
	}

}

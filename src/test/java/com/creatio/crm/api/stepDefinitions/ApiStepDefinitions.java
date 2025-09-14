package com.creatio.crm.api.stepDefinitions;

import com.creatio.crm.api.pages.APIPage;
import com.creatio.crm.framework.api.commons.APICommons;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ApiStepDefinitions {	
	
	private String repoName;
	private String repoDescription;
	
	@Given("User sets the Repository name as {string} and description as {string}")
	public void user_sets_the_repository_name_as_and_description_as(String name, String description) {
	    this.repoName = name;
	    this.repoDescription = description;	    
	}	

	@Then("User should get the response code as {int}")
	public void user_should_get_the_response_code_as(Integer expCode) {
	    APICommons.verifyStatusCode(expCode);	    
	}

	@Then("User should get the response message as {string}")
	public void user_should_get_the_response_message_as(String expMessage) {
	    APICommons.verifyStatusMessage(expMessage);	    
	}

	@Then("Response time should be less than {int} seconds")
	public void response_time_should_be_less_than_seconds(Integer expTime) {
	    APICommons.verifyResponseTime(expTime);	    
	}

	@Then("The Response body should have {string} as {string}")
	public void the_response_body_should_have_as(String key, String expValue) {
	    APICommons.verifyResponseBodyKeyValue(key, expValue);    
	}
	
	@When("User sends a {string} request to {string} to create the repository with visisbility {string}")
	public void user_sends_a_request_to_to_create_the_repository_with_visisbility(String requestType, String endPoint, String visibility) {	    
		String requestBody=APIPage.createRepoBody(repoName, repoDescription, Boolean.parseBoolean(visibility));
		APICommons.getAPIResponse(requestType, endPoint, requestBody);
	}

	@When("User sends a {string} request to {string} to get repository details")
	public void user_sends_a_request_to_to_get_repository_details(String requestType, String endPoint) {	    
		APICommons.getAPIResponse(requestType, endPoint, "");
	}

	@When("User sends a {string} request to {string} to update repository visibility {string}")
	public void user_sends_a_request_to_to_update_repository_visibility(String requestType, String endPoint, String visibility) {	    
		String requestBody=APIPage.updateRepoBody(Boolean.parseBoolean(visibility));
		APICommons.getAPIResponse(requestType, endPoint, requestBody);
	}

	@When("User sends a {string} request to {string} to delete repository")
	public void user_sends_a_request_to_to_delete_repository(String requestType, String endPoint) {	    
		APICommons.getAPIResponse(requestType, endPoint,"");
	}

}

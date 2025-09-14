Feature: GitHub API Testing
I want to use this feature file to verify all the scenarios related to GitHub repository. 

  Scenario: Request to create Invalid reporsitory for the authenticated user
  	Given User sets the Repository name as "JMeterRepo" and description as "Sample Repo Description"
  	When User sends a "POST" request to "/user/repos" to create the repository with visisbility "true"
  	Then User should get the response code as 422
  	And User should get the response message as "Unprocessable Entity"
  	And Response time should be less than 2 seconds
  	And The Response body should have "message" as "Repository creation failed."
  	
  Scenario: Request to create Valid reporsitory for the authenticated user
  	Given User sets the Repository name as "RestAssuredRepo" and description as "Sample Repo Description"
  	When User sends a "POST" request to "/user/repos" to create the repository with visisbility "true"
  	Then User should get the response code as 201
  	And User should get the response message as "Created"
  	And Response time should be less than 2 seconds
  	And The Response body should have "name" as "RestAssuredRepo"
  	And The Response body should have "private" as "true"
  	
   Scenario: Request to Get a reporsitory for the authenticated user
  	When User sends a "GET" request to "/repos/bharathtechacademy05/RestAssuredRepo" to get repository details
  	Then User should get the response code as 200
  	And User should get the response message as "OK"
  	And Response time should be less than 2 seconds
  	And The Response body should have "name" as "RestAssuredRepo"
  	And The Response body should have "private" as "true"
  	
   Scenario: Request to Update a reporsitory for the authenticated user
  	When User sends a "PATCH" request to "/repos/bharathtechacademy05/RestAssuredRepo" to update repository visibility "false"
  	Then User should get the response code as 200
  	And User should get the response message as "OK"
  	And Response time should be less than 2 seconds
  	And The Response body should have "name" as "RestAssuredRepo"
  	And The Response body should have "private" as "false"
  	
  Scenario: Request to Delete a reporsitory for the authenticated user
  	When User sends a "DELETE" request to "/repos/bharathtechacademy05/RestAssuredRepo" to delete repository
  	Then User should get the response code as 204
  	And User should get the response message as "No Content"
  	And Response time should be less than 2 seconds
  	
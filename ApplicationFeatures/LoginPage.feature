Feature: Login Feature in Creatio CRM Application 
	I want to verify all the scenarios related to Login functionality in the Creatio CRM application

Background: Initialize all Page Objects
    Given Initialize Page Objects
    
Scenario Outline: Verify Application Login 
	Given Launch the Application
	Then  Verify cookies pop-up is displayed
	When User click on the "allow all" button
	Then Cookies pop-up should be closed
	When User enter <Username> and <Password>
	And Click on Login button
	Then Login should be successful
	
	Examples:
	|Username|Password|
	|bharathtechacademy5@gmail.com|BharathTechAcademy#1234|
	|bharathtechacademy5@gmail.com|BharathTechAcademy#12345|
	
Scenario Outline: Verify Application Logout Feature 
	Given Launch the Application
	Then  Verify cookies pop-up is displayed
	When User click on the "allow all" button
	Then Cookies pop-up should be closed
	When User enter <Username> and <Password>
	And Click on Login button
	Then Login should be successful
	When User click on logout button
	Then Application logout should be successful
	
	Examples:
	|Username|Password|
	|bharathtechacademy5@gmail.com|BharathTechAcademy#1234|


Feature: Cookies Feature in Creatio CRM Application 
	I want to verify all the scenarios related to Cookies functionality in the Creatio CRM application


Background: Initialize all Page Objects
    Given Initialize Page Objects

    
Scenario: Verify whether cookies pop-up is displayed
	Given Launch the Application
	Then  Verify cookies pop-up is displayed
	
Scenario: Verify cookies pop-up content
	Given Launch the Application
	Then  Verify cookies pop-up is displayed	
	And Verify the cookies pop-up <content>
	|content|
	|We may use cookies and similar technologies to collect information about the ways you interact with and use the website, to support and enhance features and functionality, to monitor performance, to personalize content and experiences, for marketing and analytics, and for other lawful purposes. We also may share information about your use of our site with our social media, advertising and analytics partners who may combine it with other information that you’ve provided to them or that they’ve collected from your use of their services. Please, see more details on the "About" tab |
		
Scenario: Verify whether cookies pop-up logos are displayed
	Given Launch the Application
	Then  Verify cookies pop-up is displayed
	And Verify cookies pop-up logos
	
Scenario: Verify whether cookies pop-up selection buttons
	Given Launch the Application
	Then  Verify cookies pop-up is displayed
	And Verify cookies pop-up selection buttons
	
Scenario: Verify whether cookies pop-up switch buttons
	Given Launch the Application
	Then  Verify cookies pop-up is displayed
	And Verify cookies pop-up switch buttons	
	
Scenario: Verify whether cookies pop-up explanded view 
	Given Launch the Application
	Then  Verify cookies pop-up is displayed
	When User click on show details link
	Then Cookies pop-up expanded view should be displayed
	
Scenario: Verify whether cookies pop-up is closed 
	Given Launch the Application
	Then  Verify cookies pop-up is displayed
	When User click on the "allow all" button
	Then Cookies pop-up should be closed
	
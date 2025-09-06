package com.creatio.crm.application.steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.creatio.crm.application.elements.HomePageElements;

public class HomePageSteps extends HomePageElements{
	
	public HomePageSteps(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	// Method to verify home page is displayed.
	public void verifyHomePageDisplayed() {
		waitForElement(applicationHubLink);
	}
	
	// Method to verify user link is displayed.
	public void verifyUsersLinkDisplayed() {
		waitForElement(usersLink);
	}
	
	// Method to log out from the application.
	public void logout() {
		waitForElement(userProfileIcon);
		click(userProfileIcon);
		waitForElement(logoutButton);
		click(logoutButton);
	}

}

package com.creatio.crm.application.steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.creatio.crm.application.elements.LoginPageElements;

public class LoginPageSteps extends LoginPageElements{
	
	public LoginPageSteps(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	// Method to verify loginPage is getting displayed. 
	public void verifyloginPageIsDisplayed() {
		waitForElement(loginPageHeader);
	}
	
	// Method to enter businessEmail and password
	public void enterBusinessEmailAndPassword(String businessEmail, String password) {
		waitForElement(businessEmailTextbox);
		enterText(businessEmailTextbox, businessEmail);
		waitForElement(passwordTextbox);
		enterText(passwordTextbox, password);
	}
	
	// Method to click on login button
	public void clickOnLoginButton() {
		waitForElement(loginButton);
		click(loginButton);
	}
	
	// Method to click on the forgotPasswordLink 
	public void clickOnForgotPasswordLink() {
		waitForElement(forgotPasswordLink);
		click(forgotPasswordLink);
	}
	
	// Method to click on the signUpLink
	public void clickOnSignUpLink() {
		waitForElement(signUpLink);
		click(signUpLink);
	}
	
	// Method to verify social media icons. 
	public void verifySocialMediaIcons() {
		waitForElement(linkedInIcon);
		waitForElement(googleIcon);
		waitForElement(facebookIcon);
	}
	
	// Method to verify signUpHeader is getting displayed.
	public void verifySignUpHeaderIsDisplayed() {
		waitForElement(signUpHeader);
	}
	

}

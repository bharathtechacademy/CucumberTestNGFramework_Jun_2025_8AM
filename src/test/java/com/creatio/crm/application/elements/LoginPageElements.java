package com.creatio.crm.application.elements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.creatio.crm.framework.web.commons.WebCommons;

public class LoginPageElements extends WebCommons {
	
	@FindBy(xpath = "//span[text()='LOG IN TO YOUR ACCOUNT']")
	public WebElement loginPageHeader;
	
	@FindBy(xpath = "//input[@aria-label='Business email']")
	public WebElement businessEmailTextbox;	
	
	@FindBy(xpath = "//a[@class='forgot-password-link']")
	public WebElement forgotPasswordLink;	
	
	@FindBy(xpath = "//input[@aria-label='Password']")
	public WebElement passwordTextbox;	
	
	@FindBy(xpath = "//span[text()=' LOG IN ']")
	public WebElement loginButton;	
	
	@FindBy(xpath = "//img[@class='icon-LinkedIn']")
	public WebElement linkedInIcon;	
	
	@FindBy(xpath = "//img[@class='icon-google']")
	public WebElement googleIcon;	
	
	@FindBy(xpath = "//img[@class='icon-facebook']")
	public WebElement facebookIcon;		
	
	@FindBy(xpath = "//span[text()='Don’t have an account?']")
	public WebElement signUpHeader;	
	
	@FindBy(xpath = "//span[text()=' SIGN UP ']")
	public WebElement signUpLink;	

}

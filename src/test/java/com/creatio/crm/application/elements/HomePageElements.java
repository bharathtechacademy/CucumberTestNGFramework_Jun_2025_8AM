package com.creatio.crm.application.elements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.creatio.crm.framework.web.commons.WebCommons;

public class HomePageElements extends WebCommons{	
	
	@FindBy(xpath="//span[text()='Application Hub']")
	public WebElement applicationHubLink;
	
	@FindBy(xpath="//span[text()='Users']")
	public WebElement usersLink;
	
	@FindBy(xpath="//ts-contact-photo")
	public WebElement userProfileIcon;
	
	@FindBy(xpath="//button[contains(@data-item-marker,'Logout')]")
	public WebElement logoutButton;
	

}

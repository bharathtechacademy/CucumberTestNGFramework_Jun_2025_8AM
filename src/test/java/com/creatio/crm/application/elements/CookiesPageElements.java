package com.creatio.crm.application.elements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.creatio.crm.framework.web.commons.WebCommons;

public class CookiesPageElements extends WebCommons{
	
	@FindBy(xpath = "//div[@id='CybotCookiebotDialogBodyContentTitle']")
	public WebElement cookiesHeader;
	
	@FindBy(xpath = "//div[@id='CybotCookiebotDialogBodyContentText']")
	public WebElement cookiesContent;
	
	@FindBy(xpath = "//button[@id='CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll']")
	public WebElement allowAllButton;
	
	@FindBy(xpath = "//button[@id='CybotCookiebotDialogBodyLevelButtonLevelOptinAllowallSelection']")
	public WebElement allowSelectionButton;
	
	@FindBy(xpath = "//button[@id='CybotCookiebotDialogBodyButtonDecline']")
	public WebElement denyButton;
	
	@FindBy(xpath = "//input[@id='CybotCookiebotDialogBodyLevelButtonNecessary']")
	public WebElement necessarySwitchButton;
	
	@FindBy(xpath = "//input[@id='CybotCookiebotDialogBodyLevelButtonPreferences']")
	public WebElement preferencesSwitchButton;
	
	@FindBy(xpath = "//input[@id='CybotCookiebotDialogBodyLevelButtonStatistics']")
	public WebElement statisticsSwitchButton;
	
	@FindBy(xpath = "//input[@id='CybotCookiebotDialogBodyLevelButtonMarketing']")
	public WebElement marketingSwitchButton;
	
	@FindBy(xpath = "//a[@id='CybotCookiebotDialogBodyEdgeMoreDetailsLink']")
	public WebElement showDetailsLink;
	
	@FindBy(xpath = "//img[@id='CybotCookiebotDialogPoweredbyImage']")
	public WebElement creatioLogo;
	
	@FindBy(xpath = "//a[@id='CybotCookiebotDialogPoweredbyCybot']")
	public WebElement cookieBotLogo;
	
	@FindBy(xpath = "//a[@id='CybotCookiebotDialogNavDetails']")
	public WebElement cokkiesPopUpExpandedView;

}

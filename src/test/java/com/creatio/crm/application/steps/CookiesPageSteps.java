package com.creatio.crm.application.steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.creatio.crm.application.elements.CookiesPageElements;

public class CookiesPageSteps extends CookiesPageElements{
	
	public CookiesPageSteps(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	// Method to verify Cookies pop-up is getting displayed.
	public void verifyCookiesPopupIsDisplayed() {
		waitForElement(cookiesHeader);
	}
	
	// Method to verify cookies pop-up content is valid.
	public void verifyCookiesPopupContentIsValid(String expectedContent) {
		waitForElement(cookiesContent);
		String actualContent = getText(cookiesContent);
		Assert.assertEquals(actualContent, expectedContent, "Cookies pop-up content does not match the expected content.");
	}
	
	// Method to verify cookie pop-up logos are getting displayed.
	public void verifyCookiesPopupLogosAreDisplayed() {
		waitForElement(creatioLogo);
		waitForElement(cookieBotLogo);
	}
	
	// Method to Verify cookie selection buttons within the pop-up.
	public void verifyCookieSelectionButtonsAreDisplayed() {
		waitForElement(allowAllButton);
		waitForElement(allowSelectionButton);
		waitForElement(denyButton);
	}
	
	// Method to verify switch buttons within the pop-up.
	public void verifySwitchButtonsAreDisplayed() {
		waitForElement(necessarySwitchButton);
		waitForElement(preferencesSwitchButton);
		waitForElement(statisticsSwitchButton);
		waitForElement(marketingSwitchButton);
	}
	
	// Method to select the cookies selection button in the cookies pop-up.
	public void selectCookiesSelectionButton(String buttonName) {
		switch (buttonName.toLowerCase()) {
			case "allow all":
				click(allowAllButton);
				break;
			case "allow selection":
				click(allowSelectionButton);
				break;
			case "deny":
				click(denyButton);
				break;
			default:
				throw new IllegalArgumentException("Invalid button name: " + buttonName);
		}
	}
	
	// Method to click on the 'Show details' link within the cookies popup.
	public void clickShowDetailsLink() {
		waitForElement(showDetailsLink);
		click(showDetailsLink);		
	}
	
	// Method to verify whether cookies pop-up is expanded.
	public void verifyCookiesPopupIsExpanded() {
		waitForElement(cokkiesPopUpExpandedView);
	}
	
	//Method to verify whether cookies pop-up is closed or disappeared.
	public void verifyCookiesPopupIsClosed() {
		waitForElementToDisappear(cookiesHeader);
	}

}

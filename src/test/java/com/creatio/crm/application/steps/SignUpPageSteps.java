package com.creatio.crm.application.steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class SignUpPageSteps {
	
	public SignUpPageSteps(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

}

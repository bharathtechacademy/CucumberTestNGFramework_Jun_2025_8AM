package com.creatio.crm.application.stepDefinitions;

import java.util.List;

import org.openqa.selenium.WebDriver;

import com.creatio.crm.application.steps.CookiesPageSteps;
import com.creatio.crm.application.steps.HomePageSteps;
import com.creatio.crm.application.steps.LoginPageSteps;
import com.creatio.crm.application.steps.SignUpPageSteps;
import com.creatio.crm.framework.base.BasePage;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ApplicationStepDefinitions {
	
	public LoginPageSteps loginPage ;
	public CookiesPageSteps cookiesPage ;
	public HomePageSteps homePage ;
	public SignUpPageSteps signUpPage ;
	
	@Given("Initialize Page Objects")
	public void initialize_page_objects() {
		WebDriver driver = BasePage.getDriver();
		loginPage = new LoginPageSteps(driver);
		cookiesPage = new CookiesPageSteps(driver);
		homePage = new HomePageSteps(driver);
		signUpPage = new SignUpPageSteps(driver);	    
	}

	@Given("Launch the Application")
	public void launch_the_application() {
		loginPage.launchApplication();	    
	}

	@Then("Verify cookies pop-up is displayed")
	public void verify_cookies_pop_up_is_displayed() {
		cookiesPage.verifyCookiesPopupIsDisplayed();	    
	}

	@Then("Verify the cookies pop-up <content>")
	public void verify_the_cookies_pop_up_content(DataTable dataTable) {
	    List<String> content = dataTable.asList();
	    String expectedContent = content.get(1);
	    cookiesPage.verifyCookiesPopupContentIsValid(expectedContent);
	}

	@Then("Verify cookies pop-up logos")
	public void verify_cookies_pop_up_logos() {
		cookiesPage.verifyCookiesPopupLogosAreDisplayed();	    
	}

	@Then("Verify cookies pop-up selection buttons")
	public void verify_cookies_pop_up_selection_buttons() {
		cookiesPage.verifyCookieSelectionButtonsAreDisplayed();	    
	}

	@Then("Verify cookies pop-up switch buttons")
	public void verify_cookies_pop_up_switch_buttons() {
		cookiesPage.verifySwitchButtonsAreDisplayed();	    
	}

	@When("User click on show details link")
	public void user_click_on_show_details_link() {
		cookiesPage.clickShowDetailsLink();	    
	}

	@Then("Cookies pop-up expanded view should be displayed")
	public void cookies_pop_up_expanded_view_should_be_displayed() {
		cookiesPage.verifyCookiesPopupIsExpanded();	    
	}

	@When("User click on the {string} button")
	public void user_click_on_the_button(String buttonName) {
		cookiesPage.selectCookiesSelectionButton(buttonName);	    
	}

	@Then("Cookies pop-up should be closed")
	public void cookies_pop_up_should_be_closed() {
		cookiesPage.verifyCookiesPopupIsClosed();	    
	}
	
	@When("^User enter (.*) and (.*)$")
	public void enterCredentials(String businessEmail,String password) {
		loginPage.enterBusinessEmailAndPassword(businessEmail, password);	    
	}

	@When("Click on Login button")
	public void click_on_login_button() {
		loginPage.clickOnLoginButton();	    
	}

	@Then("Login should be successful")
	public void login_should_be_successful() {
		homePage.verifyHomePageDisplayed();	    
	}

	@When("User click on logout button")
	public void user_click_on_logout_button() {
		homePage.logout();	    
	}

	@Then("Application logout should be successful")
	public void application_logout_should_be_successful() {
		loginPage.verifyloginPageIsDisplayed();	    
	}


}

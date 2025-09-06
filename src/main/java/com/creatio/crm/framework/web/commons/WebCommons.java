package com.creatio.crm.framework.web.commons;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.creatio.crm.framework.base.BasePage;
import com.creatio.crm.framework.constants.Constants;
import com.creatio.crm.framework.utilities.PropUtil;

public class WebCommons {
	
	//This class will contain all the common methods related to Selenium web automation utilities.
	
	public WebDriver driver = BasePage.getDriver(); //get the driver instance from BasePage class
	public Properties prop = PropUtil.readData("Config.properties"); //read the properties file using PropUtil class
	
	
	// Common method to navigate to a URL and launch the application by verifying the page title
	public void launchApplication() {
		String url = prop.getProperty("APP_URL");
		String expectedTitle = prop.getProperty("APP_TITLE");
		driver.get(url);
		String actualTitle = driver.getTitle();
		if (!actualTitle.contains(expectedTitle)) {
			Assert.fail("Application launch failed, expected title: " + expectedTitle + ", but found: " + actualTitle);
		}
	}
	
	// Common method to scroll to a specific element
	public void scrollToElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	// Common method to click on an element
	public void click(WebElement element) {
		scrollToElement(element);
		element.click();
	}
	
	// Common method to click on an element using JavaScriptExecutor (hidden or not clickable elements)
	public void jsClick(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}
	
	//Common method to perform a double click on the WebElement.
	public void doubleClick(WebElement element) {
		scrollToElement(element);
		Actions actions = new Actions(driver);
		actions.doubleClick(element).perform();
	}
	
	// Common method to perform a right click on the WebElement.
	public void rightClick(WebElement element) {
		scrollToElement(element);
		Actions actions = new Actions(driver);
		actions.contextClick(element).perform();
	}
	
	// Common method to perform a mouse hover on the WebElement.
	public void mouseHover(WebElement element) {
		scrollToElement(element);
		Actions actions = new Actions(driver);
		actions.moveToElement(element).perform();
	}
	
	//Common method to select the checkbox element.
	public void selectCheckbox(WebElement checkbox, boolean status) {
		scrollToElement(checkbox);
		if (checkbox.isSelected() != status) {
			checkbox.click();
		}
	}
	
	// Common method to enter the text within the text box element.
	public void enterText(WebElement element, String text) {
		scrollToElement(element);
		element.clear();
		element.sendKeys(text);
	}
	
	// Common method to enter the text by using Actions class.
	public void enterInfo(WebElement element, String text) {
		scrollToElement(element);
		Actions actions = new Actions(driver);
		actions.sendKeys(element,text).perform();
	}
	
	// Common method to select the option from the drop-down.
	public void selectOption(WebElement dropdown, String option, String selectBy) {
		scrollToElement(dropdown);
		Select select = new Select(dropdown);
		if(selectBy.equalsIgnoreCase("visibletext")) {
			select.selectByVisibleText(option);
		} else if(selectBy.equalsIgnoreCase("value")) {
			select.selectByValue(option);
		} else if(selectBy.equalsIgnoreCase("index")) {
			select.selectByIndex(Integer.parseInt(option));
		} else {
			Assert.fail("Invalid selectBy parameter: " + selectBy);
		}
	}
	
	// Common method to get the text from the WebElement.
	public String getText(WebElement element) {
		scrollToElement(element);
		return element.getText();
	}
	
	// Common method to get the attribute value from the WebElement.
	public String getAttribute(WebElement element, String attribute) {
		scrollToElement(element);
		return element.getAttribute(attribute);
	}
	
	//Common method to get the current browser URL.
	public String getCurrentURL() {
		return driver.getCurrentUrl();
	}
	
	// Common method to refresh the current page.
	public void refreshPage() {
		driver.navigate().refresh();
	}
	
	//Common method to wait by using Java wait() method.
	public void wait(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	// Common method to use implicit weights.
	public void setImplicitWait() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Constants.WAIT_TIME));
	}
	
	//Common method to wait for the element by using explicit wait.
	public void waitForElement(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.WAIT_TIME));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	// Common method to wait for the element by using explicit wait.
	public void waitForElement(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.WAIT_TIME));
		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(locator, 0));
	}
	
	// Common method to wait for the element to be disappeared by using explicit wait.
	public void waitForElementToDisappear(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.WAIT_TIME));
		wait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	// Common method to wait for the alert to be present.
	public void waitForAlert() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.WAIT_TIME));
		wait.until(ExpectedConditions.alertIsPresent());
	}
	
	// Common method to switch to the alert and accept /reject it.
	public void handleAlert(boolean accept) {
		waitForAlert();
		if (accept) {
			driver.switchTo().alert().accept();
		} else {
			driver.switchTo().alert().dismiss();
		}
	}
	
	//Common method to take a screenshot of the complete browser window.
	public static String takeScreenshot(WebDriver driver, String screenshotName) throws IOException {
		String screenshotPath = System.getProperty("user.dir") + "\\screenshots\\" + screenshotName + ".png";
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshot, new File(screenshotPath));
		return screenshotPath;
	}
	
	// Common method to take a screenshot of the Web Element
	public static String takeScreenshot(WebElement element, String screenshotName) throws IOException {
		String screenshotPath = System.getProperty("user.dir") + "\\screenshots\\" + screenshotName + ".png";
		File screenshot = element.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshot, new File(screenshotPath));
		return screenshotPath;
	}
	
	

}

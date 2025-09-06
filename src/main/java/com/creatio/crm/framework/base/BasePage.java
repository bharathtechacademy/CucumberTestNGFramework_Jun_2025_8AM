package com.creatio.crm.framework.base;

import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

import com.creatio.crm.framework.utilities.PropUtil;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class BasePage{
	
	//This class will contain all the common methods related to browser setup and browser driver configurations.
	private static WebDriver driver = null;
	public Properties prop = PropUtil.readData("Config.properties");
	
	// Common method to setup the driver/browser session
	@Before
	public void setupBrowser() {
		String browserName = prop.getProperty("BROWSER");
		if (browserName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else {
			Assert.fail("Invalid browser name, please pass the correct browser name");
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
	}
	
	// Common method to tear down the driver/browser session
	@After(order=0)
	public void tearDownBrowser() {
		if (driver != null) {
			driver.quit();
		}
	}
	
	@After(order=1)
	public void failedTestListener(Scenario scenario) {
		if(scenario.isFailed()) {
			byte [] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot, "image/png", scenario.getName());
		}
	}
	
	// Common method to get the driver instance
	public static WebDriver getDriver() {
		return driver;
	}
	
	// Common method to set the driver instance
	public static void setDriver(WebDriver driver) {
		BasePage.driver = driver;
	}

}

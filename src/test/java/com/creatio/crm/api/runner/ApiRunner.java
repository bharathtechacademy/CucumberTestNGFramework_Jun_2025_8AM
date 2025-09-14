package com.creatio.crm.api.runner;

import org.testng.annotations.Test;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features="APIFeatures",
		glue= {"com.creatio.crm.api.stepDefinitions"},
		plugin = {"pretty","html:Reports/AutomationReport.html"}
//		,tags="@Sanity"
		)


public class ApiRunner extends AbstractTestNGCucumberTests{	
	
	@Test
	public void runApplicationTests() {
		System.out.println("Execution Completed for API Functional Tests...");
	}

}

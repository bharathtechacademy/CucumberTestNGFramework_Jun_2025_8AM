package com.creatio.crm.api.stepDefinitions;

import java.io.File;
import java.io.IOException;

import org.apache.jmeter.report.config.ConfigurationException;
import org.apache.jmeter.report.dashboard.GenerationException;

import com.creatio.crm.framework.api.commons.JMeterCommons;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class JMeterStepDefinitions {
	
	static String JMeterFile ;
	
	@When("The user load the JMeter file {string}")
	public void the_user_load_the_j_meter_file(String fileName) {
		JMeterFile = fileName;
	}

	@Then("Run the JMeter file and publish the test results")
	public void run_the_j_meter_file_and_publish_the_test_results() throws IOException, ConfigurationException, GenerationException {
		//Clear the Old Test Results
		File outputFolder = new File(System.getProperty("user.dir")+"\\src\\test\\resources\\jmeter\\results");
		File jsonReportsFolder = new File(System.getProperty("user.dir")+"\\report-output");
		File htmlReportsFolder = new File(System.getProperty("user.dir")+"\\src\\test\\resources\\jmeter\\bin\\report-output");
		JMeterCommons.deleteDirectory(outputFolder);
		JMeterCommons.deleteDirectory(jsonReportsFolder);
		JMeterCommons.deleteDirectory(htmlReportsFolder);
		
		// Run the JMeter Test Plan
		JMeterCommons.runJMeterTestPlan(JMeterFile);	    
	}

}

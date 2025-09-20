package com.creatio.crm.api.runner;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

import com.creatio.crm.framework.utilities.EmailUtil;
import com.creatio.crm.framework.web.commons.WebCommons;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features="JMeterFeatures",
		glue= {"com.creatio.crm.api.stepDefinitions"},
		plugin = {"pretty","html:Reports/AutomationReport.html"}
//		,tags="@Sanity"
		)


public class JMeterRunner extends AbstractTestNGCucumberTests{	
	
	@Test
	public void runApplicationTests() {
		System.out.println("Execution Completed for API Performance Tests...");
	}
	
	@AfterSuite
	public void sendEmailReport() {
		System.out.println("Sending Email Report...");
		String to = "BharathTechAcademy@Gmail.com";
		String subject = "API Test Automation Report Generated on "+WebCommons.uniqueId("dd-MM-yyyy_hh-mm-ss");
		String body = "Hi Team,\n\nPlease find the attached API automation test report.\n\nRegards,\nBharath Tech Academy";
		String attachmentPath = "src/test/resources/jmeter/bin/report-output/index.html";
		EmailUtil.sendEmailWithAttachment(to, subject, body, attachmentPath);
	}

}

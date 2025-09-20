package com.creatio.crm.application.runner;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

import com.creatio.crm.framework.utilities.EmailUtil;
import com.creatio.crm.framework.web.commons.WebCommons;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features="ApplicationFeatures",
		glue= {"com.creatio.crm.application.stepDefinitions","com.creatio.crm.framework.base"},
		plugin = {"pretty","html:Reports/AutomationReport.html"}
//		,tags="@Sanity"
		)


public class ApplicationTestRunner extends AbstractTestNGCucumberTests{	
	
	@Test
	public void runApplicationTests() {
		System.out.println("Execution Completed for Application Tests...");
	}
	
	@AfterSuite
	public void sendEmailReport() {
		System.out.println("Sending Email Report...");
		String to = "BharathTechAcademy@Gmail.com";
		String subject = "UI Test Automation Report Generated on "+WebCommons.uniqueId("dd-MM-yyyy_hh-mm-ss");
		String body = "Hi Team,\n\nPlease find the attached UI automation test report.\n\nRegards,\nBharath Tech Academy";
		String attachmentPath = "Reports/AutomationReport.html";
		EmailUtil.sendEmailWithAttachment(to, subject, body, attachmentPath);
	}

}

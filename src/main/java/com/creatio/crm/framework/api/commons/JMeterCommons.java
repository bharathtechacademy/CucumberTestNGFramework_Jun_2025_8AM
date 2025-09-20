package com.creatio.crm.framework.api.commons;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.report.config.ConfigurationException;
import org.apache.jmeter.report.dashboard.GenerationException;
import org.apache.jmeter.report.dashboard.ReportGenerator;
import org.apache.jmeter.reporters.ResultCollector;
import org.apache.jmeter.save.SaveService;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;

import com.creatio.crm.framework.web.commons.WebCommons;

public class JMeterCommons {
	
	//This class will contain all the common methods to automate JMeter performance testing.
	
	// Method to delete old JMETER test results
	public static void deleteDirectory(File directory) {
		
		//Get all files in the directory
		File[] files = directory.listFiles();
		
		//If the directory is not empty, delete all files
		if (files != null) {
			for (File file : files) {
				if (file.isDirectory()) {
					deleteDirectory(file);
				} else {
					file.delete();
				}
			}
		}
		
		//Delete the directory itself
		directory.delete();		
	}
	
	//Method to run JMETER test plan
	public static void runJMeterTestPlan(String jmxFile) throws IOException, ConfigurationException, GenerationException {
		//1. Get the JMeter home directory path
		String jmeterHome = "src/test/resources/jmeter";
		
		//2. Set the path to store all the JMeter test results in CSV file
		String  resultsFile = "TestResults_"+WebCommons.uniqueId("dd_MM_yyyy_HH_mm_ss")+".csv";
		String resultsPath = Paths.get(jmeterHome,"results",resultsFile).toString();
		System.out.println("JMeter Results Path :"+resultsPath);
		
		//3. Set the path to the JMX file
		String testPlanPath = Paths.get(jmeterHome,"testplans",jmxFile).toString();
		System.out.println("JMeter Test Plan Path :"+testPlanPath);
		
		//4. Set the property file path
		String propertiesFilePath = Paths.get(jmeterHome,"bin","jmeter.properties").toString();
		System.out.println("JMeter Properties Path :"+propertiesFilePath);
		
		//5.  Set the JMeter Home directory
		JMeterUtils.setJMeterHome(jmeterHome);
		
		//6. Load the JMeter properties file
		JMeterUtils.loadJMeterProperties(propertiesFilePath);
		
		//7. Load the JMX file 
		File file = new File(testPlanPath);
		HashTree  testPlanTree = SaveService.loadTree(file);
		
		//8. Collect and Save all test results in CSV file
		ResultCollector resultsCollector = new ResultCollector();
		resultsCollector.setFilename(resultsPath);
		
		//9. Run the JMeter test plan and copy results to the CSV file
		testPlanTree.add(testPlanTree.getArray()[0],resultsCollector);
		StandardJMeterEngine jmeterEngine = new StandardJMeterEngine();
		jmeterEngine.configure(testPlanTree);
		jmeterEngine.run();
		
		//10. Generate the HTML report from the CSV file
		ReportGenerator report = new ReportGenerator(resultsPath,null);
		report.generate();
	}
	

}

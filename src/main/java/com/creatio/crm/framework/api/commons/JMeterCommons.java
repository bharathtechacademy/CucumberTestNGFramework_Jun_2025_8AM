package com.creatio.crm.framework.api.commons;

import java.io.File;

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

}

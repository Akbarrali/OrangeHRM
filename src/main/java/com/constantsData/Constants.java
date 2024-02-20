package com.constantsData;

import java.io.File;

public class Constants {
	
	public static final int PAGE_LOAD_TIMEOUT = 20;
	
	public static final int IMPLICIT_WAIT_TIME = 30;
	
	public static final int WAIT_TIME = 70;
	
	public static final String LOGINPAGE_URL = "https://staging.ocgo.us/login";
	
	public static final String PROJECTPATH = System.getProperty("user.dir");
	
	public static final String EXTENTREPORT_PATH = File.separator + "test-output" + File.separator + "ExtentReports" + File.separator + "ExtentReport.html";
	
	public static final String DATA_PROPERTIES_PATH = File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator +  "com" + 
	                                                  File.separator + "config" + File.separator + "properties" + File.separator + "data.properties";
	
	public static final String TESTDATA_PROPERTIES_PATH = File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator +  "com" + 
                                                          File.separator + "orange" + File.separator + "testdata" + File.separator + "Testdata.properties";

	
	public static final String CONFIGDATA_PROPERTY_FILEPATH = File.separator + "src" + File.separator + "main"
			+ File.separator + "java" + File.separator + "com" + File.separator + "config" + File.separator
			+ "properties" + File.separator + "data.properties";
	
	
	
	
	
	
	
	
	
	
	
	
	}



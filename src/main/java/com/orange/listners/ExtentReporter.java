package com.orange.listners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.constantsData.Constants;

import java.io.*;
import java.util.Properties;



public class ExtentReporter extends com.orange.base.BaseClass
{
	public static ExtentReports extentReport;
	public static ExtentSparkReporter sparkReporter;
	public static Properties prop;
	
	
	public static ExtentReports generateExtentReport() 
	{
		extentReport = new ExtentReports();
		File extentReportFile = new File(Constants.PROJECTPATH + Constants.EXTENTREPORT_PATH);
		sparkReporter = new ExtentSparkReporter(extentReportFile);
		
		try
		{
			prop = new Properties();
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + Constants.CONFIGDATA_PROPERTY_FILEPATH);
			try {
				prop.load(fis);
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
			
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("UCaas Automation Test");
		sparkReporter.config().setDocumentTitle("UCaas Automation Test");
		sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");

		extentReport.attachReporter(sparkReporter);

		extentReport.setSystemInfo("Application URL", prop.getProperty("stagingurl"));
		extentReport.setSystemInfo("Browser Name", prop.getProperty("browser"));
		extentReport.setSystemInfo("Operating System", prop.getProperty("OS"));
		extentReport.setSystemInfo("Environments", prop.getProperty("environment"));
	
		return extentReport;
	}
}


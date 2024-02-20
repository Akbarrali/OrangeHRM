package com.orange.listners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.constantsData.Constants;
import com.orange.base.BaseClass;
import com.orange.utility.utility;

public class Listerners extends BaseClass implements ITestListener {
	ExtentReports extentreport;
	ExtentTest extentTest;
	
	
	@Override
	public void onStart(ITestContext context) {
		extentreport = ExtentReporter.generateExtentReport();
	}

	@Override
	public void onTestStart(ITestResult result) {
		String testName = result.getName();
		extentTest = extentreport.createTest(testName);
		extentTest.log(Status.INFO, testName + " Started execution").assignCategory(result.getMethod().getGroups());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String testName = result.getName();
		//extentTest.log(Status.PASS, testName + " Successfully executed");
		extentTest.log(Status.PASS, MarkupHelper.createLabel(testName + " Successfully executed", ExtentColor.GREEN))
		.assignCategory(result.getMethod().getGroups());
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testName = result.getName();
		String destinationScreenshotPath=utility.captureScreenshot(driver, testName);
		extentTest.addScreenCaptureFromPath(destinationScreenshotPath);
		extentTest.log(Status.INFO, result.getThrowable());
		//extentTest.log(Status.FAIL, testName + " Got failed");
		extentTest.log(Status.FAIL, MarkupHelper.createLabel(testName + " Got failed", ExtentColor.RED));		
	}

	
	@Override
	public void onTestSkipped(ITestResult result) {
		String testName = result.getName();
		extentTest.log(Status.INFO, result.getThrowable());
		//extentTest.log(Status.SKIP, testName + " Got skipped");
		extentTest.log(Status.SKIP, MarkupHelper.createLabel(testName + " Got skipped", ExtentColor.ORANGE));
	}

	@Override
	public void onFinish(ITestContext context) {
		extentreport.flush();	
		String pathofExtentReport = Constants.PROJECTPATH + Constants.EXTENTREPORT_PATH;		
		File extentReport = new File(pathofExtentReport);
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {	
			e.printStackTrace();
		}
		
	}

}

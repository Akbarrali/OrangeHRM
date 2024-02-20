package com.orange.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.orange.base.BaseClass;
import com.orange.utility.utility;

public class EmployeePage extends BaseClass {

	By PIM = By.xpath("//div[@class='oxd-sidepanel-body']/ul/li[2]/a");
	By PIMTitle = By.xpath("//span[@class='oxd-topbar-header-breadcrumb']/h6");
	By AddEmployee = By.xpath("//a[normalize-space()='Add Employee']");
	By AddEmployeeTitle = By.xpath("//div[@class='orangehrm-card-container']/h6");
	By EmployeeFirstname = By.xpath("//input[@name='firstName']");
	By EmployeeMiddleName = By.xpath("//input[@name='middleName']");
	By EmployeeLastName = By.xpath("//input[@name='lastName']");
	By LoginDetail = By.xpath("//span[@class='oxd-switch-input oxd-switch-input--active --label-right']");
	By EmployeeUsername = By.xpath("(//input[@class='oxd-input oxd-input--active'])[3]");
	By EmployeePassword = By.xpath("(//input[@type='password'])[1]");
	By EmployeeConfirmPassword = By.xpath("(//input[@class='oxd-input oxd-input--active'])[5]");
	By Enabled = By.xpath("(//div[@class='oxd-radio-wrapper'])[1]");
	By Disable = By.xpath("(//div[@class='oxd-radio-wrapper'])[2]");
	By click = By.xpath("//div[@class='orangehrm-employee-image']");
	By Save = By.xpath("//button[@type='submit']");
	By SuccessMessage = By.xpath("//div[@class='oxd-toast-start']");

	public boolean navigateToAddEmployeepage() {
		utility.explicitwait(driver, PIM);
		utility.clickElementwithjs(driver, PIM);
		utility.threadWait(1000);
		utility.explicitwait(driver, AddEmployee);
		utility.clickElementwithjs(driver, AddEmployee);
		utility.explicitwait(driver, AddEmployeeTitle);
		utility.threadWait(3000);
		return utility.isElementDisplayed(driver, AddEmployee);
	}

	public boolean createNewUser(String Firstname, String MiddleName, String LastName, String EUsername, String Epassword, String EConfirmPass){
		utility.explicitwait(driver, EmployeeFirstname);
		
		boolean Success = false;
		
		utility.sendkeys(driver, EmployeeFirstname, Firstname);
		utility.threadWait(1000);
		utility.sendkeys(driver, EmployeeMiddleName, MiddleName);
		utility.threadWait(1000);
		utility.sendkeys(driver, EmployeeLastName, LastName);
		utility.threadWait(1000);
		
		String loginuser = prop.getProperty("LoginUser");
		switch (loginuser) {
		
		case "WithLoginUser":		
			utility.explicitwait(driver, LoginDetail);
			utility.clickElementwithjs(driver, LoginDetail);			
			utility.explicitwait(driver, EmployeeUsername);
			utility.sendkeys(driver, EmployeeUsername, EUsername);
			utility.explicitwait(driver, EmployeePassword);
			utility.sendkeys(driver, EmployeePassword, Epassword);
			//utility.threadWait(1000);
			utility.click(driver, click);
			utility.sendkeys(driver, EmployeeConfirmPassword, EConfirmPass);			
			utility.explicitwait(driver, Save);
			utility.clickElementwithjs(driver, Save);
			Success = utility.isElementDisplayed(driver, SuccessMessage);
			break;
			
		case "Withoutlogin":
			utility.explicitwait(driver, Save);
			utility.clickElementwithjs(driver, AddEmployee);
			Success = utility.isElementDisplayed(driver, SuccessMessage);
			break;
		}
			
		return Success;

	}

}

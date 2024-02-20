package com.orange.pages;

import org.openqa.selenium.By;

import com.orange.base.BaseClass;
import com.orange.utility.utility;

public class UpdateEmployeepage extends BaseClass {
	
	By PIM = By.xpath("//div[@class='oxd-sidepanel-body']/ul/li[2]/a");
	By Employeename = By.xpath("(//input[@placeholder='Type for hints...'])[1]");
	By EmployeeID = By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]");
	By Search = By.xpath("//button[@type='submit']");
	By SearchedEmployee = By.xpath("//div[@class='oxd-table-row oxd-table-row--with-border oxd-table-row--clickable']/div[2]/div");
	By personalDetail = By.xpath("//h6[normalize-space()='Personal Details']");
	
	public boolean searchEmployee(String empID)
	{
		utility.explicitwait(driver, PIM);
		utility.clickElementwithjs(driver, PIM);
		utility.threadWait(1000);
		utility.explicitwait(driver, EmployeeID);
		utility.sendkeys(driver, EmployeeID, empID);
		utility.clickElementwithjs(driver, Search);
		utility.explicitwait(driver, SearchedEmployee);
		utility.clickElementwithjs(driver, SearchedEmployee);
		utility.threadWait(3000);
		utility.explicitwait(driver, personalDetail);
		return utility.isElementDisplayed(driver, personalDetail);
	}
	
	
	
	
	
	
	
	
	
	
	

}

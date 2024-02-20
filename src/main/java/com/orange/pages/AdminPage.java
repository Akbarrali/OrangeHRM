package com.orange.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.orange.base.BaseClass;
import com.orange.utility.utility;

public class AdminPage extends BaseClass{
	
	
	By Admin = By.xpath("//ul[@class='oxd-main-menu']/li[1]");
	By AdminTitle = By.xpath("//h6[@class='oxd-text oxd-text--h6 oxd-topbar-header-breadcrumb-module']");
	By Addbutton = By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--secondary']");
	By AddUserTtile = By.xpath("//h6[@class='oxd-text oxd-text--h6 orangehrm-main-title']");
	By EmployeeName = By.xpath("//div[@class='oxd-autocomplete-text-input oxd-autocomplete-text-input--active']//input");
	By Username = By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]");
	By Password = By.xpath("(//input[@class='oxd-input oxd-input--active'])[3]");
	By ConfirmPassword = By.xpath("(//input[@class='oxd-input oxd-input--active'])[4]");
	By UserRole = By.xpath("//div[@class='oxd-select-text oxd-select-text--focus']");
	By UserRoleOption = By.xpath("//div[@role='listbox']/div");
	By Status = By.xpath("(//div[@class='oxd-select-text--after'])[2]");
	By Save = By.xpath("//button[@type='submit']");
	
	
	
	public String navidateToAdminPage()
	{
		utility.explicitwait(driver, Admin);
		utility.click(driver, Admin);
		utility.explicitwait(driver, AdminTitle);
		return utility.getTextofElement(driver, AddUserTtile);
	}
	
	public String navigateToAddUserpage()
	{
		utility.explicitwait(driver, Addbutton);
		utility.click(driver, Addbutton);
		utility.explicitwait(driver, AddUserTtile);
		return utility.getTextofElement(driver, AddUserTtile);
	}
	
	public void createNewUser(String empname, String Uname, String pass, String Cpass, int string, int string2)
	{
		utility.explicitwait(driver, EmployeeName);
		utility.sendkeys(driver, EmployeeName, empname);
		utility.sendkeys(driver, Username, Uname);
		utility.sendkeys(driver, Password, pass);
		utility.sendkeys(driver, ConfirmPassword, Cpass);
		utility.click(driver, UserRole);
		List<WebElement> userrole = driver.findElements(UserRoleOption);
		userrole.get(string).click();
		
		utility.threadWait(1000);
		
		utility.click(driver, Status);
		List<WebElement> statuses = driver.findElements(UserRoleOption);
		userrole.get(string2).click();
		utility.explicitwait(driver, AddUserTtile);
		
	}
	
	
	

}

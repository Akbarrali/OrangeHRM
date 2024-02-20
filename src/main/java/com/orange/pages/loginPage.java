package com.orange.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.orange.base.BaseClass;
import com.orange.utility.utility;

public class loginPage extends BaseClass{
	
	By logo = By.xpath("//div[@class='orangehrm-login-branding']");
	By username = By.name("username"); 
	By password = By.name("password");
	By logintitle = By.xpath("//h5[text()='Login']");
	By login = By.xpath("//button[@type='submit']");
	By dashboardtitle = By.xpath("//h6[text()='Dashboard']");
	By profile = By.xpath("//p[@class='oxd-userdropdown-name']");
	By logoutOption = By.xpath("//ul[@class='oxd-dropdown-menu']/li");
	
	
	public void launchurl()
	{
		driver.get(prop.getProperty("siteurl"));
	}
	
	public boolean verifyLofinPage()
	{
		return utility.isElementDisplayed(driver, logintitle);
	}
	
	public String loginWithValidCredentials(String loginuser, String loginpassword)
	{
		utility.sendkeys(driver, username, loginuser);
		utility.sendkeys(driver, password, loginpassword);
		utility.threadWait(1000);
		utility.clickElementwithjs(driver, login);
		utility.threadWait(2000);
		return utility.getTextofElement(driver, dashboardtitle);
	}
	
	public boolean logout(int logoutoption)
	{
		utility.clickElementwithjs(driver, profile);
		utility.threadWait(1000);
		List<WebElement> logoutf = driver.findElements(logoutOption);
		logoutf.get(logoutoption).click();
		return utility.isElementDisplayed(driver, logo);
	}
	
	
}

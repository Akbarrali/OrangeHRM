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
	By login = By.xpath("//button[@type='submit']");
	By dashboardtitle = By.xpath("//span[@class='oxd-topbar-header-breadcrumb']");
	By logout = By.xpath("//ul[@class='oxd-dropdown-menu']/li");
	
	
	public void launchurl()
	{
		driver.get(prop.getProperty("siteurl"));
	}
	
	public String verifyLofinPage()
	{
		return driver.getCurrentUrl();	
	}
	
	public String loginWithValidCredentials()
	{
		utility.sendkeys(driver, username, prop.getProperty("username"));
		utility.sendkeys(driver, password, prop.getProperty("password"));
		utility.threadWait(2000);
		utility.click(driver, login);
		utility.threadWait(2000);
		return utility.getTextofElement(driver, dashboardtitle);
	}
	
	public boolean logout(int logoutoption)
	{
		loginWithValidCredentials();
		utility.threadWait(1000);
		List<WebElement> logoutf = driver.findElements(logout);
		logoutf.get(logoutoption).click();
		return utility.isElementDisplayed(driver, logo);
	}
	
	
}

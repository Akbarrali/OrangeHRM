package com.orange.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.orange.base.BaseClass;
import com.orange.pages.loginPage;



public class LoginTestcase extends BaseClass{
	
	loginPage login = new loginPage();
	
	@BeforeClass 
	public void setup() throws Exception
	{
		initialize();				
	}
		
	@BeforeMethod 
    public void launchSiteUrlTest()
    {
	    login.launchurl();
    }
	
	@Test (priority = 0)
	public void verifyLoginPageUITest()
	{
		boolean loginURL = login.verifyLofinPage();
		Assert.assertTrue(loginURL);
	}
	
	@Test (priority = 1)
	public void validateLoginFunctionality()
	{
		String NavigatetoDashboard = login.loginWithValidCredentials(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(NavigatetoDashboard, testdata.getProperty("DashboardTitle"));
	}
	
	
	@Test (priority = 2)
	public void logouttest()
	{
		boolean logoutcheck = login.logout(3);
		Assert.assertTrue(logoutcheck);		
	}
	
	
	@AfterClass 
	public void tearDown1()
	{
		driver.quit();
	}
	
	
	
	

}

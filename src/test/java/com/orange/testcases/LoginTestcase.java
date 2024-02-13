package com.orange.testcases;

import org.testng.Assert;
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
		String loginURL = login.verifyLofinPage();
		Assert.assertEquals(loginURL , "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	}
	
	//@Test (priority = 1)
	public void validateLoginFunctionality()
	{
		String NavigatetoDashboard = login.loginWithValidCredentials();
		Assert.assertEquals(NavigatetoDashboard, "Dashboard");
	}
	
	//@Test (priority = 3)
	public void logouttest()
	{
		validateLoginFunctionality();
		boolean logoutcheck = login.logout(3);
		Assert.assertTrue(logoutcheck);		
	}
	
	
	
	

}

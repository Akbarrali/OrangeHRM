package com.orange.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.orange.base.BaseClass;
import com.orange.pages.HelpPage;
import com.orange.pages.loginPage;

public class HelpTest extends BaseClass{
	
	loginPage login = new loginPage();
	HelpPage help = new HelpPage();
	
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
	
	@Test
	public void navigatetoHelpScreen()
	{
		login.loginWithValidCredentials(prop.getProperty("username"), prop.getProperty("password"));
		boolean helpverification = help.verifyHelpPage();
		Assert.assertTrue(helpverification);
	}
	
	
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}

}

package com.orange.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.orange.base.BaseClass;
import com.orange.pages.AdminPage;
import com.orange.pages.loginPage;

public class AdminTest extends BaseClass{
	
	loginPage login = new loginPage();
	AdminPage admin = new AdminPage();
	
	
	@BeforeClass
	public void setup() throws Exception
	{
		initialize();
	}
	
	@BeforeMethod
	public void launchURL()
	{
		login.launchurl();
		login.loginWithValidCredentials(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void navigateToAdminScreenTest()
	{
		String Admintitle = admin.navidateToAdminPage();
		Assert.assertEquals(Admintitle, prop.getProperty("TitleOFAdminPage"));
	}
	
	public void navigateToAddUserScreenTest()
	{
		String AddUsertitle = admin.navigateToAddUserpage();
		Assert.assertEquals(AddUsertitle, prop.getProperty("TitleOFAddUserForm"));
	}
	
	public void createNewUserTest()
	{
		admin.createNewUser(prop.getProperty("Employeename"), prop.getProperty("Username"), prop.getProperty("Password"),
		prop.getProperty("ConfirmPassword"), 1, 1);
	}
	

	
	
	
	

}

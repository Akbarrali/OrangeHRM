package com.orange.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.orange.base.BaseClass;
import com.orange.pages.EmployeePage;
import com.orange.pages.UpdateEmployeepage;
import com.orange.pages.loginPage;

public class UpldateEmployeeTest extends BaseClass{
	
	
	loginPage login = new loginPage();
	EmployeePage employee = new EmployeePage();
	UpdateEmployeepage updateemployee = new UpdateEmployeepage();

	@BeforeClass
	public void setup() throws Exception {
		initialize();
	}

	@BeforeMethod
	public void launchURL() {
		login.launchurl();
		login.loginWithValidCredentials(prop.getProperty("username"), prop.getProperty("password"));
	}
	
    @Test
	public void searchEmployeeTest()

	{
    	employee.navigateToAddEmployeepage();
		boolean searchEmployeecheck = updateemployee.searchEmployee(prop.getProperty("EmployeeID"));
		Assert.assertTrue(searchEmployeecheck);
	}

}

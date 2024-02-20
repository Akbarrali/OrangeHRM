package com.orange.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.orange.base.BaseClass;
import com.orange.pages.AdminPage;
import com.orange.pages.EmployeePage;
import com.orange.pages.loginPage;
import com.orange.utility.utility;

public class AddEmployee extends BaseClass {

	loginPage login = new loginPage();
	EmployeePage employee = new EmployeePage();

	@BeforeClass
	public void setup() throws Exception {
		initialize();
	}

	@BeforeMethod
	public void launchURL() {
		login.launchurl();
		login.loginWithValidCredentials(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test(priority = 0)
	public void navigateToAddEmployeeUITest() {
		boolean AddUsertitle = employee.navigateToAddEmployeepage();
		Assert.assertTrue(AddUsertitle);
		login.logout(3);
	}

	@Test(priority = 1)
	public void createNewUserTest() {

		employee.navigateToAddEmployeepage();
		utility.threadWait(1000);
		boolean Employee = employee.createNewUser(prop.getProperty("EmployeeFirstName"),
				prop.getProperty("EmployeeMiddleName"), prop.getProperty("EmployeeLastName"),
				prop.getProperty("EUsername"), prop.getProperty("EPassword"), prop.getProperty("EConfirmPassword"));
		Assert.assertTrue(Employee, "Success message does not display");
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}

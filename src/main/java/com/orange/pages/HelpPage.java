package com.orange.pages;

import java.util.Set;

import org.openqa.selenium.By;

import com.orange.base.BaseClass;
import com.orange.utility.utility;

public class HelpPage extends BaseClass {

	By Help = By.xpath("//button[@title='Help']");
	By AdminUSerGuide = By.xpath("//span[contains(text(),'Admin User Guide')]");

	
	public boolean verifyHelpPage() {
		String mailWindow = driver.getWindowHandle();
		utility.explicitwait(driver, Help);
		utility.clickElementwithjs(driver, Help);
		Set<String> windows = driver.getWindowHandles();
		for (String handle : windows) {
			if (!handle.equalsIgnoreCase(mailWindow))
			{
				driver.switchTo().window(handle);
			}
		}
		utility.explicitwait(driver, AdminUSerGuide);
		return utility.isElementDisplayed(driver, AdminUSerGuide);
	}
}

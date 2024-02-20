package com.orange.utility;

	import java.awt.AWTException;
	import java.awt.Robot;
	import java.awt.Toolkit;
	import java.awt.datatransfer.StringSelection;
	import java.awt.event.KeyEvent;
	import java.awt.image.BufferedImage;
	import java.io.File;
	import java.io.IOException;
	import java.time.Duration;
	import java.util.List;
	import javax.imageio.ImageIO;
	import javax.imageio.stream.ImageOutputStream;
	import org.apache.commons.io.FileUtils;
	import org.openqa.selenium.By;
	import org.openqa.selenium.JavascriptExecutor;
	import org.openqa.selenium.NoSuchElementException;
	import org.openqa.selenium.OutputType;
	import org.openqa.selenium.StaleElementReferenceException;
	import org.openqa.selenium.TakesScreenshot;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.interactions.Actions;
	import org.openqa.selenium.io.FileHandler;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;
	import org.testng.Assert;
	import com.constantsData.Constants;
import com.orange.base.BaseClass;

import ru.yandex.qatools.ashot.AShot;
	import ru.yandex.qatools.ashot.Screenshot;
	import ru.yandex.qatools.ashot.comparison.ImageDiff;
	import ru.yandex.qatools.ashot.comparison.ImageDiffer;



	public class utility {


		public static WebElement explicitwait(WebDriver driver, By locator) 
		{    
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.WAIT_TIME));
	        WebElement element = null;
	        try 
	        {
	        	
	            element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	        } 
	        catch (Exception e) 
	        {
	            element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	        }
	        return element;
	    }
		
		public static void implicitWait(WebDriver driver) 
		{    
			try 
	        {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Constants.IMPLICIT_WAIT_TIME));          
	        } 
	        catch (Exception e) 
	        {
	           
	        }
	        
	    }
		
		
		public static void threadWait(long miliSeconds) {
			try {
				Thread.sleep(miliSeconds);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				//Log.info(e);
			}
		}
		
		public static WebElement findElement(WebDriver driver, By locator) {
			WebElement element = null;
			try {
				element = utility.explicitwait(driver, locator);
			} catch (Exception e) {
				element = driver.findElement(locator);
				//Log.error("Getting exception in find element --> " + e.toString());
			}
			return element;
		}
		
		
		public static boolean isElementDisplayed(WebDriver driver, By locator) {
			try {
				return findElement(driver, locator).isDisplayed();
			} catch (NoSuchElementException e) {
				return false;
			}
		}
		
		
		public static String getTextofElement(WebDriver driver, By locator) {
			
			WebElement element = utility.findElement(driver, locator);
			return element.getText();
		}
		
		public static Boolean explicitwaitTextToBePresent(WebDriver driver, By locator, String value) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.WAIT_TIME));
			return wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, value));
		}
		
		
		public static WebElement explicitwaitclickable(WebDriver driver, By locator) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.WAIT_TIME));
			return wait.until(ExpectedConditions.elementToBeClickable(locator));
		}
		
		
		public static void sendkeys(WebDriver driver, By locator, String val) {
			WebElement element = explicitwait(driver, locator);
			element.clear();
			element.sendKeys(val);
		}
		
		public static void isselected(WebDriver driver, By locator)
		{
			WebElement element = explicitwait(driver, locator);
			element.isSelected();
		}
		
		
		public static void click(WebDriver driver, By locator) {
			WebElement element = explicitwaitclickable(driver, locator);
//			try {
//				element.click();
//			} catch (ElementClickInterceptedException | StaleElementReferenceException | NoSuchElementException e) {
//				//Log.error("Getting exception in Click --> " + e.toString());
//				//Utilities.clickElementwithjs(driver, locator);
//			}
		}

		public static void clickElementwithjs(WebDriver driver, By locator) {
			JavascriptExecutor jsexecutor = (JavascriptExecutor) driver;
			WebElement element = explicitwaitclickable(driver, locator);
			try {
				jsexecutor.executeScript("arguments[0].click();", element);
			} catch (Exception e) {
				//Log.error("Getting exception in click element with JS --> " + e.toString());
				jsexecutor.executeScript("arguments[0].click();", element);
			}
		}
		
		public static <WebElements> void findElements(WebDriver driver, List<WebElement> element, By locator)
		{
				element = null;		
				try
				{
					element = driver.findElements(locator);
				}
				catch (StaleElementReferenceException e) 
				{
					element = driver.findElements(locator);
				}
				//return element;
			}
		
		//Upload file

		public static void uploadFile(String filePath) throws AWTException
		{
			Robot rb = new Robot();
			rb.delay(1000);	
			StringSelection Sselection = new StringSelection(filePath);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(Sselection, null);
			
			rb.keyPress(KeyEvent.VK_CONTROL);
			rb.keyPress(KeyEvent.VK_V);
			rb.keyRelease(KeyEvent.VK_CONTROL);
			rb.keyRelease(KeyEvent.VK_V);
			
			rb.keyPress(KeyEvent.VK_ENTER);
			rb.keyRelease(KeyEvent.VK_ENTER);
		}
		
		public static void downloadImage(WebDriver driver, WebElement element, By locator, String fileFormat, String downloadedFilePath) throws IOException
		{
			//element = Utilities.findElement(driver, locator);	
			element = driver.findElement(locator);
			Screenshot imageScreenshot = new AShot().takeScreenshot(driver, element);
			ImageIO.write(imageScreenshot.getImage(),fileFormat,new File(downloadedFilePath));			
		}
		
		public static void compareImages(WebDriver driver, WebElement element, By locator, String expectedFilePath) throws IOException {
			
		BufferedImage expectedImage = ImageIO.read(new File(expectedFilePath));
		element = utility.findElement(driver, locator);		
		Screenshot imageScreenshot = new AShot().takeScreenshot(driver, element);
		BufferedImage actualImage = imageScreenshot.getImage();
		
		ImageDiffer imgdif = new ImageDiffer();
		ImageDiff imageDifferent =  imgdif.makeDiff(expectedImage, actualImage);
		
		if(imageDifferent.hasDiff()==true)
		{
			boolean image = driver.findElement(locator).isDisplayed();
			Assert.assertTrue(image);
		}
			
		}
		
		
		private ImageOutputStream File(String string) {
			// TODO Auto-generated method stub
			return null;
		}

		//JavaScriptExecutor Click 
		public static WebElement javascriptexecutorClick(WebElement element, WebDriver driver)
		{
			JavascriptExecutor jsexecutor = (JavascriptExecutor) driver;
			jsexecutor.executeScript("arguments[0].click();", element);
			return element;
		}
		
		//JavaScriptExecutor Scroll 
		public static WebElement javascriptExecutorScroll(WebElement element, WebDriver driver)
		{
			JavascriptExecutor jsexecutor = (JavascriptExecutor) driver;
			jsexecutor.executeScript("arguments[0].scrollIntoView();", element);
			return element;		
		}
		
		//JavaScriptExecutor DrawLine
		public static WebElement drawBorderJS(WebDriver driver, WebElement element)
		{
			JavascriptExecutor jsexecutor = (JavascriptExecutor) driver;
			jsexecutor.executeScript("arguments[0].style.border='3px solid red'", element);
	        return element;		
		}
		
		//Action Class Hover
		public static void actionclasshover(WebElement element, WebDriver driver)
		{	
			Actions action = new Actions(driver);
			action.moveToElement(element).build().perform();		
		}

		
		//Action Class Click 
		public static WebElement actionClassClick(WebElement element, WebDriver driver)
		{	
			Actions action = new Actions(driver);
			action.moveToElement(element).click().perform();
			return element;
		}
		
		
		public static String captureScreenshot(WebDriver driver, String testName) 
		{		
			File srsScreenshot = ((TakesScreenshot) BaseClass.driver).getScreenshotAs(OutputType.FILE);
			String destinationScreenshotPath = System.getProperty("user.dir") + "\\Screenshots\\" + testName + ".png";
			System.out.println(destinationScreenshotPath);
			try 
			{
				FileHandler.copy(srsScreenshot, new File(destinationScreenshotPath));
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			return destinationScreenshotPath;
		}
		
		//Screenshot of Webelement
		public static void screenshotOfElement(By locator, String srt, WebDriver driver) throws IOException
		{
			WebElement element = driver.findElement(locator);
			File src = element.getScreenshotAs(OutputType.FILE);
			File trg = new File((System.getProperty("user.dir") + "/Screenshots/" + srt));
			FileUtils.copyFile(src, trg);
		}

		

	}

	



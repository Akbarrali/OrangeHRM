package com.orange.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.constantsData.Constants;

public class BaseClass {
	
	public static Properties prop;
	public static Properties testdata;
	public static WebDriver driver;
	
	
	public static WebDriver initialize() throws Exception
	{
		try
		{
			prop = new Properties();
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + Constants.DATA_PROPERTIES_PATH);
			prop.load(fis);	
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		
		try
		{
			testdata = new Properties();
			FileInputStream testfis = new FileInputStream(System.getProperty("user.dir") + Constants.TESTDATA_PROPERTIES_PATH);
			testdata.load(testfis);	
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}

		
		String browsername = prop.getProperty("browser");
		
		if(browsername.equals("chrome"))
			{
			ChromeOptions options = new ChromeOptions();	
			options.addArguments("--disable-notifications");
			options.addArguments("--use-fake-ui-for-media-stream");
	        options.addArguments("--use-fake-device-for-media-stream");
	        options.addArguments("disable-geolocation");
			driver = new ChromeDriver();
			}
		
		else if(browsername.equals("firefox"))
			{		
			driver = new FirefoxDriver();
			}
		
		else if(browsername.equals("edge"))
			{		
			driver = new EdgeDriver();
			}
		else 
		   {			
			driver = new ChromeDriver();
		   }
		
		//driver.get(prop.getProperty("stagingurl"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Constants.IMPLICIT_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Constants.PAGE_LOAD_TIMEOUT));  
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		
		return driver;
		
	}
	
	
	
	
	

	
}

	
	
	
	


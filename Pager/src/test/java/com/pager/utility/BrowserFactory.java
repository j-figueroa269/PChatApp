package com.pager.utility;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * @author JFigu
 *   This a program to set properties of the browser drivers
 */

public class BrowserFactory {
	
	public static WebDriver startApplication(WebDriver driver, String browserName, String appURL) {
		
		if(browserName.equalsIgnoreCase("chrome")) {
			//WebDriverManager is a dependency plugin jar that updates driver after fetching browser version. 
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			
		}else if(browserName.equalsIgnoreCase("firefox")){
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			
		}else if(browserName.equalsIgnoreCase("edge")){
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			
		}else if(browserName.equalsIgnoreCase("ie")){
			WebDriverManager.iedriver().setup();
			System.setProperty("webdriver.ie.driver", "./Drivers/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			
		}else if(browserName.equalsIgnoreCase("opera")){
			WebDriverManager.operadriver().setup();
			driver = new OperaDriver();
			
		}else if(browserName.equalsIgnoreCase("safari")){
			System.setProperty("webdriver.safari.driver", "./Drivers/safaridriver.exe");
			driver = new SafariDriver();
			
		}else {
			System.out.println("Sorry, the browser name provided is not supported - add driver and update browserfactory if needed");
		}
		
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(appURL);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		
		return driver;
	}
	
	public static void quitBrowser(WebDriver driver) {
		driver.quit();
	}
	
	public static void closeBrowser(WebDriver driver) {
		driver.close();
	}

}

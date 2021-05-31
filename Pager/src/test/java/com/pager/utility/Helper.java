package com.pager.utility;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


/**
 * @author JFigu
 *Helper class contains methods that are independent of the test
 *Ex: capture screenshots, handle alerts, frames, multiple windows, synchronization issues, javascript executor
 */

public class Helper {
	
	//method to capture screenshots at run and returns path where screenshot is present
	public static String captureScreenshot(WebDriver driver) {
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		//local variable screenshotPath 
		String screenshotPath = System.getProperty("user.dir")+"/Screenshots/PagerChat1_"+getCurrentDateTime()+ ".png";
		try {
			//copying screenshot
			FileHandler.copy(src, new File(screenshotPath));		
		} catch (IOException e) {
			System.err.println("Unable to capture screenshot " + e.getMessage());
	    }
		 return screenshotPath;
	}
	
	//method to return current date and time
	public static String getCurrentDateTime() {
		//abstract parent class dateformat and create object of simpledateformat
		DateFormat customformat = new SimpleDateFormat("MM_dd_yyy_HH_mm_ss");
		
		//capture date using date class object
		Date currentDate = new Date();
		
		//format date into a date/time string and return it
		return customformat.format(currentDate);
	}
	
	public static void verifyTitle(WebDriver driver, String expTitle) {
		 String actTitle = driver.getTitle();
		 Assert.assertEquals(actTitle, expTitle);
	}
	
	public static void VerifyText(String actTxt, String expTxt) {
		 Assert.assertEquals(actTxt, expTxt);
	}
	
	public static void VerifyPartialText(String actTxt, String expTxt, String msg) {
		 Assert.assertTrue(actTxt.contains(expTxt), msg + " -Actual: "+actTxt + " -Excpected to contain:  " + expTxt);
	}
	
	public static List<WebElement> waitForAllVisibleElements(WebDriver driver, int waitTime, List<WebElement> eleList ) {
		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.visibilityOfAllElements(eleList));
		return eleList;
	}
	

}

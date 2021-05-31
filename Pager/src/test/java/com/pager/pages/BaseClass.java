package com.pager.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.pager.utility.BrowserFactory;
import com.pager.utility.ConfigDataProvider;
import com.pager.utility.ExcelDataProvider;
import com.pager.utility.Helper;


public class BaseClass {
	public WebDriver driver;
	public ExcelDataProvider excel;
	public ConfigDataProvider config;
	public ExtentReports report;
	public ExtentTest logger;
	protected ChatHomePage chathomepage;
	protected ChatPage chatpage;
	
	//Will execute as soon as Test suite starts
	@BeforeSuite
	public void setupSuite() {
		Reporter.log("Executing @BeforeSuite setupSuite() - Setting up reports and Test is getting ready ", true);

		//class objects
		excel = new ExcelDataProvider();
		config = new ConfigDataProvider();
		report = new ExtentReports(); 
		
		ExtentSparkReporter spark = new ExtentSparkReporter("./Reports/PagerChat_"+Helper.getCurrentDateTime()+".html");
		spark.config().setTheme(Theme.DARK);
		spark.config().setDocumentTitle("Chat Report");
		//Attaching report using ExtentReports obj - flushed in @AfterMethod
		report.attachReporter(spark);
		
		Reporter.log("Completed executing @BeforeSuite  setupSuite()- Setup Done- Test can be started ", true);
		
	}
	
	//Will execute before every class
	@BeforeClass
	public void launchChatApp() {
		Reporter.log("Executing @BeforeTest setup() - Trying to start Browser and getting application ready ", true);
		
		driver = BrowserFactory.startApplication(driver, config.getBrowser(), config.getQaURL());
		
		Reporter.log("Completed executing @BeforeTest setup() - Browser and Application is up and running ", true);
	}
	
	@BeforeMethod
	public void setupTest() {
		chathomepage = PageFactory.initElements(driver, ChatHomePage.class);
		chatpage = PageFactory.initElements(driver, ChatPage.class);
		
	}


	@AfterMethod
	public void tearDownMethod(ITestResult result) {
		Reporter.log("Executing @AfterMethod tearDownMethod() -Capturing screenshot- Test is about to end ", true);
	
	
		if(result.getStatus() == ITestResult.FAILURE) {
	 
			Helper.captureScreenshot(driver);
			Reporter.log("Screenshot captured - added to Screenshots folder", true);

			logger.fail("Test Failed ", MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
			Reporter.log("Test Failed - screenshot captured and added to report", true);
			
		}  else if(result.getStatus() == ITestResult.SUCCESS) {
	
			Helper.captureScreenshot(driver);
			Reporter.log("Screenshot captured - added to Screenshots folder",true);
	
			logger.pass("Test Passed ", MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
			Reporter.log("Test Passed - screenshot captured and added to report", true);
			
			
		}else if(result.getStatus() == ITestResult.SKIP) {

			Helper.captureScreenshot(driver);
			Reporter.log("Screenshot captured - added to Screenshots folder",true);

			logger.skip("Test Skipped ", MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
			Reporter.log("Test Skipped - screenshot captured and added to report", true);
		}
		
		//will continue generating reports and adding report onto a single report
		report.flush();
		
		Reporter.log("Test Completed >> Reports Generated ", true);
		
		Reporter.log("Completed executing @AfterMethod tearDownMethod() - Test Completed >> Reports Generated ", true);
		System.out.println("");
	}
	
	//Will execute after every class
	@AfterClass
	public void tearDown() {
		System.out.println("executing @AfterClass tearDown()");
		Reporter.log("Preparing to close Browser", true);
		
		BrowserFactory.closeBrowser(driver);
		
		Reporter.log("Completed executing @AfterClass tearDown() -Browser and driver instance closed", true);
	}
	
	@AfterSuite
	public void tearDownSuite() {
		System.out.println("executing @AfterSuite tearDownSuite()");
		Reporter.log("Preparing to quit driver instance", true);
		
		BrowserFactory.quitBrowser(driver);
		
		Reporter.log("Completed executing @AfterSuite tearDownSuite() -driver instance closed", true);
	}
		
	
}

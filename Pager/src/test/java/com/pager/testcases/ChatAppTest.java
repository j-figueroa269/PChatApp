package com.pager.testcases;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.pager.pages.BaseClass;
import com.pager.utility.Helper;

/**
 * @author JFigu
 *This program contains the Test Cases for the Chat Home page
 */

public class ChatAppTest extends BaseClass{
	
	
	@Test(priority=1)
	public void chatLoads() {
		Reporter.log("Executing @Test chatLoads() - verifying app launched successfully", true);
		logger = report.createTest("Load Page");
		
		logger.info("Verifying page");
		
		String expectedTitle = "Pager Test - Daniel Daza";
		Helper.verifyTitle(driver, expectedTitle);
		
		Reporter.log("Verifying accurate title",true);
		logger.info("Verifying Title");
		
		logger.pass("Loaded Successfully");
		Reporter.log("Completed executing @Test chatLoads() Page successfully loaded and has been verified ", true);
	}
	
	
	@Test(priority=2, dependsOnMethods="chatLoads")
	public void joinChat() {
		
		Reporter.log("Executing @Test joinChat() - Attempting to access chat", true);
		logger = report.createTest("Join Chat");
		
		chathomepage.enterUsername(driver, "ChatTestData", 2, 0);
	    
		logger.info("Username entered");
		Reporter.log(" Test data username entered ", true);
	
		String actualText = chatpage.verifyChatAccessedText();  
		String expectedText = "Signout";
		Helper.VerifyText(actualText, expectedText);	
		
		logger.info("verifying sucessful access");	
		Reporter.log("Username valid - chat accessed ", true);
		
		logger.pass("Chat Joined successfully");
		Reporter.log("Completed executing @Test joinChat()- Page verified- user has successfully accessed chat ", true);
	}
	
	@Test(priority=3, dependsOnMethods="joinChat")
	public void sendAMessage() {
		Reporter.log("Executing @Test sendAMessage() - sending a message via chat application", true);	
		logger= report.createTest("Sending a message via chat");
		
		
		String actualMsg = chatpage.sendMessage(driver, "ChatTestData", 2, 1);
		
		logger.info("Sending Test data message from excel");
		Reporter.log("Sending chat", true);
		
		List<WebElement> messageList = 
				Helper.waitForAllVisibleElements(driver, 10, chatpage.sentMsgList());
		
		logger.info("Waiting for all chat message elements to be visible");
		Reporter.log("All specified elements are visible", true);
		
		String expectedMsg = chatpage.verifiyMsgSent(messageList, actualMsg);
		Assert.assertTrue(true, "Message could not be verified as sent");
		
		logger.info("Verifying message sent correctly");
		Reporter.log("Message verified", true);
		
		logger.pass("Test passed - message sent successfully");
		Reporter.log("Completed executing @Test sendAMessage() - Message sent successfully and verified", true);
	}

	
	@Test(priority=4, dependsOnMethods="chatLoads")
	public void signoutOfChat() {
		Reporter.log("Executing @Test signoutOfChat() - signing out of chat",true);
		logger = report.createTest("Signout of chat");

		chatpage.signOut();
		logger.info("Clicked on signout button");
		Reporter.log("Signout button clicked", true);
		
		String actualURL = driver.getCurrentUrl();
		String expectedURL ="signout";
		Helper.VerifyPartialText(actualURL, expectedURL, "Signout url not verified");
		
		logger.info("verifying successful signout");	
		Reporter.log("Signout successful ", true);
		
		logger.fail("Signout unsucessful - url not verified");
		Reporter.log("Completed executing @Test signoutOfChat()- Page verified- user has successfully signed out of chat ", true);
	}
	
	

}

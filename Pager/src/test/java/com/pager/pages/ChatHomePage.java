package com.pager.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.pager.utility.ExcelDataProvider;

/**
 * @author JFigu
 *This class stores all the locators and methods for the join chat page
 */

public class ChatHomePage  {
	WebDriver driver;
	
	public ChatHomePage(WebDriver ldriver) {
		this.driver = ldriver;
	}

	@FindBy(how = How.CSS, using = "input.username-input")
	WebElement username;

	@FindBy(how = How.CSS, using = "button.btn")
	WebElement nextButton;

	@FindBy(how = How.XPATH, using = "//h1[text()='Join chat']")
	WebElement boxTitleText;

	@FindBy(how = How.CSS, using = "div.title")
	WebElement inputTitleText;

	/*public void enterUsername(String uname) {
		//this.driver = ldriver;
		username.sendKeys(uname);
		nextButton.click();
	}*/
	public void enterUsername(WebDriver ldriver, String sheetName, int row, int col) {
		this.driver = ldriver;
		ExcelDataProvider excel = new ExcelDataProvider();
		String uname = excel.getStringData(sheetName, row, col);
		if (uname != null) {
			username.sendKeys(uname);
			nextButton.click();
		} else {
			System.out.println("NullPointerException thrown!");
		}
	}

	public String verifyBoxTitleText() {
		String actualText = boxTitleText.getText();
		return actualText;
	}

	public String verifyInputTitleText() {
		String actualText = boxTitleText.getText();
		return actualText;
	}

	
	
	
}

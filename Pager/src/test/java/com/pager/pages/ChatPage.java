package com.pager.pages;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.pager.utility.ExcelDataProvider;

/**
 * @author JFigu This program contains the locators and methods for the Chat
 *         Home page
 */

public class ChatPage {
	WebDriver driver;

	public ChatPage(WebDriver ldriver) {
		this.driver = ldriver;
	}

	@FindBy(css = "div.messages > div")
	WebElement startConvoTxt;

	@FindBy(css = "input[placeholder='Message']")
	WebElement msgInput;

	@FindBy(css = "button.btn-send")
	WebElement sendButton;

	@FindBy(css = "button.btn-logout")
	WebElement signoutButton;

	@FindBy(css = "img.avatar")
	List<WebElement> userAvatarName;

	@FindBy(css = "div.icon")
	List<WebElement> userConnectedIcon;

	@FindBy(css = "div.title")
	List<WebElement> nameOfUser;

	@FindBy(css = "div.message-line")
	List<WebElement> msgSentList;

	@FindBy(css = "span.detail")
	List<WebElement> timeMsgSent;

	public String verifyChatAccessedText() {
		return signoutButton.getText();
	}

	public void signOut() {
		signoutButton.click();
	}

	public List<WebElement> avatarNameList() {
		return userAvatarName;
	}

	public List<WebElement> statusIndicatorList() {
		return userConnectedIcon;
	}

	public List<WebElement> usersInChatList () {
		return nameOfUser;
	}

	public List<WebElement> sentMsgList() {
		return msgSentList;
	}

	public List<WebElement> timeStampList() {
		return timeMsgSent;
	}

	public String sendMessage(WebDriver ldriver, String sheetName, int row, int col) {
		this.driver = ldriver;
		ExcelDataProvider excel = new ExcelDataProvider();
		String message = excel.getStringData(sheetName, row, col);
		if (message != null) {
			msgInput.sendKeys(message);
			sendButton.click();
		} else {

			System.out.println("NullPointerException thrown! ");
		}
		return message;
	}

	public String verifiyMsgSent(List<WebElement> elementsList, String ex) {
		Iterator<WebElement> itr = elementsList.iterator();
		String elementText = null;
		
		while (itr.hasNext()) {
			elementText = itr.next().getText();
			if(elementText.equalsIgnoreCase(ex)) {
				return elementText;
			}
			break;
			
		}
		return elementText;

	}

}

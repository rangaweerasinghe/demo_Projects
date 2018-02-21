package com.xero.qa.module_a.page_objects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	WebDriver driver;
	@FindBy(linkText = "Connect your bank accounts")
	WebElement addBankLink;
	
	@FindBy(className = "username")
	WebElement userName;
	
	@FindBy(className = "xn-h-profile-card-navigation")
	WebElement logoutPopupList;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	public void clickOn_addNewAccount() {
		addBankLink.click();
	}

	public String getPageTitle() {
		return driver.getTitle();
	}
	
	public void clickOnUserName() {
		userName.click();
	}	
	public void LogOutXero(){
		clickOnUserName();
		List<WebElement> rows = logoutPopupList.findElements(By.tagName("li"));
		rows.get(2).click();
	}
}

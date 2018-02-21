package com.xero.qa.module_a.page_objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver driver;

	@FindBy(id = "email")
	WebElement email;

	@FindBy(id = "password")
	WebElement password;

	@FindBy(id = "submitButton")
	WebElement login;
	

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public void setUserNameEmail(String strEmail) {
		email.sendKeys(strEmail);
	}

	public void setPassword(String strPassword) {
		password.sendKeys(strPassword);
	}

	public void clickLogin() {
		login.click();
	}

	public String getPageTitle() {
		return driver.getTitle();
	}
	
    public void loginToXero(String strEmail,String strPassword){

        this.setUserNameEmail(strEmail);
        this.setPassword(strPassword);
        this.clickLogin();

                

    }

}
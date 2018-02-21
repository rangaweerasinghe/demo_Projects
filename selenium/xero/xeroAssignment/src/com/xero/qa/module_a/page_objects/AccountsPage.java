package com.xero.qa.module_a.page_objects;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountsPage {
	WebDriver driver;

	@FindBy(id = "xui-searchfield-1018-inputEl")
	WebElement serchText;

	@FindBy(id = "dataview-1021")
	WebElement bankList;

	@FindBy(id = "accountname-1037-inputEl")
	WebElement accountName;

	@FindBy(id = "accounttype-1039-inputEl")
	WebElement accountType;

	@FindBy(id = "common-button-submit-1015-btnInnerEl")
	WebElement continueButton;

	@FindBy(id = "boundlist-1076-listEl")
	WebElement accTypeValue;
	
	@FindBy(id = "accountnumber-1068-inputEl")
	WebElement accountNumber;
	
	@FindBy(id = "accountnumber-1063-inputEl")
	WebElement creditCardNumber;
	
	@FindBy(id = "ext-gen33")
	WebElement messgeText;
	
	@FindBy (css = "li[data-recordid=6]")
	private WebElement optionANZ;
	
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String getPageTitle() {
		return driver.getTitle();
	}

	public void setSerchText(String searchText) {
		serchText.sendKeys(searchText);
	}

	public void selectBankFromList(String bank) {
		List<WebElement> rows = bankList.findElements(By.tagName("li"));
		java.util.Iterator<WebElement> i = rows.iterator();
		while (i.hasNext()) {
			WebElement row = i.next();
			if (row.getText().equals(bank)) {
				row.click();
				break;
			}
		}
	}

	public void setAccName(String strAccName) {
		accountName.sendKeys(strAccName);
	}

	public int selectAccuntType() {
		accountType.click();
		List<WebElement> rows = accTypeValue.findElements(By.tagName("li"));
		Random rand = new Random();
		int select = rand.nextInt(rows.size());
		rows.get(select).click();
		return select;
	}
	
	public void setAccNumber(String strAccNumber) {
		accountNumber.sendKeys(strAccNumber);
	}
	
	public void setCreditCardNumber(String strCritCadNumber) {
		creditCardNumber.sendKeys(strCritCadNumber);
	}
	
	public void clickContinueButton() {
		continueButton.click();
	}
	
	public String getMessage(){
		return messgeText.getText();
	}
}

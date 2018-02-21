package com.xero.qa.module_a.tests;

import org.testng.annotations.Test;

import com.xero.qa.module_a.page_objects.AccountsPage;
import com.xero.qa.module_a.page_objects.HomePage;
import com.xero.qa.module_a.page_objects.LoginPage;
import com.xero.qa.utils.ReadFromExcel;

import java.io.File;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

public class BankAccounts {
	LoginPage objLoginPage;
	HomePage objHomePage;
	AccountsPage objAccountsPage;
	WebDriver driver;
	
	  @DataProvider(name = "xeroModule_a_data")
	  public static Object[][] dataProvider() {
		  ReadFromExcel excelReader = new ReadFromExcel("data"+File.separator+"DataSheet.xlsx", "xeroModule_a");
			
			Object[][] sheetData = excelReader.getSheetData();

	        return sheetData;

	  }

	@BeforeTest
	public void beforeTest()throws Exception {
		System.setProperty("webdriver.chrome.driver","C:\\Program Files (x86)\\Google\\Chrome\\Application\\Chrome.exe");//TODO
		System.setProperty("webdriver.chrome.driver", "lib\\chromeDriver\\chromedriver.exe");
		
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://go.xero.com");
		driver.manage().window().maximize();
	}

	@Test(priority = 0,dataProvider="xeroModule_a_data")
	public void addBanakAccount(String userName,String password,String accountName,String accNumber,String criditCrdNumber)throws Exception {
		objLoginPage = new LoginPage(driver);
		objHomePage = new HomePage(driver);
		objAccountsPage = new AccountsPage(driver);

		String loginPageTitle = objLoginPage.getPageTitle();
		Assert.assertTrue(loginPageTitle.contains("Login | Xero Accounting Software"));
		objLoginPage.loginToXero(userName, password);

		
		String loginHomeTitle = objHomePage.getPageTitle();
		Assert.assertTrue(loginHomeTitle.contains("Xero | Dashboard"));
		objHomePage.clickOn_addNewAccount();

		
		//String loginAccountTitle = objAccountsPage.getPageTitle();
		// Assert.assertTrue(loginAccountTitle.contains("Xero | Find your bank"));
		
		// TODO Improvement: If search "ANZ (NZ)" and pick it from list its more stable and correct one
		
		objAccountsPage.selectBankFromList("ANZ (NZ)");
		objAccountsPage.setAccName(accountName);
		int accType = objAccountsPage.selectAccuntType();
		
		// Check whether credit card account selected
		if (accType == 3) {
			objAccountsPage.setCreditCardNumber(criditCrdNumber);
		} else {
			objAccountsPage.setAccNumber(accNumber);
		}
		objAccountsPage.clickContinueButton();
		String message = objAccountsPage.getMessage();
		Assert.assertTrue(message.contains("added.") && message.contains(accountName));

		objHomePage.LogOutXero();
		driver.close();

	}
}

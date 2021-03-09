package com.anz.SampleTestcases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.anz.pageobject.Landingpage;
import com.anz.pageobject.LoginPage;
import com.anz.pageobject.Registration;
import com.anz.util.ConfigFileReader;
import com.anz.util.ReusableMethods;
import com.anz.util.Wait;
import com.anz.util.WebDriverManager;


public class AddingCart {
	ConfigFileReader configFileReader = null;
	WebDriverManager webDriverManager = null;
	WebDriver driver = null;
	LoginPage loginPage = null;
	Registration registration = null;
	Landingpage landing = null;
	ReusableMethods reusableMethods = null;

	@BeforeClass()
	public void initWebDriver() throws IOException{
		webDriverManager = new WebDriverManager();
		configFileReader = new ConfigFileReader();
		
			driver = webDriverManager.getDriver();
		loginPage = new LoginPage(driver);
		registration = new Registration(driver);
		landing = new Landingpage(driver);
		loginPage.naviageURL();
		reusableMethods = new ReusableMethods();
		
		
	}
	@DataProvider(name = "data-provider03")
	public Object[][] dataProviderMethod03() {
		return reusableMethods.getData("RegisterPage");

	}

	@Test(priority = 1, dataProvider = "data-provider03")
	public void loginTest(String testCase) {
		loginPage.dologin(testCase);
		Wait.untilPageLoadComplete(driver);
		
		

	}

	@Test(priority = 2, dataProvider = "data-provider03")
	public void addingCartTest(String testCase) {
		
		landing.addCart(testCase);
		Wait.untilPageLoadComplete(driver);
		
	}

	@Test(priority = 3)
	public void checkCartDetails() {
		landing.checkcartdetails();
	}

	@AfterClass
	public void logout() {
		landing.dologout();
		Wait.untilPageLoadComplete(driver);
		driver.quit();
	}

}

package com.anz.SampleTestcases;




import org.openqa.selenium.WebDriver;
import org.testng.Assert;
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

public class register {

	ConfigFileReader configFileReader = null;
	WebDriverManager webDriverManager = null;
	WebDriver driver = null;
	LoginPage loginPage = null;
	Registration registration = null;
	Landingpage landing = null;
	ReusableMethods ReusableMethods = null;

	@BeforeClass()
	public void initWebDriver() throws Exception {
		webDriverManager = new WebDriverManager();
		configFileReader = new ConfigFileReader();
		driver = webDriverManager.getDriver();
		loginPage = new LoginPage(driver);
		registration = new Registration(driver);
		landing = new Landingpage(driver);
		ReusableMethods = new ReusableMethods();

	}
	@DataProvider(name = "data-provider03")
	public Object[][] dataProviderMethod03() {
		Object[][] obj=ReusableMethods.getData("RegisterPage");
		return obj;

	}
	@Test(priority = 1, dataProvider = "data-provider03")
	public void registratioTest(String testCase) {
		
		loginPage.gotoresistration(testCase);
        registration.doRegistartion(testCase);
		Wait.untilPageLoadComplete(driver);
		

	}

	@Test(priority = 2,dataProvider = "data-provider03")
	public void usernameCheckTest(String testCase) {
		boolean flag = landing.checkName(testCase);
		Assert.assertEquals(flag, true);
	}

	@AfterClass
	public void logout() {
		landing.dologout();
		Wait.untilPageLoadComplete(driver);
		driver.quit();
	}

}
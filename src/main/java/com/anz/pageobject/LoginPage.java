package com.anz.pageobject;

import java.util.Map;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.PageFactory;

import com.anz.util.ConfigFileReader;
import com.anz.util.Log;
import com.anz.util.Wait;

import com.anz.util.ReusableMethods;

public class LoginPage {

	WebDriver driver;

	ConfigFileReader configFileReader = null;

	ReusableMethods ReusableMethods = null;

	public LoginPage(WebDriver driver) {

		this.driver = driver;

		configFileReader = new ConfigFileReader();

		ReusableMethods = new ReusableMethods();

		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a")

	private WebElement Signin_link;

	@FindBy(name = "email_create")

	private WebElement email;

	@FindBy(xpath = "//*[@id=\"SubmitCreate\"]")

	private WebElement submit_button;
	
	@FindBy(name = "email")

	private WebElement loginemail;
	
	@FindBy(name = "passwd")

	private WebElement loginpsswrd;

	@FindBy(name = "SubmitLogin")

	private WebElement loginsbtn;
	
	public void naviageURL() {

		driver.get(configFileReader.getApplicationUrl());

	}

	public void gotoresistration(String condition) {
 Log.debug("enter");
		naviageURL();
		Log.debug("exit");
		Map<String, String> resultHash = ReusableMethods.getInputData("RegisterPage", condition);
		Wait.untilPageLoadComplete(driver);
		

		ReusableMethods.click(Signin_link, "Signin_link", driver);

		Wait.untilPageLoadComplete(driver);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) { 
			e.printStackTrace();
		}

		ReusableMethods.enterTextByClearValue(email, resultHash.get("email"), "email", driver);

		ReusableMethods.click(submit_button, "submit_button", driver);

		Wait.untilPageLoadComplete(driver);

	}
	
	public void dologin(String condition) {
		naviageURL();
		Map<String, String> resultHash = ReusableMethods.getInputData("RegisterPage", condition);
		Wait.untilPageLoadComplete(driver);
		Wait.untilPageLoadComplete(driver);
		ReusableMethods.click(Signin_link, "Signin_link", driver);
		Wait.untilPageLoadComplete(driver);
		ReusableMethods.enterTextByClearValue(loginemail, resultHash.get("email"), "email", driver);
		ReusableMethods.enterTextByClearValue(loginpsswrd, resultHash.get("Password"), "email", driver);
		ReusableMethods.click(loginsbtn, "loginsbtn", driver);
	}
	

}
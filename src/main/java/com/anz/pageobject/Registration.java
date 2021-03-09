package com.anz.pageobject;

import java.util.Map;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.PageFactory;

import com.anz.util.ConfigFileReader;

import com.anz.util.ReusableMethods;
import com.anz.util.Wait;

public class Registration {

	WebDriver driver;

	ConfigFileReader configFileReader = null;

	ReusableMethods ReusableMethods = null;

	public Registration(WebDriver driver) {

		this.driver = driver;

		configFileReader = new ConfigFileReader();

		ReusableMethods = new ReusableMethods();

		PageFactory.initElements(driver, this);

	}

	@FindBy(id = "id_gender1")

	private WebElement gender;

	@FindBy(name = "customer_firstname")

	private WebElement customer_firstname;

	@FindBy(name = "customer_lastname")

	private WebElement customer_lastname;

	@FindBy(name = "passwd")

	private WebElement passwd;

	@FindBy(name = "days")

	private WebElement days;

	@FindBy(name = "months")

	private WebElement months;

	@FindBy(name = "years")

	private WebElement years;

	@FindBy(name = "address1")

	private WebElement address1;

	@FindBy(name = "city")

	private WebElement city;

	@FindBy(name = "id_state")

	private WebElement id_state;

	@FindBy(name = "postcode")

	private WebElement postcode;

	@FindBy(name = "id_country")

	private WebElement id_country;

	@FindBy(name = "phone_mobile")

	private WebElement phone_mobile;

	@FindBy(name = "alias")

	private WebElement alias;

	@FindBy(name = "submitAccount")

	private WebElement submitAccount;

	public void doRegistartion(String condition) {
		Map<String, String> resultHash = ReusableMethods.getInputData("RegisterPage", condition);
		
		
		Wait.waitUnitWebElementVisible(driver, gender);
		ReusableMethods.click(gender, "gender", driver);

		
		 ReusableMethods.enterTextByClearValue(customer_firstname, resultHash.get("FristName"),
		  "customer_firstname", driver);
		 
		 ReusableMethods.enterTextByClearValue(customer_lastname, resultHash.get("LastName"),
		 "customer_lastname", driver);
		 
		 ReusableMethods.enterTextByClearValue(passwd, resultHash.get("Password"), "passwd", driver);
		 
		 ReusableMethods.selectUsingValue(days, resultHash.get("days"));
		 
		 ReusableMethods.selectUsingValue(months, resultHash.get("months"));
		 
		 ReusableMethods.selectUsingValue(years, resultHash.get("Year"));
		 
		 ReusableMethods.enterTextByClearValue(address1, resultHash.get("Address"), "address1",
		 driver);
		 ReusableMethods.enterTextByClearValue(city, resultHash.get("City"), "passwd", driver);
		 ReusableMethods.selectUsingValue(id_state, resultHash.get("State"));
		 
		 ReusableMethods.enterTextByClearValue(postcode, resultHash.get("postcode"), "postcode", driver);
		 
		 ReusableMethods.enterTextByClearValue(phone_mobile, resultHash.get("phoneNumber"), "phone_mobile",
		 driver);
		 
		 ReusableMethods.enterTextByClearValue(alias, resultHash.get("Alas"), "alias", driver);
		 
		 ReusableMethods.click(submitAccount, "submitAccount", driver);
		 

	}

}
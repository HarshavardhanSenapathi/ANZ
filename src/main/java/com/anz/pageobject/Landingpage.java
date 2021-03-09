package com.anz.pageobject;

import java.util.Map;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.PageFactory;

import com.anz.util.ConfigFileReader;
import com.anz.util.Log;
import com.anz.util.ReusableMethods;
import com.anz.util.Wait;
import org.testng.Assert;

public class Landingpage {

	WebDriver driver;

	ConfigFileReader configFileReader = null;

	ReusableMethods ReusableMethods = null;
	LoginPage loginPage = null;
	

	public Landingpage(WebDriver driver) {

		this.driver = driver;
		configFileReader = new ConfigFileReader();
		ReusableMethods = new ReusableMethods();
		PageFactory.initElements(driver, this);
		loginPage = new LoginPage(driver);

	}
	public static  String ActualText = "";
	public static  String text="";

	@FindBy(xpath = "//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a/span")

	private WebElement UserName;

	@FindBy(xpath = "//*[@id=\"header\"]/div[2]/div/div/nav/div[2]/a")

	private WebElement Signout;

	@FindBy(name = "submit_search")

	private WebElement TshirtLink;

	@FindBy(xpath = "//*[@id=\"center_column\"]/ul/li/div/div[2]/h5/a")

	private WebElement Linktext;

	@FindBy(xpath = "//*[@id=\"center_column\"]/ul/li")

	private WebElement Product;

	@FindBy(xpath = "/html/body/div/div[2]/div/div[3]/div/div/div/div[4]/form/div/div[3]/div[1]/p/button")

	private WebElement Cartbtn;

	@FindBy(xpath = "//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a")

	private WebElement Checkoutbtn;

	@FindBy(xpath = "//*[@id=\"center_column\"]/p[2]/a[1]")

	private WebElement Proceedbtn1;

	@FindBy(xpath = "//*[@id=\"center_column\"]/form/p/button")

	private WebElement Proceedaddbtn;

	@FindBy(id = "uniform-cgv")

	private WebElement Proceedaddchck;

	@FindBy(xpath = "//*[@id=\"form\"]/p/button")

	private WebElement Proceedshiping;

	@FindBy(xpath = "/html/body/div/div[2]/div/div[3]/div/div/div[2]/table/tbody/tr/td[2]/p/a")

	private WebElement PaymentText;

	@FindBy(xpath = "/html/body/div/div[2]/div/div[3]/div/ul/li[5]/span")

	private WebElement PaymentspanText;
	
	
	
	@FindBy(name = "search_query")

	private WebElement QueryTab;

	public boolean checkName(String condition) {

		String username = ReusableMethods.getElementValueByText(UserName, "UserName", driver);
		Map<String, String> resultHash = ReusableMethods.getInputData("RegisterPage", condition);
		String Actualvalue = resultHash.get("FristName") + " " + resultHash.get("LastName");
		Log.debug(username);
		Log.debug(Actualvalue);

		if (username.equals(Actualvalue)) {

			return true;

		} else {

			return false;

		}

	}

	public void addCart(String condition) {
		
		Wait.waitUnitWebElementVisible(driver, TshirtLink);
		Map<String, String> resultHash = ReusableMethods.getInputData("RegisterPage", condition);
		
		ReusableMethods.enterTextByClearValue(QueryTab,resultHash.get("Product") , "QueryTab", driver);
		
		ReusableMethods.click(TshirtLink, "TshirtLink", driver);
		Wait.untilPageLoadComplete(driver);
		 text = Linktext.getText();
		Log.debug(text);
		Wait.waitUnitWebElementVisible(driver, Product);
		
		
		
		ReusableMethods.click(Product, "Product", driver);
		Wait.untilPageLoadComplete(driver);
		ReusableMethods.click(Cartbtn, "Cartbtn", driver);

		Wait.waitUnitWebElementVisible(driver, Checkoutbtn);
		ReusableMethods.click(Checkoutbtn, "Checkoutbtn", driver);
		Wait.untilPageLoadComplete(driver);
		Wait.waitUnitWebElementVisible(driver, Proceedbtn1);
		ReusableMethods.click(Proceedbtn1, "Proceedbtn1", driver);
		Wait.untilPageLoadComplete(driver);
		Wait.waitUnitWebElementVisible(driver, Proceedaddbtn);
		ReusableMethods.click(Proceedaddbtn, "Proceedaddbtn", driver);
		Wait.untilPageLoadComplete(driver);

		ReusableMethods.click(Proceedaddchck, "Proceedaddchck", driver);
		Wait.untilPageLoadComplete(driver);
		Wait.waitUnitWebElementVisible(driver, Proceedshiping);
		ReusableMethods.click(Proceedshiping, "Proceedshiping", driver);
		
	}
	
	public void checkcartdetails() {
		 ActualText = PaymentText.getText();
			if (text.equals(ActualText)) {
				Assert.assertEquals(true, true);
			} else {
				Assert.assertEquals(false, true);
			}

			if ((PaymentspanText.getText()).equals("Payment")) {
				Log.debug("Payment is visiible");
			}

		}
	
	public void dologout() {
		Wait.waitUnitWebElementVisible(driver, Signout);
		ReusableMethods.click(Signout, "Signout", driver);

	}

}
package com.anz.util;



import java.util.Map;





import org.apache.poi.xssf.usermodel.XSSFSheet;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.Select;


public class ReusableMethods {

	private XSSFSheet excelWSheet = null;
	private ExcelReader excelReader = new ExcelReader();
	

	
	

	public void enterTextByClearValue(WebElement ele, String text, String eleName, WebDriver driver) {
		try {
			Wait.waitUnitWebElementVisible(driver, ele);
			if (ele.isDisplayed()) {
				ele.clear();
				if (text != null) {
					ele.sendKeys(text);
				}
			}
		} catch (Exception e) {
			Log.debug(eleName + " Is not present" + ": EXception Error : " + e);
		}
	}

	public void click(WebElement ele, String eleName, WebDriver driver) {
		try {
			Wait.waitUnitWebElementVisible(driver, ele);
			if (ele.isDisplayed()) {
				ele.click();
				Log.debug(eleName + " is  present");
			}
		} catch (Exception e) {
			Log.debug(eleName + " is Not present" + ": EXCEption Error : " + e);
		}
	}

	public String getElementValueByText(WebElement webelement, String webelementName, WebDriver driver) {

		String elementValue = null;
		try {
			Wait.waitUnitWebElementVisible(driver, webelement);
			elementValue = webelement.getText().trim();
		} catch (Exception e) {
			Log.debug(webelementName + "Not PREsent" + ": ExceptiOn Error : " + e);
		}
		return elementValue;
	}

	public void selectUsingValue(WebElement element, String value) {
		Select select = new Select(element);
		Log.debug("selectUsingValue and value is: " + value);
		select.selectByValue(value);
	}

	public Map<String, String> getInputData(String InputDataSheetName, String condition) {
		excelWSheet = excelReader.getSheet(InputDataSheetName);
		return excelReader.getHashValueFromExcel(excelWSheet, condition.trim());
	}
	
	public Object[][] getData(String InputDataSheetName) {
		excelWSheet = excelReader.getSheet(InputDataSheetName);
		return excelReader.gettestcaseID(excelWSheet);
	}

	
}
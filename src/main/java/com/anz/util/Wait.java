package com.anz.util;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Wait {

	public static void untilPageLoadComplete(WebDriver driver) {
		untilPageLoadComplete(driver, FileReaderManager.getInstance().getConfigReader().getImplicitlyWait());
	}

	public static void untilPageLoadComplete(final WebDriver driver, Long timeoutInSeconds) {
		until(driver, new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver d) {
				Boolean isPageLoaded = (Boolean) "complete"
						.equals(((JavascriptExecutor) driver).executeScript("return document.readyState"));
				if (!isPageLoaded)
					Log.debug("Document is loading");
				return isPageLoaded;
			}
		}, timeoutInSeconds);
	}

	public static void until(WebDriver driver, Function<WebDriver, Boolean> waitCondition) {
		until(driver, waitCondition, FileReaderManager.getInstance().getConfigReader().getImplicitlyWait());
	}

	public static void waitUnitWebElementVisible(WebDriver waitdriver, WebElement webElement) {
		WebDriverWait wait = new WebDriverWait(waitdriver,
				FileReaderManager.getInstance().getConfigReader().getWebElemntWaitTime());
		wait.until(ExpectedConditions.visibilityOf(webElement));
	}

	private static void until(WebDriver driver, Function<WebDriver, Boolean> waitCondition, Long timeoutInSeconds) {
		WebDriverWait webDriverWait = new WebDriverWait(driver, timeoutInSeconds);
		webDriverWait.withTimeout(timeoutInSeconds, TimeUnit.SECONDS);
		try {
			webDriverWait.until(waitCondition);
		} catch (Exception e) {
			Log.debug("until, Exception Error message : " + e);
		}
	}

}
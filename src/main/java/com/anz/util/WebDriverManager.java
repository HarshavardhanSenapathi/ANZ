package com.anz.util;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class WebDriverManager {
	private ConfigFileReader configFileReader = new ConfigFileReader();
	private WebDriver driver;

	public WebDriver getDriver() throws IOException {
		if (driver == null) {
			driver = createDriver();
			Log.debug("As Drver is Null Creatig driver" + driver);
		}
		return driver;
	}

	private WebDriver createDriver() throws IOException {
		driver = createLocalDriver();
		return driver;
	}

	private WebDriver createLocalDriver() throws IOException {

		Log.debug("Initializing Browser.....");
		String browser = configFileReader.getBrowser();
		Log.debug(browser);
		if ("CHROME".equalsIgnoreCase(browser.toUpperCase())) {
			System.setProperty("webdriver.chrome.driver", configFileReader.getChromeDriverPath());
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			Log.debug("DRIVER:" + driver);

		} else if ("IE".equalsIgnoreCase(browser.toUpperCase())) {
			Runtime.getRuntime().exec("taskkill /F /IM IEDriver.exe");
			Runtime.getRuntime().exec("taskkill /F /IM IExplore.exe");
			Runtime.getRuntime().exec("RunDll32.exe InetCpl.cpl,ClearMyTracksByProcess 255");
			System.setProperty("webdriver.ie.driver", configFileReader.getIEDriverPath());
			DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
			ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			ieCapabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
			driver = new InternetExplorerDriver(ieCapabilities);
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

			driver.manage().window().maximize();
		} else if ("FIREFOX".equalsIgnoreCase(browser.toUpperCase())) {
			System.setProperty("webdriver.gecko.driver", configFileReader.getFirefoxDriverPath());
			driver = new FirefoxDriver();
			driver.manage().window().maximize();

		}
		return driver;
	}
}
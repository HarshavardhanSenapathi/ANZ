package com.anz.util;

import java.io.BufferedReader;



import java.io.FileReader;

import java.io.IOException;

import java.util.Properties;

public class ConfigFileReader {

	private Properties properties = new Properties();

	private static final String propertyFilePath = ".\\src\\main resources\\Configs\\Configeration.properties";

	public ConfigFileReader() {

		BufferedReader reader;

		try {

			reader = new BufferedReader(new FileReader(propertyFilePath));

			properties = new Properties();

			properties.load(reader);

			reader.close();

		} catch (IOException e) {

			Log.debug(

					"Configuration.properties not found at :" + propertyFilePath + " : Exception Error message : " + e);

		}

	}

	public String getChromeDriverPath() {

		String driverPath = properties.getProperty("CHROMEDRIVERPATH");

		if (driverPath != null)

			return driverPath;

		else

			Log.debug("driverPath not specified in the Configuration.properties file.");

		return driverPath;

	}

	public String getIEDriverPath() {

		String driverPath = properties.getProperty("IEDRIVERPATH");

		if (driverPath != null)

			return driverPath;

		else

			Log.debug("driverPath not specified in the Configuration.properties file.");

		return driverPath;

		
		
	}
	
	public String getFirefoxDriverPath() {

		String driverPath = properties.getProperty("FireFoxDRIVERPATH");

		if (driverPath != null)

			return driverPath;

		else

			Log.debug("driverPath not specified in the Configuration.properties file.");

		return driverPath;

	}

	public long getImplicitlyWait() {

		String implicitlyWait = properties.getProperty("IMPLICITLYWAIT");

		if (implicitlyWait != null)

			return Long.parseLong(implicitlyWait);

		else

			Log.debug("implicitlyWait not specified in the Configuration.properties file.");

		return 0;

	}

	public long getWebElemntWaitTime() {

		String ElemntWaitTime = properties.getProperty("WAITFORELEMENT");

		if (ElemntWaitTime != null)

			return Long.parseLong(ElemntWaitTime);

		else

			Log.debug("implicitlyWait not specified in the Configuration.properties file.");

		return 0;

	}

	public String getApplicationUrl() {

		String url = properties.getProperty("URL");

		if (url != null)

			return url;

		else

			Log.debug("url not specified in the Configuration.properties file.");

		return url;

	}

	public String getBrowser() {

		String browser = properties.getProperty("BROWSER").trim();

		if (browser != null)

			return browser;

		else

			Log.debug("url not specified in the Configuration.properties file.");

		return browser;

	}

	public String getValue(String field) {

		String feedFilePath = properties.getProperty(field);
		if (feedFilePath != null)

			return feedFilePath;

		else

			Log.debug("field is not specified in the Configuration.properties file.");

		return feedFilePath;

	}

}
package com.anz.util;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;

import org.testng.xml.XmlSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentReporterNG implements IReporter {

	private ExtentReports extent;
	

	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
		Log.debug("Started ");
	
		Date date = new Date();
	      //This method returns the time in millis
	    long timeMilli = date.getTime();		
	    String fileName = "Result_"+ String.valueOf(timeMilli);
		String home = System.getProperty("user.home");
		String path = home + "\\Desktop\\Result\\" + fileName;
		
		extent = new ExtentReports(outputDirectory + File.separator + "Report.html", true);
		extent = new ExtentReports(path + File.separator + "Report.html", true);

		for (ISuite suite : suites) {
			Map<String, ISuiteResult> result = suite.getResults();
			for (ISuiteResult r : result.values()) {
				ITestContext context = r.getTestContext();
				try {
					buildTestNodes(context.getPassedTests(), LogStatus.PASS);
				} catch (Exception e) {
					Log.debug("IOException Error message : " + e);
				}
				try {
					buildTestNodes(context.getFailedTests(), LogStatus.FAIL);
				} catch (Exception e) {
					Log.debug("IOException Error message : " + e);
				}
				try {
					buildTestNodes(context.getSkippedTests(), LogStatus.SKIP);
				} catch (Exception e) {
					Log.debug("ExtentReport Exception Error message : " + e);
				}
			}
		}
		extent.flush();
		extent.close();
	}

	private void buildTestNodes(IResultMap tests, LogStatus status)  {
		ExtentTest test;

		if (tests.size() > 0) {
			for (ITestResult result : tests.getAllResults()) {
				test = extent.startTest(result.getMethod().getMethodName());

				test.setStartedTime(getTime(result.getStartMillis()));
				test.setEndedTime(getTime(result.getEndMillis()));

				for (String group : result.getMethod().getGroups())
					test.assignCategory(group);

				if (result.getThrowable() != null) {
					test.log(status, result.getThrowable());
					test.log(status, "Test " + status.toString().toLowerCase() + "ed");
					String locatorType = result.getTestClass().getName();
					String testClassName = locatorType.split("\\.")[1];
					File destinationFile = new File(System.getProperty("user.dir") + "\\target\\" + testClassName + "\\"
							+ result.getMethod().getMethodName() + ".png");
					String filepath = destinationFile.getAbsolutePath();
					try {
						test.log(status, result.getMethod().getMethodName(), test.addScreenCapture(filepath));
					} catch (Exception e) {
						Log.debug("buildTestNodes,Test log : Exception Error message : " + e);
					}
				} else {
					test.log(status, "Test " + status.toString().toLowerCase() + "ed");
					String locatorType = result.getTestClass().getName();
					String locator = locatorType.split("\\.")[1];
					File destinationFile = new File(System.getProperty("user.dir") + "\\target\\" + locator + "\\"
							+ result.getMethod().getMethodName() + ".png");
					String filepath = destinationFile.getAbsolutePath();
					try {
						test.log(status, result.getMethod().getMethodName(), test.addScreenCapture(filepath));
					} catch (Exception e) {
						Log.debug("buildTestNodes, Test Log : " + e);
					}
				}
				extent.endTest(test);
			}
		}
	}

	private Date getTime(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}
	
	
}
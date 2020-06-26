package com.learnautomation.pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.learnautomation.factory.BrowserFactory;
import com.learnautomation.factory.DataProviderFactory;
import com.learnautomation.utility.Helper;

public class BaseClass {

	public ExtentReports report;
	public ExtentTest logger;
	public WebDriver driver;

	@BeforeSuite
	public void setupReport() {
		System.out.println("****LOG:INFO - Setting up report****");
		ExtentHtmlReporter html = new ExtentHtmlReporter(System.getProperty("user.dir") + "/Report/Selenium"+Helper.getCurrentDateTime()+".html");
		report = new ExtentReports();
		report.attachReporter(html);
		System.out.println("****LOG:INFO - Reporting set****");
	}
	
	@BeforeClass
	public void startSession() 
	{
		System.out.println("****LOG:INFO - Starting Browser Session****");
		String browser=DataProviderFactory.getConfig().getData("Browser");
		String url=DataProviderFactory.getConfig().getData("stagingURL");
		driver = BrowserFactory.startBrowser(browser,url);
		System.out.println("****LOG:INFO - Started Browser Session****");
	}
	
	@AfterClass
	public void closeSession()
	{
		System.out.println("****LOG:INFO - Closing Browser Session****");
		BrowserFactory.closeBrowser(driver);
		System.out.println("****LOG:INFO -Browser Session Closed****");	
	}
	

	@AfterMethod
	public void tearDownReport(ITestResult result) 
	{
		System.out.println("****LOG:INFO -Collecting Result from Test****");
		if (ITestResult.SUCCESS == result.getStatus()) {
			try 
			{
				logger.log(Status.PASS, "Test Passed",MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshotNew(driver)).build());
			} catch (IOException e) {
				System.out.println("Exception " + e.getMessage());
			}
		} else if (ITestResult.FAILURE == result.getStatus()) {
			try {
				logger.log(Status.FAIL, "Test Failed " + result.getThrowable().getMessage(),
						MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshotNew(driver)).build());
			} catch (IOException e) {

				System.out.println("Exception " + e.getMessage());
			}
		} else if (ITestResult.SKIP == result.getStatus()) {
			logger.log(Status.SKIP, "Test Skipped");
		}

		System.out.println("****LOG:INFO - Status updated in report****");
		report.flush();
	}

}

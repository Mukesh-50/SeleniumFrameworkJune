package com.learnautomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.learnautomation.utility.Helper;

public class LogoutPage 
{
	
	WebDriver driver;
	
	public LogoutPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	By welcomeAdmin=By.xpath("//a[text()='Welcome Admin']");
	
	By logOff=By.xpath("//a[text()='Logout']");
	
	
	public void logOutFromApplication()
	{
		Helper.waitForWebElementAndClick(driver, welcomeAdmin, "Click on admin button");
		Helper.waitForWebElementAndClick(driver, logOff, "Click on logout button");
	}
	
}

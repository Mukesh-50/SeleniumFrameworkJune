package com.learnautomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.learnautomation.utility.Helper;

public class LoginPage 
{
	
	WebDriver driver;

	// this constructor will get driver instance from script that will reuse this
	public LoginPage(WebDriver driver) 
	{
		this.driver=driver;
	}
	
	private By username=By.id("txtUsername");
	private By password=By.xpath("//input[@id='txtPassword']");
	private By loginButton=By.id("btnLogin");

	
	public void loginToApplication(String user,String pass)
	{
		Helper.waitForWebElementAndType(driver, username, user, "Enter username");
		Helper.waitForWebElementAndType(driver, password, pass, "Enter password");
		Helper.waitForWebElementAndClick(driver, loginButton, "Click on on login button");
		Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"),"URL Does not has dashboard- Login Failed");
	}

	
}

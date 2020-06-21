package com.learnautomation.testcases;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.learnautomation.factory.DataProviderFactory;
import com.learnautomation.pages.BaseClass;
import com.learnautomation.pages.LoginPage;
import com.learnautomation.pages.LogoutPage;

public class LoginTest extends BaseClass
{
	LoginPage login;
	LogoutPage logOut;
	
	@Test(priority=1)
	public void loginWithAdmin() 
	{
		logger=report.createTest("LoginTest", "Login to CRM with valid credential");
		login=PageFactory.initElements(driver, LoginPage.class);
		login.loginToApplication(DataProviderFactory.getExcel().getData("Login", 0, 0),DataProviderFactory.getExcel().getData("Login", 0, 1));
	}
	
	@Test(priority=2,dependsOnMethods="loginWithAdmin")
	public void logout() 
	{
		logger=report.createTest("Logout Test", "Logout from current session");
		logOut=PageFactory.initElements(driver, LogoutPage.class);
		logOut.logOutFromApplication();
	}

}

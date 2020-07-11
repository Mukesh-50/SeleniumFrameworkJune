package com.learnautomation.testcases;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class BrowserStackTest 
{
	
	
	@Test
	public void test1() throws MalformedURLException, InterruptedException
	{
		
		 	DesiredCapabilities caps = new DesiredCapabilities();
		 	
		    caps.setCapability("browserName", "android");
		    
		    caps.setCapability("device", "Samsung Galaxy S20");
		    
		    caps.setCapability("realMobile", "true");
		    
		    caps.setCapability("os_version", "10.0");
		    
		    caps.setCapability("name", "Bstack-[Java] Selenium Test");
		    
		    WebDriver driver=new RemoteWebDriver(new URL("https://mukeshotwani3:s4GTbs4fFzQxJxzpzp2f@hub-cloud.browserstack.com/wd/hub"), caps);
		    
		    driver.get("http://www.google.com");
		    
		    WebElement element = driver.findElement(By.name("q"));

		    element.sendKeys("Mukesh Otwani");
		    
		    Thread.sleep(5000);
		    
		    element.submit();

		    System.out.println(driver.getTitle());
		    
		    driver.quit();
	}

}

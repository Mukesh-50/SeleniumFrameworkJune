package com.learnautomation.factory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BrowserFactory {
	
	// create method with Platform name,version Browser name,version
	
	// create method with MobileDevice name,version Browser name,version
	
	
	public static WebDriver startBrowserOnHub(String platformType,String browserType,String urlToAutomate)
	{
		
		System.out.println("****LOG:INFO-Starting browser session****");
		WebDriver driver=null;
		
		DesiredCapabilities cap=new DesiredCapabilities();
		
		if(platformType.contains("Windows"))
		{
			cap.setPlatform(Platform.WINDOWS);
		}
		else if(platformType.contains("Linux"))
		{
			cap.setPlatform(Platform.LINUX);
		}
		else {
			cap.setPlatform(Platform.MAC);
		}
		
		if(browserType.equalsIgnoreCase("Chrome"))
		{
			cap.setBrowserName(BrowserType.CHROME);
		}
		else if(browserType.equalsIgnoreCase("Firefox"))
		{
			cap.setBrowserName(BrowserType.FIREFOX);

		}
		else if(browserType.equalsIgnoreCase("IE"))
		{
			cap.setBrowserName(BrowserType.IE);

		}
		
		try {
			driver=new RemoteWebDriver(new URL(DataProviderFactory.getConfig().getData("HUBURL")),cap);
		} catch (MalformedURLException e) 
		{
			System.out.println("LOG:ERROR -hub is not reachable" +e.getMessage());
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.get(urlToAutomate);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		System.out.println("****LOG:INFO-Browser session is up and Running****");
		
		return driver;
		
	}
	
	
	
	public static WebDriver startBrowser(String browserType,String urlToAutomate)
	{
		
		System.out.println("****LOG:INFO-Starting browser session****");
		WebDriver driver=null;
		
		if(browserType.equalsIgnoreCase("Chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\USER\\Desktop\\April2020\\chromedriver.exe");
			driver=new ChromeDriver();	
		}
		else if(browserType.equalsIgnoreCase("Firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "C:\\Users\\USER\\Desktop\\April2020\\geckodriver.exe");
			driver=new FirefoxDriver();
		}
		else if(browserType.equalsIgnoreCase("IE"))
		{
			System.setProperty("webdriver.ie.driver", "C:\\Users\\USER\\Desktop\\April2020\\IEDriverServer_32.exe");
			driver=new InternetExplorerDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.get(urlToAutomate);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		System.out.println("****LOG:INFO-Browser session is up and Running****");
		
		return driver;
		
	}
	
	public static void closeBrowser(WebDriver driver)
	{
		driver.quit();
		System.out.println("****LOG:INFO-Browser session terminated****");
	}

}

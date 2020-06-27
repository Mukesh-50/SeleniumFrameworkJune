package com.learnautomation.utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Helper {

	
	public static void highLightElement(WebDriver driver,WebElement element)
	{
		
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			
		}
		
		js.executeScript("arguments[0].setAttribute('style','border: solid 2px white');", element); 

	}
	
	
	public static String getCurrentDateTime()
	{
		SimpleDateFormat myDataFormat=new SimpleDateFormat("HH_mm_ss_dd_MM_yyyy");
		
		Date date=new Date();
		
		return myDataFormat.format(date);
	}
	
	
	public static void captureScreenshot(WebDriver driver)
	{
		try 
		{
			FileHandler.copy(((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE), new File(System.getProperty("user.dir")+"\\Screenshot\\Selenium"+System.currentTimeMillis()+".png"));
		} catch (IOException e) 
		{
			System.out.println("Could not capture screenshot "+e.getMessage());
		}
	}
	
	public static String captureScreenshotNew(WebDriver driver)
	{
		
		String path=System.getProperty("user.dir")+"\\Screenshot\\Selenium"+Helper.getCurrentDateTime()+".png";
		
		try 
		{
			FileHandler.copy(((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE), new File(path));
		} catch (IOException e) 
		{
			System.out.println("Could not capture screenshot "+e.getMessage());
		}
		
		return path;
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
	
	
	public static void waitForWebElementAndClick(WebDriver driver,String xpath,String stepInfo)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath))).click();
		
		System.out.println("LOG:INFO "+stepInfo);
	}
	
	public static void waitForWebElementAndClick(WebDriver driver,By element,String stepInfo)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		
		WebElement ele=wait.until(ExpectedConditions.elementToBeClickable(element));
		
		highLightElement(driver, ele);
		
		ele.click();
		
		System.out.println("LOG:INFO "+stepInfo);
	}
	
	public static void waitForWebElementAndType(WebDriver driver,By element,String textToByTyped,String stepInfo)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		
		WebElement ele=wait.until(ExpectedConditions.elementToBeClickable(element));
		
		highLightElement(driver, ele);
		
		ele.sendKeys(textToByTyped);
		
		System.out.println("LOG:INFO "+stepInfo);
	}
	
	public static void waitForWebElementAndType(WebDriver driver,String xpath,String textToByTyped,String stepInfo)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath))).sendKeys(textToByTyped);
		
		System.out.println("LOG:INFO "+stepInfo);
	}
	
	
	public static WebElement waitForWebElement(WebDriver driver,String xpath)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		
		return wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
	}
	
	public static void handleAlertAndVerifyPartialText(WebDriver driver,String expectedText)
	{
		Alert alt=new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.alertIsPresent());
		
		if(alt.getText().contains(expectedText))
		{
			System.out.println("Alert text verified");
		}
		else
		{
			System.out.println("Alert text not verified- Failed");
		}
		
		alt.accept();
	}
	
	public static void handleAlertAndVerifyText(WebDriver driver,String expectedText)
	{
		Alert alt=new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.alertIsPresent());
		
		if(alt.getText().equalsIgnoreCase(expectedText))
		{
			System.out.println("Alert text verified");
		}
		else
		{
			System.out.println("Alert text not verified- Failed");
		}
		
		alt.accept();
	}
	

}

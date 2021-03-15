package sudipJenkins;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class sudiptestng {
	WebDriver driver;
	static ExtentTest test;
	static ExtentReports report;
	
	@BeforeClass
	public static void startTest()
	{
		

		System.out.println(System.getProperty("user.dir")+"CHECK THIS--->>>");
		report = new ExtentReports(System.getProperty("user.dir")+"//SimpleGyan"+"ExtentReportResultsNew.html",true);
        report.loadConfig(new File(System.getProperty("user.dir")+"//extent-config.xml"));
		test = report.startTest("ExtentDemo");
	}
	
  @Test
  public void Login() throws InterruptedException {
	  
	  
		driver.findElement(By.id("user_login")).sendKeys("sudip");
		driver.findElement(By.id("user_pass")).sendKeys("123qwe");
		driver.findElement(By.id("wp-submit")).click();
		Thread.sleep(1000);
		
	
		
		WebElement title=driver.findElement(By.xpath("//span[@class='display-name']"));
		String Expected="sudip";
		String Actual=title.getText();
		//Assert.assertEquals(Expected, Actual);  
		
		if(Actual.equalsIgnoreCase(Expected))
		{
			test.log(LogStatus.PASS,"SUCCESSFULLY LOGGED IN "+ "\n" +" EXPECTED::  "+Expected+ " ACTUAL:: "+Actual);	
		}
		else
		{
			test.log(LogStatus.FAIL, "FAILED TO LOG IN "+ "\n" + "EXPECTED::  "+Expected+"ACTUAL:: "+Actual);
		}
	  
  }
  @BeforeMethod
  public void beforeMethod() {
	  
	  System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver.exe");
		test.assignAuthor("SUDIP ROY");
		test.assignCategory("SMOKE TEST");
		driver=new ChromeDriver();
		
		driver.get("http://localhost/sudipsite/wp-admin/");
		
		test.log(LogStatus.PASS, "Browser Launced Successfully");
  }

  @AfterMethod
  public void afterMethod() {
	  
	  driver.quit();
  }
  
  @AfterClass
  public static void endTest()
  {
  report.endTest(test);
  report.flush();
  }

}

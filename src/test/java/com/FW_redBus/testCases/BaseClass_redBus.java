package com.FW_redBus.testCases;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.FW_redBus.utilities.CaptureDate_Scrshot;
import com.FW_redBus.utilities.ReadConfig;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
//call the readconfig class file
//declare the common process: setUp and teardown method
//create general methods for screenshot without testng
//and reading excel data
//create only those methods which are accessed by testng annotations
//keep general methods in utility file ass class files and call them here using the objects of those files

public class BaseClass_redBus

{
	public static WebDriver driver;
	ReadConfig read_config = new ReadConfig(); // object created for config.java class file
	CaptureDate_Scrshot date_scrshot = new CaptureDate_Scrshot(); // object created for Screenshot and date capture
																	// method .java class file
	public String phoneNum = read_config.getPhonenum_reg();
	public static Logger logger;
	public String date_stamp = CaptureDate_Scrshot.captureDate();

	// Start with testNg common method declaration

	@Parameters("browser")

	@BeforeClass()
	public void setUpBrowser(String br) 
	{
		logger = Logger.getLogger("redBus");
		PropertyConfigurator.configure("Log4j.properties");

		if (br.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", read_config.getChromePath());
			ChromeOptions ops = new ChromeOptions();
			driver = new ChromeDriver(ops);
			ops.addArguments("--disable-notifications");
		}

		if (br.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", read_config.getFirefoxPath());
			driver = new FirefoxDriver();
		}

		if (br.equals("ie")) {
			System.setProperty("webdriver.ie.driver", read_config.getIEPath());
			driver = new InternetExplorerDriver();
		}
		
		

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(read_config.getUrl());
		logger.info("setUp method is run");

	}

	/*
	 * ChromeOptions chromeOptions= new ChromeOptions(); chromeOptions.
	 * setBinary("C:\Program Files (x86)\Google\Chrome Beta\Application\chrome.exe"
	 * ); System.setProperty("webdriver.chrome.driver",
	 * "C:\STUDY\Selenium\CHROMEDRIVERS\chromedriver.exe");
	 * 
	 * ChromeDriver driver = new ChromeDriver(chromeOptions);
	 * driver.get("http://newtours.demoaut.com/");
	 */

	@AfterClass
	public void closeBrowser() {
		driver.quit();
	}

	public ExtentHtmlReporter htmlReporter; // for look and feel of report
	public ExtentReports extent;// to log the entry of every test case
	public ExtentTest test;// to log the status of every test case

	@BeforeSuite
	public void setUpExtent() {
		htmlReporter = new ExtentHtmlReporter(
				System.getProperty("user.dir") + "/test-output/extentReport" + date_stamp + ".html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter); // attach the html report

		htmlReporter.config().setDocumentTitle("Red Bus Automation Framework");
		htmlReporter.config().setReportName("Functioanl report");
		htmlReporter.config().setTheme(Theme.DARK);

		extent.setSystemInfo("Host Name", "local host");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("OS", "Windows 10");
		extent.setSystemInfo("User", "Shallu");
		htmlReporter.config().setAutoCreateRelativePathMedia(true);

	}

	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {

			test.log(Status.FAIL, "test failed is : " + result.getName());
			test.log(Status.FAIL, "test failed" + result.getThrowable());
			String ScrshotPath = date_scrshot.captureScreenshot(result.getName(), driver);
			test.fail("test failed", MediaEntityBuilder.createScreenCaptureFromPath(ScrshotPath).build());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test = extent.createTest(result.getName());
			test.log(Status.PASS, "the test case is passed");
			String ScrshotPath = date_scrshot.captureScreenshot(result.getName(), driver);
			test.pass("test passed", MediaEntityBuilder.createScreenCaptureFromPath(ScrshotPath).build());
		} else if (result.getStatus() == ITestResult.SKIP) {
			test = extent.createTest(result.getName());
			String ScrshotPath = date_scrshot.captureScreenshot(result.getName(), driver);
			test.skip("test skipped", MediaEntityBuilder.createScreenCaptureFromPath(ScrshotPath).build());

		}
	}

	@AfterSuite
	public void endExtent() {
		extent.flush();
	}

}

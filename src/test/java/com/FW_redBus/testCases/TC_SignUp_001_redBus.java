package com.FW_redBus.testCases;

import org.testng.annotations.Test;

import com.FW_redBus.pageObjects.LoginPage_redBus;

public class TC_SignUp_001_redBus extends BaseClass_redBus1
{
	@Test(priority = 0)
	public void signUp() throws InterruptedException 
	{
		//test = extent.createTest("test signUp initiated"); 
		//the above statement is not required if the test is logged using the extent object in the respective methods in the base class 

		logger.info("url is opened");

		LoginPage_redBus lp = new LoginPage_redBus(driver);
		Thread.sleep(2000);
		lp.ClickLogin();
		Thread.sleep(2000);
		driver.switchTo().frame(lp.Iframe1);

		Thread.sleep(2000);
		lp.enterPhoneNum(phoneNum);
	//	logger.info("the mobile num clicked");

		driver.switchTo().defaultContent();

	//	logger.info("returned to default window");
		Thread.sleep(2000);
	}

	@Test(priority = 1)
	public void closeWindow() throws InterruptedException {
		LoginPage_redBus lp = new LoginPage_redBus(driver);
try {
		driver.switchTo().frame(lp.Iframe_captcha);
		Thread.sleep(2000);
		lp.clickCaptcha();

//		logger.info("the captcha clicked");

		Thread.sleep(3000);

		lp.click_genOTP();
		lp.click_SignIn();
}catch (Exception e) {
	e.getMessage();
	System.out.println("the above method could not run");
}
		lp.click_crossBtn();
//		logger.info("the cross btn is clicked");
	}

}

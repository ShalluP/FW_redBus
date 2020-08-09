package com.FW_redBus.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage_redBus {
	
	public LoginPage_redBus(WebDriver driver) {
		PageFactory.initElements(driver, this);
		
	}
	@FindBy(how = How.ID,using = "sign-in-icon-down")
	WebElement SignInDDL;
	
	@FindBy(how = How.ID,using ="signInLink")
	WebElement SignIn;
	//@FindBy(xpath = "//iframe[@class='modalIframe']") WebElement Iframe;
	
	@FindBy(xpath = "//iframe[@class='modalIframe']")
	public WebElement Iframe1;
	
	@FindBy(how = How.XPATH,using = "//div[@class='mobileInput clearfix']//input[@id='mobileNoInp']")
	WebElement SelPhoneNum;
	
	//iframe[@name='a-xy7buyuir4m1']
	@FindBy(xpath="//iframe[@name='a-xy7buyuir4m1']")
	public WebElement Iframe_captcha;
	
	@FindBy(xpath = "//span[@id='recaptcha-anchor']")
	WebElement Captcha;
	
	@FindBy(id = "otp-container")
	WebElement Gen_OTP;
	
	@FindBy(xpath="//button[@id='verifyUser']")
	WebElement SigningIn_Click;
	
	@FindBy(how = How.XPATH,using = "//div[@class='modalCloseSmall']//i[@class='icon-close']")
	WebElement click_cross;
	
	public void ClickLogin()
	{
		SignInDDL.click();
		SignIn.click();
	}
	
	public void enterPhoneNum(String phone_num)
	{
		//Iframe1.isSelected();
		//Iframe1.isEnabled();
		SelPhoneNum.click();
		SelPhoneNum.sendKeys(phone_num);
	}
	public void clickCaptcha()
	{
		Captcha.click();
	}
	public void click_genOTP()
	{
		 Gen_OTP.click();
	}
	
	public void click_SignIn()
	{
		SigningIn_Click.click();
	}
	public void click_crossBtn()
	{
		click_cross.click();
	}
}

package com.FW_redBus.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

// this class will read data from config.properties file and load it
// its like a pageObject class for the config.properties file
// constructor has to be instantiated
public class ReadConfig {
	Properties pro;// same as webdriver driver

	public ReadConfig() 
	{
		File src = new File("./Configuration/config.properties");
		// File is input using try catch block, as the file location process may be give error
		try {
			FileInputStream fis = new FileInputStream(src);
			pro = new Properties();
			pro.load(fis); // input file is loaded to be read

		} catch (Exception e) {
			System.out.println("Exception is : " + e.getMessage());
		}
	
	}
	// all the data entries in the read config file are to be read
	// methods are to be declared for reading the data stored in the read config file	
	// methods should not be a null/void, it should return String value or any other data type
	// because the data from the read config file is to be fetched, so must return some value7data type
	public String getUrl()
	{
		String baseUrl= pro.getProperty("BaseUrl");
		return baseUrl ;
		
	}
	public String getPhonenum_reg()
	{
		String phoneNum_signIn= pro.getProperty("SignPhone_Num");
		return phoneNum_signIn ;
		
	}
	
	public String getChromePath()
	{
		String Chromepath= pro.getProperty("Chromedriver");
		return Chromepath ;
		
	}
	public String getFirefoxPath()
	{
		String fireFoxpath= pro.getProperty("GeckoDriver");
		return fireFoxpath;
		
	}
	public String getIEPath()
	{
		String iepath= pro.getProperty("ie");
		return iepath ;
		
	}
}

package com.FW_redBus.utilities;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class CaptureDate_Scrshot {
	
	  public static String captureDate() 
	  { 
		  DateFormat form_date = new
	  SimpleDateFormat("dd.MM.yyyy hh.mm.ss"); 
	  Date system_date = new Date();
	  String date = form_date.format(system_date);
	  return date; 
	  }
	 
	public String captureScreenshot(String ScreenshotName, WebDriver driver) throws IOException {
		
		  DateFormat form_date = new SimpleDateFormat("dd.MM.yyyy hh.mm.ss"); 
		  Date system_date = new Date();
		  String date = form_date.format(system_date);
		 
		// TakesScreenshot ts = (TakesScreenshot) driver;
		// File src_location = ts.getScreenshotAs(OutputType.FILE);

		File src_location = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		
		String target_location= "./Screenshots/" + ScreenshotName + date + ".png";
		
		File target_location_file = new File(target_location);
		
		
		FileUtils.copyFile(src_location, target_location_file);;
		System.out.println("The screenshot is taken");
		return target_location;

	}

}

package iOSApp.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import iOSApp.test_all.AllTest;

public class takeScreenShot 
{
	public static String ScreenShotPath="./Screenshots/" +getCurrentDateTime()+ ".jpg";
	//截图保存到Screenshots，以当前时间命名
	public static void takeScreenShots(WebDriver driver)
	{  
	   File screenShotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);  
	   try 
	   {   		 
	      FileUtils.copyFile(screenShotFile, new File("./Screenshots/" +getCurrentDateTime()+ ".jpg"));  
	   } 
	   catch (IOException e) {e.printStackTrace();}  
	} 
	
	//获取当前时间
	public static String getCurrentDateTime()
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss"); 
	    return df.format(new Date()); 
    }
	//调用idevicescreenshot截图工具
	public static void idevicescreenshot() throws Exception
	{
		File file = new File("Screenshots");
		String[] shell = {"sh","-c","cd "+file.getAbsolutePath()+" && "+AllTest.idevicescreenshotPath};
		Runtime.getRuntime().exec(shell);
		Runtime.getRuntime().exec(shell).waitFor();
	}
}

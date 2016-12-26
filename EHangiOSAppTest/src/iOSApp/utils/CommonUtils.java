package iOSApp.utils;

import io.appium.java_client.AppiumDriver;
import java.io.File;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import iOSApp.test_all.AllTest;

public class CommonUtils 
{  	
	 //初始化配置，EHangPlayVersion是app名字，DeviceName是真机或模拟器的名字，PlatformVersion是真机的系统版本
	 public static AppiumDriver CreatAppiumDriver() throws Exception
	 {
		 AppiumDriver driver;		 
		 File classpathRoot = new File(System.getProperty("user.dir"));
	     File appDir = new File(classpathRoot, "apps");
	     File app = new File(appDir, AllTest.EhangAppName);  
	         
	     DesiredCapabilities capabilities = new DesiredCapabilities();
	     //设备平台   
	     capabilities.setCapability("platformName", "iOS");
	     // 设备名称  
	     capabilities.setCapability("deviceName", AllTest.IphoneType); 
	     //系统版本    
	     capabilities.setCapability("platformVersion", AllTest.IphoneVersion);  
	     //apk路径  
	     capabilities.setCapability("app", app.getAbsolutePath()); 
	     //自动接受提示信息
	     capabilities.setCapability("autoAcceptAlerts", true);	     
	            
	     driver = new AppiumDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);      
	     
	     return driver;
	 }
	 
	 //进入登陆页
	 public static void GoToLoginPage(AppiumDriver driver)throws Exception
	 {
		 //判断是否在引导页，不是则捕获异常
		 try
		 {
			 //等待引导页出现
			 driver.findElementByName("icon_guide_pages1").isDisplayed();
			 new WebDriverWait(driver,10).until(
		    			ExpectedConditions.presenceOfElementLocated(By.name("icon_guide_pages1"))); 
			 //尝试滑动两次引导页，没滑过则捕获异常后再滑动两次
			 try
			 {
				 //滑动引导页
				 int x=driver.manage().window().getSize().width;
				 int y=driver.manage().window().getSize().height;
				 
				 driver.swipe(x/4*3, y/2, x/8*1, y/2, 0);	 
			     		
				 driver.swipe(x/4*3, y/2, x/8*1, y/2, 0);
				 
		         driver.findElementByName("icon EHGuidePage5").click();	
		         Thread.sleep(5000);
			 }catch(Exception e)
			 {
				 int x=driver.manage().window().getSize().width;
				 int y=driver.manage().window().getSize().height;
				 
				 driver.swipe(x/4*3, y/2, x/8*1, y/2, 0);	 
			     		
				 driver.swipe(x/4*3, y/2, x/8*1, y/2, 0);
				 
		         driver.findElementByName("icon EHGuidePage5").click();
			 }	
		 }catch(Exception e)
		 {
			 
		 }         
	 }
	 
	 //等待某一元素出现,不会出现异常,Time为等待时间，单位秒，Text为控件的name
	 public static Boolean WaitForName(AppiumDriver driver,String Text,int Time)
	 {
		 try
		 {
			 new WebDriverWait(driver,Time).until(
		    			ExpectedConditions.presenceOfElementLocated(By.name(Text))); 
			 return true;
		 }catch(Exception e)
		 {
			 return false;
		 }		
	 }
	 
	 public static Boolean WaitForXPath(AppiumDriver driver,String xpath,int Time)
	 {
		 try
		 {
			 new WebDriverWait(driver,Time).until(
		    			ExpectedConditions.presenceOfElementLocated(By.xpath(xpath))); 
			 return true;
		 }catch(Exception e)
		 {
			 return false;
		 }		
	 }
	 
	 //进入到主页
	 public static void GoToPersonalInfoPage(AppiumDriver driver)
	 {
		 try
		 {			 
			 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
			 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
		     driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys("13000000001");
		    	
		     driver.getKeyboard().pressKey(Keys.RETURN);
		    	
		     driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").click();
		     driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").clear();
		     driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").sendKeys("123456");
		    	
		     driver.getKeyboard().pressKey(Keys.RETURN);
		    	
		     driver.findElementByName(Read_Excel.R_Excel("确认登录")).click();
		    	
		     CommonUtils.WaitForName(driver, Read_Excel.R_Excel("飞行"), 10);
		    			     
		 }catch(Exception e)
		 {
			 
		 }
	 }
	 
	 //向下滑动直到找到某元素
	 public static void SwipDownFindElement(AppiumDriver driver,String Name)
	 {
		 int x=driver.manage().window().getSize().width;
		 int y=driver.manage().window().getSize().height;
		 while(WaitForName(driver,Name,3)==false)
		 {
			 driver.swipe(x/2, y/4*3, x/2, y/4*1, 0);
		 }	
	 }
	 
	 //向上滑动直到找到某元素
	 public static void SwipUpFindElement(AppiumDriver driver,String Name)
	 {
		 int x=driver.manage().window().getSize().width;
		 int y=driver.manage().window().getSize().height;
			do
			{
				driver.swipe(x/2, y/4*1, x/2, y/4*3, 0);
			}while(WaitForName(driver,Name,3)==false);	
	 }
}

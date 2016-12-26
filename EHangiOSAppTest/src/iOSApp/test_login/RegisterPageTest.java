package iOSApp.test_login;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import iOSApp.test_all.AllTest;
import iOSApp.utils.CommonUtils;
import iOSApp.utils.GetImages;
import iOSApp.utils.Read_Excel;
import io.appium.java_client.AppiumDriver;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegisterPageTest 
{
	 private static AppiumDriver driver;
		
	 @BeforeClass
	 public static void setUpBeforeClass() throws Exception 
	 {  
		
	 }
	
	 @Before
	 public void setUp() throws Exception
	 {
		 driver = CommonUtils.CreatAppiumDriver();		
		 CommonUtils.GoToLoginPage(driver); 	
		 driver.findElementByAccessibilityId(Read_Excel.R_Excel("注册账号")).click();
	 } 
	 
	 @After 
	 public void tearDown() throws Exception 
	 { 
		 driver.quit();
	 }

	 @AfterClass 
	 public static void tearDownAfterClass() throws Exception
     { 
		
	 } 
	 
	 //注册页页面多语言判断
	 @Test
	 public void test01CheckString()
	 {		 
		 assertEquals(Read_Excel.R_Excel("输入您的手机号或邮箱"),
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").getText());
		 assertEquals(Read_Excel.R_Excel("输入账号密码，不少于6位").replace("è", "è"),
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").getText());		
		 assertEquals(Read_Excel.R_Excel("同意EHANG Play服务协议"),driver.findElementByName(Read_Excel.R_Excel("同意EHANG Play服务协议")).getText());
		 assertEquals(Read_Excel.R_Excel("确认注册"),driver.findElementByName(Read_Excel.R_Excel("确认注册")).getText());	
		 
		 if(AllTest.ExcelPath().endsWith("法语总表.xls"))
		 {
			 assertEquals(Read_Excel.R_Excel("输入验证码").replace("é", "é"),driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").getText());
			 assertEquals(Read_Excel.R_Excel("获取验证码").replace("é", "é"),driver.findElementByName(Read_Excel.R_Excel("获取验证码")).getText());
			 assertEquals(Read_Excel.R_Excel("< 返回登录").replace("à", "à"),driver.findElementByName(Read_Excel.R_Excel("< 返回登录")).getText());
		 }else if(AllTest.ExcelPath().endsWith("7.4德语表.xls"))
		 {
			 if(AllTest.EnterColumns == 3)
			 {
				 assertEquals(Read_Excel.R_Excel("输入验证码").replace("ä","ä"),driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").getText());
				 assertEquals(Read_Excel.R_Excel("获取验证码").replace("ä","ä"),driver.findElementByName(Read_Excel.R_Excel("获取验证码")).getText());
				 assertEquals(Read_Excel.R_Excel("< 返回登录").replace("ü","ü"),driver.findElementByName(Read_Excel.R_Excel("< 返回登录")).getText());
			 }else
			 {
				 assertEquals(Read_Excel.R_Excel("输入验证码"),driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").getText());
				 assertEquals(Read_Excel.R_Excel("获取验证码"),driver.findElementByName(Read_Excel.R_Excel("获取验证码")).getText());
				 assertEquals(Read_Excel.R_Excel("< 返回登录"),driver.findElementByName(Read_Excel.R_Excel("< 返回登录")).getText());
			 }						 
		 }
	 }
	 //账号框提示语消失	 
	 @Test  
	 public void test02SetUserNameas1() 
	 {
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys("1");
		
		assertEquals("提示语未消失","1",driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/"
				+ "UIATextField[1]").getText());
	 }  
	 
	 //账号字符可以为英文字母、数字、字符
	 @Test  
	 public void test03SetUserNameasCN() 
	 { 		 
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys
		 ("123abc~!@#$%^&＊（）");
		 
		assertEquals("不可输入","123abc~!@#$%^&＊（）",driver.findElementByXPath("//UIAApplication[1]/"
				+ "UIAWindow[1]/UIATextField[1]").getText()); 
	 } 
	 
	 //手机号码账号，不能为10位数字
	 @Test  
	 public void test05PhoneSignup() throws Exception 
	 { 
		 //删除Screenshots目录下所有文件
		 GetImages.DelAllFile();
	     
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys
		 ("1234567890");
		 
		 driver.getKeyboard().pressKey(Keys.RETURN);
		 
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").sendKeys
		 ("123456");
		 
		 driver.getKeyboard().pressKey(Keys.RETURN);
		 
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").sendKeys
		 ("123456");
		 
		 driver.hideKeyboard();
		 
		 driver.findElementByName(Read_Excel.R_Excel("确认注册")).click();
		 
	     //连续截20张图
	     GetImages.takeTwentyPictures();
	     
	     if(AllTest.ExcelPath().endsWith("7.4德语表.xls"))
	     {
	    	 if(AllTest.EnterColumns == 3)
	    	 {
	    		 GetImages.cutCenterImage(100);
	    	 }else
	    	 {
	    		 GetImages.cutCenterImage(200);
	    	 }	    	 
	     }else
	     {	    	 
	    	 //裁剪200高度居中图片
		     GetImages.cutCenterImage(200);
	     }	
	     
	     //图片去躁后保存在tmp
	     GetImages.cleanImages();
	     //图像识别
	     GetImages.GetImage();
	     //去除空格和换行
	     GetImages.txtForm();
	     //合并所有txt文件
	     GetImages.CombineAlltxt();    
	    	
	     assertTrue(GetImages.txtcontain(Read_Excel.R_Excel("用户邮箱或手机号格式错误")));  
	    	
	     GetImages.DelAllFile();    	 	 	
	 }
	 
	 //手机号码账号，需要为11位数字
	 @Test  
	 public void test06PhoneSignup() throws Exception 
	 {
		 //删除Screenshots目录下所有文件
		 GetImages.DelAllFile(); 
	     
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys
		 ("13445678901");
		 
		 driver.getKeyboard().pressKey(Keys.RETURN);
		 
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").sendKeys
		 ("123456");
		 
		 driver.getKeyboard().pressKey(Keys.RETURN);
		 
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").sendKeys
		 ("123456");
		 
		 driver.hideKeyboard();
		 
		 driver.findElementByName(Read_Excel.R_Excel("确认注册")).click();
		 
	     //连续截20张图
	     GetImages.takeTwentyPictures();
	     
	     if(AllTest.ExcelPath().endsWith("7.4德语表.xls"))
	     {
		     if(AllTest.EnterColumns==2)
		     {
			     //裁剪100高度居中图片
			     GetImages.cutCenterImage(100);
		     }else if(AllTest.EnterColumns == 3)
		     {
		    	 GetImages.cutCertenImage(100, 600);
		     }else
		     {
		    	 GetImages.cutCenterImage(200);
		     }
	     }else if(AllTest.ExcelPath().endsWith("法语总表.xls"))
	     {
	    	 GetImages.cutCenterImage(200);
	     }
	     
	     //图片去躁后保存在tmp
	     GetImages.cleanImages();
	     //图像识别
	     GetImages.GetImage();
	     //去除空格和换行
	     GetImages.txtForm();
	     //合并所有txt文件
	     GetImages.CombineAlltxt();    
	    	
	     assertTrue(GetImages.txtcontain(Read_Excel.R_Excel("验证码错误")));  
	    	
	     GetImages.DelAllFile(); 	 	
	 }
	 
	 //手机号码账号，不能为12位数字
	 @Test  
	 public void test07PhoneSignup() throws Exception 
	 {
		 //删除Screenshots目录下所有文件
		 GetImages.DelAllFile(); 
	     
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys
		 ("134456789012");
		 
		 driver.getKeyboard().pressKey(Keys.RETURN);
		 
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").sendKeys
		 ("123456");
		 
		 driver.getKeyboard().pressKey(Keys.RETURN);
		 
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").sendKeys
		 ("123456");
		 
		 driver.hideKeyboard();
		 
		 driver.findElementByName(Read_Excel.R_Excel("确认注册")).click();
		 
	     //连续截20张图
	     GetImages.takeTwentyPictures();
	     
	     if(AllTest.ExcelPath().endsWith("7.4德语表.xls"))
	     {
	    	 if(AllTest.EnterColumns == 3)
	    	 {
	    		 GetImages.cutCenterImage(100);
	    	 }else
	    	 {
	    		 GetImages.cutCenterImage(200);
	    	 }	    	 
	     }else
	     {	    	 
	    	 //裁剪200高度居中图片
		     GetImages.cutCenterImage(200);
	     }	
	     
	     //图片去躁后保存在tmp
	     GetImages.cleanImages();
	     //图像识别
	     GetImages.GetImage();
	     //去除空格和换行
	     GetImages.txtForm();
	     //合并所有txt文件
	     GetImages.CombineAlltxt();    
	    	
	     assertTrue(GetImages.txtcontain(Read_Excel.R_Excel("用户邮箱或手机号格式错误")));  
	    	
	     GetImages.DelAllFile();	 	
	 }
	 
	 //手机号码账号，可以为14开头的11位数字
	 @Test  
	 public void test09PhoneSignup() throws Exception 
	 {
		 //删除Screenshots目录下所有文件
		 GetImages.DelAllFile(); 
	     
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys
		 ("14445678901");
		 
		 driver.getKeyboard().pressKey(Keys.RETURN);
		 
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").sendKeys
		 ("123456");
		 
		 driver.getKeyboard().pressKey(Keys.RETURN);
		 
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").sendKeys
		 ("123456");
		 
		 driver.hideKeyboard();
		 
		 driver.findElementByName(Read_Excel.R_Excel("确认注册")).click();
		 
	     //连续截20张图
	     GetImages.takeTwentyPictures();
	     
	     if(AllTest.ExcelPath().endsWith("7.4德语表.xls"))
	     {
		     if(AllTest.EnterColumns==2)
		     {
			     //裁剪100高度居中图片
			     GetImages.cutCenterImage(100);
		     }else if(AllTest.EnterColumns == 3)
		     {
		    	 GetImages.cutCertenImage(100, 600);
		     }else
		     {
		    	 GetImages.cutCenterImage(200);
		     }
	     }else if(AllTest.ExcelPath().endsWith("法语总表.xls"))
	     {
	    	 GetImages.cutCenterImage(200);
	     }
	     
	     //图片去躁后保存在tmp
	     GetImages.cleanImages();
	     //图像识别
	     GetImages.GetImage();
	     //去除空格和换行
	     GetImages.txtForm();
	     //合并所有txt文件
	     GetImages.CombineAlltxt();    
	    	
	     assertTrue(GetImages.txtcontain(Read_Excel.R_Excel("验证码错误")));  
	    	
	     GetImages.DelAllFile();	 	
	 }
	 
	 //手机号码账号，可以为15开头的11位数字
	 @Test  
	 public void test10PhoneSignup() throws Exception 
	 {
		 //删除Screenshots目录下所有文件
		 GetImages.DelAllFile(); 
	     
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys
		 ("15445678901");
		 
		 driver.getKeyboard().pressKey(Keys.RETURN);
		 
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").sendKeys
		 ("123456");
		 
		 driver.getKeyboard().pressKey(Keys.RETURN);
		 
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").sendKeys
		 ("123456");
		 
		 driver.hideKeyboard();
		 
		 driver.findElementByName(Read_Excel.R_Excel("确认注册")).click();
		 
	     //连续截20张图
	     GetImages.takeTwentyPictures();
	     
	     if(AllTest.ExcelPath().endsWith("7.4德语表.xls"))
	     {
		     if(AllTest.EnterColumns==2)
		     {
			     //裁剪100高度居中图片
			     GetImages.cutCenterImage(100);
		     }else if(AllTest.EnterColumns == 3)
		     {
		    	 GetImages.cutCertenImage(100, 600);
		     }else
		     {
		    	 GetImages.cutCenterImage(200);
		     }
	     }else if(AllTest.ExcelPath().endsWith("法语总表.xls"))
	     {
	    	 GetImages.cutCenterImage(200);
	     }
	     
	     //图片去躁后保存在tmp
	     GetImages.cleanImages();
	     //图像识别
	     GetImages.GetImage();
	     //去除空格和换行
	     GetImages.txtForm();
	     //合并所有txt文件
	     GetImages.CombineAlltxt();    
	    	
	     assertTrue(GetImages.txtcontain(Read_Excel.R_Excel("验证码错误")));  
	    	
	     GetImages.DelAllFile(); 	
	 }
	 
	 //手机号码账号，可以为17开头的11位数字
	 @Test  
	 public void test11PhoneSignup() throws Exception 
	 {
		 //删除Screenshots目录下所有文件
		 GetImages.DelAllFile(); 
	     
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys
		 ("17445678901");
		 
		 driver.getKeyboard().pressKey(Keys.RETURN);
		 
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").sendKeys
		 ("123456");
		 
		 driver.getKeyboard().pressKey(Keys.RETURN);
		 
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").sendKeys
		 ("123456");
		 
		 driver.hideKeyboard();
		 
		 driver.findElementByName(Read_Excel.R_Excel("确认注册")).click();
		 
	     //连续截20张图
	     GetImages.takeTwentyPictures();
	     
	     if(AllTest.ExcelPath().endsWith("7.4德语表.xls"))
	     {
		     if(AllTest.EnterColumns==2)
		     {
			     //裁剪100高度居中图片
			     GetImages.cutCenterImage(100);
		     }else if(AllTest.EnterColumns == 3)
		     {
		    	 GetImages.cutCertenImage(100, 600);
		     }else
		     {
		    	 GetImages.cutCenterImage(200);
		     }
	     }else if(AllTest.ExcelPath().endsWith("法语总表.xls"))
	     {
	    	 GetImages.cutCenterImage(200);
	     }
	     
	     //图片去躁后保存在tmp
	     GetImages.cleanImages();
	     //图像识别
	     GetImages.GetImage();
	     //去除空格和换行
	     GetImages.txtForm();
	     //合并所有txt文件
	     GetImages.CombineAlltxt();    
	    	
	     assertTrue(GetImages.txtcontain(Read_Excel.R_Excel("验证码错误")));  
	    	
	     GetImages.DelAllFile();	 	
	 }
	 
	 //手机号码账号，可以为18开头的11位数字
	 @Test  
	 public void test12PhoneSignup() throws Exception 
	 {
		 //删除Screenshots目录下所有文件
		 GetImages.DelAllFile(); 
	     
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys
		 ("18445678901");
		 
		 driver.getKeyboard().pressKey(Keys.RETURN);
		 
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").sendKeys
		 ("123456");
		 
		 driver.getKeyboard().pressKey(Keys.RETURN);
		 
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").sendKeys
		 ("123456");
		 
		 driver.hideKeyboard();
		 
		 driver.findElementByName(Read_Excel.R_Excel("确认注册")).click();
		 
	     //连续截20张图
	     GetImages.takeTwentyPictures();
	     
	     if(AllTest.ExcelPath().endsWith("7.4德语表.xls"))
	     {
		     if(AllTest.EnterColumns==2)
		     {
			     //裁剪100高度居中图片
			     GetImages.cutCenterImage(100);
		     }else if(AllTest.EnterColumns == 3)
		     {
		    	 GetImages.cutCertenImage(100, 600);
		     }else
		     {
		    	 GetImages.cutCenterImage(200);
		     }
	     }else if(AllTest.ExcelPath().endsWith("法语总表.xls"))
	     {
	    	 GetImages.cutCenterImage(200);
	     }
	     
	     //图片去躁后保存在tmp
	     GetImages.cleanImages();
	     //图像识别
	     GetImages.GetImage();
	     //去除空格和换行
	     GetImages.txtForm();
	     //合并所有txt文件
	     GetImages.CombineAlltxt();    
	    	
	     assertTrue(GetImages.txtcontain(Read_Excel.R_Excel("验证码错误")));  
	    	
	     GetImages.DelAllFile();	     	 	
	 }
	 
	 //邮箱账号，账号不能出现中文
	 @Test  
	 public void test13MailSignup() throws Exception 
	 {
		 //删除Screenshots目录下所有文件
		 GetImages.DelAllFile(); 
	     
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys
		 ("123abc中@efg.com");
		 
		 driver.getKeyboard().pressKey(Keys.RETURN);
		 
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").sendKeys
		 ("123456");
		 
		 driver.getKeyboard().pressKey(Keys.RETURN);
		 
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").sendKeys
		 ("123456");
		 
		 driver.hideKeyboard();
		 
		 driver.findElementByName(Read_Excel.R_Excel("确认注册")).click();
		 
	     //连续截20张图
	     GetImages.takeTwentyPictures();

		 if(AllTest.ExcelPath().endsWith("7.4德语表.xls"))
		 {	
		     if(AllTest.EnterColumns==2)
		     {
		    	//裁剪100高度居中图片
		    	GetImages.cutCenterImage(100);
			 }else
			 {			 
			     GetImages.cutCenterImage(200);
			 }
	         //图片去躁后保存在tmp
	         GetImages.cleanImages();
	         //图像识别
	         GetImages.GetImage();
		 }else if(AllTest.ExcelPath().endsWith("法语总表.xls"))
		 {
			GetImages.cutCenterImage(200);
			//识别原图		    	
		    GetImages.orgImage();
		 }
	     //去除空格和换行
	     GetImages.txtForm();
	     //合并所有txt文件
	     GetImages.CombineAlltxt();    
	    	
	     assertTrue(GetImages.txtcontain(Read_Excel.R_Excel("账号格式错误")));  
	    	
	     GetImages.DelAllFile();    	 	 	
	 }
	 
	 //邮箱账号，账号不能没有@
	 @Test  
	 public void test14MailSignup() throws Exception 
	 {
		 //删除Screenshots目录下所有文件
		 GetImages.DelAllFile(); 
	     
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys
		 ("123abcefg.com");
		 
		 driver.getKeyboard().pressKey(Keys.RETURN);
		 
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").sendKeys
		 ("123456");
		 
		 driver.getKeyboard().pressKey(Keys.RETURN);
		 
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").sendKeys
		 ("123456");
		 
		 driver.hideKeyboard();
		 
		 driver.findElementByName(Read_Excel.R_Excel("确认注册")).click();
		 
	     //连续截20张图
	     GetImages.takeTwentyPictures();
	     
	     if(AllTest.ExcelPath().endsWith("7.4德语表.xls"))
	     {
	    	 if(AllTest.EnterColumns == 3)
	    	 {
	    		 GetImages.cutCenterImage(100);
	    	 }else
	    	 {
	    		 GetImages.cutCenterImage(200);
	    	 }	    	 
	     }else
	     {	    	 
	    	 //裁剪200高度居中图片
		     GetImages.cutCenterImage(200);
	     }	
	   
	     //图片去躁后保存在tmp
	     GetImages.cleanImages();
	     //图像识别
	     GetImages.GetImage();
	     //去除空格和换行
	     GetImages.txtForm();
	     //合并所有txt文件
	     GetImages.CombineAlltxt();    
	    	
	     assertTrue(GetImages.txtcontain(Read_Excel.R_Excel("用户邮箱或手机号格式错误")));  
	    	
	     GetImages.DelAllFile();	 	 	
	 }
	 
	 //邮箱账号，账号不能没有.
	 @Test  
	 public void test15MailSignup() throws Exception 
	 {
		 //删除Screenshots目录下所有文件
		 GetImages.DelAllFile(); 
	     
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys
		 ("123abc@efgcom");
		 
		 driver.getKeyboard().pressKey(Keys.RETURN);
		 
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").sendKeys
		 ("123456");
		 
		 driver.getKeyboard().pressKey(Keys.RETURN);
		 
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").sendKeys
		 ("123456");
		 
		 driver.hideKeyboard();
		 
		 driver.findElementByName(Read_Excel.R_Excel("确认注册")).click();
		 
	     //连续截20张图
	     GetImages.takeTwentyPictures();
	     
		 if(AllTest.ExcelPath().endsWith("7.4德语表.xls"))
		 {	
		     if(AllTest.EnterColumns==2)
		     {
		    	//裁剪100高度居中图片
		    	GetImages.cutCenterImage(100);
			 }else
			 {			 
			     GetImages.cutCenterImage(200);
			 }
	         //图片去躁后保存在tmp
	         GetImages.cleanImages();
	         //图像识别
	         GetImages.GetImage();
		 }else if(AllTest.ExcelPath().endsWith("法语总表.xls"))
		 {
			 GetImages.cutCenterImage(200);
			 //识别原图		    	
		     GetImages.orgImage();
		 }
	     //去除空格和换行
	     GetImages.txtForm();
	     //合并所有txt文件
	     GetImages.CombineAlltxt();    
	    	
	     assertTrue(GetImages.txtcontain(Read_Excel.R_Excel("账号格式错误")));  
	    	
	     GetImages.DelAllFile();	      	
	 }
	 
	 //邮箱账号，账号不能出现2个@
	 @Test  
	 public void test16MailSignup() throws Exception 
	 {
		 //删除Screenshots目录下所有文件
		 GetImages.DelAllFile(); 
	     
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys
		 ("123abc@@efg.com");
		 
		 driver.getKeyboard().pressKey(Keys.RETURN);
		 
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").sendKeys
		 ("123456");
		 
		 driver.getKeyboard().pressKey(Keys.RETURN);
		 
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").sendKeys
		 ("123456");
		 
		 driver.hideKeyboard();
		 
		 driver.findElementByName(Read_Excel.R_Excel("确认注册")).click();
		 
	     //连续截20张图
	     GetImages.takeTwentyPictures();
	     
		 if(AllTest.ExcelPath().endsWith("7.4德语表.xls"))
		 {	
		     if(AllTest.EnterColumns==2)
		     {
		    	//裁剪100高度居中图片
		    	GetImages.cutCenterImage(100);
			 }else
			 {			 
			     GetImages.cutCenterImage(200);
			 }
	         //图片去躁后保存在tmp
	         GetImages.cleanImages();
	         //图像识别
	         GetImages.GetImage();
		 }else if(AllTest.ExcelPath().endsWith("法语总表.xls"))
		 {
			 GetImages.cutCenterImage(200);
			 //识别原图		    	
		     GetImages.orgImage();
		 }
	     //去除空格和换行
	     GetImages.txtForm();
	     //合并所有txt文件
	     GetImages.CombineAlltxt();    
	    	
	     assertTrue(GetImages.txtcontain(Read_Excel.R_Excel("账号格式错误")));  
	    	
	     GetImages.DelAllFile();	 	
	 }
	 
	 //邮箱账号，账号不能最后一位为@
	 @Test  
	 public void test18MailSignup() throws Exception 
	 {
		 //删除Screenshots目录下所有文件
		 GetImages.DelAllFile(); 
	     
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys
		 ("123abcefg.com@");
		 
		 driver.getKeyboard().pressKey(Keys.RETURN);
		 
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").sendKeys
		 ("123456");
		 
		 driver.getKeyboard().pressKey(Keys.RETURN);
		 
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").sendKeys
		 ("123456");
		 
		 driver.hideKeyboard();
		 
		 driver.findElementByName(Read_Excel.R_Excel("确认注册")).click();
		 
	     //连续截20张图
	     GetImages.takeTwentyPictures();
	     
		 if(AllTest.ExcelPath().endsWith("7.4德语表.xls"))
		 {	
		     if(AllTest.EnterColumns==2)
		     {
		    	//裁剪100高度居中图片
		    	GetImages.cutCenterImage(100);
			 }else
			 {			 
			     GetImages.cutCenterImage(200);
			 }
	         //图片去躁后保存在tmp
	         GetImages.cleanImages();
	         //图像识别
	         GetImages.GetImage();
		 }else if(AllTest.ExcelPath().endsWith("法语总表.xls"))
		 {
			 GetImages.cutCenterImage(200);
			 //识别原图		    	
		     GetImages.orgImage();
		 }
	     //去除空格和换行
	     GetImages.txtForm();
	     //合并所有txt文件
	     GetImages.CombineAlltxt();    
	    	
	     assertTrue(GetImages.txtcontain(Read_Excel.R_Excel("账号格式错误")));  
	    	
	     GetImages.DelAllFile();
	 }
	 
	 //邮箱账号，账号@不能在最后的.后面
	 @Test  
	 public void test19MailSignup() throws Exception 
	 {
		 //删除Screenshots目录下所有文件
		 GetImages.DelAllFile(); 
	     
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys
		 ("123abc.efg.@com");
		 
		 driver.getKeyboard().pressKey(Keys.RETURN);
		 
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").sendKeys
		 ("123456");
		 
		 driver.getKeyboard().pressKey(Keys.RETURN);
		 
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").sendKeys
		 ("123456");
		 
		 driver.hideKeyboard();
		 
		 driver.findElementByName(Read_Excel.R_Excel("确认注册")).click();
		 
	     //连续截20张图
	     GetImages.takeTwentyPictures();
	     
		 if(AllTest.ExcelPath().endsWith("7.4德语表.xls"))
		 {	
		     if(AllTest.EnterColumns==2)
		     {
		    	//裁剪100高度居中图片
		    	GetImages.cutCenterImage(100);
			 }else
			 {			 
			     GetImages.cutCenterImage(200);
			 }
	         //图片去躁后保存在tmp
	         GetImages.cleanImages();
	         //图像识别
	         GetImages.GetImage();
		 }else if(AllTest.ExcelPath().endsWith("法语总表.xls"))
		 {
			 GetImages.cutCenterImage(200);
			 //识别原图		    	
		     GetImages.orgImage();
		 }
	     //去除空格和换行
	     GetImages.txtForm();
	     //合并所有txt文件
	     GetImages.CombineAlltxt();    
	    	
	     assertTrue(GetImages.txtcontain(Read_Excel.R_Excel("账号格式错误")));  
	    	
	     GetImages.DelAllFile();	     	 	
	 }
	 
	 //邮箱账号，账号最后一位不能为.
	 @Test  
	 public void test20MailSignup() throws Exception 
	 {
		 //删除Screenshots目录下所有文件
		 GetImages.DelAllFile(); 
	     
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys
		 ("123abc@efgcom.");
		 
		 driver.getKeyboard().pressKey(Keys.RETURN);
		 
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").sendKeys
		 ("123456");
		 
		 driver.getKeyboard().pressKey(Keys.RETURN);
		 
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").sendKeys
		 ("123456");
		 
		 driver.hideKeyboard();
		 
		 driver.findElementByName(Read_Excel.R_Excel("确认注册")).click();
		 
	     //连续截20张图
	     GetImages.takeTwentyPictures();
	     
		 if(AllTest.ExcelPath().endsWith("7.4德语表.xls"))
		 {
		     if(AllTest.EnterColumns==2)
		     {
		    	//裁剪100高度居中图片
		    	GetImages.cutCenterImage(100);
			 }else
			 {			 
			     GetImages.cutCenterImage(200);
			 }
	         //图片去躁后保存在tmp
	         GetImages.cleanImages();
	         //图像识别
	         GetImages.GetImage();
		 }else if(AllTest.ExcelPath().endsWith("法语总表.xls"))
		 {
			GetImages.cutCenterImage(200);
			//识别原图		    	
		    GetImages.orgImage();
		 }
	     //去除空格和换行
	     GetImages.txtForm();
	     //合并所有txt文件
	     GetImages.CombineAlltxt();    
	    	
	     assertTrue(GetImages.txtcontain(Read_Excel.R_Excel("账号格式错误")));  
	    	
	     GetImages.DelAllFile(); 	
	 }
	 
	 //邮箱账号，账号@后面不能接.
	 @Test  
	 public void test21MailSignup() throws Exception 
	 {
		 //删除Screenshots目录下所有文件
		 GetImages.DelAllFile(); 
	     
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys
		 ("123abc@.efgcom");
		 
		 driver.getKeyboard().pressKey(Keys.RETURN);
		 
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").sendKeys
		 ("123456");
		 
		 driver.getKeyboard().pressKey(Keys.RETURN);
		 
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").sendKeys
		 ("123456");
		 
		 driver.hideKeyboard();
		 
		 driver.findElementByName(Read_Excel.R_Excel("确认注册")).click();
		 
	     //连续截20张图
	     GetImages.takeTwentyPictures();
	     
		 if(AllTest.ExcelPath().endsWith("7.4德语表.xls"))
		 {	
		     if(AllTest.EnterColumns==2)
		     {
		    	//裁剪100高度居中图片
		    	GetImages.cutCenterImage(100);
			 }else
			 {			 
			     GetImages.cutCenterImage(200);
			 }
	         //图片去躁后保存在tmp
	         GetImages.cleanImages();
	         //图像识别
	         GetImages.GetImage();
		 }else if(AllTest.ExcelPath().endsWith("法语总表.xls"))
		 {
			GetImages.cutCenterImage(200);
			//识别原图		    	
		    GetImages.orgImage();
		 }
	     //去除空格和换行
	     GetImages.txtForm();
	     //合并所有txt文件
	     GetImages.CombineAlltxt();    
	    	
	     assertTrue(GetImages.txtcontain(Read_Excel.R_Excel("账号格式错误")));  
	    	
	     GetImages.DelAllFile(); 	
	 }
	 
	 //邮箱账号，账号需要@与.
	 @Test  
	 public void test22MailSignup() throws Exception 
	 {
		 //删除Screenshots目录下所有文件
		 GetImages.DelAllFile(); 
	     
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys
		 ("wenlin.jiayou@fooxmail.com");
		 
		 driver.getKeyboard().pressKey(Keys.RETURN);
		 
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").sendKeys
		 ("123456");
		 
		 driver.getKeyboard().pressKey(Keys.RETURN);
		 
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").sendKeys
		 ("123456");
		 
		 driver.hideKeyboard();
		 
		 driver.findElementByName(Read_Excel.R_Excel("确认注册")).click();
		 
	     //连续截20张图
	     GetImages.takeTwentyPictures();
	     
	     if(AllTest.ExcelPath().endsWith("7.4德语表.xls"))
	     {
	    	 if(AllTest.EnterColumns == 3)
	    	 {
	    		 GetImages.cutCenterImage(100);
	    	 }else
	    	 {
	    		 GetImages.cutCenterImage(200);
	    	 }	    	 
	     }else
	     {	    	 
	    	 //裁剪200高度居中图片
		     GetImages.cutCenterImage(200);
	     }	
	     
	     //图片去躁后保存在tmp
	     GetImages.cleanImages();
	     //图像识别
	     GetImages.GetImage();
	     //去除空格和换行
	     GetImages.txtForm();
	     //合并所有txt文件
	     GetImages.CombineAlltxt();    
	    	
	     assertTrue(GetImages.txtcontain(Read_Excel.R_Excel("用户邮箱或手机号格式错误")));  
	    	
	     GetImages.DelAllFile(); 	
	 }
	 
	 //账号字符不能为1位
	 @Test  
	 public void test23UsersNameUnable() throws Exception 
	 {
		 //删除Screenshots目录下所有文件
		 GetImages.DelAllFile();  
	     
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys
		 ("1");
		 
		 driver.getKeyboard().pressKey(Keys.RETURN);
		 
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").sendKeys
		 ("123456");
		 
		 driver.getKeyboard().pressKey(Keys.RETURN);
		 
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").sendKeys
		 ("123456");
		 
		 driver.hideKeyboard();
		 
		 assertFalse("确认注册可点击",driver.findElementByName(Read_Excel.R_Excel("确认注册")).isEnabled());	
	 }
	 
	 //账号字符可以为2位
	 @Test  
	 public void test24UsersNameEnable() throws Exception 
	 {
	     
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys
		 ("12");
		 
		 driver.getKeyboard().pressKey(Keys.RETURN);
		 
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").sendKeys
		 ("123456");
		 
		 driver.getKeyboard().pressKey(Keys.RETURN);
		 
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").sendKeys
		 ("123456");
		 
		 driver.hideKeyboard();
		 
		 assertTrue("确认注册不可点击",driver.findElementByName(Read_Excel.R_Excel("确认注册")).isEnabled());	
	 }
	 
	 //账号字符可以为3位
	 @Test  
	 public void test25UsersNameEnable() throws Exception 
	 {	     
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys
		 ("123");
		 
		 driver.getKeyboard().pressKey(Keys.RETURN);
		 
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").sendKeys
		 ("123456");
		 
		 driver.getKeyboard().pressKey(Keys.RETURN);
		 
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").sendKeys
		 ("123456");
		 
		 driver.hideKeyboard();
		 
		 assertTrue("确认注册不可点击",driver.findElementByName(Read_Excel.R_Excel("确认注册")).isEnabled());	
	 }
	 
	 //账号字符可以为79位
	 @Test  
	 public void test26UsersNameEnable() throws Exception 
	 {	     
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys
		 ("1234567890123456789012345678901234567890123456789012345678901234567890123456789");
		 
		 driver.getKeyboard().pressKey(Keys.RETURN);
		 
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").sendKeys
		 ("123456");
		 
		 driver.getKeyboard().pressKey(Keys.RETURN);
		 
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").sendKeys
		 ("123456");
		 
		 driver.hideKeyboard();
		 
		 assertTrue("确认注册不可点击",driver.findElementByName(Read_Excel.R_Excel("确认注册")).isEnabled());	
	 }
	 
	 //账号字符可以为80位
	 @Test  
	 public void test27UsersNameEnable() throws Exception 
	 {
	     
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys
		 ("12345678901234567890123456789012345678901234567890123456789012345678901234567890");
		 
		 driver.getKeyboard().pressKey(Keys.RETURN);
		 
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").sendKeys
		 ("123456");
		 
		 driver.getKeyboard().pressKey(Keys.RETURN);
		 
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").sendKeys
		 ("123456");
		 
		 driver.hideKeyboard();
		 
		 assertTrue("确认注册不可点击",driver.findElementByName(Read_Excel.R_Excel("确认注册")).isEnabled());	
	 }
	 
	 //账号字符最多80位，不能输入81位
	 @Test  
	 public void test28UsersNameEnable() throws Exception 
	 {
	     
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys
		 ("123456789012345678901234567890123456789012345678901234567890123456789012345678901");
		 
		 driver.getKeyboard().pressKey(Keys.RETURN);
		 
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").sendKeys
		 ("123456");
		 
		 driver.getKeyboard().pressKey(Keys.RETURN);
		 
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").sendKeys
		 ("123456");
		 
		 driver.hideKeyboard();
		 
		 assertEquals("最多不是80位","12345678901234567890123456789012345678901234567890123456789012345678901234567890"
				 ,driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").getText());	
	 }
	 
	 //手机号获取验证码
	 @Test  
	 public void test29PhoneAuthcodeSignup() throws Exception 
	 {
		 GetImages.DelAllFile();
		 
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys
		 ("13000000000");
		 
		 driver.getKeyboard().pressKey(Keys.RETURN);		 
		 
		 driver.findElementByName(Read_Excel.R_Excel("获取验证码")).click();
		 
	     //连续截20张图
	     GetImages.takeTwentyPictures();
	     
	     if(AllTest.ExcelPath().endsWith("7.4德语表.xls"))
	     {
		     if(AllTest.EnterColumns == 2 )
		     {
			     //裁剪100高度居中图片
			     GetImages.cutCenterImage(100);
		     }else if(AllTest.EnterColumns == 3)
		     {
		    	 GetImages.cutCertenImage(100, 600);
		     }else
		     {
		    	 GetImages.cutCenterImage(200);
		     }
	     }else if(AllTest.ExcelPath().endsWith("法语总表.xls"))
	     {
	    	 GetImages.cutCenterImage(200);
	     }
	     
	     //图片去躁后保存在tmp
	     GetImages.cleanImages();
	     //图像识别
	     GetImages.GetImage();
	     //去除空格和换行
	     GetImages.txtForm();
	     //合并所有txt文件
	     GetImages.CombineAlltxt();    
	    	
	     assertTrue(GetImages.txtcontain(Read_Excel.R_Excel("验证码已发送，请注意查收")));  
	    	
	     GetImages.DelAllFile();	 	 	
	 }
	 
	 //邮箱获取验证码
	 @Test  
	 public void test01MailAuthcodesignup() throws Exception 
	 {
		 //删除Screenshots目录下所有文件
		 GetImages.DelAllFile();
	     
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys
		 ("130@df.com");
		 
		 driver.getKeyboard().pressKey(Keys.RETURN);		 
		 
		 driver.findElementByName(Read_Excel.R_Excel("获取验证码")).click();
		 
	     //连续截20张图
	     GetImages.takeTwentyPictures();
	     
	     if(AllTest.ExcelPath().endsWith("7.4德语表.xls"))
	     {
		     if(AllTest.EnterColumns == 2 )
		     {
			     //裁剪100高度居中图片
			     GetImages.cutCenterImage(100);
		     }else if(AllTest.EnterColumns == 3)
		     {
		    	 GetImages.cutCertenImage(100, 600);
		     }else
		     {
		    	 GetImages.cutCenterImage(200);
		     }
	     }else if(AllTest.ExcelPath().endsWith("法语总表.xls"))
	     {
	    	 GetImages.cutCenterImage(200);
	     }
	     
	     //图片去躁后保存在tmp
	     GetImages.cleanImages();
	     //图像识别
	     GetImages.GetImage();
	     //去除空格和换行
	     GetImages.txtForm();
	     //合并所有txt文件
	     GetImages.CombineAlltxt();    
	    	
	     assertTrue(GetImages.txtcontain(Read_Excel.R_Excel("验证码已发送，请注意查收")));  
	    	
	     GetImages.DelAllFile();
	 }
	 
	 //toast"账号格式错误"
	 @Test  
	 public void test31ClickAuthcode() throws Exception 
	 {
		 //输入格式错误的邮箱
		 
		 //等待上一个用例验证码的倒计时结束才开始用例
		 CommonUtils.WaitForName(driver, Read_Excel.R_Excel("获取验证码"), 60);
		 //删除Screenshots目录下所有文件
		 GetImages.DelAllFile();
	     
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys
		 ("130df.com");
		 
		 driver.getKeyboard().pressKey(Keys.RETURN);		 
		 
		 driver.findElementByName(Read_Excel.R_Excel("获取验证码")).click();
		 
	     //连续截20张图
	     GetImages.takeTwentyPictures();
		 if(AllTest.ExcelPath().endsWith("7.4德语表.xls"))
		 {	
		     if(AllTest.EnterColumns==2)
		     {
		    	//裁剪100高度居中图片
		    	GetImages.cutCenterImage(100);
			 }else
			 {			 
			     GetImages.cutCenterImage(200);
			 }
	         //图片去躁后保存在tmp
	         GetImages.cleanImages();
	         //图像识别
	         GetImages.GetImage();
		 }else if(AllTest.ExcelPath().endsWith("法语总表.xls"))
		 {
			 GetImages.cutCenterImage(200);
			 //识别原图		    	
		     GetImages.orgImage();
		 }
	     //去除空格和换行
	     GetImages.txtForm();
	     //合并所有txt文件
	     GetImages.CombineAlltxt();    
	    	
	     assertTrue(GetImages.txtcontain(Read_Excel.R_Excel("账号格式错误")));  
	    	
	     GetImages.DelAllFile();	 
	     
	     //输入格式错误的手机
	     //删除Screenshots目录下所有文件
	     GetImages.DelAllFile();
	     
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys
		 ("130000000000");
		 
		 driver.getKeyboard().pressKey(Keys.RETURN);
		 
		 driver.findElementByName(Read_Excel.R_Excel("获取验证码")).click();
		 
	     //连续截20张图
	     GetImages.takeTwentyPictures();
		 if(AllTest.ExcelPath().endsWith("7.4德语表.xls"))
		 {	
		     if(AllTest.EnterColumns==2)
		     {
		    	//裁剪100高度居中图片
		    	GetImages.cutCenterImage(100);
			 }else
			 {			 
			     GetImages.cutCenterImage(200);
			 }
	         //图片去躁后保存在tmp
	         GetImages.cleanImages();
	         //图像识别
	         GetImages.GetImage();
		 }else if(AllTest.ExcelPath().endsWith("法语总表.xls"))
		 {
			 GetImages.cutCenterImage(200);
			 //识别原图		    	
		     GetImages.orgImage();
		 }
	     //去除空格和换行
	     GetImages.txtForm();
	     //合并所有txt文件
	     GetImages.CombineAlltxt();    
	    	
	     assertTrue(GetImages.txtcontain(Read_Excel.R_Excel("账号格式错误")));
	     
	     GetImages.DelAllFile();
	 }
	 
	 //toast"该账号已被注册，请直接登录"
	 @Test  
	 public void test56ExistCountSignup() throws Exception 
	 {
		 //输入已注册手机
		 //删除Screenshots目录下所有文件
		 GetImages.DelAllFile();
	     
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys
		 ("13000000001");
		 
		 driver.getKeyboard().pressKey(Keys.RETURN);
		 		 
		 driver.findElementByName(Read_Excel.R_Excel("获取验证码")).click();
		 
	     //连续截20张图
	     GetImages.takeTwentyPictures();
	     if(AllTest.EnterColumns==2)
	     {
		     //裁剪100高度居中图片
		     GetImages.cutCenterImage(100);
	     }else
	     {
	    	 GetImages.cutCenterImage(200);
	     }
	     //图片去躁后保存在tmp
	     GetImages.cleanImages();
	     //图像识别
	     GetImages.GetImage();
	     //去除空格和换行
	     GetImages.txtForm();
	     //合并所有txt文件
	     GetImages.CombineAlltxt();  
	     
	     if(AllTest.ExcelPath().endsWith("7.4德语表.xls"))	
	     {
		     assertTrue(GetImages.txtcontain(Read_Excel.R_Excel("该账号已被注册,请直接登录")));	
	     }else if(AllTest.ExcelPath().endsWith("法语总表.xls"))
	     {
	    	 assertTrue(GetImages.txtcontain(Read_Excel.R_Excel("该帐号已被注册，请直接登录")));		    	 
	     }else
	     {
	    	 System.out.println("请输入正确的Excel路径");
	     } 	 	     
	     //输入已注册的邮箱
	     //删除Screenshots目录下所有文件
	     GetImages.DelAllFile();
	     
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys
		 ("wenlin.jiayou@foxmail.com");
		 
		 driver.getKeyboard().pressKey(Keys.RETURN);
		 		 
		 driver.findElementByName(Read_Excel.R_Excel("获取验证码")).click();
		 
	     //连续截20张图
	     GetImages.takeTwentyPictures();
	     if(AllTest.EnterColumns==2)
	     {
		     //裁剪100高度居中图片
		     GetImages.cutCenterImage(100);
	     }else
	     {
	    	 GetImages.cutCenterImage(200);
	     }
	     //图片去躁后保存在tmp
	     GetImages.cleanImages();
	     //图像识别
	     GetImages.GetImage();
	     //去除空格和换行
	     GetImages.txtForm();
	     //合并所有txt文件
	     GetImages.CombineAlltxt();    
	    	
	     if(AllTest.ExcelPath().endsWith("7.4德语表.xls"))	
	     {
		     assertTrue(GetImages.txtcontain(Read_Excel.R_Excel("该账号已被注册,请直接登录")));	
	     }else if(AllTest.ExcelPath().endsWith("法语总表.xls"))
	     {
	    	 assertTrue(GetImages.txtcontain(Read_Excel.R_Excel("该帐号已被注册，请直接登录")));	
	    	 
	     }else
	     {
	    	 System.out.println("请输入正确的Excel路径");
	     }
	     
	     GetImages.DelAllFile();
	 }
	 
	 //密码字符可以为英文字母、数字、字符
	 @Test
	 public void test34PwCanbeEnNuCh()
	 {		 
		 //删除Screenshots目录下所有文件
		 GetImages.DelAllFile();
	     		 
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").sendKeys
		 ("abcdefg1234567~!@#$%");
		 
		 driver.getKeyboard().pressKey(Keys.RETURN);
		 		 
		 driver.findElementByName("密码不可见").click();
		 
		 assertEquals("提示语消失","abcdefg1234567~!@#$%",driver.findElementByXPath
				 ("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").getText());			 		 
	 }
	 
	 //密码可输入19位
	 @Test
	 public void test35PwCanbe19()
	 {		     
		 
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").sendKeys
		 ("1234567890123456789");
		 
		 driver.getKeyboard().pressKey(Keys.RETURN);
		 
		 driver.findElementByName("密码不可见").click();
		 
		 assertEquals("提示语消失","1234567890123456789",driver.findElementByXPath
				 ("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").getText());
	 }
	 
	 //密码可输入20位
	 @Test
	 public void test36PwCanbe20()
	 {			 
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").sendKeys
		 ("12345678901234567890");
		 
		 driver.getKeyboard().pressKey(Keys.RETURN);
		 
		 driver.findElementByName("密码不可见").click();
		 
		 assertEquals("提示语消失","12345678901234567890",driver.findElementByXPath
				 ("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").getText());
	 }
	 
	 //密码不能输入21位
	 @Test
	 public void test37PwCantbe21()
	 {	    		 
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").sendKeys
		 ("123456789012345678901");
		 
		 driver.getKeyboard().pressKey(Keys.RETURN);	
		 
		 driver.findElementByName("密码不可见").click();
		 
		 assertEquals("提示语消失","12345678901234567890",driver.findElementByXPath
				 ("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").getText());
	 }
	 
	 //密码可见按钮
	 @Test
	 public void test38PwCanbesaw()
	 {	  
		 
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").sendKeys
		 ("abcdefg1234567~!@#$%");
		 
		 driver.getKeyboard().pressKey(Keys.RETURN);			 
		 
		 assertEquals("密码可见","••••••••••••••••••••",driver.findElementByXPath
				 ("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").getText());			 		 
	 }
	 //密码不可少于6位
	 @Test
	 public void test39PwUnable()
	 {		 	     
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys
		 ("0123456789");
		 
		 driver.getKeyboard().pressKey(Keys.RETURN);
		 
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").sendKeys("12345");
		 
		 driver.getKeyboard().pressKey(Keys.RETURN);
		 
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").sendKeys("123456");
		 
		 driver.hideKeyboard();
		 
		 assertFalse("注册可点击",driver.findElementByName(Read_Excel.R_Excel("确认注册")).isEnabled());	
	 }
	 
	 //密码可以输入6位
	 @Test
	 public void test40PwnEable()
	 {		 	     
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys
		 ("0123456789");
		 
		 driver.getKeyboard().pressKey(Keys.RETURN);
		 
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").sendKeys("123456");
		 
		 driver.getKeyboard().pressKey(Keys.RETURN);
		 
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").sendKeys("123456");
		 
		 driver.hideKeyboard();
		 
		 assertTrue("注册不可点击",driver.findElementByName(Read_Excel.R_Excel("确认注册")).isEnabled());	
	 }
	 
	 //密码可输入7位
	 @Test
	 public void test41PwEnable()
	 {		 	     
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys
		 ("0123456789");
		 
		 driver.getKeyboard().pressKey(Keys.RETURN);
		 
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").sendKeys("1234567");
		 
		 driver.getKeyboard().pressKey(Keys.RETURN);
		 
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").sendKeys("123456");
		 
		 driver.hideKeyboard();
		 
		 assertTrue("注册不可点击",driver.findElementByName(Read_Excel.R_Excel("确认注册")).isEnabled());	
	 }
	 
	 //验证码不能少于6位
	 @Test
	 public void test42AcUnable()
	 {		 	     
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys
		 ("0123456789");
		 
		 driver.getKeyboard().pressKey(Keys.RETURN);
		 
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").sendKeys("123456");
		 
		 driver.getKeyboard().pressKey(Keys.RETURN);
		 
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").sendKeys("12345");
		 
		 driver.hideKeyboard();
		 
		 assertFalse("注册可点击",driver.findElementByName(Read_Excel.R_Excel("确认注册")).isEnabled());	
	 }
	 
	 //验证码不能输入7位
	 @Test
	 public void test44AcCantbeSeven()
	 {		
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").sendKeys("1234567");
		 
		 driver.hideKeyboard();
		 
		 assertEquals("验证码可以输入七位","123456",driver.findElementByXPath("//UIAApplication[1]"
		 		+ "/UIAWindow[1]/UIATextField[2]").getText());	
	 }
	 
	 //服务协议同意框可打勾
	 @Test
	 public void test45CheckBox()
	 {		 
	     assertTrue(driver.findElementByName("同意协议对号1").isEnabled());
	     
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys
		 ("0123456789");
		 
		 driver.getKeyboard().pressKey(Keys.RETURN);
		 
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").sendKeys("123456");
		 
		 driver.getKeyboard().pressKey(Keys.RETURN);
		 
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").sendKeys("123456");
		 
		 driver.hideKeyboard();
		 
		 driver.findElementByName("同意协议对号1").click();
		 
		 assertFalse("确认注册不可点击",driver.findElementByName(Read_Excel.R_Excel("确认注册")).isEnabled());
	 }
	 
	 //返回登录页
	 @Test
	 public void test47GoLoginPage()
	 {
		 driver.findElementByName(Read_Excel.R_Excel("< 返回登录")).click();
		 assertTrue("没有返回登陆页",driver.findElementByName(Read_Excel.R_Excel("确认登录")).isDisplayed());
		 
		 driver.findElementByName(Read_Excel.R_Excel("注册账号")).click();
	 }
	 
	 //提示验证码错误
	 @Test
	 public void test48ErrorAc() throws Exception
	 {
	     //删除Screenshots目录下所有文件
	     GetImages.DelAllFile();
		 //输入未注册的手机
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys
		 ("13000000000");
		 
		 driver.getKeyboard().pressKey(Keys.RETURN);
		 
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").sendKeys("123456");
		 
		 driver.getKeyboard().pressKey(Keys.RETURN);
		 
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").sendKeys("123456");
		 
		 driver.hideKeyboard();
		 
		 driver.findElementByName(Read_Excel.R_Excel("确认注册")).click();
		 
	     //连续截20张图
	     GetImages.takeTwentyPictures();
	     
	     if(AllTest.ExcelPath().endsWith("7.4德语表.xls"))
	     {
		     if(AllTest.EnterColumns==2)
		     {
			     //裁剪100高度居中图片
			     GetImages.cutCenterImage(100);
		     }else if(AllTest.EnterColumns == 3)
		     {
		    	 GetImages.cutCertenImage(100, 600);
		     }else
		     {
		    	 GetImages.cutCenterImage(200);
		     }
	     }else if(AllTest.ExcelPath().endsWith("法语总表.xls"))
	     {
	    	 GetImages.cutCenterImage(200);
	     }
	     
	     //图片去躁后保存在tmp
	     GetImages.cleanImages();
	     //图像识别
	     GetImages.GetImage();
	     //去除空格和换行
	     GetImages.txtForm();
	     //合并所有txt文件
	     GetImages.CombineAlltxt();    
	    	
	     assertTrue(GetImages.txtcontain(Read_Excel.R_Excel("验证码错误")));
	     
	     //输入未注册的邮箱
	     //删除Screenshots目录下所有文件
	     GetImages.DelAllFile();
	     
	     driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys
		 ("13000000000@qq.com");
		 
		 driver.getKeyboard().pressKey(Keys.RETURN);
		 
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").sendKeys("123456");
		 
		 driver.getKeyboard().pressKey(Keys.RETURN);
		 
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").sendKeys("123456");
		 
		 driver.hideKeyboard();
		 
		 driver.findElementByName(Read_Excel.R_Excel("确认注册")).click();
		 
	     //连续截20张图
	     GetImages.takeTwentyPictures();
	     
	     if(AllTest.ExcelPath().endsWith("7.4德语表.xls"))
	     {
		     if(AllTest.EnterColumns==2)
		     {
			     //裁剪100高度居中图片
			     GetImages.cutCenterImage(100);
		     }else if(AllTest.EnterColumns == 3)
		     {
		    	 GetImages.cutCertenImage(100, 600);
		     }else
		     {
		    	 GetImages.cutCenterImage(200);
		     }
	     }else if(AllTest.ExcelPath().endsWith("法语总表.xls"))
	     {
	    	 GetImages.cutCenterImage(200);
	     }
	     
	     //图片去躁后保存在tmp
	     GetImages.cleanImages();
	     //图像识别
	     GetImages.GetImage();
	     //去除空格和换行
	     GetImages.txtForm();
	     //合并所有txt文件
	     GetImages.CombineAlltxt();    
	    	
	     assertTrue(GetImages.txtcontain(Read_Excel.R_Excel("验证码错误")));
	     
	     GetImages.DelAllFile();
	 }
	 
	 //手机可注册
	 @Test
	 public void test51PhoneRegister() throws Exception
	 {
		 //删除Screenshots目录下所有文件
		 GetImages.DelAllFile();
	     
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys
		 ("13000000000");
		 
		 driver.getKeyboard().pressKey(Keys.RETURN);
		 
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").sendKeys("123456");
		 
		 driver.getKeyboard().pressKey(Keys.RETURN);
		 
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").sendKeys("666666");
		 
		 driver.hideKeyboard();
		 
		 driver.findElementByName(Read_Excel.R_Excel("确认注册")).click();
		 
         assertTrue(CommonUtils.WaitForName(driver, Read_Excel.R_Excel("飞行"), 10));
         
         //退出登录
     	 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIAElement[3]").click();
    	
     	 new WebDriverWait(driver,10).until(
 	    			 ExpectedConditions.invisibilityOfElementLocated(By.name(Read_Excel.R_Excel("飞行"))));
     	 //向下滑动屏幕直到出现退出登录
     	 int x=driver.manage().window().getSize().width;
 		 int y=driver.manage().window().getSize().height;
 		 do 
 		 {
 			 driver.swipe(x/2, y/4*3, x/2, y/4*1, 0);			
 		 }while(CommonUtils.WaitForName(driver, Read_Excel.R_Excel("退出登录"),3)==false);
 		
     	 driver.findElementByName(Read_Excel.R_Excel("退出登录")).click();  
     	 //不加try会出现异常，可能是appium的bug
     	 try
     	 {
     		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIAAlert[1]/UIACollecti"
         			 + "onView[1]/UIACollectionCell[2]/UIAButton[1]").click();  	
     	 }catch(Exception e)
     	 {
     		
     	 }
     	 assertTrue(CommonUtils.WaitForName(driver, Read_Excel.R_Excel("确认登录"), 10));
	 }
	 
	 //邮箱可注册
	 @Test
	 public void test52MailRegister() throws Exception
	 {
	     
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys
		 ("13000000000@qq.com");
		 
		 driver.getKeyboard().pressKey(Keys.RETURN);
		 
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").sendKeys("123456");
		 
		 driver.getKeyboard().pressKey(Keys.RETURN);
		 
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").sendKeys("666666");
		 
		 driver.hideKeyboard();
		 
		 driver.findElementByName(Read_Excel.R_Excel("确认注册")).click();
		 
         assertTrue(CommonUtils.WaitForName(driver, Read_Excel.R_Excel("飞行"), 10));
         
         //退出登录
        	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIAElement[3]").click();
       	
       	 new WebDriverWait(driver,10).until(
   	    		 	 ExpectedConditions.invisibilityOfElementLocated(By.name(Read_Excel.R_Excel("飞行"))));
       	 //向下滑动屏幕直到出现退出登录
       	 int x=driver.manage().window().getSize().width;
   		 int y=driver.manage().window().getSize().height;
   		 do
   		 {
   			 driver.swipe(x/2, y/4*3, x/2, y/4*1, 0);			
   		 }while(CommonUtils.WaitForName(driver, Read_Excel.R_Excel("退出登录"),3)==false);
   		
       	 driver.findElementByName(Read_Excel.R_Excel("退出登录")).click();  
       	 //不加try会出现异常，可能是appium的bug
       	 try
       	 {
       		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIAAlert[1]/UIACollecti"
           			 + "onView[1]/UIACollectionCell[2]/UIAButton[1]").click();  	
       	 }catch(Exception e)
       	 {
       		
       	 }
       	 assertTrue(CommonUtils.WaitForName(driver, Read_Excel.R_Excel("确认登录"), 10));
	 }
	 
	 //注册需要账号、密码、验证码、勾选同意
	 @Test
	 public void test55UnAcPwChNeeded()
	 {		 
	     
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys
		 ("123456");
		 
		 driver.getKeyboard().pressKey(Keys.RETURN);
		 
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").sendKeys("123456");
		 
		 driver.getKeyboard().pressKey(Keys.RETURN);
		 
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").sendKeys("123456");
		 
		 driver.hideKeyboard();
		 
		 //去除勾选
		 driver.findElementByName("同意协议对号1").click();
		 
		 assertFalse("确认注册可点击",driver.findElementByName(Read_Excel.R_Excel("确认注册")).isEnabled());
		 
		 driver.findElementByName("同意协议对号0").click();
		 
		 //去除账号
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
		 
		 driver.getKeyboard().pressKey(Keys.RETURN);
		 
		 assertFalse("确认注册不可点击",driver.findElementByName(Read_Excel.R_Excel("确认注册")).isEnabled());
		 
		 //去除密码
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys
		 ("123456");
		 
		 driver.getKeyboard().pressKey(Keys.RETURN);
		 
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").clear();
		 
		 driver.getKeyboard().pressKey(Keys.RETURN);
		 
		 assertFalse("确认注册不可点击",driver.findElementByName(Read_Excel.R_Excel("确认注册")).isEnabled());
		 
		 //去除验证码
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").clear();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").sendKeys("123456");
		 
		 driver.getKeyboard().pressKey(Keys.RETURN);
		 
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").click();
		 driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").clear();
		 
		 driver.hideKeyboard();
		 
		 assertFalse("确认注册不可点击",driver.findElementByName(Read_Excel.R_Excel("确认注册")).isEnabled());
	 }
 	
}

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
public class LoginPageTest
{	
    static AppiumDriver driver;
	@BeforeClass
    public static void setUpBeforeClass() throws Exception 
    { 	
				
    }		
	
	@Before
	public void setUp() throws Exception 
    { 
		driver= CommonUtils.CreatAppiumDriver();
		CommonUtils.GoToLoginPage(driver); 
    }
	//登陆页页面多语言判断
	@Test
	public void test01CheckString()
	{
		assertEquals(Read_Excel.R_Excel("输入您的手机号或邮箱"),
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").getText());
		assertEquals(Read_Excel.R_Excel("输入您的账号密码"),
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").getText());
		assertEquals(Read_Excel.R_Excel("确认登录"),driver.findElementByName(Read_Excel.R_Excel("确认登录")).getText());
		assertEquals(Read_Excel.R_Excel("注册账号"),driver.findElementByName(Read_Excel.R_Excel("注册账号")).getText());		
		assertEquals(Read_Excel.R_Excel("快速登录"),driver.findElementByName(Read_Excel.R_Excel("快速登录")).getText());
		if(AllTest.ExcelPath().endsWith("法语总表.xls"))
		{
			assertEquals(Read_Excel.R_Excel("忘记密码").replace("é", "é"),driver.findElementByName(Read_Excel.R_Excel("忘记密码")).getText());
		}else
		{
			assertEquals(Read_Excel.R_Excel("忘记密码"),driver.findElementByName(Read_Excel.R_Excel("忘记密码")).getText());
		}
	}
	//账号框提示语消失，键盘拉出			
    @Test 
    public void test02SetUsersNameAs1() 
    {
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys("1");  
    	
        assertEquals("输入的不是1",String.valueOf(1), driver.findElementByXPath
    		    ("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").getText());     	                             
    }  
    
    //账号字符可以为英文字母、数字、字符
    @Test  
    public void test03SetUsersNameAs123() 
    {
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys("123abc～!@.");  
    	
        assertEquals("输入的不是123abc~!@.","123abc～!@.", driver.findElementByXPath
    		    ("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").getText());        	        
    }
    
    //手机号码登录，不能为10位数字
    @Test
    public void test04PhoneLogin() throws Exception 
    {
    	GetImages.DelAllFile();
    	
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys("1234567890"); 
    	
    	driver.getKeyboard().pressKey(Keys.RETURN);
    	
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").click();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").clear();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").sendKeys("123456");
    	
    	driver.getKeyboard().pressKey(Keys.RETURN);
    	
    	driver.findElementByName(Read_Excel.R_Excel("确认登录")).click(); 
    	
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
        	GetImages.cutCenterImage(100);
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
    
    // 手机号码登录，需要为11位数字     
    @Test
    public void test05PhoneLogin() throws Exception 
    {
    	//删除Screenshots目录下所有文件
    	GetImages.DelAllFile();   	
    	
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys("13411111111"); 
    	
    	driver.getKeyboard().pressKey(Keys.RETURN);
    	
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").click();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").clear();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").sendKeys("123456");
    	
    	driver.getKeyboard().pressKey(Keys.RETURN);
    	
    	driver.findElementByName(Read_Excel.R_Excel("确认登录")).click();
    	
        GetImages.takeTwentyPictures();
        
        if(AllTest.ExcelPath().endsWith("7.4德语表.xls"))
        {
        	if(AllTest.EnterColumns==2)
        	{
        		GetImages.cutCertenImage(100, 500);        	
        	}else
        	{
        		GetImages.cutCenterImage(200);
        	}
        }else if(AllTest.ExcelPath().endsWith("法语总表.xls"))
        {
        	GetImages.cutCenterImage(100);
        }
    	
    	GetImages.cleanImages();
    	
    	GetImages.GetImage();
    	
    	GetImages.txtForm();
    	
    	GetImages.CombineAlltxt();   
    	
        assertTrue(GetImages.txtcontain(Read_Excel.R_Excel("该账号未注册")));  
    	
    	GetImages.DelAllFile(); 	    	
    }     
 
    //手机号码登录，不能为12位数字
    @Test
    public void test06PhoneLogin()throws Exception
    {
    	//删除Screenshots目录下所有文件
    	GetImages.DelAllFile(); 
    	
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys("123456789012"); 
    	
    	driver.getKeyboard().pressKey(Keys.RETURN);
    	
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").click();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").clear();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").sendKeys("123456");
    	
    	driver.getKeyboard().pressKey(Keys.RETURN);
    	
    	driver.findElementByName(Read_Excel.R_Excel("确认登录")).click();
    	
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
        	GetImages.cutCenterImage(100);
	    	//识别原图
	    	GetImages.orgImage();
	    }
    	
    	GetImages.txtForm();
    	
    	GetImages.CombineAlltxt();    
    	
    	assertTrue(GetImages.txtcontain(Read_Excel.R_Excel("账号格式错误")));  
    	
    	GetImages.DelAllFile(); 
    }
    
    // 手机号码登录，可以为14开头的11位数字      
    @Test
    public void test08PhoneLogin() throws Exception
    {
    	//删除Screenshots目录下所有文件
    	GetImages.DelAllFile(); 
    	
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys("14345678901"); 
    	
    	driver.getKeyboard().pressKey(Keys.RETURN);
    	
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").click();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").clear();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").sendKeys("123456");
    	
    	driver.getKeyboard().pressKey(Keys.RETURN);
    	
    	driver.findElementByName(Read_Excel.R_Excel("确认登录")).click();
    	
        GetImages.takeTwentyPictures();
    	
        if(AllTest.ExcelPath().endsWith("7.4德语表.xls"))
        {
        	if(AllTest.EnterColumns==2)
        	{
        		GetImages.cutCertenImage(100, 500);        	
        	}else
        	{
        		GetImages.cutCenterImage(200);
        	}
        }else if(AllTest.ExcelPath().endsWith("法语总表.xls"))
        {
        	GetImages.cutCenterImage(100);
        }
    	
    	GetImages.cleanImages();
    	
    	GetImages.GetImage();
    	
    	GetImages.txtForm();
    	
    	GetImages.CombineAlltxt();   
    	
        assertTrue(GetImages.txtcontain(Read_Excel.R_Excel("该账号未注册")));  
    	
    	GetImages.DelAllFile();  
    }
    
    // 手机号码登录，可以为15开头的11位数字      
    @Test
    public void test09PhoneLogin() throws Exception
    {
    	//删除Screenshots目录下所有文件
    	GetImages.DelAllFile(); 
    	
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys("15345678901"); 
    	
    	driver.getKeyboard().pressKey(Keys.RETURN);
    	
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").click();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").clear();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").sendKeys("123456");
    	
    	driver.getKeyboard().pressKey(Keys.RETURN);
    	
    	driver.findElementByName(Read_Excel.R_Excel("确认登录")).click();
    	
        GetImages.takeTwentyPictures();
    	
        if(AllTest.ExcelPath().endsWith("7.4德语表.xls"))
        {
        	if(AllTest.EnterColumns==2)
        	{
        		GetImages.cutCertenImage(100, 500);        	
        	}else
        	{
        		GetImages.cutCenterImage(200);
        	}
        }else if(AllTest.ExcelPath().endsWith("法语总表.xls"))
        {
        	GetImages.cutCenterImage(100);
        }
    	
    	GetImages.cleanImages();
    	
    	GetImages.GetImage();
    	
    	GetImages.txtForm();
    	
    	GetImages.CombineAlltxt();   
    	
        assertTrue(GetImages.txtcontain(Read_Excel.R_Excel("该账号未注册")));  
    	
    	GetImages.DelAllFile();
    }

    // 手机号码登录，可以为17开头的11位数字     
    @Test
    public void test10PhoneLogin() throws Exception
    {
    	//删除Screenshots目录下所有文件
    	GetImages.DelAllFile(); 
    	
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys("17345678901"); 
    	
    	driver.getKeyboard().pressKey(Keys.RETURN);
    	
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").click();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").clear();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").sendKeys("123456");
    	
    	driver.getKeyboard().pressKey(Keys.RETURN);
    	
    	driver.findElementByName(Read_Excel.R_Excel("确认登录")).click();
    	
        GetImages.takeTwentyPictures();
    	
        if(AllTest.ExcelPath().endsWith("7.4德语表.xls"))
        {
        	if(AllTest.EnterColumns==2)
        	{
        		GetImages.cutCertenImage(100, 500);        	
        	}else
        	{
        		GetImages.cutCenterImage(200);
        	}
        }else if(AllTest.ExcelPath().endsWith("法语总表.xls"))
        {
        	GetImages.cutCenterImage(100);
        }
    	
    	GetImages.cleanImages();
    	
    	GetImages.GetImage();
    	
    	GetImages.txtForm();
    	
    	GetImages.CombineAlltxt();   
    	
        assertTrue(GetImages.txtcontain(Read_Excel.R_Excel("该账号未注册")));  
    	
    	GetImages.DelAllFile(); 
    }

    //手机号码登录，可以为18开头的11位数字     
    @Test
    public void test11PhoneLogin() throws Exception
    {
    	//删除Screenshots目录下所有文件
    	GetImages.DelAllFile(); 
    	
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys("18345678901"); 
    	
    	driver.getKeyboard().pressKey(Keys.RETURN);
    	
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").click();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").clear();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").sendKeys("123456");
    	
    	driver.getKeyboard().pressKey(Keys.RETURN);
    	
    	driver.findElementByName(Read_Excel.R_Excel("确认登录")).click();
    	
        GetImages.takeTwentyPictures();
    	
        if(AllTest.ExcelPath().endsWith("7.4德语表.xls"))
        {
        	if(AllTest.EnterColumns==2)
        	{
        		GetImages.cutCertenImage(100, 500);        	
        	}else
        	{
        		GetImages.cutCenterImage(200);
        	}
        }else if(AllTest.ExcelPath().endsWith("法语总表.xls"))
        {
        	GetImages.cutCenterImage(100);
        }
    	
    	GetImages.cleanImages();
    	
    	GetImages.GetImage();
    	
    	GetImages.txtForm();
    	
    	GetImages.CombineAlltxt();   
    	
        assertTrue(GetImages.txtcontain(Read_Excel.R_Excel("该账号未注册")));  
    	
    	GetImages.DelAllFile(); 
    }
    
    //邮箱登录，账号不能出现中文    
    @Test
    public void test12MailLoginWithCN() throws Exception
    {
    	//删除Screenshots目录下所有文件
    	GetImages.DelAllFile(); 
    	
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys("123abc中@efg.com"); 
    	
    	driver.getKeyboard().pressKey(Keys.RETURN);
    	
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").click();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").clear();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").sendKeys("123456");
    	
    	driver.getKeyboard().pressKey(Keys.RETURN);
    	
    	driver.findElementByName(Read_Excel.R_Excel("确认登录")).click();
    	
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
        	GetImages.cutCenterImage(100);
	    	//识别原图
	    	GetImages.orgImage();
	    } 
    	
    	GetImages.txtForm();
    	
    	GetImages.CombineAlltxt();    
    	
    	assertTrue(GetImages.txtcontain(Read_Excel.R_Excel("账号格式错误")));  
    	
    	GetImages.DelAllFile(); 
     }

    //邮箱登录，账号不能没有@
    @Test
    public void test13MailLogin() throws Exception
    {
    	//删除Screenshots目录下所有文件
    	GetImages.DelAllFile(); 
    	
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys("123abcefg.com"); 
    	
    	driver.getKeyboard().pressKey(Keys.RETURN);   	
    	try
    	{
    		driver.getKeyboard().pressKey(Keys.RETURN);
    	}catch(Exception e)
    	{
    		
    	}
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").click();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").clear();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").sendKeys("123456");
    	
    	driver.getKeyboard().pressKey(Keys.RETURN);
    	
    	driver.findElementByName(Read_Excel.R_Excel("确认登录")).click();
    	
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
        	GetImages.cutCenterImage(100);
	    	//识别原图
	    	GetImages.orgImage();
	    } 
    	
    	GetImages.txtForm();
    	
    	GetImages.CombineAlltxt();    
    	
    	assertTrue(GetImages.txtcontain(Read_Excel.R_Excel("账号格式错误")));  
    	
    	GetImages.DelAllFile(); 
     }
    
    //邮箱登录，账号不能没有.
    @Test
    public void test14MailLogin() throws Exception
    {
    	//删除Screenshots目录下所有文件
    	GetImages.DelAllFile(); 
    	
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys("123abc@efgcom"); 
    	
    	driver.getKeyboard().pressKey(Keys.RETURN);
    	
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").click();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").clear();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").sendKeys("123456");
    	
    	driver.getKeyboard().pressKey(Keys.RETURN);
    	
    	driver.findElementByName(Read_Excel.R_Excel("确认登录")).click();
    	
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
        	GetImages.cutCenterImage(100);
	    	//识别原图
	    	GetImages.orgImage();
	    } 
    	
    	GetImages.txtForm();
    	
    	GetImages.CombineAlltxt();    
    	
    	assertTrue(GetImages.txtcontain(Read_Excel.R_Excel("账号格式错误")));  
    	
    	GetImages.DelAllFile();
    }
    
    //邮箱登录，账号不能出现2个@
    @Test
    public void test15MailLogin() throws Exception
    {
    	//删除Screenshots目录下所有文件
    	GetImages.DelAllFile();  
    	
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys("123abc@@efg.com"); 
    	
    	driver.getKeyboard().pressKey(Keys.RETURN);
    	try
    	{
    		driver.getKeyboard().pressKey(Keys.RETURN);
    	}catch(Exception e)
    	{
    		
    	}
    	
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").click();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").clear();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").sendKeys("123456");
    	
    	driver.getKeyboard().pressKey(Keys.RETURN);
    	
    	driver.findElementByName(Read_Excel.R_Excel("确认登录")).click();
    	
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
        	GetImages.cutCenterImage(100);
	    	//识别原图
	    	GetImages.orgImage();
	    } 
    	
    	GetImages.txtForm();
    	
    	GetImages.CombineAlltxt();    
    	
    	assertTrue(GetImages.txtcontain(Read_Excel.R_Excel("账号格式错误")));  
    	
    	GetImages.DelAllFile();
    }
    
    //邮箱登录，账号不能第一位为@
    @Test
    public void test16MailLogin() throws Exception
    {
	   	//删除Screenshots目录下所有文件
    	GetImages.DelAllFile(); 
    	
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys("@123abcefg.com"); 
    	
    	driver.getKeyboard().pressKey(Keys.RETURN);
    	try
    	{
    		driver.getKeyboard().pressKey(Keys.RETURN);
    	}catch(Exception e)
    	{
    		
    	}
    	
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").click();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").clear();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").sendKeys("123456");
    	
    	driver.getKeyboard().pressKey(Keys.RETURN);
    	
    	driver.findElementByName(Read_Excel.R_Excel("确认登录")).click();   	
    	
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
        	GetImages.cutCenterImage(100);
	    	//识别原图
	    	GetImages.orgImage();
	    } 
    	
    	GetImages.txtForm();
    	
    	GetImages.CombineAlltxt();    
    	
    	assertTrue(GetImages.txtcontain(Read_Excel.R_Excel("账号格式错误")));  
    	
    	GetImages.DelAllFile();
    }
    
    //邮箱登录，账号不能最后一位为@
    @Test
    public void test17MailLogin() throws Exception
    {
	   	//删除Screenshots目录下所有文件
    	GetImages.DelAllFile(); 
    	
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys("123abcefg.com@"); 
    	
    	driver.getKeyboard().pressKey(Keys.RETURN);
    	try
    	{
    		driver.getKeyboard().pressKey(Keys.RETURN);
    	}catch(Exception e)
    	{
    		
    	}
    	
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").click();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").clear();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").sendKeys("123456");
    	
    	driver.getKeyboard().pressKey(Keys.RETURN);
    	
    	driver.findElementByName(Read_Excel.R_Excel("确认登录")).click();
    	
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
        	GetImages.cutCenterImage(100);
	    	//识别原图
	    	GetImages.orgImage();
	    } 
    	
    	GetImages.txtForm();
    	
    	GetImages.CombineAlltxt();    
    	
    	assertTrue(GetImages.txtcontain(Read_Excel.R_Excel("账号格式错误")));  
    	
    	GetImages.DelAllFile(); 
    }
    
    //邮箱登录，账号@不能在最后的.后面
    @Test
    public void test18MailLogin() throws Exception
    {
    	//删除Screenshots目录下所有文件
    	GetImages.DelAllFile(); 
    	
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys("123abc.efg.@com"); 
    	
    	driver.getKeyboard().pressKey(Keys.RETURN);
    	try
    	{
    		driver.getKeyboard().pressKey(Keys.RETURN);
    	}catch(Exception e)
    	{
    		
    	}
    	
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").click();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").clear();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").sendKeys("123456");
    	
    	driver.getKeyboard().pressKey(Keys.RETURN);
    	
    	driver.findElementByName(Read_Excel.R_Excel("确认登录")).click();
    	
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
        	GetImages.cutCenterImage(100);
	    	//识别原图
	    	GetImages.orgImage();
	    } 
    	
    	GetImages.txtForm();
    	
    	GetImages.CombineAlltxt();    
    	
    	assertTrue(GetImages.txtcontain(Read_Excel.R_Excel("账号格式错误")));  
    	
    	GetImages.DelAllFile();
    }
    
    //邮箱登录，账号最后一位不能为.
    @Test
    public void test19MailLogin() throws Exception
    {
	   	//删除Screenshots目录下所有文件
    	GetImages.DelAllFile(); 
    	
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys("123abc@efgcom."); 
    	
    	driver.getKeyboard().pressKey(Keys.RETURN);
    	try
    	{
    		driver.getKeyboard().pressKey(Keys.RETURN);
    	}catch(Exception e)
    	{
    		
    	}
    	
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").click();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").clear();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").sendKeys("123456");
    	
    	driver.getKeyboard().pressKey(Keys.RETURN);   	
    	
    	driver.findElementByName(Read_Excel.R_Excel("确认登录")).click();
    	
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
        	GetImages.cutCenterImage(100);
	    	//识别原图
	    	GetImages.orgImage();
	    } 
    	
    	GetImages.txtForm();
    	
    	GetImages.CombineAlltxt();    
    	
    	assertTrue(GetImages.txtcontain(Read_Excel.R_Excel("账号格式错误")));  
    	
    	GetImages.DelAllFile(); 
    }
    
    //邮箱登录，账号@后面不能接.
    @Test
    public void test20MailLogin() throws Exception
    {
    	//删除Screenshots目录下所有文件
    	GetImages.DelAllFile(); 
    	
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys("123abc@.efgcom"); 
    	
    	driver.getKeyboard().pressKey(Keys.RETURN);
    	try
    	{
    		driver.getKeyboard().pressKey(Keys.RETURN);
    	}catch(Exception e)
    	{
    		
    	}
    	
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").click();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").clear();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").sendKeys("123456");
    	
    	driver.getKeyboard().pressKey(Keys.RETURN);
    	
    	driver.findElementByName(Read_Excel.R_Excel("确认登录")).click();
    	
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
        	GetImages.cutCenterImage(100);
	    	//识别原图
	    	GetImages.orgImage();
	    } 
    	
    	GetImages.txtForm();
    	
    	GetImages.CombineAlltxt();    
    	
    	assertTrue(GetImages.txtcontain(Read_Excel.R_Excel("账号格式错误")));  
    	
    	GetImages.DelAllFile(); 
    }
    
    //邮箱登录，账号需要@与.
    @Test
    public void test21MailLogin() throws Exception
    {
    	//删除Screenshots目录下所有文件
    	GetImages.DelAllFile(); 
    	
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys("123abc@efg.com"); 
    	
    	driver.getKeyboard().pressKey(Keys.RETURN);
    	try
    	{
    		driver.getKeyboard().pressKey(Keys.RETURN);
    	}catch(Exception e)
    	{
    		
    	}
    	
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").click();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").clear();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").sendKeys("123456");
    	
    	driver.getKeyboard().pressKey(Keys.RETURN);
    	
    	driver.findElementByName(Read_Excel.R_Excel("确认登录")).click();
    	
        GetImages.takeTwentyPictures();
    	
        if(AllTest.ExcelPath().endsWith("7.4德语表.xls"))
        {
        	if(AllTest.EnterColumns==2)
        	{
        		GetImages.cutCertenImage(100, 500);        	
        	}else
        	{
        		GetImages.cutCenterImage(200);
        	}
        }else if(AllTest.ExcelPath().endsWith("法语总表.xls"))
        {
        	GetImages.cutCenterImage(100);
        }
    	
    	GetImages.cleanImages();
    	
    	GetImages.GetImage();
    	
    	GetImages.txtForm();
    	
    	GetImages.CombineAlltxt();   
    	
        assertTrue(GetImages.txtcontain(Read_Excel.R_Excel("该账号未注册")));  
    	
    	GetImages.DelAllFile();
    }
    
    //账号字符不能为1位
    @Test
    public void test22ButtonUnable()
    {
    	//删除Screenshots目录下所有文件
    	GetImages.DelAllFile(); 
    	
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys("1"); 
    	
    	driver.getKeyboard().pressKey(Keys.RETURN);
    	
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").click();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").clear();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").sendKeys("123456");
    	
    	driver.getKeyboard().pressKey(Keys.RETURN);
    	
    	assertFalse("登录按钮可点击",driver.findElementByName(Read_Excel.R_Excel("确认登录")).isEnabled());
    }
    
    //账号字符可以为2位
    @Test
    public void test23ButtonEnable() 
    {   	
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys("12"); 
    	
    	driver.getKeyboard().pressKey(Keys.RETURN);
    	
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").click();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").clear();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").sendKeys("123456");
    	
    	driver.getKeyboard().pressKey(Keys.RETURN);
    	
    	assertTrue("登录按钮不可点击",driver.findElementByName(Read_Excel.R_Excel("确认登录")).isEnabled());
    }
    
    //账号字符可以为3位
    @Test
    public void test24ButtonEnable() 
    {    
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys("123"); 
    	
    	driver.getKeyboard().pressKey(Keys.RETURN);
    	
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").click();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").clear();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").sendKeys("123456");
    	
    	driver.getKeyboard().pressKey(Keys.RETURN);
    	
    	assertTrue("登录按钮不可点击",driver.findElementByName(Read_Excel.R_Excel("确认登录")).isEnabled());
    }
    
    //账号字符可以为79位
    @Test
    public void test25ButtonEnable() 
    {   	
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]")
    	.sendKeys("1234567890123456789012345678901234567890123456789012345678901234567890123456789"); 
    	
    	driver.getKeyboard().pressKey(Keys.RETURN);
    	
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").click();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").clear();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").sendKeys("123456");
    	
    	driver.getKeyboard().pressKey(Keys.RETURN);
    	
    	assertTrue("登录按钮不可点击",driver.findElementByName(Read_Excel.R_Excel("确认登录")).isEnabled());
    }
    
    //账号字符可以为80位
    @Test
    public void test26ButtonEnable() 
    {    	
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]")
    	.sendKeys("12345678901234567890123456789012345678901234567890123456789012345678901234567890"); 
    	
    	driver.getKeyboard().pressKey(Keys.RETURN);
    	
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").click();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").clear();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").sendKeys("123456");
    	
    	driver.getKeyboard().pressKey(Keys.RETURN);
    	
    	assertTrue("登录按钮不可点击",driver.findElementByName(Read_Excel.R_Excel("确认登录")).isEnabled());
    }
    
    //账号字符最多80位，不能输入81位
    @Test
    public void test27UserLimit() 
    {    	
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]")
    	.sendKeys("123456789012345678901234567890123456789012345678901234567890123456789012345678901"); 
    	
    	driver.getKeyboard().pressKey(Keys.RETURN);
    	
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").click();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").clear();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").sendKeys("123456");
    	
    	driver.getKeyboard().pressKey(Keys.RETURN);
    	
    	assertEquals("最多不是80位","12345678901234567890123456789012345678901234567890123456789012345678901234567890"
    			,driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").getText());
    }
    
    //密码框提示语消失
    @Test
    public void test28PasswordAs1() 
    {     
    	
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").click();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").clear();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").sendKeys("1");
    	
    	driver.getKeyboard().pressKey(Keys.RETURN);
    	
    	assertEquals("密码提示语未消失","•",driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").getText());
     }
    
    //密码显示为"*"
    @Test
    public void test29PasswordAs123()
    {   
    	
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").click();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").clear();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").sendKeys("123456");
    	
    	driver.getKeyboard().pressKey(Keys.RETURN);
    	
    	assertEquals("密码提示语未消失","••••••",driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").getText());
     }
    
    //密码字符可以为英文字母、数字、字符
    @Test
    public void test30PasswordAsEn()
    {   
    	
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").click();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").clear();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").sendKeys("123abc~!@.");
    	
    	driver.getKeyboard().pressKey(Keys.RETURN);
    	
    	assertEquals("密码提示语未消失","••••••••••",driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").getText());
     }
    
    //密码可见按钮
    @Test
    public void test31PasswordCanbesaw() 
    {   
    	
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").click();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").clear();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").sendKeys("123456");
    	
    	driver.getKeyboard().pressKey(Keys.RETURN);
    	
    	driver.findElementByName("密码不可见").click();
    	
    	assertEquals("密码不可见","123456",driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").getText());
    	
    	driver.findElementByName("密码可见").click();
    	
    	assertEquals("密码不可见","••••••",driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").getText());
     }
    
    //密码不能为5位
    @Test
    public void test32ButtonUnable() 
    {   
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys("123");
    	
    	driver.getKeyboard().pressKey(Keys.RETURN);
    	
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").click();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").clear();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").sendKeys("12345");
    	
    	driver.getKeyboard().pressKey(Keys.RETURN);
    	   	
    	assertFalse("登录按钮可点击",driver.findElementByName(Read_Excel.R_Excel("确认登录")).isEnabled());
     }
    
    //密码可以为6位
    @Test
    public void test33ButtonEnable() 
    {   
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys("123");
    	
    	driver.getKeyboard().pressKey(Keys.RETURN);
    	
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").click();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").clear();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").sendKeys("123456");
    	
    	driver.getKeyboard().pressKey(Keys.RETURN);
    	   	
    	assertTrue("登录按钮不可点击",driver.findElementByName(Read_Excel.R_Excel("确认登录")).isEnabled());
     }
    
    //密码可以为7位
    @Test
    public void test34ButtonEnable() 
    {   
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys("123");
    	
    	driver.getKeyboard().pressKey(Keys.RETURN);
    	
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").click();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").clear();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").sendKeys("1234567");
    	
    	driver.getKeyboard().pressKey(Keys.RETURN);
    	   	
    	assertTrue("登录按钮不可点击",driver.findElementByName(Read_Excel.R_Excel("确认登录")).isEnabled());
     }
    
    //密码可以为19位
    @Test
    public void test35ButtonEnable() 
    {   
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys("123");
    	
    	driver.getKeyboard().pressKey(Keys.RETURN);
    	
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").click();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").clear();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").sendKeys("1234567890123456789");
    	
    	driver.getKeyboard().pressKey(Keys.RETURN);
    	   	
    	assertTrue("登录按钮不可点击",driver.findElementByName(Read_Excel.R_Excel("确认登录")).isEnabled());
     }
    
    //密码可以为20位
    @Test
    public void test36ButtonEnable() 
    {   
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys("123");
    	
    	driver.getKeyboard().pressKey(Keys.RETURN);
    	
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").click();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").clear();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").sendKeys("12345678901234567890");
    	
    	driver.getKeyboard().pressKey(Keys.RETURN);
    	
        driver.findElementByName("密码不可见").click();
    	
    	assertEquals("密码不可见","12345678901234567890",driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").getText());
    	
    	driver.findElementByName("密码可见").click(); 
    	
    	assertTrue("登录按钮不可点击",driver.findElementByName(Read_Excel.R_Excel("确认登录")).isEnabled());
     }
    
    //密码最多为20位,不能输入21位
    @Test
    public void test37ButtonUnable()
    {   
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys("123");
    	
    	driver.getKeyboard().pressKey(Keys.RETURN);
    	
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").click();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").clear();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").sendKeys("123456789012345678901");
    	
    	driver.getKeyboard().pressKey(Keys.RETURN);
    	   	
        driver.findElementByName("密码不可见").click();
    	
    	assertEquals("密码不可见","12345678901234567890",driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").getText());  	
     }
    
    //确认登录按钮可转为可点击状态
    @Test
    public void test38ButtonEnable() 
    {   
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys("01234567891");
    	
    	driver.getKeyboard().pressKey(Keys.RETURN);
    	
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").click();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").clear();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").sendKeys("123456");
    	
    	driver.getKeyboard().pressKey(Keys.RETURN);
    	
    	assertTrue("登录按钮不可点击",driver.findElementByName(Read_Excel.R_Excel("确认登录")).isEnabled());  	
     }
    
    //登录需要账号
    @Test
    public void test39ButtonUnable() 
    {   
    	
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").click();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").clear();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").sendKeys("123456");
    	
    	driver.getKeyboard().pressKey(Keys.RETURN);
    	
    	assertFalse("登录按钮可点击",driver.findElementByName(Read_Excel.R_Excel("确认登录")).isEnabled());  	
     }
    
    //登录需要密码
    @Test
    public void test40ButtonUnable() 
    {   
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys("123456");
    	
    	driver.getKeyboard().pressKey(Keys.RETURN);
    	
    	assertFalse("登录按钮可点击",driver.findElementByName(Read_Excel.R_Excel("确认登录")).isEnabled());  	
     }
    
    //toast“账号密码不正确”
    @Test
    public void test42CorrectUserLogin() throws Exception 
    {
    	GetImages.DelAllFile(); 
    	
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys("13000000001");
    	
    	driver.getKeyboard().pressKey(Keys.RETURN);
    	
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").click();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").clear();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").sendKeys("123456789");
    	
    	driver.getKeyboard().pressKey(Keys.RETURN);
    	
        driver.findElementByName(Read_Excel.R_Excel("确认登录")).click();
    	
    	GetImages.takeTwentyPictures();
    	
    	if(AllTest.ExcelPath().endsWith("7.4德语表.xls"))
    	{
        	if(AllTest.EnterColumns==2)
        	{
        		GetImages.cutCertenImage(100, 500);        	
        	}else if(AllTest.EnterColumns == 1)
        	{
        		GetImages.cutCenterImage(200);
        	}else if(AllTest.EnterColumns == 3)
        	{
        		GetImages.cutCenterImage(100);
        	}
    	}else if(AllTest.ExcelPath().endsWith("法语总表.xls"))
    	{
    		GetImages.cutCenterImage(200);
    	}
    	
    	GetImages.cleanImages();
    	
    	GetImages.GetImage();
    	
    	GetImages.txtForm();
    	
    	GetImages.CombineAlltxt();    
    	
    	assertTrue(GetImages.txtcontain(Read_Excel.R_Excel("账号密码不正确")));   
    	
    	//删除Screenshots目录下所有文件
    	GetImages.DelAllFile(); 
    	
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys("wenlin.jiayou@foxmail.com");

    	driver.getKeyboard().pressKey(Keys.RETURN);
    	
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").click();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").clear();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").sendKeys("123456789");

    	driver.getKeyboard().pressKey(Keys.RETURN);
    	
    	driver.findElementByName(Read_Excel.R_Excel("确认登录")).click();
    	
    	GetImages.takeTwentyPictures();
    	
    	if(AllTest.ExcelPath().endsWith("7.4德语表.xls"))
    	{
        	if(AllTest.EnterColumns==2)
        	{
        		GetImages.cutCertenImage(100, 500);        	
        	}else if(AllTest.EnterColumns == 1)
        	{
        		GetImages.cutCenterImage(200);
        	}else if(AllTest.EnterColumns == 3)
        	{
        		GetImages.cutCenterImage(100);
        	}
    	}else if(AllTest.ExcelPath().endsWith("法语总表.xls"))
    	{
    		GetImages.cutCenterImage(200);
    	}
    	
    	GetImages.cleanImages();
    	
    	GetImages.GetImage();
    	
    	GetImages.txtForm();
    	
    	GetImages.CombineAlltxt();    
    	
    	assertTrue(GetImages.txtcontain(Read_Excel.R_Excel("账号密码不正确")));  
    	
    	GetImages.DelAllFile();   	  
    }
    
    //手机号码登录
    @Test
    public void test43CorrectPhoneLogin() throws InterruptedException 
    {   
    	//删除Screenshots目录下所有文件
    	GetImages.DelAllFile(); 
    	
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys("13000000001");
    	
    	driver.getKeyboard().pressKey(Keys.RETURN);
    	
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").click();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").clear();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").sendKeys("123456");
    	
    	driver.getKeyboard().pressKey(Keys.RETURN);
    	
    	driver.findElementByName(Read_Excel.R_Excel("确认登录")).click();
    	//判断有没有登录到首页
    	try
    	{
    		 new WebDriverWait(driver,10).until(
		    			ExpectedConditions.presenceOfElementLocated(By.name(Read_Excel.R_Excel("飞行")))); 
    	    assertTrue(true);
    	}catch(Exception e)
    	{
    		assertTrue("未跳到首页",false);
    	}
    	//退出登录
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIAElement[3]").click();
    	
        CommonUtils.WaitForName(driver, "13000000001", 20);
		
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
    
    //邮箱登录
    @Test
    public void test44CorrectMailLogin() 
    {   
    	//删除Screenshots目录下所有文件
    	GetImages.DelAllFile();
    	
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys("wenlin.jiayou@foxmail.com");
    	
    	driver.getKeyboard().pressKey(Keys.RETURN);
    	
    	try
    	{
    		driver.getKeyboard().pressKey(Keys.RETURN);
    	}catch(Exception e)
    	{
    		
    	}
    	
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").click();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").clear();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").sendKeys("123456");
    	
    	driver.getKeyboard().pressKey(Keys.RETURN);
    	
    	driver.findElementByName(Read_Excel.R_Excel("确认登录")).click();
    	
    	assertTrue("未跳到首页",CommonUtils.WaitForName(driver, Read_Excel.R_Excel("飞行") , 10));   	
    	
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIAElement[3]").click();
    	
        CommonUtils.WaitForName(driver, "文文", 20);
		
    	driver.findElementByName(Read_Excel.R_Excel("退出登录")).click();
    	try
    	{
    	driver.findElementByAccessibilityId(Read_Excel.R_Excel("确定登出")).click();
    	}catch(Exception e)
    	{
    		
    	}
    	assertTrue(CommonUtils.WaitForName(driver, Read_Excel.R_Excel("确认登录"), 10));
     }
    
    //跳转到“注册新帐号”页
    @Test
    public void test45GoToRegisterPage() 
    {   
    	driver.findElementByAccessibilityId(Read_Excel.R_Excel("注册账号")).click();   	
    	
    	assertTrue("未跳到注册页",CommonUtils.WaitForName(driver, Read_Excel.R_Excel("确认注册"), 3)); 
    	
    	driver.findElementByAccessibilityId(Read_Excel.R_Excel("< 返回登录")).click();    	
     }
    
    //跳转到“忘记密码”页
    @Test
    public void test46GoToForgetPasswordPage() throws InterruptedException 
    {   
    	driver.findElementByAccessibilityId(Read_Excel.R_Excel("忘记密码")).click();   	
    	
    	assertTrue("未跳到注册页",CommonUtils.WaitForName(driver, Read_Excel.R_Excel("确认修改"), 3)); 
    	
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIANavigat"
    			+ "ionBar[1]/UIAButton[1]/UIAImage[1]").click();    	
    }
    
    //登录需要账号与密码
    @Test
    public void test48ButtonUnable() 
    {  
    	//输入账号和密码
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys("123456");
    	
    	driver.getKeyboard().pressKey(Keys.RETURN);
    	
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").click();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").clear();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").sendKeys("123456");
    	
    	driver.getKeyboard().pressKey(Keys.RETURN);
    	
    	assertTrue("登录按钮不可点击",driver.findElementByName(Read_Excel.R_Excel("确认登录")).isEnabled());  
    	//只有账号
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").click();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").clear();
    	
    	driver.getKeyboard().pressKey(Keys.RETURN);
    	
    	assertFalse("登录按钮可点击",driver.findElementByName(Read_Excel.R_Excel("确认登录")).isEnabled());
    	//只有密码
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();

    	driver.getKeyboard().pressKey(Keys.RETURN);
    	
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").click();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").clear();
    	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIASecureTextField[1]").sendKeys("123456");
    	
    	driver.getKeyboard().pressKey(Keys.RETURN);
    	
    	assertFalse("登录按钮可点击",driver.findElementByName(Read_Excel.R_Excel("确认登录")).isEnabled());
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
	
	
}

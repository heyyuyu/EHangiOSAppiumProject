 /*
  * Author: chengchao.lu
  * Date: 2016.08.01
  * Content: Forget Password
  * 
  */

package iOSApp.test_login;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.Keys;

import iOSApp.test_all.AllTest;
import iOSApp.utils.CommonUtils;
import iOSApp.utils.GetImages;
import iOSApp.utils.Read_Excel;
import io.appium.java_client.AppiumDriver;
import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ForgetPasswordPageTest 
{  
	private static AppiumDriver driver;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		
		
	}
	@Before
	public void setUp() throws Exception
	{
		driver=CommonUtils.CreatAppiumDriver();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIAButton[4]").click();
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
	
	//账号字符可以为英文字母、数字、字符
	@Test
	public void test104AccountCHCanBeEngNumChar() throws Exception
	{
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys("123abc@$");
		driver.getKeyboard().pressKey(Keys.RETURN);
		assertEquals("123abc@$",driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").getText());
	}
	
	//手机号码账号，不能为10位数字
	@Test
	public void test105PhoneNumCanNotOverTenBits() throws Exception
	{
		GetImages.DelAllFile();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys("1234567890");
        driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIAButton[1]").click();
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
	
	//手机号码账号，需要为11位数字
	@Test
	public void test106PhoneNumMustBeEleven() throws Exception
	{
		GetImages.DelAllFile();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys("13978494765");
        driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIAButton[1]").click();
        
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
	
	//手机号码账号，不能为12位数字
	@Test
	public void test107PhoneNumCanNotBeTwelf() throws Exception
	{
		GetImages.DelAllFile();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys("123456789012");
        driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIAButton[1]").click();
        
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
	
	//手机号码账号，可以为13开头的11位数字
	@Test
	public void test108PhoneAccountCanBeElevenNumOf13Start() throws Exception
	{
		GetImages.DelAllFile();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys("13978494765");
        driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIAButton[1]").click();        
        
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
	
	//手机号码账号，可以为14开头的11位数字
	@Test
	public void test109PhoneAccountCanBeElevenNumOf14Start() throws Exception
	{
		GetImages.DelAllFile();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys("14978494765");
        driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIAButton[1]").click();
        
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
	
	//手机号码账号，可以为15开头的11位数字
	@Test
	public void test110PhoneAccountCanBeElevenNumOf15Start() throws Exception
	{
		GetImages.DelAllFile();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys("15978494765");
        driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIAButton[1]").click();
        
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
	
	//手机号码账号，可以为17开头的11位数字
	@Test
	public void test111PhoneAccountCanBeElevenNumOf17Start() throws Exception
	{
		GetImages.DelAllFile();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys("17978494765");
        driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIAButton[1]").click();
                
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
	
	//手机号码账号，可以为18开头的11位数字
	@Test
	public void test112PhoneAccountCanBeElevenNumOf18Start() throws Exception
	{
		GetImages.DelAllFile();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys("18978494765");
        driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIAButton[1]").click();
        
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
	
	//邮箱账号，账号不能出现中文
	@Test
	public void test113MailAccountCanNotBeChi() throws Exception
	{
		GetImages.DelAllFile();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys("123abc中@efg.com");
        driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIAButton[1]").click();
        
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
	
	//邮箱账号，账号不能没有@
	@Test
	public void test114MailAccountCanNotWithoutEt() throws Exception
	{
		GetImages.DelAllFile();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys("123abcefg.com");
        driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIAButton[1]").click();
        
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
	
	//邮箱账号，账号不能没有.
	@Test
	public void test115MailAccountCanNotWithoutDot() throws Exception
	{
		GetImages.DelAllFile();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys("123abc@efgcom");
        driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIAButton[1]").click();
        
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
	
	//邮箱账号，账号不能出现2个@
	@Test
	public void test116MailAccountCanNotBeTwoEt() throws Exception
	{
		GetImages.DelAllFile();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys("123abc@@efg.com");
        driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIAButton[1]").click();
        
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
	
	//邮箱账号，账号不能第一位为@
	@Test
	public void test117MailAccountCanNotBeEtFirst() throws Exception
	{
		GetImages.DelAllFile();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys("@123abcefg.com");
        driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIAButton[1]").click();
        
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
	
	//邮箱账号，账号不能最后一位为@
	@Test
	public void test118MailAccountCanNotBeEtLast() throws Exception
	{
		GetImages.DelAllFile();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys("123abcefg.com@");
        driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIAButton[1]").click();
        
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
	
	//邮箱账号，账号@不能在最后的.后面
	@Test
	public void test119MailAccountEtCanNotBehindDot() throws Exception
	{
		GetImages.DelAllFile();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys("123abc.efg.@com");
        driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIAButton[1]").click();        
        
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
	
	//邮箱账号，账号最后一位不能为.
	@Test
	public void test120MailAccountCanNotBeDotlast() throws Exception
	{
		GetImages.DelAllFile();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys("123abc@efgcom.");
        driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIAButton[1]").click();
        
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
	
	//邮箱账号，账号@后面不能接.
	@Test
	public void test121MailAccountEtBackCanNotConnectDot() throws Exception
	{
		GetImages.DelAllFile();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys("123abc@.efgcom");
        driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIAButton[1]").click();
        
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
	
	//邮箱账号，账号需要@与.
	@Test
	public void test122MailAccountNeedDotAndEt() throws Exception
	{
		GetImages.DelAllFile();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys("changfang.lu@163.com");
        driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIAButton[1]").click();
        
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
	public void test123AccountCanNotBeOneBit() throws Exception
	{
		GetImages.DelAllFile();
		//输入账号
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys("1");
		//输入账号
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").clear();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").sendKeys("123456");
		//输入密码
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[3]").clear();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[3]").sendKeys("123456");
		driver.getKeyboard().pressKey(Keys.RETURN);
        assertFalse(driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIAButton[2]").isEnabled());
	}
	
	//账号字符可以为2位
	@Test
	public void test124AccountCanBeTwoBit() throws Exception
	{
		//输入账号
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys("12");
		//输入账号
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").clear();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").sendKeys("123456");
		//输入密码
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[3]").clear();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[3]").sendKeys("123456");
		driver.getKeyboard().pressKey(Keys.RETURN);
        assertTrue(driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIAButton[2]").isEnabled());
	}
	
	//账号字符可以为3位
	@Test
	public void test125AccountCanBeThreeBit() throws Exception
	{
		//输入账号
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys("123");
		//输入账号
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").clear();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").sendKeys("123456");
		//输入密码
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[3]").clear();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[3]").sendKeys("123456");
		driver.getKeyboard().pressKey(Keys.RETURN);
        assertTrue(driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIAButton[2]").isEnabled());
	}
     
	//账号字符可以为29位
	@Test
	public void test126AccountCanBeTwenty_NightBit() throws Exception
	{
		//输入账号
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys("12345678901234567890123456789");
		//输入账号
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").clear();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").sendKeys("123456");
		//输入密码
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[3]").clear();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[3]").sendKeys("123456");
		driver.getKeyboard().pressKey(Keys.RETURN);
        assertTrue(driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIAButton[2]").isEnabled());
        
	}
     
	//账号字符可以为30位
	@Test
	public void test127AccountCanBeThirtyBit() throws Exception
	{
		
		//输入账号
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys("123456789012345678901234567890");
		//输入验证码
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").clear();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").sendKeys("123456");
		//输入密码
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[3]").clear();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[3]").sendKeys("123456");
		driver.getKeyboard().pressKey(Keys.RETURN);
        assertTrue(driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIAButton[2]").isEnabled());
        
	}
	
	//账号字符最多30位，不能输入31位
	@Test
	public void test128AccountCanNotOVerThirtyBit() throws Exception
	{
		
		//输入账号
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys("1234567890123456789012345678901");
		
		driver.getKeyboard().pressKey(Keys.RETURN);
		assertEquals("1234567890123456789012345678901",driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").getText());
		
	}
	
	//验证码不能为5位
	@Test
	public void test130VeriticationCodeCanNotBeFiveyBit() throws Exception
	{
		
		//输入账号
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys("123456");
		//输入验证码
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").clear();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").sendKeys("12345");
		//输入密码
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[3]").clear();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[3]").sendKeys("123456");
		driver.getKeyboard().pressKey(Keys.RETURN);
        assertFalse(driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIAButton[2]").isEnabled());
	}
	
	//验证码可以为6位
	@Test
	public void test131VeriticationCodeCanBeSixyBit() throws Exception
	{
		
		//输入账号
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys("123456");
		//输入验证码
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").clear();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").sendKeys("123456");
		//输入密码
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[3]").clear();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[3]").sendKeys("123456");
		driver.getKeyboard().pressKey(Keys.RETURN);
        assertTrue(driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIAButton[2]").isEnabled());
	}
	
	//验证码不能大于6位
	@Test
	public void test132VeriticationCodeCanNotOverSixBit() throws Exception
	{
		
		//输入验证码
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").clear();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").sendKeys("1234567");
		
		assertEquals("123456",driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").getText());
	}
	
	//密码可输入19位
	@Test
	public void test136PasswordCanBeNineteenBit() throws Exception
	{
		
		//输入密码
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[3]").clear();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[3]").sendKeys("1234567890123456789");
		driver.getKeyboard().pressKey(Keys.RETURN);
		assertEquals("1234567890123456789",driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[3]").getText());
	}
	
	//密码可输入20位
	@Test
	public void test137PasswordCanBeTwentyBit() throws Exception
	{
		
		//输入密码
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[3]").clear();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[3]").sendKeys("12345678901234567890");
		driver.getKeyboard().pressKey(Keys.RETURN);
		assertEquals("12345678901234567890",driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[3]").getText());
	}
	
	//密码不能输入21位
	@Test
	public void test138PasswordCanBeTwenty_OneBit() throws Exception
	{
		
		//输入密码
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[3]").clear();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[3]").sendKeys("123456789012345678901");
		driver.getKeyboard().pressKey(Keys.RETURN);
		assertEquals("12345678901234567890",driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[3]").getText());
	}
	
	//密码不可少于6位
	@Test
	public void test139PasswordCanNotLessSixBit() throws Exception
	{
		
		//输入账号
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys("0123456789");
		//输入验证码
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").clear();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").sendKeys("123456");
		//输入密码
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[3]").clear();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[3]").sendKeys("12345");
		driver.getKeyboard().pressKey(Keys.RETURN);
        assertFalse(driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIAButton[2]").isEnabled());
	}
	
	//密码可输入6位
	@Test
	public void test140PasswordCanBeSixBit() throws Exception
	{
		
		//输入账号
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys("0123456789");
		//输入验证码
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").clear();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").sendKeys("123456");
		//输入密码
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[3]").clear();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[3]").sendKeys("123456");
		driver.getKeyboard().pressKey(Keys.RETURN);
        assertTrue(driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIAButton[2]").isEnabled());
	}
	
	//密码可输入7位
	@Test
	public void test141PasswordCanBeSevenBit() throws Exception
	{
		
		//输入账号
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys("0123456789");
		//输入验证码
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").clear();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").sendKeys("123456");
		//输入密码
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[3]").clear();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[3]").sendKeys("1234567");
		driver.getKeyboard().pressKey(Keys.RETURN);
        assertTrue(driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIAButton[2]").isEnabled());
	}
	
	//确认修改按钮呈可点击状态
	@Test
	public void test142ConfireBtnCanClick() throws Exception
	{
		
		//输入账号
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys("0123456789");
		//输入验证码
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").clear();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").sendKeys("123456");
		//输入密码
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[3]").clear();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[3]").sendKeys("123456");
		driver.getKeyboard().pressKey(Keys.RETURN);
        assertTrue(driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIAButton[2]").isEnabled());
	}
	
	//未注册的账号不能修改密码
	@Test
	public void test143UnRegisterAccountCanNotFix() throws Exception
	{
		GetImages.DelAllFile();
		//输入账号
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys("13978494765");
		//输入验证码
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").clear();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").sendKeys("123456");
		//输入密码
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[3]").clear();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[3]").sendKeys("1234567");
		driver.getKeyboard().pressKey(Keys.RETURN);
		
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIAButton[2]").click();
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
	
	//提示验证码错误
	@Test
	public void test144TipsVertificationWrong() throws Exception
	{
		GetImages.DelAllFile();
		//输入账号
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys("595080064@qq.com");
		
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIAButton[1]").click();
		//输入验证码
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").clear();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").sendKeys("123456");
		//输入密码
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[3]").clear();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[3]").sendKeys("123456");
		driver.getKeyboard().pressKey(Keys.RETURN);
		
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIAButton[2]").click();
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
		}else if(AllTest.ExcelPath().endsWith("法语总表.xls"))
		{
			GetImages.cutCenterImage(200);
		}
		
	    GetImages.cleanImages();    	
	    GetImages.GetImage();    	
	    GetImages.txtForm();    	
	    GetImages.CombineAlltxt();       	
	    assertTrue(GetImages.txtcontain(Read_Excel.R_Excel("验证码错误")));
	    GetImages.DelAllFile();
	}	
	
	//修改需要账号、验证码、密码，一项都不能少
	@Test
	public void test146NoneCanNot() throws Exception
	{
		
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys("123456");
		//输入验证码
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").clear();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").sendKeys("123456");
		
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[3]").clear();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[3]").sendKeys("123456");
		assertTrue(driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIAButton[2]").isEnabled());
				
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[3]").clear();
		//输入账号
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys("123456");
		//输入验证码
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").clear();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").sendKeys("123456");		
		assertFalse(driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIAButton[2]").isEnabled());
		
		
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").clear();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys("123456");
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[3]").clear();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[3]").sendKeys("123456");
		assertFalse(driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIAButton[2]").isEnabled());
		
		
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").clear();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").sendKeys("123456");
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[3]").clear();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[3]").sendKeys("123456");
		assertFalse(driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIAButton[2]").isEnabled());
		
	}
	
	@Test
	public void test147ForgetPasswordTranslate() throws Exception
	{
		assertTrue("忘记密码",CommonUtils.WaitForName(driver, Read_Excel.R_Excel("忘记密码"), 5));
		assertTrue("我的账号",CommonUtils.WaitForName(driver, Read_Excel.R_Excel("我的账号"), 5));
		assertEquals(Read_Excel.R_Excel("输入您的手机号或邮箱"),driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").getText());				
		assertTrue("验证码",CommonUtils.WaitForName(driver, Read_Excel.R_Excel("验证码"), 5));				
		assertTrue("新密码",CommonUtils.WaitForName(driver, Read_Excel.R_Excel("新密码"), 5));		
		assertTrue("获取验证码",CommonUtils.WaitForName(driver, Read_Excel.R_Excel("获取验证码"), 5));
		assertTrue("确认修改",CommonUtils.WaitForName(driver, Read_Excel.R_Excel("确认修改"), 5));
		if(AllTest.ExcelPath().endsWith("法语总表.xls"))
		{
			assertEquals(Read_Excel.R_Excel("输入验证码").replace("é", "é"),driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").getText());
			assertEquals(Read_Excel.R_Excel("输入账号密码，不少于6位").replace("è","è"),driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[3]").getText());
		}else if(AllTest.ExcelPath().endsWith("7.4德语表.xls"))
		{
			if(AllTest.EnterColumns == 3)
			{
				assertEquals(Read_Excel.R_Excel("输入验证码").replace("ä","ä"),driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").getText());				
			}else
			{
				assertEquals(Read_Excel.R_Excel("输入验证码"),driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").getText());
			}	
			assertEquals(Read_Excel.R_Excel("输入账号密码，不少于6位"),driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[3]").getText());
		}
	}
}

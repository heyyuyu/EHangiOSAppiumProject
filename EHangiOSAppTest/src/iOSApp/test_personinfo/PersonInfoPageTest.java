package iOSApp.test_personinfo;

import static org.junit.Assert.*;
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

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersonInfoPageTest
{	
	private static AppiumDriver driver;
	
	@BeforeClass
    public static void setUpBeforeClass() throws Exception 
    { 		
		
    }		
	
	@Before
	public void setUp() throws Exception 
    { 
		driver= CommonUtils.CreatAppiumDriver();
		CommonUtils.GoToLoginPage(driver); 
		CommonUtils.GoToPersonalInfoPage(driver);
		try
		{
			driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIAElement[3]").click();
	    	
			CommonUtils.WaitForName(driver, "13000000001", 20);		
		}catch(Exception e)
		{
			
		}
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
	//个人中心页多语言判断
	@Test
	public void test01CheckString()
	{
		//更改密码页
		driver.findElementByName(Read_Excel.R_Excel("更改密码")).click();
		
		assertTrue("更改密码",CommonUtils.WaitForName(driver, Read_Excel.R_Excel("更改密码"), 5));
		assertEquals(Read_Excel.R_Excel("我的账号"),driver.findElementByName(Read_Excel.R_Excel("我的账号")).getText());
		assertEquals(Read_Excel.R_Excel("新密码"),driver.findElementByName(Read_Excel.R_Excel("新密码")).getText());				
		assertEquals(Read_Excel.R_Excel("设置新的账号密码"),driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").getText());
		
		if(AllTest.ExcelPath().endsWith("法语总表.xls"))
		{
			assertEquals(Read_Excel.R_Excel("验证码").replace("é", "é"),driver.findElementByName(Read_Excel.R_Excel("验证码")).getText());
			assertEquals(Read_Excel.R_Excel("获取验证码").replace("é", "é"),driver.findElementByName(Read_Excel.R_Excel("获取验证码")).getText());
			assertEquals(Read_Excel.R_Excel("输入验证码").replace("é", "é"),driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").getText());
			assertEquals(Read_Excel.R_Excel("确认修改"),driver.findElementByName(Read_Excel.R_Excel("确认修改")).getText());
		}else if(AllTest.ExcelPath().endsWith("7.4德语表.xls"))
		{
			if(AllTest.EnterColumns == 3)
			{
				assertEquals(Read_Excel.R_Excel("确认修改").replace("ä","ä"),driver.findElementByName(Read_Excel.R_Excel("确认修改")).getText());
				assertEquals(Read_Excel.R_Excel("验证码").replace("ä","ä"),driver.findElementByName(Read_Excel.R_Excel("验证码")).getText());
				assertEquals(Read_Excel.R_Excel("获取验证码").replace("ä","ä"),driver.findElementByName(Read_Excel.R_Excel("获取验证码")).getText());
				assertEquals(Read_Excel.R_Excel("输入验证码").replace("ä","ä"),driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").getText());
			}else
			{
				assertEquals(Read_Excel.R_Excel("确认修改"),driver.findElementByName(Read_Excel.R_Excel("确认修改")).getText());
				assertEquals(Read_Excel.R_Excel("验证码"),driver.findElementByName(Read_Excel.R_Excel("验证码")).getText());
				assertEquals(Read_Excel.R_Excel("获取验证码"),driver.findElementByName(Read_Excel.R_Excel("获取验证码")).getText());
				assertEquals(Read_Excel.R_Excel("输入验证码"),driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").getText());
			}						
			
		}
		
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[1]/UIAImage[1]").click();
		
		//用户反馈页
		driver.findElementByName(Read_Excel.R_Excel("用户反馈")).click();
		
		driver.tap(1, driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIAStaticText[1]"), 100);
		
		assertTrue("用户反馈",CommonUtils.WaitForName(driver, Read_Excel.R_Excel("用户反馈"), 5));
		assertEquals(Read_Excel.R_Excel("提交"),driver.findElementByName(Read_Excel.R_Excel("提交")).getText());			
		assertEquals(Read_Excel.R_Excel("请输入您的个人邮箱"),
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").getText().trim());
				
		if(AllTest.ExcelPath().endsWith("法语总表.xls"))
		{
			assertEquals(Read_Excel.R_Excel("您的建议 （500字以内)").replace("è", "è").replace("à", "à"),
					driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIAStaticText[1]").getText().trim());
			assertEquals(Read_Excel.R_Excel("请填写您对亿航的建议，在这里，您可以畅所欲言").replace("é", "é").replace("è", "è"),
					driver.findElementByName(Read_Excel.R_Excel("请填写您对亿航的建议，在这里，您可以畅所欲言")).getText());
			assertEquals(Read_Excel.R_Excel("感谢您的宝贵意见，亿航将不懈努力，为您提供更加优质的飞行体验~").replace("é", "é"),
					driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIAStaticText[2]").getText().trim());
		}else if(AllTest.ExcelPath().endsWith("7.4德语表.xls"))
		{
			if(AllTest.EnterColumns == 3)
			{
				assertEquals(Read_Excel.R_Excel("请填写您对亿航的建议，在这里，您可以畅所欲言").replace("ü","ü").replace("ö","ö"),
						driver.findElementByName(Read_Excel.R_Excel("请填写您对亿航的建议，在这里，您可以畅所欲言")).getText());
			}
			else
			{
				assertEquals(Read_Excel.R_Excel("请填写您对亿航的建议，在这里，您可以畅所欲言"),
						driver.findElementByName(Read_Excel.R_Excel("请填写您对亿航的建议，在这里，您可以畅所欲言")).getText());
			}
			assertEquals(Read_Excel.R_Excel("您的建议 （500字以内)"),
					driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIAStaticText[1]").getText().trim());
			assertEquals(Read_Excel.R_Excel("感谢您的宝贵意见，亿航将不懈努力，为您提供更加优质的飞行体验~"),
					driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIAStaticText[2]").getText().trim());
		}
		
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[1]/UIAImage[1]").click();
		
		//关于我们
		driver.findElementByName(Read_Excel.R_Excel("关于我们")).click();
		
		assertTrue("关于我们",CommonUtils.WaitForName(driver, Read_Excel.R_Excel("关于我们"), 4));
		assertEquals(Read_Excel.R_Excel("联系我们"),driver.findElementByName(Read_Excel.R_Excel("联系我们")).getText());
		assertEquals(Read_Excel.R_Excel("官方微信"),driver.findElementByName(Read_Excel.R_Excel("官方微信")).getText());
		assertEquals(Read_Excel.R_Excel("官方微博"),driver.findElementByName(Read_Excel.R_Excel("官方微博")).getText());
		assertEquals(Read_Excel.R_Excel("官方网站"),driver.findElementByName(Read_Excel.R_Excel("官方网站")).getText());
		assertEquals(Read_Excel.R_Excel("客服电话"),driver.findElementByName(Read_Excel.R_Excel("客服电话")).getText());
		assertEquals(Read_Excel.R_Excel("EHANG Play 服务协议"),driver.findElementByName(Read_Excel.R_Excel("EHANG Play 服务协议")).getText());			
	}
	//用户反馈页面提交
	@Test
	public void test30Feedback()
	{		
		driver.findElementByName(Read_Excel.R_Excel("用户反馈")).click();
		
		driver.getKeyboard().pressKey(Keys.RETURN);
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextView[1]").clear();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextView[1]")
		.sendKeys("Ehang is very good");
		
		assertEquals("显示与输入不一致","Ehang is very good"
				,driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextView[1]").getText());
		
		driver.findElementByName(Read_Excel.R_Excel("提交")).click();
		
		assertTrue(CommonUtils.WaitForName(driver, Read_Excel.R_Excel("用户反馈"), 8));
	}
	
	// 能够进入更改密码页面 用例32、33、34、35
	@Test 
	public void test32ChangePassword()
	{		
		driver.findElementByName(Read_Excel.R_Excel("更改密码")).click();
		
		assertTrue("未进入更改密码页",CommonUtils.WaitForName(driver,Read_Excel.R_Excel("新密码"), 4));
		
		assertFalse("确认修改可点击",driver.findElementByName(Read_Excel.R_Excel("确认修改")).isEnabled());
		
		assertTrue("获取验证码不可点击",driver.findElementByName(Read_Excel.R_Excel("获取验证码")).isEnabled());
		
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]"
		+ "/UIANavigationBar[1]/UIAButton[1]/UIAImage[1]").click();	
		
		assertTrue(CommonUtils.WaitForName(driver, Read_Excel.R_Excel("用户反馈"), 3));
	}
	
	//获取验证码按钮禁止
	@Test
	public void test36GetAuthcodeUnable()
	{		
		driver.findElementByName(Read_Excel.R_Excel("更改密码")).click();
		
		driver.findElementByName(Read_Excel.R_Excel("获取验证码")).click();
		
		assertFalse(driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIAButton[1]").isEnabled());
		
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]"
		+ "/UIANavigationBar[1]/UIAButton[1]/UIAImage[1]").click();	
		
		assertTrue(CommonUtils.WaitForName(driver, Read_Excel.R_Excel("用户反馈"), 3));
	}
	
	//获取验证码提示
	@Test
	public void test01GetAuthcodeToast() throws Exception
	{
		//删除Screenshots目录下所有文件
    	GetImages.DelAllFile(); 
		
		driver.findElementByName(Read_Excel.R_Excel("更改密码")).click();
		
		driver.findElementByName(Read_Excel.R_Excel("获取验证码")).click();
		
		//连续截20张图
	    GetImages.takeTwentyPictures();
	    //裁剪200高度居中图片
	    GetImages.cutCenterImage(200);
	    //图片去躁后保存在tmp
	    GetImages.cleanImages();
	    //图像识别
	    GetImages.GetImage();
	    //去除空格和换行
	    GetImages.txtForm();
	    //合并所有txt文件
	    GetImages.CombineAlltxt();    
	   	
	    assertTrue(GetImages.txtcontain(Read_Excel.R_Excel("验证码发送成功")));
	    
	    GetImages.DelAllFile();	
	}
	
	//新密码长度小于6位
	@Test
	public void test39ConfirmButtonUnable() throws Exception
	{
		//删除Screenshots目录下所有文件
    	GetImages.DelAllFile();
 		
 		driver.findElementByName(Read_Excel.R_Excel("更改密码")).click();
 		
 		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
 		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
 		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys("66666");
 		
 		driver.getKeyboard().pressKey(Keys.RETURN);
 		
 		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").clear();
 		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").click();
 		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").sendKeys("123456");
 		
 		driver.hideKeyboard();
// 		driver.getKeyboard().pressKey(Keys.RETURN);
 		
 		assertFalse(driver.findElementByName(Read_Excel.R_Excel("确认修改")).isEnabled());		
	}
	
	//新密码长度大于20位
	@Test
	public void test40NewPassword()
	{		
 		driver.findElementByName(Read_Excel.R_Excel("更改密码")).click();
 		
 		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
 		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
 		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys
 		("123456789012345678901");
 		
 		driver.getKeyboard().pressKey(Keys.RETURN);
 		
 		assertEquals("新密码可输入超过20位","12345678901234567890",
 		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").getText());
	}
	
	// 验证码长度大于6位
	@Test
	public void test41Authcode()
	{	 		
	 	driver.findElementByName(Read_Excel.R_Excel("更改密码")).click();
	 	
	 	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").clear();
 		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").click();
 		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").sendKeys("1234567");
 		
 		driver.hideKeyboard();
 		
 		assertEquals("验证码可输入超过6位","123456",
 		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").getText());
	}
	
	//验证码长度小于6位
	@Test
	public void test42Authcodeless()
	{	 		
	 	driver.findElementByName(Read_Excel.R_Excel("更改密码")).click();
	 	
	 	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").clear();
 		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").click();
 		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").sendKeys("12345");
 		
 		driver.hideKeyboard();
 		
 		assertFalse("验证码可输入少于6位",
 		driver.findElementByName(Read_Excel.R_Excel("确认修改")).isEnabled());
	}
	
	//密码和验证码输入格式均正确
	@Test
	public void test43RightFormPwAu()
	{ 		
	 	driver.findElementByName(Read_Excel.R_Excel("更改密码")).click();
	 	
	 	driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").clear();
 		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").click();
 		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]").sendKeys("666666");
 		
 		driver.getKeyboard().pressKey(Keys.RETURN);
 		
 		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").clear();
 		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").click();
 		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextField[2]").sendKeys("123456");
 		
 		driver.hideKeyboard();
 		
        assertTrue(driver.findElementByName(Read_Excel.R_Excel("确认修改")).isEnabled());		
	}
	
	//进入关于软件界面和返回 用例44，45
	@Test
	public void test44AboutSoftware()
	{		
		driver.findElementByName(Read_Excel.R_Excel("关于我们")).click();
		
		assertTrue(CommonUtils.WaitForName(driver, Read_Excel.R_Excel("联系我们"), 3));
		
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]"
		+ "/UIANavigationBar[1]/UIAButton[1]/UIAImage[1]").click();
		
		assertTrue(CommonUtils.WaitForName(driver, Read_Excel.R_Excel("用户反馈"), 3));
	}
	
	//进入官方微信 用例51、52
	@Test
	public void test51GotoWechat() throws Exception
	{
		if(AllTest.EnterColumns==1)
		{		
			driver.findElementByName("关于我们").click();
			
			driver.findElementByName("help wechat").click();
			
			assertTrue(CommonUtils.WaitForName(driver, "微信二维码", 3));
			
			driver.findElementByName("保存到相册").click();
			
			assertTrue(CommonUtils.WaitForName(driver, "已存入手机相册", 10));				
		}
	}	
	
	//进入飞行固件升级页面
	@Test
	public void test59FlighFirmtware() throws Exception
	{
		GetImages.DelAllFile();
		driver.findElementByName(Read_Excel.R_Excel("飞机固件升级")).click();
		
		//连续截20张图
	    GetImages.takeTwentyPictures();
	    //裁剪200高度居中图片
	    GetImages.cutCenterImage(200);	    
	    //图像识别--用原图识别更准确
	    GetImages.orgImage();
	    //去除空格和换行
	    GetImages.txtForm();
	    //合并所有txt文件
	    GetImages.CombineAlltxt();   
	    
	    if(AllTest.ExcelPath().endsWith("7.4德语表.xls"))	
	    {
		    assertTrue(GetImages.txtcontain(Read_Excel.R_Excel("上传固件需要连接飞机,请连接飞机后重试"))); 
	    }else
	    {
		    assertTrue(GetImages.txtcontain(Read_Excel.R_Excel("上传固件需要连接飞机，请连接飞机后重试"))); 
	    }
 	    	
	    GetImages.DelAllFile();
	}
	
	//进入云台固件升级页面
	@Test
	public void test60CloudFirmeware() throws Exception
	{
		GetImages.DelAllFile();
		
		driver.findElementByName(Read_Excel.R_Excel("云台固件升级")).click();
		
		//连续截20张图
	    GetImages.takeTwentyPictures();
	    //裁剪200高度居中图片
	    GetImages.cutCenterImage(100);
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
	    	assertTrue(GetImages.txtcontain(Read_Excel.R_Excel("飞机未连接,无法检测版本")));  
	    }else
	    {
	    	assertTrue(GetImages.txtcontain(Read_Excel.R_Excel("飞机未连接，无法检测版本"))); 
	    }
	     	    	
	    GetImages.DelAllFile();

		//最后一个用例退出登录
		try
		{
		driver.findElementByName(Read_Excel.R_Excel("退出登录")).click(); 
		}catch(Exception e)
		{
			
		}
    	//不加try会出现异常，可能是appium的bug
    	try
    	{
    		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIAAlert[1]/UIACollecti"
        			+ "onView[1]/UIACollectionCell[2]/UIAButton[1]").click();  	
    	}catch(Exception e)
    	{
    		
    	}
	}
}
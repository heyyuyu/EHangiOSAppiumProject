/*
  * Author: chengchao.lu
  * Date: 2016.08.03
  * Content: EditInfoPageTest
  * 
  */

package iOSApp.test_personinfo;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import iOSApp.test_all.AllTest;
import iOSApp.utils.CommonUtils;
import iOSApp.utils.GetImages;
import iOSApp.utils.Read_Excel;
import io.appium.java_client.AppiumDriver;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EditInfoPageTest 
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
		CommonUtils.GoToPersonalInfoPage(driver);
		try
		{
			driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIAElement[3]").click();
			CommonUtils.WaitForName(driver, "13000000001", 20);
		}
		catch(Exception e)
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
	
	//Test01在初始化时已经验证了。
	//能否进入编辑页面
	@Test 
	public void test02WhetherEnterEditePage() throws Exception
	{	
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[2]").click();
		Thread.sleep(5000);
		assertTrue(CommonUtils.WaitForName(driver, Read_Excel.R_Excel("编辑资料"), 20));
	}
	
	//进入编辑头像
	@Test
	public void test08EnterEditPhoto() throws Exception
	{
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[2]").click();
		driver.findElementByName(Read_Excel.R_Excel("我的头像")).click();
		assertTrue(CommonUtils.WaitForName(driver, Read_Excel.R_Excel("照相"), 5));
	}
	
	//进入拍摄头像
	@Test
	public void test09EnterTakePhoto() throws Exception
	{
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[2]").click();
		driver.findElementByName(Read_Excel.R_Excel("我的头像")).click();
		driver.findElementByName(Read_Excel.R_Excel("照相")).click();
		if(AllTest.ExcelPath().endsWith("7.4德语表.xls"))
		{
			if(AllTest.EnterColumns == 3)
			{
				assertTrue(CommonUtils.WaitForName(driver, "Abbrechen", 5));
			}else
			{
				assertTrue(CommonUtils.WaitForName(driver, Read_Excel.R_Excel("取消"), 5));
			}
		}else
		{
			assertTrue(CommonUtils.WaitForName(driver, Read_Excel.R_Excel("取消"), 5));
		}
	}
	
	//进入相册选择头像
	@Test
	public void test10EnterAlbum() throws Exception
	{
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[2]").click();
		driver.findElementByName(Read_Excel.R_Excel("我的头像")).click();
		Thread.sleep(1000);
		driver.findElementByName(Read_Excel.R_Excel("相册")).click();
		assertTrue(CommonUtils.WaitForName(driver, Read_Excel.R_Excel("照片"), 5));
	}
	
	//取消编辑头像
	@Test
	public void test11CancelEditPhoto() throws Exception
	{
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[2]").click();
		driver.findElementByName(Read_Excel.R_Excel("我的头像")).click();
		Thread.sleep(1000);
		driver.tap(1, 20, 20, 100);		
		assertTrue(CommonUtils.WaitForName(driver, Read_Excel.R_Excel("编辑资料"), 5));
	}
	
	//进入编辑性别模块
	@Test
	public void test12EnterEditGender() throws Exception
	{
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[2]").click();
		driver.findElementByName(Read_Excel.R_Excel("性别")).click();
		assertTrue(CommonUtils.WaitForName(driver, Read_Excel.R_Excel("取消"), 5));
	}
	
	//编辑性别取消
	@Test
	public void test13CancelEditGender() throws Exception
	{
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[2]").click();
		driver.findElementByName(Read_Excel.R_Excel("性别")).click();	
		Thread.sleep(500);
		driver.findElementByName(Read_Excel.R_Excel("取消")).click();
		assertTrue(CommonUtils.WaitForName(driver, Read_Excel.R_Excel("编辑资料"), 5));
	}
	
	//进入编辑地区模块
	@Test
	public void test15EnterEditRegion() throws Exception
	{
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[2]").click();
		driver.findElementByName(Read_Excel.R_Excel("地区")).click();
		//中文与其他语言界面不同
		if(AllTest.EnterColumns == 1)
		{
			assertTrue(CommonUtils.WaitForName(driver, Read_Excel.R_Excel("取消"), 10));
		}else
		{
			assertTrue(CommonUtils.WaitForName(driver, "Angola", 10));
		}

	}
	
	//编辑地区取消
	@Test
	public void test16CancelEditRegion() throws Exception
	{
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[2]").click();
		driver.findElementByName(Read_Excel.R_Excel("地区")).click();	
		Thread.sleep(500);
		if(AllTest.EnterColumns == 1)
		{
			driver.findElementByName(Read_Excel.R_Excel("取消")).click();
		}else
		{
			driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[1]/UIAImage[1]").click();
		}
		assertTrue(CommonUtils.WaitForName(driver, Read_Excel.R_Excel("编辑资料"), 5));
	}
	
	//进入编辑生日模块
	@Test
	public void test18EnterEditBirthDay() throws Exception
	{
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[2]").click();
		driver.findElementByName(Read_Excel.R_Excel("生日")).click();
		assertTrue(CommonUtils.WaitForName(driver, Read_Excel.R_Excel("取消"), 10));
	}
	
	//编辑生日取消
	@Test
	public void test19CancelEditBirthDay() throws Exception
	{
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[2]").click();
		driver.findElementByName(Read_Excel.R_Excel("生日")).click();	
		Thread.sleep(500);
		driver.findElementByName(Read_Excel.R_Excel("取消")).click();
		assertTrue(CommonUtils.WaitForName(driver, Read_Excel.R_Excel("编辑资料"), 5));	
	}
	
	//能否进入修改昵称页面
	@Test
	public void test21EnterEditNickName() throws Exception
	{
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[2]").click();
		driver.findElementByName(Read_Excel.R_Excel("昵称")).click();
		assertTrue(CommonUtils.WaitForName(driver, Read_Excel.R_Excel("昵称"), 5));
	}
	
	 //编辑资料页面返回
	@Test
	public void test26RebackPersonInf() throws Exception
	{
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[2]").click();
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[1]").click();
		assertTrue(CommonUtils.WaitForName(driver, Read_Excel.R_Excel("飞行记录"), 10));		
	}
	
	//能否进入用户反馈页面
	@Test
	public void test27Feedback() throws Exception
	{		
		driver.findElementByName(Read_Excel.R_Excel("用户反馈")).click();		
		assertTrue(CommonUtils.WaitForName(driver, Read_Excel.R_Excel("用户反馈"), 10));
	}
	
	//用户反馈页面返回
	@Test
	public void test28backFeedback() throws Exception
	{	
		driver.findElementByName(Read_Excel.R_Excel("用户反馈")).click();				
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[1]/UIAImage[1]").click();
		assertTrue(CommonUtils.WaitForName(driver, Read_Excel.R_Excel("飞行记录"), 10));	
	}
	
	//用户反馈页面提交内容不能为空
	@Test
	public void test29FeedbackContentNotEmpty() throws Exception
	{
		GetImages.DelAllFile();
		driver.findElementByName(Read_Excel.R_Excel("用户反馈")).click();	
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIATextView[1]").clear();
		driver.findElementByName(Read_Excel.R_Excel("提交")).click();
		
		GetImages.takeTwentyPictures(); 	
	    GetImages.cutCenterImage(100);   	
	    GetImages.cleanImages();    	
	    GetImages.GetImage();    	
	    GetImages.txtForm();    	
	    GetImages.CombineAlltxt();       	
	    assertTrue(GetImages.txtcontain(Read_Excel.R_Excel("提交内容不能为空"))); 
	    GetImages.DelAllFile();

	}
	
	//“我”部分的文字多语言翻译
	@Test
	public void test30MePartTranslate() throws Exception
	{
		assertTrue("飞行记录",CommonUtils.WaitForName(driver, Read_Excel.R_Excel("飞行记录"), 5));
		assertTrue("总里程",CommonUtils.WaitForName(driver, Read_Excel.R_Excel("总里程"), 5));
		assertTrue("最大高度",CommonUtils.WaitForName(driver, Read_Excel.R_Excel("最大高度"), 5));
		assertTrue("飞行次数",CommonUtils.WaitForName(driver, Read_Excel.R_Excel("飞行次数"), 5));
		assertTrue("总时长",CommonUtils.WaitForName(driver, Read_Excel.R_Excel("总时长"), 5));
		assertTrue("飞机固件升级",CommonUtils.WaitForName(driver, Read_Excel.R_Excel("飞机固件升级"), 5));
		assertTrue("云台固件升级",CommonUtils.WaitForName(driver, Read_Excel.R_Excel("云台固件升级"), 5));
		assertTrue("更改密码",CommonUtils.WaitForName(driver, Read_Excel.R_Excel("更改密码"), 5));
		assertTrue("用户反馈",CommonUtils.WaitForName(driver, Read_Excel.R_Excel("用户反馈"), 5));
		assertTrue("关于我们",CommonUtils.WaitForName(driver, Read_Excel.R_Excel("关于我们"), 5));
		assertTrue("软件更新",CommonUtils.WaitForName(driver, Read_Excel.R_Excel("软件更新"), 5));
		assertTrue("退出登录",CommonUtils.WaitForName(driver, Read_Excel.R_Excel("退出登录"), 5));
		
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[2]").click();
		assertTrue("编辑资料",CommonUtils.WaitForName(driver, Read_Excel.R_Excel("编辑资料"), 20));
		assertEquals(Read_Excel.R_Excel("保存"),
		driver.findElementByXPath(" //UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[3]").getText().trim());
		assertTrue("我的头像",CommonUtils.WaitForName(driver, Read_Excel.R_Excel("我的头像"), 5));
		assertTrue("昵称",CommonUtils.WaitForName(driver, Read_Excel.R_Excel("昵称"), 5));
		assertTrue("性别",CommonUtils.WaitForName(driver, Read_Excel.R_Excel("性别"), 5));
		assertTrue("地区",CommonUtils.WaitForName(driver, Read_Excel.R_Excel("地区"), 5));
		assertTrue("生日",CommonUtils.WaitForName(driver, Read_Excel.R_Excel("生日"), 5));
		
		driver.findElementByXPath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAButton[1]").click();
		assertTrue("飞行记录",CommonUtils.WaitForName(driver, Read_Excel.R_Excel("飞行记录"), 10));	
		
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

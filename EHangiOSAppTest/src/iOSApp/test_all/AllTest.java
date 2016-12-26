package iOSApp.test_all;

import java.io.File;
import iOSApp.test_login.ForgetPasswordPageTest;
import iOSApp.test_login.LoginPageTest;
import iOSApp.test_login.RegisterPageTest;
import iOSApp.test_personinfo.EditInfoPageTest;
import iOSApp.test_personinfo.PersonInfoPageTest;
import junit.framework.*;

public class AllTest 
{
	//初始化设置，输入app名字，真机名字和系统版本
	public static String EhangAppName="EhangGhost2.ipa";
	public static String IphoneType="iPhone 4S";
	public static String IphoneVersion="9.3";
	//输入图像识别的路径
	public static String tesseractPath="/usr/local/Cellar/tesseract/3.04.01_1/bin/tesseract";
	//输入截图工具的路径
	public static String idevicescreenshotPath = "/usr/local/bin/idevicescreenshot";
	//读取Excel的参数，EnterColumns是目标语言所在列数
	public static int EnterColumns = 2;
	//Excel表的路径
	public static String ExcelPath()
	{
		File ExcelDir = new File("ExcelFold");
		File[] file=ExcelDir.listFiles();
		int i=file.length;
		String xlsPath="";
		for(int j=0;j<i;j++)
		{	
			//法语总表.xls 7.4德语表.xls
			if(file[j].getName().endsWith("7.4德语表.xls"))
			{
				xlsPath=file[j].getAbsolutePath();			
			}
		}		
		return xlsPath;
	}
	
	//执行所有选中类
	public static Test suite()
	{
		TestSuite suite = new TestSuite();
		
		//增加一个class
		suite.addTest(new JUnit4TestAdapter(LoginPageTest.class));  
		suite.addTest(new JUnit4TestAdapter(RegisterPageTest.class)); 
		suite.addTest(new JUnit4TestAdapter(ForgetPasswordPageTest.class)); 
		suite.addTest(new JUnit4TestAdapter(PersonInfoPageTest.class)); 
		suite.addTest(new JUnit4TestAdapter(EditInfoPageTest.class)); 
		return suite;
	}
	public static void main(String args[])
	{
		suite();
	}
	
}



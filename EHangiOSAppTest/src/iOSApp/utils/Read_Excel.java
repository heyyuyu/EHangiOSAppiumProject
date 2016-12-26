package iOSApp.utils;

import java.io.File;
import java.io.IOException;
import iOSApp.test_all.AllTest;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;

public class Read_Excel
{
	public static String R_Excel(String parameter)
	{
		String string1="";
		int i=0;
		String path=AllTest.ExcelPath();
		WorkbookSettings setting =new WorkbookSettings();
		setting.setEncoding("iso-8859-1");
		Workbook book=null;
		
		try
		{
			book=Workbook.getWorkbook(new File(path),setting);
		}
		catch(BiffException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		Sheet sheet=book.getSheet(0);
		while(i<sheet.getRows())
		{
			Cell cell=sheet.getCell(0,i);
			String result=cell.getContents();
			String result1=result.trim();
			if(result1.equals(parameter))
			{
				string1=sheet.getCell(AllTest.EnterColumns-1,i).getContents().trim();
				break;
			}			
			i++;
		}
		book.close();
		if(string1.equals(""))
		{
			System.out.println("未找到目标字符串");
			return "false";
		}else
		{
		return string1;
		}
	}
}

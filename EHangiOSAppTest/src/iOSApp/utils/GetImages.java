package iOSApp.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import iOSApp.test_all.AllTest;

public class GetImages 
{
	//循环截20张图
	public static void takeTwentyPictures() throws Exception
	{
		for(int i=0;i<20;i++)
		{			
			takeScreenShot.idevicescreenshot();
			//重命名文件,防止同一时间的图片覆盖
			File file = new File("Screenshots");			
			File[] oldfile = file.listFiles();
		    int k = oldfile.length;
		    for(int j = 0;j<k;j++)
		    {
		    	if(oldfile[j].getName().endsWith(".tiff") &&
		    	   oldfile[j].getName().endsWith("a.tiff")==false)
		    	{
		    		File newname = new File(file.getAbsolutePath()+"/"+oldfile[j].getName().replace(".tiff","a"+i+"a.tiff"));
		    		oldfile[j].renameTo(newname);
		    		break;
		    	}
		    }
		}
	}
	
	//循环截取居中图片部分覆盖原图
	public static void cutCenterImage(int h) throws Exception
	{
		File testDataDir = new File("Screenshots");			
		File[] file=testDataDir.listFiles();
		int i=file.length;	
		for(int j=0;j<i;j++)
		{	
			//判断是tiff格式的才会截图，忽略文件夹和其他文件
			if(file[j].getName().endsWith(".tiff"))
			{
				String filepath=file[j].getAbsolutePath();	
				CenterImage.cutImage(file[j], h,filepath);			
			}
		}
	}
	//截取指定宽度和高度的图片
	public static void cutCertenImage(int h,int w) throws Exception
	{
		File testDataDir = new File("Screenshots");			
		File[] file=testDataDir.listFiles();
		int i=file.length;	
		for(int j=0;j<i;j++)
		{	
			//判断是tiff格式的才会截图，忽略文件夹和其他文件
			if(file[j].getName().endsWith(".tiff"))
			{
				String filepath=file[j].getAbsolutePath();	
				CenterImage.cutImageContainW(file[j], h, w, filepath);		
			}
		}
	}
	//创建tmp和txtFile文件夹
    public static String CreatetmpFold()
    {
    	File testDataDir = new File("Screenshots");
    	//创建tmp文件夹存放去躁后的图片
    	final String destDir = testDataDir.getAbsolutePath()+"/tmp";
    	File destF = new File(destDir);
    	if (!destF.exists())
    	{
    		destF.mkdirs();
    	}
    	return destDir;
    }
    
    //创建放txt文件的文件夹
    public static String CreatetxtFold()
    {   
    	File testDataDir = new File("Screenshots");
		final String txtDir = testDataDir.getAbsolutePath()+"/txtFile";
		File destxt=new File(txtDir);
		if (!destxt.exists())
		{
			destxt.mkdirs();
		}
		return txtDir;
    }
    
    //图片去躁
    public static void cleanImages() throws Exception
    {
    	File testDataDir = new File("Screenshots");			
		File[] file=testDataDir.listFiles();
		int i=file.length;
		for(int j=0;j<i;j++)
		{
			if(file[j].getName().endsWith(".tiff"))
			{				
				cleanImage.cleanimage(file[j], CreatetmpFold());
			}
		}
    } 
    
    /*  图像识别主函数
        tesseractpath，路径/usr/local/Cellar/tesseract/3.04.01_1/bin/tesseract
        language，/usr/local/Cellar/tesseract/3.04.01_1/share／tessdata
    */
    public static void GetImage() 
	{							
		File testDataDir = new File(CreatetmpFold());
		File txtDataDir =new File(CreatetxtFold());
		File[] file=testDataDir.listFiles();
		int i=file.length;
		String lan="";
		for(int j=0;j<i;j++)
		{
			String txtpath=txtDataDir.getAbsolutePath()+"/"+file[j].getName();			
			try 
			{
				if(AllTest.ExcelPath().endsWith("7.4德语表.xls"))
				{
					if(AllTest.EnterColumns == 1)
					{
						lan = "num";
					}else if(AllTest.EnterColumns == 2)
					{
						lan = "eng";
					}else if(AllTest.EnterColumns == 3)
					{
						lan = "deu";
					}
				}else if(AllTest.ExcelPath().endsWith("法语总表.xls"))
				{
					lan = "fra";
				}
				//调用终端运行图像识别程序
				Runtime.getRuntime().exec(AllTest.tesseractPath+" -l "+lan+" "
				+file[j].getAbsolutePath()+" "+txtpath);
				//等待进程结束后才进行下一步，否则txt内容获取不到
				Runtime.getRuntime().exec(AllTest.tesseractPath+" -l "+lan+" "
				+file[j].getAbsolutePath()+" "+txtpath).waitFor();
			} catch (Exception e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}		
	}
    
    //识别原图
    public static void orgImage()
    {
	    File testDataDir = new File("Screenshots");	
	    File txtDataDir =new File(GetImages.CreatetxtFold());
		File[] file=testDataDir.listFiles();
		int i=file.length;
		String lan = "";
	    for(int j=0;j<i;j++)
		{
	    	if(file[j].getName().endsWith(".tiff"))
	    	{
	    		String txtpath=txtDataDir.getAbsolutePath()+"/"+file[j].getName();
	    		try 
				{
	    			if(AllTest.ExcelPath().endsWith("7.4德语表.xls"))
	    			{
		    			if(AllTest.EnterColumns==1)
		    			{
		    				lan = "num";
		    			}else if (AllTest.EnterColumns==2)
		    			{
		    				lan = "eng";
		    			}else if(AllTest.EnterColumns == 3)
		    			{
		    				lan = "deu";
		    			}
	    			}else if(AllTest.ExcelPath().endsWith("法语总表.xls"))
	    			{
		    				lan = "fra";
	    			}
					//调用终端运行图像识别程序
					Runtime.getRuntime().exec(AllTest.tesseractPath+" -l "+lan+" "
					+file[j].getAbsolutePath()+" "+txtpath);
					//等待进程结束后才进行下一步，否则txt内容获取不到
					Runtime.getRuntime().exec(AllTest.tesseractPath+" -l "+lan+" "
					+file[j].getAbsolutePath()+" "+txtpath).waitFor();
				} catch (Exception e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	}
		}
    }
    //去掉txt文件内的空格和换行
	public static void txtForm() throws Exception 
	{
		File txtDataDir =new File(CreatetxtFold());
		File[] file=txtDataDir.listFiles();
		int i=file.length;
		for(int j=0;j<i;j++)
		{				
			FileReader k=new FileReader(file[j]);		
			BufferedReader bis=new BufferedReader(k);
			String line=null;
			String Str = "";
			String Str1="";
			while((line=bis.readLine())!=null)
			{
				//中文去除所有空格，其他语言不去
				if(AllTest.EnterColumns==1)
				{				
					Str1=line.replace(" ","");	
				}else
				{
					Str1=line.trim();
				}
				if(Str1.equals("")==false)
				{					
					Str+=" "+Str1;			
				}
			}
			bis.close();
			file[j].delete();
			FileWriter w=new FileWriter(file[j]);
			BufferedWriter writer=new BufferedWriter(w);
			writer.write(Str);
			writer.close();
		}		
	}
	
	//删除Screenshots目录下所有文件，捕获异常防止目录下没有相关文件
	public static void DelAllFile()
	{
		try
		{
			File testDataDir = new File("Screenshots");			
			File[] file=testDataDir.listFiles();
			int i=file.length;		
			for(int j=0;j<i;j++)
			{	
				//判断是否是文件
		        if (file[j].isFile()) 
		        {
		        	//删除文件
		            file[j].delete();   
		        } else if (file[j].isDirectory()) 
		        {	
		        	//声明目录下所有的文件 files[]
		            File[] files = file[j].listFiles();  
		            for (int a = 0;a < files.length;a ++) 
		            {
		            	//遍历目录下所有的文件  
		                files[a].delete(); 
		            }  
		            file[j].delete();//删除文件夹  
		        }
			}
	    }catch(Exception e)
		{
	    	
		}
	}	
	
	//合并txtFile下所有txt文件
	public static void CombineAlltxt() throws Exception
	{
		File txtDataDir =new File(CreatetxtFold());
		File txtAllIn =new File(CreatetxtFold()+"/txtAllin.txt");
		File[] file=txtDataDir.listFiles();
		int i=file.length;
		String Str ="";
		for(int j=0;j<i;j++)
		{
			FileReader k=new FileReader(file[j]);		
			BufferedReader bis=new BufferedReader(k);
			String line=null;
			
			while((line=bis.readLine())!=null)
			{				
				Str=line+Str;				
			}
			bis.close();
		}
		txtAllIn.createNewFile();
		FileWriter w=new FileWriter(txtAllIn);
		BufferedWriter writer=new BufferedWriter(w);
		writer.write(Str);
		writer.close();
	}
	
	//判断txt中存在某一字段				
	public static Boolean txtcontain(String toast) throws Exception 
	{
		File f=new File("Screenshots/txtFile/txtAllin.txt");	
		FileReader k=new FileReader(f);		
		@SuppressWarnings("resource")
		BufferedReader bis=new BufferedReader(k);
		String line=null;
		if(toast.equals(""))
		{
			System.out.println("Excel没有找到该字符串");
			return false;
		}else
		{
			while((line=bis.readLine())!=null)
			{				
				if(line.contains(toast))
				{
					return true;		
				}		
			}		
			return false;
		}
	}	
}

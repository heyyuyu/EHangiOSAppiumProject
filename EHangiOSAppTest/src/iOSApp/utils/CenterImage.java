package iOSApp.utils;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class CenterImage 
{
    /**  
     * 截取一个图像的中央区域  
     * @param image 图像File  
     * @param w 需要截图的宽度  
     * @param h 需要截图的高度  
     * @return 返回一个  
     * @throws IOException  
     */    
	public static void cutImage(File image,int h,String path) throws IOException
	{
		// 判断参数是否合法   
        if (null == image) {   
            new Exception ("哎呀，截图出错！！！");   
        }   
        InputStream inputStream = new FileInputStream(image);   
        // 用ImageIO读取字节流   
        BufferedImage bufferedImage = ImageIO.read(inputStream);   
        BufferedImage distin = null;   
        // 返回源图片的宽度。   
        int srcW = bufferedImage.getWidth();   
        // 返回源图片的高度。   
        int srcH = bufferedImage.getHeight();   
        int y = 0;   
        // 使截图区域居中   	           
        y = srcH / 2 - h /2;     
        srcH = srcH / 2 + h /2;   
        // 生成图片   
        distin = new BufferedImage(srcW, h, BufferedImage.TYPE_INT_RGB);   
        Graphics g = distin.getGraphics();   
        g.drawImage(bufferedImage, 0, 0, srcW, h, 0, y, srcW, srcH, null);   
        ImageIO.write(distin, "jpg", new File(path));	           
	}   	
	public static void cutImageContainW(File image,int h,int w,String path) throws IOException
	{
		// 判断参数是否合法   
        if (null == image) {   
            new Exception ("哎呀，截图出错！！！");   
        }   
        InputStream inputStream = new FileInputStream(image);   
        // 用ImageIO读取字节流   
        BufferedImage bufferedImage = ImageIO.read(inputStream);   
        BufferedImage distin = null;   
        // 返回源图片的宽度。   
        int srcW = bufferedImage.getWidth();   
        // 返回源图片的高度。   
        int srcH = bufferedImage.getHeight(); 
        int y = 0;   
        int x = 0;
        // 使截图区域居中   	           
        y = srcH / 2 - h /2;     
        srcH = srcH / 2 + h /2;   
        x = srcW / 2 - w / 2;
        srcW = srcW /2 + w / 2;
        // 生成图片   
        distin = new BufferedImage(srcW, h, BufferedImage.TYPE_INT_RGB);   
        Graphics g = distin.getGraphics();   
        g.drawImage(bufferedImage, 0, 0, bufferedImage.getWidth(), h, x+50, y, srcW, srcH, null);   
        ImageIO.write(distin, "jpg", new File(path));
	}
}

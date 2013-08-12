package com.nbcedu.function.teachersignup.core.util;


import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

/**
 * @author xuechong
 */
public class ImageUtils {
	/**
	 * scalByWidth
	 * @param image
	 * @param width
	 * @return
	 */
	public BufferedImage scalByWidth(BufferedImage image,int width){
		AffineTransform tx = new AffineTransform();
		double rate = cal(image.getWidth(),width);
		tx.scale(rate, rate);
	    AffineTransformOp op = new 
	    			AffineTransformOp(tx,AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
	    return op.filter(image, null);
	}
	
	/**
	 * scalByHeight
	 * @param image
	 * @param height
	 * @return
	 */
	public BufferedImage scalByHeight(BufferedImage image,int height){
		AffineTransform tx = new AffineTransform();
		double rate = cal(image.getHeight(),height);
		tx.scale(rate, rate);
	    AffineTransformOp op = new 
	    			AffineTransformOp(tx,AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
	    return op.filter(image, null);
	}
	
	/**
	 * calculate the scale rate
	 * @param origin
	 * @param dest
	 * @return
	 */
	private static double cal(int origin,int dest){
		return new Double(dest)/new Double(origin);
	}
	
	/**
	 * square the given img
	 * @param originPath origin img path
	 * @return
	 * @throws IOException
	 */
	public static BufferedImage squareImg(String originPath) throws IOException{
		InputStream in = new FileInputStream(new File(originPath));
		Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName("jpg");
		ImageReader reader = readers.next();
		ImageInputStream imgIn = ImageIO.createImageInputStream(in);
		reader.setInput(imgIn,true);
		ImageReadParam param = reader.getDefaultReadParam();
		SquareData squareResult = calSquare(originPath);
		Rectangle rect = new Rectangle
			(squareResult.x, squareResult.y, 
					squareResult.width, squareResult.height);
		param.setSourceRegion(rect);
		BufferedImage result = reader.read(0, param);
		return result;
	}
	
	public static BufferedImage readImage(String imgPath) throws IOException{
		InputStream in = new FileInputStream(new File(imgPath));
		Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName("jpg");
		ImageReader reader = readers.next();
		ImageInputStream imgIn = ImageIO.createImageInputStream(in);
		reader.setInput(imgIn,true);
		return reader.read(0);
	}
	/**
	 * write the image
	 * @param image
	 * @param path the destination
	 */
	public static void writeJPG(BufferedImage image,String path){
		try {
			ImageIO.write(image, "jpg", new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * calculate square
	 * @param originPath
	 * @return
	 * @throws IOException
	 */
	private static SquareData calSquare(String originPath) throws IOException{
		SquareData result = new SquareData();
		BufferedImage img = ImageIO.read(ImageIO.createImageInputStream(new FileInputStream(new File(originPath))));
		if(img.getHeight()==img.getWidth()){
			result.x = result.y = 
				result.width = result.height = img.getWidth();
			return result;
		}
		int length = img.getHeight()>img.getWidth()?
						img.getWidth():img.getHeight();
		result.height = result.width = length;
		int diff = Math.abs(img.getHeight()-img.getWidth());
		if(img.getWidth()>img.getHeight()){
			result.x = diff>>1;
			result.y = 0;
		}else{
			result.x = 0;
			result.y = diff>>1;
		}
		return result;
	}
	
	private static final class SquareData{
		private int x;
		private int y;
		private int width;
		private int height;
	}
}

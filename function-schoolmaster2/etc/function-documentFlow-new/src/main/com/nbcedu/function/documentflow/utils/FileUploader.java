/*
 * @Title: FileUploader.java
 * @Package com.nbcedu.function.documentflow.utils
 * @Description: 文件上传工具类，该类包含了读取上传文件并存放在服务器端的相应方法。
 * @author Wang Zhuoxuan wangzhuoxuan@nbcedu.com
 * @date 2011-8-11 下午04:31:56
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2011-8-11                          
 */
package com.nbcedu.function.documentflow.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/** 
 * <p>文件上传工具类</p>
 * 
 * @author Wang Zhuoxuan
 * Create at:2011-8-11 下午04:31:56
 */
public class FileUploader {
	
	/**
	 * 输入流
	 */
	private InputStream is;
	/**
	 * 输出流
	 */
	private OutputStream os;
	/**
	 * 上传的文件对象
	 */
	private File file;
	
	/** 
	 * 将上传的文件保存在服务器指定位置
	 * 
	 * @param file 要保存的文件对象
	 * @param savePath 要保存的指定路径
	 */ 
	public void saveFile(File file, String savePath) {
		try {
			is = new FileInputStream(file);
			this.file = new File(savePath, file.getName());
			os = new FileOutputStream(this.file);
			byte[] bytes = new byte[1024];
			int length = 0;
			while ((length = is.read(bytes)) > 0) {
				os.write(bytes, 0, length);
			}
			
			os.close();
			is.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

package com.nbcedu.function.documentflow.utils;

import java.io.File;
import java.io.FileWriter;


/**
 * 模拟发送短信
 */
public class MessageTester {
	
	public static void sendMessage(String tel,String content){
		try {
			String[] tels = tel.split(",");
			for (String telNo : tels) {
				String date = String.valueOf(System.currentTimeMillis());
				String s = date.substring(date.length()-9);
				File f = new File("C:" + File.separator +"messages"+File.separator+"tel_"+telNo+"+date_"+ s + ".txt");
				FileWriter out = new FileWriter(f);
				out.write(content);
				out.flush();
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

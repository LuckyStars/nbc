package com.nbcedu.function.cardmanage.core.util.date;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期类
 * 
 * @author qinyuan
 * @version 1.0
 */
public class DateUtil {
	
	/**
	 * 日期 --》 字符串
	 * @param date
	 * @param pattern 转换格式，例如：yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String date2Str(Date date, String pattern) {
		if (date!=null) {
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			return dateFormat.format(date);
		}
		return null;
	}
	
	
	/**
	 * 字符串 --》 日期
	 * @param source
	 * @param pattern 转换格式，例如：yyyy-MM-dd HH:mm:ss
	 * @return
	 * @throws Exception 
	 */
	public static Date str2Date(String source, String pattern) throws Exception{
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		return dateFormat.parse(source);
	}
	
	
	
}
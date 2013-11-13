package com.nbcedu.function.schoolmaster2.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 本周工作汇总
 * @author xuechong
 */
public class WeekAction {
	
	public static void main(String[] args) {
		
		
		
	}
	
	
	private static Date weekStart(){
		int curDay = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
		Integer day = curDay >= 2 ? curDay - 2 : 6;// 计算需要减去几天才到周一
		long diff = day.longValue() * 1000L * 60L * 60L * 24L;
		Date firstDayOfWeek = new Date(new Date().getTime() - diff);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date result = null;
		try {
			result = sdf.parse(sdf.format(firstDayOfWeek));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
}

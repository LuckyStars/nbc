package com.nbcedu.function.teachersignup.util;

import java.util.Date;

import com.ibm.icu.text.SimpleDateFormat;

public class Utils {
	public static final class Dates{
		private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		public static String fullDateStr(Date date){
			return sdf.format(date);
		}
		
	}
}

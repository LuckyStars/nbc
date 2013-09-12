package com.nbcedu.function.schoolmaster2.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.nbcedu.function.schoolmaster2.constants.Constants;
import com.opensymphony.xwork2.ActionContext;

public class Utils {
	/***获取当前登录用户的UID**/
	public static String curUserUid(){
		return ActionContext.getContext().getSession().get(Constants.SESSION_UID_KEY).toString();
	}
	
	public static class Dates{
		
		public static final SimpleDateFormat dateSdf = new SimpleDateFormat("yyyy-MM-dd");
		
		public static Date safeParseSimpleDate(String origin){
			try {
				return dateSdf.parse(origin);
			} catch (Exception e) {
				return new Date();
			}
		}
	}
}
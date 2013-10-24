package com.nbcedu.function.schoolmaster2.utils;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import org.springframework.web.context.ContextLoader;

import com.nbcedu.function.functionsupport.util.PropertiesUtil;
import com.nbcedu.function.schoolmaster2.constants.Constants;
import com.opensymphony.xwork2.ActionContext;

public class Utils {
	/***获取当前登录用户的UID**/
	public static String curUserUid(){
		return ActionContext.getContext().getSession().get(Constants.SESSION_UID_KEY).toString();
	}
	
	/**
	 * 默认接受者的uid
	 * @return
	 * @author xuechong
	 */
	public static Collection<String> getDefaultMasterUids(){
		String uids = PropertiesUtil.findPropertieValue("config.properties", "defaultmasteruids");
		return Arrays.asList(uids.split(","));
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
	
	public static class Beans{
		public static Object getSpringBeanByName(String name){
			return ContextLoader.
			getCurrentWebApplicationContext()
			.getBean(name);
		}
	}
}

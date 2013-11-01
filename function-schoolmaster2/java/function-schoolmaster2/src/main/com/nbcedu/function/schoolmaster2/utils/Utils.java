package com.nbcedu.function.schoolmaster2.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.xwork.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.context.ContextLoader;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.nbcedu.function.functionsupport.util.PropertiesUtil;
import com.nbcedu.function.schoolmaster2.constants.Constants;
import com.nbcedu.function.schoolmaster2.data.vo.PersonVo;
import com.opensymphony.xwork2.ActionContext;

public class Utils {
	
	private static final Logger logger = Logger.getLogger(Utils.class);
	
	public static final Gson gson = new Gson();
	
	/***获取当前登录用户的UID**/
	public static String curUserUid(){
		return ActionContext.getContext().getSession().get(Constants.SESSION_UID_KEY).toString();
	}
	
	/***获取当前登录用户的姓名**/
	public static String curUserName(){
		String userName = (String) ActionContext.getContext().getSession().get(Constants.SESSION_USER_NAME);
		if(StringUtils.isBlank(userName)){
			userName = 	UCService.findNameByUid(curUserUid());
		}
		return userName;
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
	
	/**
	 * 所有校长
	 * @return
	 * @author xuechong
	 */
	@SuppressWarnings("serial")
	public static List<PersonVo> getAllSchoolMaster(){
		String masterJson = loadFileToString("xiaozhang.json");
		return gson.fromJson(masterJson,new TypeToken<List<PersonVo>>(){}.getType());
	}
	
	/**
	 * 所有主管
	 * @return
	 * @author xuechong
	 */
	@SuppressWarnings("serial")
	public static Collection<PersonVo> getAllManager(){
		String masterJson = loadFileToString("xiaozhang.json");
		return gson.fromJson(masterJson,new TypeToken<Collection<PersonVo>>(){}.getType());
	}
	
	public static boolean isManager(){
		for (PersonVo person : Utils.getAllSchoolMaster()) {
			if(person.getUid().equalsIgnoreCase(Utils.curUserUid())){
				return true;
			}
		}
		return false;
	}
	/**
	 * 从classPath文件中读取字符串
	 * @param filePath
	 * @return
	 * @author xuechong
	 */
	public static String loadFileToString(String filePath){
		try {
			return FileUtils.readFileToString(
					new File(
							Thread.currentThread().getContextClassLoader().
							getResource(filePath).toURI()),"UTF-8");
		} catch (IOException e) {
			logger.error("读取" + filePath  + "出错", e);
			return "";
		} catch (URISyntaxException e) {
			logger.error("读取" + filePath  + "出错", e);
			return "";
		}
	}
	
	public static class Dates{
		
		public static final SimpleDateFormat dateSdf = new SimpleDateFormat("yyyy-MM-dd");
		public static final SimpleDateFormat fullSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		public static Date safeParseSimpleDate(String origin){
			try {
				return dateSdf.parse(origin);
			} catch (Exception e) {
				return new Date();
			}
		}
		public static String formateFullDateStr(Date date){
			return fullSdf.format(date);
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

package com.nbcedu.function.schoolmaster2.utils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.xwork.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.context.ContextLoader;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.nbcedu.function.functionsupport.core.FunctionSupportUtil;
import com.nbcedu.function.functionsupport.core.SupportManager;
import com.nbcedu.function.functionsupport.mapping.PortalMessage;
import com.nbcedu.function.functionsupport.util.PropertiesUtil;
import com.nbcedu.function.schoolmaster2.constants.Constants;
import com.nbcedu.function.schoolmaster2.data.model.Sm2Zan;
import com.nbcedu.function.schoolmaster2.data.vo.PersonVo;
import com.opensymphony.xwork2.ActionContext;

public class Utils {
	
	private static final Logger logger = Logger.getLogger(Utils.class);
	
	public static final Gson gson = new Gson();
	
	/***获取当前登录用户的UID**/
	public static String curUserUid(){
		Object temp = ActionContext.getContext().getSession().get(Constants.SESSION_UID_KEY);
		String uid = temp instanceof String ?temp.toString():"";
		if(uid==null||uid.trim().isEmpty()){
			temp = ActionContext.getContext().getSession().get("edu.yale.its.tp.cas.client.filter.user").toString();
			uid = temp instanceof String ?temp.toString():"";
		}
		return uid;
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
		String masterJson = loadFileToString("zhuguan.json");
		return gson.fromJson(masterJson,new TypeToken<Collection<PersonVo>>(){}.getType());
	}
	public static List<String> getAllManagerUids(){
		return Lists.transform(Lists.newArrayList(getAllManager()), new Function<PersonVo, String>() {
			@Override
			public String apply(PersonVo input) {
				return input.getUid();
			}
		});
	}
	public static boolean isManager(){
		for (PersonVo person : Utils.getAllManager()) {
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
		/**yyyy-MM-dd**/
		public static final SimpleDateFormat dateSdf = new SimpleDateFormat("yyyy-MM-dd");
		/**yyyy-MM-dd HH:mm:ss**/
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
	
	
	/**
	 * 门户和协同消息
	 * @author xuechong
	 */
	public static class Message{
		
		
	}
	
	public static String getCurAppLocation(){
		return PropertiesUtil.findPropertieValue("config.properties", "curapplocation");
	}
	
}

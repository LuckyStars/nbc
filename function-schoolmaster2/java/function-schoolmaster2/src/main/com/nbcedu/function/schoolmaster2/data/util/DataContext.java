package com.nbcedu.function.schoolmaster2.data.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.xwork.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;

import com.nbcedu.function.schoolmaster2.data.interfaces.DataGenerator;
import com.nbcedu.function.schoolmaster2.data.interfaces.Matcher;

public class DataContext {
	private static final Logger logger = Logger.getLogger(DataContext.class);
	private static final String PACKAGE_TO_SCAN = "com.nbcedu.function.schoolmaster2.data.impl";
	
	private static DataContext context;
	
	private Map<String, Class<?>> clazzMap  = new HashMap<String, Class<?>>();
	
	public static DataContext getContext(){
		if(context==null){
			init();
		}
		return context;
	}
	
	private static void init(){
		context = new DataContext();
		Set<Class<?>> allDataGenerator = Scanner.getPackageAllClasses(PACKAGE_TO_SCAN, false);
		if(!CollectionUtils.isEmpty(allDataGenerator)){
			for (Class<?> clazz : allDataGenerator) {
				String matcherStr = getMatcherName(clazz);
				if(StringUtils.isNotBlank(matcherStr)){
					if(DataGenerator.class.isAssignableFrom(clazz)){
						context.clazzMap.put(matcherStr, clazz);
					}
				}
			}
		}
	}
	
	private static String getMatcherName(Class<?> clazz){
		Matcher anno = clazz.getAnnotation(Matcher.class);
		return anno!=null?anno.value():"";
	}
	
	/**
	 * 
	 * @param matcher
	 * @return
	 * @author xuechong
	 */
	public DataGenerator getbyMatcher(String matcher){
		if(this.clazzMap.containsKey(matcher)){
			try {
				return (DataGenerator) clazzMap.get(matcher).newInstance();
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e);
			}
		}
		return null;
	}
}

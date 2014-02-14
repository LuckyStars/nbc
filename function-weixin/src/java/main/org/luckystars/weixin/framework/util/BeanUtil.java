package org.luckystars.weixin.framework.util;

public class BeanUtil {

	@SuppressWarnings("rawtypes")
	public static Object getBeanByClassName(String className) 
		throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		Object result = null;
		Class cla = Thread.currentThread().getContextClassLoader().loadClass(className);
		result = cla.newInstance();
		return result;
	}
	
}

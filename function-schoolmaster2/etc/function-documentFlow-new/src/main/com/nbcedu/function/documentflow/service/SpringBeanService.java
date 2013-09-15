/*
 * @Title: SpringBeanService.java
 * @Package com.nbcedu.function.documentflow.service
 * @Description: 获得Spring上下文中的Bean的工具类。
 * @author Wang Zhuoxuan wangzhuoxuan@nbcedu.com
 * @date 2011-11-1 下午01:24:45
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2011-11-1                          
 */
package com.nbcedu.function.documentflow.service;

import java.io.Serializable;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

/** 
 * <p>获得Spring上下文中的Bean的工具类</p>
 * 
 * @author Wang Zhuoxuan
 * Create at:2011-11-1 下午01:24:45
 */
public class SpringBeanService implements Serializable {

	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = -3563623148543126234L;

	/** 
	 * 获得Spring上下文中的Bean
	 * 
	 * @param <T> 获得的Bean的类型
	 * @param clazz 要获得的Bean对应的Class
	 * @param beanName 要获得的Bean的名称
	 * @return Spring上下文中的Bean
	 */ 
	@SuppressWarnings("unchecked")
	public <T> T getBean(Class<T> clazz, String beanName) {  
        ApplicationContext context = ContextLoader.getCurrentWebApplicationContext();  
        return (T) context.getBean(beanName);  
    } 
}

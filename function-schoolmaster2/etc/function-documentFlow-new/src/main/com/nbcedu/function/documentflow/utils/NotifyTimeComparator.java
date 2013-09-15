/*
 * @Title: NotifyTimeComparator.java
 * @Package com.nbcedu.function.documentflow.utils
 * @Description: NotifyTime比较器
 * @author Wang Zhuoxuan wangzhuoxuan@nbcedu.com
 * @date 2011-8-9 下午11:27:47
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2011-8-9                          
 */
package com.nbcedu.function.documentflow.utils;

import java.io.Serializable;
import java.util.Comparator;

import com.nbcedu.function.documentflow.model.NotifyTime;

/** 
 * <p>NotifyTime比较器</p>
 * 
 * @author Wang Zhuoxuan
 * Create at:2011-8-9 下午11:27:47
 */
public class NotifyTimeComparator implements Comparator<NotifyTime>, Serializable {

	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = 1L;

	/* 
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(NotifyTime o1, NotifyTime o2) {
		return o1.getOrderId() - o2.getOrderId();
	}
}

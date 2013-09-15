/*
 * @Title: NotifyTimeVO.java
 * @Package com.nbcedu.function.documentflow.vo
 * @Description: NotifyTime实体的值对象，该类包含了页面交互中的属性及其对应的读写方法。
 * @author Wang Zhuoxuan wangzhuoxuan@nbcedu.com
 * @date 2011-8-8 下午05:15:20
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2011-8-8                          
 */
package com.nbcedu.function.documentflow.vo;

/** 
 * <p>NotifyTime实体的值对象</p>
 * 
 * @author Wang Zhuoxuan
 * Create at:2011-8-8 下午05:15:20
 */
public class NotifyTimeVO {
	/**
	 * 短信提醒时间唯一标识
	 */
	private String id;
	/**
	 * 短信提醒时间的名称
	 */
	private String name;
	/**
	 * 短信提醒时间的显示名称
	 */
	private String displayName;
	/**
	 * 排序序号
	 */
	private int orderId;
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}
	/**
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	/**
	 * @return the orderId
	 */
	public int getOrderId() {
		return orderId;
	}
	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
}

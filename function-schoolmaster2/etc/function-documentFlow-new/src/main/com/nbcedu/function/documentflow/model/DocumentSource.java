/*
 * @Title: DocumentSource.java
 * @Package com.nbcedu.function.documentflow.model
 * @Description: 发文单位实体对象，该类包含了发文单位实体的所有属性。
 * @author Wang Zhuoxuan wangzhuoxuan@nbcedu.com
 * @date 2011-7-21 下午02:32:55
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2011-7-21                          
 */
package com.nbcedu.function.documentflow.model;

/** 
 * <p>发文单位实体对象</p>
 * 
 * @author Wang Zhuoxuan
 * Create at:2011-7-21 下午02:32:55
 */
public class DocumentSource {
	
	/**
	 * 发文单位的唯一标识
	 */
	private String id;
	/**
	 * 发文单位的显示名称
	 */
	private String displayName;
	/**
	 * 发文单位状态（0：已撤销；1：启用中）
	 */
	private String status;

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
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
}

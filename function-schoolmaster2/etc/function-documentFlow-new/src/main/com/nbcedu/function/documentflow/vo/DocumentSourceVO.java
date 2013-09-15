/*
 * @Title: DocumentSourceVO.java
 * @Package com.nbcedu.function.documentflow.vo
 * @Description: DocumentSource实体的值对象，该类包含了页面交互中的属性及其对应的读写方法。
 * @author Wang Zhuoxuan wangzhuoxuan@nbcedu.com
 * @date 2011-8-4 下午04:50:38
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2011-8-4                          
 */
package com.nbcedu.function.documentflow.vo;

/** 
 * <p>DocumentSource实体的值对象</p>
 * 
 * @author Wang Zhuoxuan
 * Create at:2011-8-4 下午04:50:38
 */
public class DocumentSourceVO {
	
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

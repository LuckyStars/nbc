/*
 * @Title: ForwardingVO.java
 * @Package com.nbcedu.function.documentflow.vo
 * @Description: Forwarding实体的值对象，该类包含了页面交互中的属性及其对应的读写方法。
 * @author Wang Zhuoxuan wangzhuoxuan@nbcedu.com
 * @date 2011-8-22 上午09:04:36
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2011-8-22                          
 */
package com.nbcedu.function.documentflow.vo;

/** 
 * <p>Forwarding实体的值对象</p>
 * 
 * @author Wang Zhuoxuan
 * Create at:2011-8-22 上午09:04:36
 */
public class ForwardingVO {
	
	/**
	 * 转发实体的唯一标识
	 */
	private String id;
	/**
	 * 转发人唯一标识
	 */
	private String forwardingUser;
	/**
	 * 接收转发的人的唯一标识的集合
	 */
	private String[] receivers;
	/**
	 * 所转发的公文的唯一标识
	 */
	private String documentId;
	
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
	 * @return the forwardingUser
	 */
	public String getForwardingUser() {
		return forwardingUser;
	}
	/**
	 * @param forwardingUser the forwardingUser to set
	 */
	public void setForwardingUser(String forwardingUser) {
		this.forwardingUser = forwardingUser;
	}
	/**
	 * @return the receivers
	 */
	public String[] getReceivers() {
		return receivers;
	}
	/**
	 * @param receivers the receivers to set
	 */
	public void setReceivers(String[] receivers) {
		this.receivers = receivers;
	}
	/**
	 * @return the documentId
	 */
	public String getDocumentId() {
		return documentId;
	}
	/**
	 * @param documentId the documentId to set
	 */
	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}
}

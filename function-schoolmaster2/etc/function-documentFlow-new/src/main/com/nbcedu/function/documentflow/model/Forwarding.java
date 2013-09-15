/*
 * @Title: Forwarding.java
 * @Package com.nbcedu.function.documentflow.model
 * @Description: 转发实体，该类包含了转发的所有属性。
 * @author Wang Zhuoxuan wangzhuoxuan@nbcedu.com
 * @date 2011-8-18 下午01:18:04
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2011-8-18                          
 */
package com.nbcedu.function.documentflow.model;

import java.util.List;

/** 
 * <p>转发实体</p>
 * 
 * @author Wang Zhuoxuan
 * Create at:2011-8-18 下午01:18:04
 */
public class Forwarding {
	
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
	private List<String> receivers;
	/**
	 * 所转发的公文
	 */
	private Document document;
	
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
	public List<String> getReceivers() {
		return receivers;
	}
	/**
	 * @param receivers the receivers to set
	 */
	public void setReceivers(List<String> receivers) {
		this.receivers = receivers;
	}
	/**
	 * @return the document
	 */
	public Document getDocument() {
		return document;
	}
	/**
	 * @param document the document to set
	 */
	public void setDocument(Document document) {
		this.document = document;
	}
}

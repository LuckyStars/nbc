/*
 * @Title: DocumentTask.java
 * @Package com.nbcedu.function.documentflow.model
 * @Description: 公文任务实体对象，该类包含了公文任务的所有属性。
 * @author Wang Zhuoxuan wangzhuoxuan@nbcedu.com
 * @date 2011-8-15 上午10:26:27
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2011-8-15                          
 */
package com.nbcedu.function.documentflow.model;

import java.util.Date;

/** 
 * <p>公文任务实体对象</p>
 * 
 * @author Wang Zhuoxuan
 * Create at:2011-8-15 上午10:26:27
 */
public class DocumentTask {
	/**
	 * 公文任务实体的唯一标识
	 */
	private String id;
	/**
	 * 任务对应的公文实体唯一标识
	 */
	private String documentId;
	/**
	 * 处理人唯一标识
	 */
	private String handlerId;
	/**
	 * 是否已处理（0：未处理；1：已处理）
	 */
	private boolean isHandled;
	/**
	 * 处理时间
	 */
	private Date handlingTime;
	/**
	 * 任务类型（0：原发任务；1：转发任务）
	 */
	private String type;
	
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
	/**
	 * @return the handlerId
	 */
	public String getHandlerId() {
		return handlerId;
	}
	/**
	 * @param handlerId the handlerId to set
	 */
	public void setHandlerId(String handlerId) {
		this.handlerId = handlerId;
	}
	/**
	 * @return the isHandled
	 */
	public boolean getIsHandled() {
		return isHandled;
	}
	/**
	 * @param isHandled the isHandled to set
	 */
	public void setIsHandled(boolean isHandled) {
		this.isHandled = isHandled;
	}
	/**
	 * @return the handlingTime
	 */
	public Date getHandlingTime() {
		return handlingTime;
	}
	/**
	 * @param handlingTime the handlingTime to set
	 */
	public void setHandlingTime(Date handlingTime) {
		this.handlingTime = handlingTime;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
}

/*
 * @Title: Comment.java
 * @Package com.nbcedu.function.documentflow.model
 * @Description: 公文回复实体对象，该类包含了公文回复的所有属性及属性的读写方法。
 * @author Wang Zhuoxuan wangzhuoxuan@nbcedu.com
 * @date 2011-8-2 下午03:42:18
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2011-8-2                          
 */
package com.nbcedu.function.documentflow.model;

import java.util.Date;

/** 
 * <p>公文回复实体对象</p>
 * 
 * @author Wang Zhuoxuan
 * Create at:2011-8-2 下午03:42:18
 */
public class Comment {

	/**
	 * 回复的唯一标识
	 */
	private String id;
	/**
	 * 回复的内容
	 */
	private String content;
	/**
	 * 回复的序号
	 */
	private Integer orderId;
	/**
	 * 回复时间
	 */
	private Date createTime;
	/**
	 * 回复所属的公文
	 */
	private Document document;
	/**
	 * 回复人唯一标识
	 */
	private String replierId; //pid
	
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
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @return the orderId
	 */
	public Integer getOrderId() {
		return orderId;
	}
	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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
	/**
	 * @return the replierId
	 */
	public String getReplierId() {
		return replierId;
	}
	/**
	 * @param replierId the replierId to set
	 */
	public void setReplierId(String replierId) {
		this.replierId = replierId;
	}
}

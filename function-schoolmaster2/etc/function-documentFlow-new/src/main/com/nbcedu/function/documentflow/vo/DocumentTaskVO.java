/*
 * @Title: DocumentHandledVO.java
 * @Package com.nbcedu.function.documentflow.vo
 * @Description: 已处理公文的值对象，该类包含了页面交互中的属性及其对应的读写方法。
 * @author Wang Zhuoxuan wangzhuoxuan@nbcedu.com
 * @date 2011-8-15 下午01:23:48
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2011-8-15                          
 */
package com.nbcedu.function.documentflow.vo;

/** 
 * <p>公文任务的值对象</p>
 * 
 * @author Wang Zhuoxuan
 * Create at:2011-8-15 下午01:23:48
 */
public class DocumentTaskVO {
	/**
	 * 公文的唯一标识
	 */
	private String id;
	/**
	 * 公文标题
	 */
	private String documentTitle;
	/**
	 * 不进行摘要截取的长标题
	 */
	private String longTitle;
	/**
	 * 发布时间
	 */
	private String publishTime;
	/**
	 * 过期时间
	 */
	private String expireTime;
	/**
	 * 起草人
	 */
	private String authorName;
	/**
	 * 公文流转状态
	 */
	private String status;
	/**
	 * 处理时间
	 */
	private String handlingTime;
	/**
	 * 任务类型（0：原发任务；1：转发任务）
	 */
	private String type;
	/**
	 * 公文来源
	 */
	private String documentSourceDisplayName;

	private DocumentVO documentVO;

	public DocumentVO getDocumentVO() {
		return documentVO;
	}
	public void setDocumentVO(DocumentVO documentVO) {
		this.documentVO = documentVO;
	}
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
	 * @return the documentTitle
	 */
	public String getDocumentTitle() {
		return documentTitle;
	}
	/**
	 * @param documentTitle the documentTitle to set
	 */
	public void setDocumentTitle(String documentTitle) {
		this.documentTitle = documentTitle;
	}
	/**
	 * @return the longTitle
	 */
	public String getLongTitle() {
		return longTitle;
	}
	/**
	 * @param longTitle the longTitle to set
	 */
	public void setLongTitle(String longTitle) {
		this.longTitle = longTitle;
	}
	/**
	 * @return the publishTime
	 */
	public String getPublishTime() {
		return publishTime;
	}
	/**
	 * @param publishTime the publishTime to set
	 */
	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}
	/**
	 * @return the expireTime
	 */
	public String getExpireTime() {
		return expireTime;
	}
	/**
	 * @param expireTime the expireTime to set
	 */
	public void setExpireTime(String expireTime) {
		this.expireTime = expireTime;
	}
	/**
	 * @return the authorName
	 */
	public String getAuthorName() {
		return authorName;
	}
	/**
	 * @param authorName the authorName to set
	 */
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
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
	/**
	 * @return the handlingTime
	 */
	public String getHandlingTime() {
		return handlingTime;
	}
	/**
	 * @param handlingTime the handlingTime to set
	 */
	public void setHandlingTime(String handlingTime) {
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
	public String getDocumentSourceDisplayName() {
		return documentSourceDisplayName;
	}
	public void setDocumentSourceDisplayName(String documentSourceDisplayName) {
		this.documentSourceDisplayName = documentSourceDisplayName;
	}
}

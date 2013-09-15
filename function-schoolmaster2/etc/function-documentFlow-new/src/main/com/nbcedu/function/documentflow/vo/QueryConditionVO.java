/*
 * @Title: QueryConditionVO.java
 * @Package com.nbcedu.function.documentflow.vo
 * @Description: 公文查询条件的值对象，该类包含了页面交互中的属性及其对应的读写方法。
 * @author Wang Zhuoxuan wangzhuoxuan@nbcedu.com
 * @date 2011-8-15 下午01:40:39
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2011-8-15                          
 */
package com.nbcedu.function.documentflow.vo;

/** 
 * <p>公文查询条件的值对象</p>
 * 
 * @author Wang Zhuoxuan
 * Create at:2011-8-15 下午01:40:39
 */
public class QueryConditionVO {
	/**
	 * 公文名称
	 */
	private String documentName;
	/**
	 * 公文发布时间起始时间
	 */
	private String starting;
	/**
	 * 公文发布时间结束时间
	 */
	private String ending;
	/**
	 * 发文单位唯一标识
	 */
	private String documentSourceId;
	/**
	 * 状态
	 */
	private String documentStatus;
	
	/**
	 * @return the documentName
	 */
	public String getDocumentName() {
		return documentName;
	}
	/**
	 * @param documentName the documentName to set
	 */
	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}
	/**
	 * @return the starting
	 */
	public String getStarting() {
		return starting;
	}
	/**
	 * @param starting the starting to set
	 */
	public void setStarting(String starting) {
		this.starting = starting;
	}
	/**
	 * @return the ending
	 */
	public String getEnding() {
		return ending;
	}
	/**
	 * @param ending the ending to set
	 */
	public void setEnding(String ending) {
		this.ending = ending;
	}
	/**
	 * @return the documentSourceId
	 */
	public String getDocumentSourceId() {
		return documentSourceId;
	}
	/**
	 * @param documentSourceId the documentSourceId to set
	 */
	public void setDocumentSourceId(String documentSourceId) {
		this.documentSourceId = documentSourceId;
	}
	public String getDocumentStatus() {
		return documentStatus;
	}
	public void setDocumentStatus(String documentStatus) {
		this.documentStatus = documentStatus;
	}
}

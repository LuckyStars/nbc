/*
 * @Title: CommentVO.java
 * @Package com.nbcedu.function.documentflow.vo
 * @Description: Comment实体的值对象，该类包含了页面交互中的属性及其对应的读写方法。
 * @author Wang Zhuoxuan wangzhuoxuan@nbcedu.com
 * @date 2011-8-20 下午07:50:21
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2011-8-20                          
 */
package com.nbcedu.function.documentflow.vo;

/** 
 * <p>Comment实体的值对象</p>
 * 
 * @author Wang Zhuoxuan
 * Create at:2011-8-20 下午07:50:21
 */
public class CommentVO {
	/**
	 * 回复的唯一标识
	 */
	private String id;
	/**
	 * 回复人姓名
	 */
	private String replier;
	/**
	 * 回复时间
	 */
	private String replyTime;
	/**
	 * 回复内容
	 */
	private String content;
	/**
	 * 回复人唯一标识
	 */
	private String replierId;
	
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
	 * @return the replier
	 */
	public String getReplier() {
		return replier;
	}
	/**
	 * @param replier the replier to set
	 */
	public void setReplier(String replier) {
		this.replier = replier;
	}
	/**
	 * @return the replyTime
	 */
	public String getReplyTime() {
		return replyTime;
	}
	/**
	 * @param replyTime the replyTime to set
	 */
	public void setReplyTime(String replyTime) {
		this.replyTime = replyTime;
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

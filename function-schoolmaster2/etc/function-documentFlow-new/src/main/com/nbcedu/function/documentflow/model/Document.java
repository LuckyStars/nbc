/*
 * @Title: Document.java
 * @Package com.nbcedu.function.documentflow.model
 * @Description: 公文实体对象，该类包含了公文的所有属性。
 * @author Wang Zhuoxuan wangzhuoxuan@nbcedu.com
 * @date 2011-7-21 下午02:23:54
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2011-7-21                          
 */
package com.nbcedu.function.documentflow.model;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

/** 
 * <p>公文实体对象</p>
 * 
 * @author Wang Zhuoxuan
 * Create at:2011-7-21 下午02:23:54
 */
public class Document {
	
	
	/**
	 * 唯一标识
	 */
	private String id;
	/**
	 * 发布者唯一标识
	 */
	private String authorId; //pid
	/**
	 * 公文标题
	 */
	private String title;
	/**
	 * 提醒模式的唯一标识
	 */
	private String notifyProfileId;
	/**
	 * 发文单位唯一标识
	 */
	private String documentSourceId;
	/**
	 * 有效期: -1(请选择) 0(自定义) 1(1个月) 2(2个月) 3(3个月) 6(6个月) 12(12个月)
	 */
	private String period;
	/**
	 * 发布时间
	 */
	private Date publishTime;
	/**
	 * 截止日期
	 */
	private Date expireTime;
	/**
	 * 录入时间
	 */
	private Date insertTime;
	
	/**
	 * 公文内容
	 */
	private String content;
	/**
	 * 接收人唯一标识列表
	 */
	private List<String> receiverIds; //pid
	/**
	 * 短信提醒内容
	 */
	private String notifyContent;
	/**
	 * 提醒人的唯一标识列表
	 */
	private List<String> notifierIds;
	/**
	 * 公文状态(0:未发布；1：已发布(流转中)；2：已结束；3：已过期)
	 */
	private int status;
	/**
	 * 关闭状态 0未关 1已关
	 */
	private int shut;
	public int getShut() {
		return shut;
	}
	public void setShut(int shut) {
		this.shut = shut;
	}
	/**
	 * 公文回复
	 */
	private Set<Comment> comments;
	/**
	 * 已经处理的人员的唯一标识列表
	 */
	private List<String> receivedIds; 
	/**
	 * 转发流向的唯一标识列表
	 */
	private Set<Forwarding> forwardings;
	/**
	 * 附件列表
	 */
	private Set<Attachment> attachments;
	
	private  static final Logger logger = Logger.getLogger(Document.class);
	/**
	 * 公文的流转状态
	 * @author xuechong
	 */
	public static enum Status{
		/**"未发布",0*/
		NOT_PUB("未发布",0),
		/**"流转中",1*/
		FLOWING("流转中",1),
		/**"已结束",2*/
		FLOW_FIN("已结束",2),
		/**"已过期",3*/
		TIME_OVER("已过期",3);
		private String str;
		private int num;
		private Status(String str, int num) {
			this.str = str;
			this.num = num;
		}
		public String getStr() {
			return str;
		}
		public int getNum() {
			return num;
		}
		public static Status getStatusById(int id){
			switch (id) {
			case 0:
				return NOT_PUB;
			case 1:
				return FLOWING;
			case 2:
				return FLOW_FIN;
			case 3:
				return TIME_OVER;
			default:
				logger.error("尝试获取状态id为" + id + "的状态值,没有匹配的结果,返回默认值:未发布");
				return NOT_PUB;
			}
		}
	}
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAuthorId() {
		return authorId;
	}
	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getNotifyProfileId() {
		return notifyProfileId;
	}
	public void setNotifyProfileId(String notifyProfileId) {
		this.notifyProfileId = notifyProfileId;
	}
	public String getDocumentSourceId() {
		return documentSourceId;
	}
	public void setDocumentSourceId(String documentSourceId) {
		this.documentSourceId = documentSourceId;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public Date getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
	public Date getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public List<String> getReceiverIds() {
		return receiverIds;
	}
	public void setReceiverIds(List<String> receiverIds) {
		this.receiverIds = receiverIds;
	}
	public String getNotifyContent() {
		return notifyContent;
	}
	public void setNotifyContent(String notifyContent) {
		this.notifyContent = notifyContent;
	}
	public List<String> getNotifierIds() {
		return notifierIds;
	}
	public void setNotifierIds(List<String> notifierIds) {
		this.notifierIds = notifierIds;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Set<Comment> getComments() {
		return comments;
	}
	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}
	public List<String> getReceivedIds() {
		return receivedIds;
	}
	public void setReceivedIds(List<String> receivedIds) {
		this.receivedIds = receivedIds;
	}
	public Set<Forwarding> getForwardings() {
		return forwardings;
	}
	public void setForwardings(Set<Forwarding> forwardings) {
		this.forwardings = forwardings;
	}
	public Set<Attachment> getAttachments() {
		return attachments;
	}
	public void setAttachments(Set<Attachment> attachments) {
		this.attachments = attachments;
	}
	public Date getInsertTime() {
		return insertTime;
	}
	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}
}

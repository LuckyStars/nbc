/*
 * @Title: DocumentVO.java
 * @Package com.nbcedu.function.documentflow.vo
 * @Description: Document实体的值对象，该类包含了页面交互中的属性及其对应的读写方法。
 * @author Wang Zhuoxuan wangzhuoxuan@nbcedu.com
 * @date 2011-8-4 上午12:01:26
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2011-8-4                          
 */
package com.nbcedu.function.documentflow.vo;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.nbcedu.function.documentflow.model.Attachment;
import com.nbcedu.function.documentflow.model.Comment;

/** 
 * <p>Document实体的值对象</p>
 * @author Wang Zhuoxuan
 * Create at:2011-8-4 上午12:01:26
 */
public class DocumentVO {
	/**
	 * 唯一标识
	 */
	private String id;
	/**
	 * 发布者唯一标识
	 */
	private String authorId;
	/**
	 * 发布者名称
	 */
	private String authorName;
	/**
	 * 公文标题
	 */
	private String title;
	/**
	 * 提醒模式的唯一标识
	 */
	private String notifyProfileId;
	/**
	 * 提醒模式的名称
	 */
	private String notifyProfileName;
	/**
	 * 发文单位唯一标识
	 */
	private String documentSourceId;
	/**
	 * 发文单位名称
	 */
	private String documentSourceName;
	/**
	 * 发布时间
	 */
	private String publishTime;
	/**
	 * 有效期
	 */
	private String period;
	/**
	 * 截止日期
	 */
	private String expireTime;
	/**
	 * 录入日期
	 */
	private String insertTime;
	/**
	 * 公文内容
	 */
	private String content;
	/**
	 * 接收人唯一标识列表
	 */
	private String[] receiverIds;
	/**
	 * 短信提醒内容
	 */
	private String notifyContent;
	/**
	 * 提醒人的唯一标识列表
	 */
	private String[] notifierIds;
	/**
	 * 提醒的人，key:要提醒的人的唯一标识，value:要提醒的人的姓名
	 */
	private Map<String, String> notifiers;
	/**
	 * 公文状态
	 */
	private String status;
	/**
	 * 公文回复
	 */
	private List<CommentVO> comments;
	/**
	 * 已经处理的人员的唯一标识列表
	 */
	private String[] receivedIds;
	/**
	 * 已处理的人员姓名
	 */
	private List<String> receivedNames;
	/**
	 * 未处理的人员姓名
	 */
	private List<String> unhandledNames;
	private List<String> aLLDocNames;
	
	/**
	 * 转发流向的唯一标识列表
	 */
	private String[] forwardingIds;
	/**
	 * 转发流向的人员姓名
	 */
	private List<String> forwardingNames;
	/**
	 * 附件列表
	 */
	private Set<Attachment> attachments;
	/**
	 * 公文流转路径(k:转发人姓名,v:接收人姓名列表)
	 */
	private Map<String, List<String>> route;
	/**
	 * 接收人集合，key：接收人唯一标识，value：接收人姓名
	 */
	private Map<String, String> receivers;
	/**
	 * 转发人集合，key：接收人姓名，value：接收人姓名,分隔
	 */
	private Map<String, List<String>> forwarders;
	/**
	 * 最新回复
	 */
	private Comment lastComment;
	/**
	 * 已处理数量
	 */
	private int handledCount;
	/**
	 * 未处理数量
	 */
	private int unhandledCount;
	/**
	 * 最后回复人名称
	 */
	private String lastReplier;
	/**
	 * 最后回复的内容
	 */
	private String lastCommentContent;
	/**
	 * 是否可编辑，true可编辑，否则false
	 */
	private boolean isEditable;
	/**
	 * 公文结束时间
	 */
	private String endingTime;
	/**
	 * 公文回复时间
	 */
	private String commentReplyTime;
	/**
	 * 不进行摘要截取的长标题
	 */
	private String longTitle;
	///过期的
	private boolean outTime;
	
	
	
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
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
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
	public String getNotifyProfileName() {
		return notifyProfileName;
	}
	public void setNotifyProfileName(String notifyProfileName) {
		this.notifyProfileName = notifyProfileName;
	}
	public String getDocumentSourceId() {
		return documentSourceId;
	}
	public void setDocumentSourceId(String documentSourceId) {
		this.documentSourceId = documentSourceId;
	}
	public String getDocumentSourceName() {
		return documentSourceName;
	}
	public void setDocumentSourceName(String documentSourceName) {
		this.documentSourceName = documentSourceName;
	}
	public String getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public String getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(String expireTime) {
		this.expireTime = expireTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String[] getReceiverIds() {
		return receiverIds;
	}
	public void setReceiverIds(String[] receiverIds) {
		this.receiverIds = receiverIds;
	}
	public String getNotifyContent() {
		return notifyContent;
	}
	public void setNotifyContent(String notifyContent) {
		this.notifyContent = notifyContent;
	}
	public String[] getNotifierIds() {
		return notifierIds;
	}
	public void setNotifierIds(String[] notifierIds) {
		this.notifierIds = notifierIds;
	}
	public Map<String, String> getNotifiers() {
		return notifiers;
	}
	public void setNotifiers(Map<String, String> notifiers) {
		this.notifiers = notifiers;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<CommentVO> getComments() {
		return comments;
	}
	public void setComments(List<CommentVO> comments) {
		this.comments = comments;
	}
	public String[] getReceivedIds() {
		return receivedIds;
	}
	public void setReceivedIds(String[] receivedIds) {
		this.receivedIds = receivedIds;
	}
	public List<String> getReceivedNames() {
		return receivedNames;
	}
	public void setReceivedNames(List<String> receivedNames) {
		this.receivedNames = receivedNames;
	}
	public List<String> getUnhandledNames() {
		return unhandledNames;
	}
	public void setUnhandledNames(List<String> unhandledNames) {
		this.unhandledNames = unhandledNames;
	}
	public String[] getForwardingIds() {
		return forwardingIds;
	}
	public void setForwardingIds(String[] forwardingIds) {
		this.forwardingIds = forwardingIds;
	}
	public List<String> getForwardingNames() {
		return forwardingNames;
	}
	public void setForwardingNames(List<String> forwardingNames) {
		this.forwardingNames = forwardingNames;
	}
	public Set<Attachment> getAttachments() {
		return attachments;
	}
	public void setAttachments(Set<Attachment> attachments) {
		this.attachments = attachments;
	}
	public Map<String, List<String>> getRoute() {
		return route;
	}
	public void setRoute(Map<String, List<String>> route) {
		this.route = route;
	}
	public Comment getLastComment() {
		return lastComment;
	}
	public void setLastComment(Comment lastComment) {
		this.lastComment = lastComment;
	}
	public int getHandledCount() {
		return handledCount;
	}
	public void setHandledCount(int handledCount) {
		this.handledCount = handledCount;
	}
	public int getUnhandledCount() {
		return unhandledCount;
	}
	public void setUnhandledCount(int unhandledCount) {
		this.unhandledCount = unhandledCount;
	}
	public Map<String, String> getReceivers() {
		return receivers;
	}
	public void setReceivers(Map<String, String> receivers) {
		this.receivers = receivers;
	}
	public String getLastReplier() {
		return lastReplier;
	}
	public void setLastReplier(String lastReplier) {
		this.lastReplier = lastReplier;
	}
	public String getLastCommentContent() {
		return lastCommentContent;
	}
	public void setLastCommentContent(String lastCommentContent) {
		this.lastCommentContent = lastCommentContent;
	}
	public boolean getIsEditable() {
		return isEditable;
	}
	public void setIsEditable(boolean isEditable) {
		this.isEditable = isEditable;
	}
	public String getEndingTime() {
		return endingTime;
	}
	public void setEndingTime(String endingTime) {
		this.endingTime = endingTime;
	}
	public String getCommentReplyTime() {
		return commentReplyTime;
	}
	public void setCommentReplyTime(String commentReplyTime) {
		this.commentReplyTime = commentReplyTime;
	}
	public String getLongTitle() {
		return longTitle;
	}
	public void setLongTitle(String longTitle) {
		this.longTitle = longTitle;
	}
	public Map<String, List<String>> getForwarders() {
		return forwarders;
	}
	public void setForwarders(Map<String, List<String>> forwarders) {
		this.forwarders = forwarders;
	}
	public List<String> getaLLDocNames() {
		return aLLDocNames;
	}
	public void setaLLDocNames(List<String> aLLDocNames) {
		this.aLLDocNames = aLLDocNames;
	}
	public String getInsertTime() {
		return insertTime;
	}
	public void setInsertTime(String insertTime) {
		this.insertTime = insertTime;
	}
	public boolean getOutTime() {
		return outTime;
	}
	public void setOutTime(boolean outTime) {
		this.outTime = outTime;
	}
	
}

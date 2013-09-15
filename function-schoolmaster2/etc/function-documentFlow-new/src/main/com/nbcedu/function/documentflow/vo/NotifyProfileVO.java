/*
 * @Title: NotifyProfileVO.java
 * @Package com.nbcedu.function.documentflow.vo
 * @Description: NotifyProfile实体的值对象，该类包含了页面交互中的属性及其对应的读写方法。
 * @author Wang Zhuoxuan wangzhuoxuan@nbcedu.com
 * @date 2011-8-8 下午01:36:22
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2011-8-8                          
 */
package com.nbcedu.function.documentflow.vo;

/** 
 * <p>NotifyProfile实体的值对象</p>
 * 
 * @author Wang Zhuoxuan
 * Create at:2011-8-8 下午01:36:22
 */
public class NotifyProfileVO {
	
	/**
	 * 短信提醒方案的唯一标识
	 */
	private String id;
	/**
	 * 是否是默认的方案
	 */
	private Boolean isDefault;
	/**
	 * 方案的名称（预置“普通”、“紧急”、“其他”）
	 */
	private String profileName;
	/**
	 * 是否可进行人员编辑
	 */
	private Boolean isEdit;
	/**
	 * 提醒的时间数组
	 */
	private NotifyTimeVO[] notifyTimeVos;
	/**
	 * 提醒的内容
	 */
	private String content;
	/**
	 * 提醒模式状态（0：已撤销；1：启用中）
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
	 * @return the isDefault
	 */
	public Boolean getIsDefault() {
		return isDefault;
	}
	/**
	 * @param isDefault the isDefault to set
	 */
	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}
	/**
	 * @return the profileName
	 */
	public String getProfileName() {
		return profileName;
	}
	/**
	 * @param profileName the profileName to set
	 */
	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}
	/**
	 * @return the isEdit
	 */
	public Boolean getIsEdit() {
		return isEdit;
	}
	/**
	 * @param isEdit the isEdit to set
	 */
	public void setIsEdit(Boolean isEdit) {
		this.isEdit = isEdit;
	}
	/**
	 * @return the notifyTimeVos
	 */
	public NotifyTimeVO[] getNotifyTimeVos() {
		return notifyTimeVos;
	}
	/**
	 * @param notifyTimeVos the notifyTimeVos to set
	 */
	public void setNotifyTimeVos(NotifyTimeVO[] notifyTimeVos) {
		this.notifyTimeVos = notifyTimeVos;
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

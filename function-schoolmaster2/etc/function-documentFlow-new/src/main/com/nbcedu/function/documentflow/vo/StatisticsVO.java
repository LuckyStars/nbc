/*
 * @Title: StatisticsVO.java
 * @Package com.nbcedu.function.documentflow.vo
 * @Description: 统计信息的值对象，该类包含了页面交互中的属性及其对应的读写方法。
 * @author Wang Zhuoxuan wangzhuoxuan@nbcedu.com
 * @date 2011-9-1 下午02:46:34
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2011-9-1                          
 */
package com.nbcedu.function.documentflow.vo;

/** 
 * <p>统计信息的值对象</p>
 * 
 * @author Wang Zhuoxuan
 * Create at:2011-9-1 下午02:46:34
 */
public class StatisticsVO {
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 所属部门
	 */
	private String department;
	/**
	 * 处理公文的数量
	 */
	private int handledCount;
	/**
	 * 处理公文的百分比
	 */
	private String handlePercent;
	/**
	 * 未处理公文的数量
	 */
	private int unhandledCount;
	/**
	 * 为处理公文的百分比
	 */
	private String unhandledPercent;
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}
	/**
	 * @param department the department to set
	 */
	public void setDepartment(String department) {
		this.department = department;
	}
	/**
	 * @return the handledCount
	 */
	public int getHandledCount() {
		return handledCount;
	}
	/**
	 * @param handledCount the handledCount to set
	 */
	public void setHandledCount(int handledCount) {
		this.handledCount = handledCount;
	}
	/**
	 * @return the handlePercent
	 */
	public String getHandlePercent() {
		return handlePercent;
	}
	/**
	 * @param handlePercent the handlePercent to set
	 */
	public void setHandlePercent(String handlePercent) {
		this.handlePercent = handlePercent;
	}
	/**
	 * @return the unhandledCount
	 */
	public int getUnhandledCount() {
		return unhandledCount;
	}
	/**
	 * @param unhandledCount the unhandledCount to set
	 */
	public void setUnhandledCount(int unhandledCount) {
		this.unhandledCount = unhandledCount;
	}
	/**
	 * @return the unhandledPercent
	 */
	public String getUnhandledPercent() {
		return unhandledPercent;
	}
	/**
	 * @param unhandledPercent the unhandledPercent to set
	 */
	public void setUnhandledPercent(String unhandledPercent) {
		this.unhandledPercent = unhandledPercent;
	}
}

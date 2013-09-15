/*
 * @Title: Route.java
 * @Package com.nbcedu.function.documentflow.model
 * @Description: 流转路径实体对象，该类包含了流转路径的所有属性及属性的读写方法。
 * @author Wang Zhuoxuan wangzhuoxuan@nbcedu.com
 * @date 2011-10-8 下午10:19:38
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2011-10-8                          
 */
package com.nbcedu.function.documentflow.model;

import java.util.Set;

/** 
 * <p>流转路径实体对象</p>
 * 
 * @author Wang Zhuoxuan
 * Create at:2011-10-8 下午10:19:38
 */
public class Route {

	/**
	 * 流转路径的唯一标识
	 */
	private String id;
	/**
	 * 流转路径的显示名称
	 */
	private String routeName;
	/**
	 * 创建者的唯一标识
	 */
	private String creatorId; //以前是user表主键，改成 pid
	/**
	 * 流转人员唯一标识的列表
	 */
	private Set<String> flow;
	
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
	 * @return the routeName
	 */
	public String getRouteName() {
		return routeName;
	}
	/**
	 * @param routeName the routeName to set
	 */
	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}
	/**
	 * @return the creatorId
	 */
	public String getCreatorId() {
		return creatorId;
	}
	/**
	 * @param creatorId the creatorId to set
	 */
	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}
	/**
	 * @return the flow
	 */
	public Set<String> getFlow() {
		return flow;
	}
	/**
	 * @param flow the flow to set
	 */
	public void setFlow(Set<String> flow) {
		this.flow = flow;
	}
}

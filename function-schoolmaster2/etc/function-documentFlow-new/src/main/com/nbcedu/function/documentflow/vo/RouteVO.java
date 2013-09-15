/*
 * @Title: RouteVO.java
 * @Package com.nbcedu.function.documentflow.vo
 * @Description: Route业务实体，该类包含了页面交互中的属性及其对应的读写方法。
 * @author Wang Zhuoxuan wangzhuoxuan@nbcedu.com
 * @date 2011-10-8 下午10:59:09
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2011-10-8                          
 */
package com.nbcedu.function.documentflow.vo;

/** 
 * <p>Route业务实体</p>
 * 
 * @author Wang Zhuoxuan
 * Create at:2011-10-8 下午10:59:09
 */
public class RouteVO {
	
	/**
	 * Route业务实体唯一标识
	 */
	private String id;
	/**
	 * 流转路径名称
	 */
	private String routeName;
	/**
	 * 创建者唯一标识
	 */
	private String creatorId;
	/**
	 * 流转人员唯一标识的字符串，用“,”分隔
	 */
	private String flow;
	
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
	public String getFlow() {
		return flow;
	}
	/**
	 * @param flow the flow to set
	 */
	public void setFlow(String flow) {
		this.flow = flow;
	}
}

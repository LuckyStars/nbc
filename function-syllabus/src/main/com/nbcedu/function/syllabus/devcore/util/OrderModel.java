package com.nbcedu.function.syllabus.devcore.util;

/**
 * 排序bean
 * 
 * @author qinyuan
 * @version 1.0
 */
public class OrderModel {
	public static final String ASC = "asc";
	public static final String DESC = "desc";
	
	private String order; //排序，取值上述2个常量：ASC，DESC
	private String orderBy; //排序字段名
	
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	
}

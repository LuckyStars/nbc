package org.luckystars.weixin.framework.api;

import java.io.Serializable;


/**
 * 返回视图
 * @author xuechong
 */
public interface View extends Serializable{
	
	public ViewType getViewType();
	
	public String toViewString();
	
	public static enum ViewType{
		WeixinView,JSP;
	}
}

/*
 * @Title: HomeAction.java
 * @Package com.nbcedu.function.cardmanage.action
 * @Description: 首页的请求处理控制器。
 * @author wanglei@nbcedu.com
 * @date 2013-8-8 上午09:33:55
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2013-8-8                          
 */
package com.nbcedu.function.cardmanage.action;

import com.nbcedu.function.cardmanage.core.action.BaseAction;

/**
 * <p>
 * 首页的请求处理控制器。
 * </p>
 * 
 * @author wanglei Create at:2013-8-8 上午09:33:55
 */
public class CMCardHomeAction extends BaseAction{
	/**
	 * 预加载信息并显示首页
	 * v2
	 * @return 跳转至首页
	 */
	public String home() {

//		List<String> roleList = new ArrayList<String>();
//		User user = (User) ActionContext.getContext().getSession().get("documentFlow_init");
//
//		for (Role role : user.getRoles()) {
//			roleList.add(role.getName());
//		}
//		ActionContext.getContext().getSession().put("roleList", roleList);
		return "home";
	}

}

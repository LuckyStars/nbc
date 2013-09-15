/*
 * @Title: HomeAction.java
 * @Package com.nbcedu.function.documentflow.action
 * @Description: 首页的请求处理控制器。
 * @author Wang Zhuoxuan wangzhuoxuan@nbcedu.com
 * @date 2011-9-15 上午09:33:55
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2011-9-15                          
 */
package com.nbcedu.function.documentflow.action;

import java.util.ArrayList;
import java.util.List;

import com.nbcedu.core.privilege.model.Role;
import com.nbcedu.core.privilege.model.User;
import com.opensymphony.xwork2.ActionContext;

/**
 * <p>
 * 首页的请求处理控制器。
 * </p>
 * 
 * @author Wang Zhuoxuan Create at:2011-9-15 上午09:33:55
 */
public class HomeAction {
	/**
	 * 预加载信息并显示首页
	 * v2
	 * @return 跳转至首页
	 */
	public String home() {
		// 测试
		// Map<String,String> param = new HashMap<String, String>();
		// param.put("uid", "1");
		// ServiceInfoLoader loader = new SpringBeanService().getBean(ServiceInfoLoader.class, "documentFlowLoader");
		// ActionContext.getContext().getSession().put("documentFlow_init", loader.load(param));
		
		List<String> roleList = new ArrayList<String>();
		User user = (User) ActionContext.getContext().getSession().get("documentFlow_init");

		for (Role role : user.getRoles()) {
			roleList.add(role.getName());
		}
		ActionContext.getContext().getSession().put("roleList", roleList);
		return "home";
	}

}

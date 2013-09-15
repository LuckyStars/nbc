package com.nbcedu.function.documentflow.intercept;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class DoucumentFlowURLInterceptor extends AbstractInterceptor {
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		String uid = (String) ServletActionContext.getRequest().getSession().getAttribute(
				"edu.yale.its.tp.cas.client.filter.user");
		if ((uid == null) || ("".equals(uid))) {
			// ((ActionSupport) invocation.getAction()).addActionError("请先登录系统!");
			return "doucumentFlowLogin";
		} else {
			return invocation.invoke();
		}
	}

}

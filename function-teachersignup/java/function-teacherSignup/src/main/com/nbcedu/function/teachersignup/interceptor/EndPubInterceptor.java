package com.nbcedu.function.teachersignup.interceptor;

import com.nbcedu.function.teachersignup.biz.TSActivityBiz;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

@SuppressWarnings("serial")
public class EndPubInterceptor extends MethodFilterInterceptor{

	private TSActivityBiz actBiz;
	
	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		this.actBiz.modifyFinActs();
		return invocation.invoke();
	}

	public void setActBiz(TSActivityBiz actBiz) {
		this.actBiz = actBiz;
	}
	
	
}

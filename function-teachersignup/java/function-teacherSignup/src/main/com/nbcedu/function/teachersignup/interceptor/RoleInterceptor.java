package com.nbcedu.function.teachersignup.interceptor;

import java.util.Map;

import org.apache.commons.lang.xwork.StringUtils;

import com.nbcedu.function.teachersignup.biz.TSUserPrivilegeBiz;
import com.nbcedu.function.teachersignup.constants.Constants;
import com.nbcedu.function.teachersignup.model.TSUserPrivilege;
import com.nbcedu.function.teachersignup.util.UcService;
import com.nbcedu.function.teachersignup.vo.TSUser;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

@SuppressWarnings("serial")
public class RoleInterceptor implements Interceptor {

	private TSUserPrivilegeBiz userPrivilegeBiz;
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		if(getSession(invocation).get(Constants.SESSION_USER_KEY) == null){
			String uid = (String) getSession(invocation).get(Constants.SESSION_UID_KEY);
			String userName = UcService.findUserNameByUid(uid);
			TSUserPrivilege pri = this.userPrivilegeBiz.findByUid(uid);
			boolean isAdmin = pri!=null&&StringUtils.isNotBlank(pri.getId());
			TSUser curUser = new TSUser(userName, uid, isAdmin);
			getSession(invocation).put(Constants.SESSION_USER_KEY, curUser);
		}
		return invocation.invoke();
	}
	private Map<String, Object> getSession(ActionInvocation invocation){
		return invocation.getInvocationContext().getSession();
	}
	public void destroy() {	}
	public void init() {}
	public void setUserPrivilegeBiz(TSUserPrivilegeBiz userPrivilegeBiz) {
		this.userPrivilegeBiz = userPrivilegeBiz;
	}
}

package com.nbcedu.function.cardmanage.interceptor;

import java.util.Map;

import org.apache.commons.lang.xwork.StringUtils;

import com.nbcedu.function.cardmanage.biz.CMUserPrivilegeBiz;
import com.nbcedu.function.cardmanage.constants.Constants;
import com.nbcedu.function.cardmanage.model.CMUserPrivilege;
import com.nbcedu.function.cardmanage.util.UcService;
import com.nbcedu.function.cardmanage.vo.CMUser;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

@SuppressWarnings("serial")
public class RoleInterceptor implements Interceptor {

	private CMUserPrivilegeBiz cmPriBiz;
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		if(getSession(invocation).get(Constants.SESSION_USER_KEY) == null){
			String uid = (String) getSession(invocation).get(Constants.SESSION_UID_KEY);
			String userName = "";
			boolean isAdmin = false;
			if(uid=="1"||uid.equals("1")){
//				CMUserPrivilege pri = new CMUserPrivilege();
				isAdmin = true;
				userName = "admin";
			}else{
				userName = UcService.findUserNameByUid(uid);
			CMUserPrivilege pri = this.cmPriBiz.findByUid(uid);
			 isAdmin = pri!=null&&StringUtils.isNotBlank(pri.getId());
			}
			CMUser curUser = new CMUser(userName, uid, isAdmin);
			getSession(invocation).put(Constants.SESSION_USER_KEY, curUser);
		}
		return invocation.invoke();
	}
	private Map<String, Object> getSession(ActionInvocation invocation){
		return invocation.getInvocationContext().getSession();
	}
	public void destroy() {	}
	public void init() {}
	public CMUserPrivilegeBiz getCmPriBiz() {
		return cmPriBiz;
	}
	public void setCmPriBiz(CMUserPrivilegeBiz cmPriBiz) {
		this.cmPriBiz = cmPriBiz;
	}
	
}

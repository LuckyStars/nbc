package com.nbcedu.function.schoolmaster2.utils;

import com.nbcedu.function.schoolmaster2.constants.Constants;
import com.opensymphony.xwork2.ActionContext;

public class Utils {
	/***获取当前登录用户的UID**/
	public static String curUserUid(){
		return ActionContext.getContext().getSession().get(Constants.SESSION_UID_KEY).toString();
	}
}

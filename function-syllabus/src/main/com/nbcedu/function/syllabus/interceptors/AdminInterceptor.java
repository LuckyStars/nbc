package com.nbcedu.function.syllabus.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.nbcedu.function.syllabus.utils.Constants;
import com.nbcedu.function.syllabus.vo.SLBCurUser;

public class AdminInterceptor implements HandlerInterceptor{
	
	
	/**
	 * vali admin 
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object Object) throws Exception {
		if(((SLBCurUser)request.getSession().getAttribute(Constants.CUR_USER_SESSIONID)).isAdmin()){
			return true;
		}else{
			response.sendRedirect("/403.jsp");
			return false;
		}
	}
	
	
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		
	}

	
}

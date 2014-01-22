package com.xuechong.bootstraptests.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


public class LoginInterceptor implements HandlerInterceptor {

	private static final Logger logger = Logger.getLogger(LoginInterceptor.class);
	public void afterCompletion(HttpServletRequest reqeust,
			HttpServletResponse response, Object object, Exception exception)
			throws Exception {
		System.out.println("afterCompletion executed");
	}

	public void postHandle(HttpServletRequest reqeust, HttpServletResponse response,
			Object object, ModelAndView modelAndView) throws Exception {
		System.out.println("postHandle executed");
	}

	public boolean preHandle(HttpServletRequest reqeust, HttpServletResponse response,
			Object object) throws Exception {
		System.out.println("preHandle executed");
		return true;
	}

}

package com.nbcedu.function.syllabus.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.nbcedu.core.framework.filter.ServiceInfoLoader;
import com.nbcedu.integration.uc.client.facade.BaseClient;
import com.nbcedu.integration.uc.client.vo.NbcUcPerson;

public class SyllabusFilter implements Filter{
		 
	private static Logger logger = Logger.getLogger(SyllabusFilter.class);
	private static final String FUNCTION_NAME = "syllabus";
	 //ucclient调用函数中身份type，1--教师，2--学生，3--家长
	  private static final int TYPE_1 = 1;
	  private static final int TYPE_2 = 2;
	  private static final int TYPE_3 = 3;
	@SuppressWarnings("unchecked")
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String serviceName = getServiceName(req);
	
		Map paramMap = new HashMap();
		
		String uid = (String) req.getSession().getAttribute("edu.yale.its.tp.cas.client.filter.user");
		if ((uid == null) || ("".equals(uid))) {
			AttributePrincipal principal = (AttributePrincipal)((HttpServletRequest)request).getUserPrincipal();
			String loginName = principal.getName();
			uid = getUidByUsername(loginName);
		}
		if ((uid == null) || ("".equals(uid))) {
			logger.info("===单点登录中获取的UID为null，无法载入loader...");
			resp.sendRedirect(req.getContextPath() + "/teacherIndex.jsp");
			return;
		}
		paramMap.put("uid", uid);
		Object obj = getLoader(serviceName, req).load(paramMap);
		req.getSession().setAttribute(getAttributeName(serviceName), obj);

		resp.setHeader("P3P","CP=CAO PSA OUR IDC DSP COR ADM DEVi TAIi PSD IVAi IVDi CONi HIS IND CNT");
		chain.doFilter(req, response);
	}
	
	private String getServiceName(HttpServletRequest request) {
	    return FUNCTION_NAME;
	}
	
	private String getAttributeName(String serviceName) {
		return serviceName + "_init";
	}
	
	private String getLoaderName(String serviceName) {
		return serviceName + "Loader";
	}
	
	private ServiceInfoLoader getLoader(String serviceName, HttpServletRequest request) {
		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
		return (ServiceInfoLoader)ac.getBean(getLoaderName(serviceName));
	}
	/**
	 * 根据传进来的用户名得到uid
	 * @param username
	 * @return
	 */
	private String getUidByUsername(String username){
		if(username == null || "".equals(username)){
			return null;
		}
//		BaseClient bc = new BaseClient();
		BaseClient bc=new BaseClient();
		String uid=bc.queryUidByLoginName(username);
		List<NbcUcPerson> ucp = new ArrayList<NbcUcPerson>();
		//如果是老师
		ucp = bc.queryPersonByName(TYPE_1, username);
		if(ucp.size()>0){
			uid = ucp.get(0).getUid();
			return uid;
		}
		//如果是学生
		ucp = bc.queryPersonByName(TYPE_2, username);
		if(ucp.size()>0){
			uid = ucp.get(0).getUid();
			return uid;
		}
		//如果是家长
		ucp = bc.queryPersonByName(TYPE_3, username);
		if(ucp.size()>0){
			uid = ucp.get(0).getUid();
			return uid;
		}
		return uid;
	}
	public void init(FilterConfig filterConfig) throws ServletException {}
	public void destroy() {}
}


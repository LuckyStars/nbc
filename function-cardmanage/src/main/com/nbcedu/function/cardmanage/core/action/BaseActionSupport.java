package com.nbcedu.function.cardmanage.core.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.nbcedu.function.cardmanage.core.util.PagerModel;
import com.opensymphony.xwork2.ActionSupport;

public abstract class BaseActionSupport extends ActionSupport implements ServletRequestAware, ServletResponseAware {
	private static final long serialVersionUID = 1L;
	protected final Logger logger = Logger.getLogger(this.getClass());
	
	protected HttpServletResponse response;
	protected HttpServletRequest request;
	protected PagerModel page = new PagerModel(); //分页
	
	//返回值
	public static final String RELOAD = "reload";
	public static final String LIST = "list";
	
	
	protected String id; //公用，便于大家接受参数id的值
	
	
	
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	public PagerModel getPage() {
		return page;
	}

	public void setPage(PagerModel page) {
		this.page = page;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}

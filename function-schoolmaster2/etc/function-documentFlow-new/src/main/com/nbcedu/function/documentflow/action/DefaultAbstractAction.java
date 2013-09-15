package com.nbcedu.function.documentflow.action;

import java.io.IOException;
import java.io.PrintWriter;

import org.apache.struts2.ServletActionContext;

import com.nbcedu.core.privilege.model.User;
import com.opensymphony.xwork2.ActionContext;

public abstract class DefaultAbstractAction {
	
	protected static final int DEFAULT_PAGE_SIZE = 10;
	
	
	protected String getCurUserPId (){
		return this.getCurUser().getPid();
	}
	
	protected User getCurUser(){
		
		return (User) ActionContext.getContext().getSession().get("documentFlow_init");
	}
	
	protected String getRequestParameter(String key){
		return ServletActionContext.getRequest().getParameter(key);
	}
	
	protected void writeString(String content){
		PrintWriter out = null;
		try {
			ServletActionContext.getResponse().setCharacterEncoding("utf-8");
			out = ServletActionContext.getResponse().getWriter();
			out.write(content);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{if(out!=null){out.close();}}
	}
}

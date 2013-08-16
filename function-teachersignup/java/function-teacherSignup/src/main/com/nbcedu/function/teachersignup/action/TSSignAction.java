package com.nbcedu.function.teachersignup.action;

import com.nbcedu.function.teachersignup.biz.TSSignBiz;
import com.nbcedu.function.teachersignup.core.action.BaseAction;

public class TSSignAction extends BaseAction{

	private String[] subjectIds;
	private TSSignBiz signBiz;
	
	public String addSign(){
		
		this.signBiz.addNewSign(this.getCurUser(),this.subjectIds);
		return "";
	}

	
	
	//////////////////////////
	///getters&setters//////
	/////////////////////
	public void setSignBiz(TSSignBiz signBiz) {
		this.signBiz = signBiz;
	}
	public String[] getSubjectIds() {
		return subjectIds;
	}
	public void setSubjectIds(String[] subjectIds) {
		this.subjectIds = subjectIds;
	}
	
	
	
}

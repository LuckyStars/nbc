package com.nbcedu.function.teachersignup.action;


import java.util.HashSet;
import java.util.Set;

import com.nbcedu.function.teachersignup.biz.TSSignBiz;
import com.nbcedu.function.teachersignup.core.action.BaseAction;
import com.nbcedu.function.teachersignup.model.TSReward;
import com.nbcedu.function.teachersignup.model.TSSubject;

@SuppressWarnings("serial")
public class TSSignAction extends BaseAction{

	private TSSignBiz signBiz;
	
	private String[] subjectIds;
	private String actId;
	private String userName;
	private String subId;
	private String rewId;
	private Set<TSReward> rewSet = new HashSet<TSReward>();
	private Set<TSSubject> subSet = new HashSet<TSSubject>();
	
	public String addSign(){
		
		this.signBiz.addNewSign(this.getCurUser(),this.subjectIds);
		return "";
	}

	
	public String adminList(){
		this.pm = this.signBiz.pageByAct(actId, subId, rewId, userName);
		return "adminList";
	}
	
	public void adminExl(){
		
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
	public String getActId() {
		return actId;
	}
	public void setActId(String actId) {
		this.actId = actId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getSubId() {
		return subId;
	}
	public void setSubId(String subId) {
		this.subId = subId;
	}
	public String getRewId() {
		return rewId;
	}
	public void setRewId(String rewId) {
		this.rewId = rewId;
	}
	
}

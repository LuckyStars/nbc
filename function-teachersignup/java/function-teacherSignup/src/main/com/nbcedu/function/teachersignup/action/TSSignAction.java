package com.nbcedu.function.teachersignup.action;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.nbcedu.function.teachersignup.biz.TSActivityBiz;
import com.nbcedu.function.teachersignup.biz.TSSignBiz;
import com.nbcedu.function.teachersignup.biz.TSSubjectBiz;
import com.nbcedu.function.teachersignup.core.action.BaseAction;
import com.nbcedu.function.teachersignup.model.TSActivity;
import com.nbcedu.function.teachersignup.model.TSReward;
import com.nbcedu.function.teachersignup.model.TSSign;
import com.nbcedu.function.teachersignup.model.TSSubject;
import com.nbcedu.function.teachersignup.vo.TSActivitiSignVo;

@SuppressWarnings("serial")
public class TSSignAction extends BaseAction{

	private TSSignBiz signBiz;
	private TSActivityBiz actBiz;
	private TSSubjectBiz subBiz;
	
	private String[] subjectIds;
	private String actId;
	private String userName;
	private String subId;
	private String rewId;
	private TSActivitiSignVo asvo;
	
	private Set<TSReward> rewSet = new HashSet<TSReward>();
	private Set<TSSubject> subSet = new HashSet<TSSubject>();
	
	public String addSign(){
		this.signBiz.addNewSign(this.getCurUser(),new String[]{this.subId});
		return "reloadCom";
	}

	public String cancel(){
		this.signBiz.removeById(this.id);
		return "reloadCom";
	}
	public String adminList(){
		this.pm = this.signBiz.pageByAct(actId, subId, rewId, userName);
		return "adminList";
	}
	
	public void adminExl(){
		
	}
	
	public String subList(){
		TSActivity act = this.actBiz.findById(this.actId);
		List<TSSign> signedList = 
			this.signBiz.findAllByUidActId(getCurUserUid(), actId);
		this.asvo = TSActivitiSignVo.Factory.build(act, signedList);
		return "comSignList";
	}
	
	//////////////////////////
	///getters&setters////////
	//////////////////////////
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
	public Set<TSReward> getRewSet() {
		return rewSet;
	}
	public void setRewSet(Set<TSReward> rewSet) {
		this.rewSet = rewSet;
	}
	public Set<TSSubject> getSubSet() {
		return subSet;
	}
	public void setSubSet(Set<TSSubject> subSet) {
		this.subSet = subSet;
	}
	public void setActBiz(TSActivityBiz actBiz) {
		this.actBiz = actBiz;
	}
	public void setSubBiz(TSSubjectBiz subBiz) {
		this.subBiz = subBiz;
	}
	public TSActivitiSignVo getAsvo() {
		return asvo;
	}
	public void setAsvo(TSActivitiSignVo asvo) {
		this.asvo = asvo;
	}
	
}

package com.nbcedu.function.teachersignup.action;

import java.io.File;

import com.nbcedu.function.teachersignup.biz.TSActivityBiz;
import com.nbcedu.function.teachersignup.core.action.BaseAction;
import com.nbcedu.function.teachersignup.model.TSActivity;
/**
 * 报名事件ACTION
 * @author xuechong
 */
@SuppressWarnings("serial")
public class TSActivityAction extends BaseAction{

	private TSActivityBiz actBiz;
	
	private File atta;
	private String[] subjectName;
	private String[] rewardName;
	private TSActivity act; 
	                             
	public String add(){
		this.actBiz.addOrUpdate(act, subjectName, rewardName);
		return RELOAD;
	}
	
	
	////////////////////////
	////getters&setters/////
	////////////////////////
	public void setActBiz(TSActivityBiz actBiz) {
		this.actBiz = actBiz;
	}
	public String[] getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String[] subjectName) {
		this.subjectName = subjectName;
	}
	public String[] getRewardName() {
		return rewardName;
	}
	public void setRewardName(String[] rewardName) {
		this.rewardName = rewardName;
	}
	public TSActivity getAct() {
		return act;
	}
	public void setAct(TSActivity act) {
		this.act = act;
	}
	public File getAtta() {
		return atta;
	}
	public void setAtta(File atta) {
		this.atta = atta;
	}
	
}

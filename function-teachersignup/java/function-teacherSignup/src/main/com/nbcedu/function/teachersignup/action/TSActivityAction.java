package com.nbcedu.function.teachersignup.action;

import java.io.File;

import org.apache.commons.lang.xwork.StringUtils;

import com.nbcedu.function.teachersignup.biz.TSActivityBiz;
import com.nbcedu.function.teachersignup.core.action.BaseAction;
import com.nbcedu.function.teachersignup.model.TSActivity;
/**
 * 报名事件ACTIONaaaaaa
 * @author xuechong
 */
@SuppressWarnings("serial")
public class TSActivityAction extends BaseAction{
	private TSActivityBiz actBiz;
	
	private File atta;
	private String subjectName;
	private String rewardName;
	private TSActivity act; 
	                             
	public String add(){
		
		
		String[] sub = StringUtils.isNotBlank(subjectName)?subjectName.split(","):null;
		String[] rew = StringUtils.isNotBlank(rewardName)?rewardName.split(","):null;
		this.actBiz.addOrUpdate(act, sub, rew);
		return RELOAD;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public String list(){
		this.actBiz.findAll();
		return LIST;
	}
	private String savePath(){
		
		
		return "";
	}
	
	////////////////////////
	////getters&setters/////
	////////////////////////
	public void setActBiz(TSActivityBiz actBiz) {
		this.actBiz = actBiz;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getRewardName() {
		return rewardName;
	}
	public void setRewardName(String rewardName) {
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

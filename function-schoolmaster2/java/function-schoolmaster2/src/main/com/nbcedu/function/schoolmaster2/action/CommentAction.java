package com.nbcedu.function.schoolmaster2.action;

import java.util.Date;

import com.nbcedu.function.schoolmaster2.biz.SM2CommentBiz;
import com.nbcedu.function.schoolmaster2.core.action.BaseAction;
import com.nbcedu.function.schoolmaster2.data.model.TSm2Comment;
import com.nbcedu.function.schoolmaster2.utils.Utils;

@SuppressWarnings("serial")
public class CommentAction extends BaseAction{

	private SM2CommentBiz comBiz;
	
	private TSm2Comment comm = new TSm2Comment();
	private String stepId;
	
	public String add(){
		this.comm.setCreaterId(this.getUserId());
		Date date = new Date();
		this.comm.setLastUpdateTime(date);
		this.comm.setCreatetime(date);
		this.comm.setUserName(Utils.curUserName());
		this.comBiz.add(comm);
		return "refreshStep";
	}
	
	
	//////////////////////////
	/////getters&setters////
	///////////////////////////
	public void setComBiz(SM2CommentBiz comBiz) {
		this.comBiz = comBiz;
	}
	public TSm2Comment getComm() {
		return comm;
	}
	public void setComm(TSm2Comment comm) {
		this.comm = comm;
	}
	public String getStepId() {
		return stepId;
	}
	public void setStepId(String stepId) {
		this.stepId = stepId;
	}
}

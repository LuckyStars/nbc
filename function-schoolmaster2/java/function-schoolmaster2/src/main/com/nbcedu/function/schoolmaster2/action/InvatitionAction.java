package com.nbcedu.function.schoolmaster2.action;


import com.nbcedu.function.schoolmaster2.biz.SM2SubjectBiz;
import com.nbcedu.function.schoolmaster2.core.action.BaseAction;
import com.nbcedu.function.schoolmaster2.data.model.TSm2Subject;

@SuppressWarnings("serial")
public class InvatitionAction extends BaseAction{

	private static final String INV_MODULE_ID = null;

	private TSm2Subject subject;

	private SM2SubjectBiz subBiz;
	
	public void add(){
		
	}
	
	public String teacherList(){
		this.pm =  this.subBiz.findByCreaterId(getUserId(), INV_MODULE_ID);
		return "teacherList";
	}
	
	/////////////////////////
	///getter setter//////
	/////////////////////
	public TSm2Subject getSubject() {
		return subject;
	}
	public void setSubject(TSm2Subject subject) {
		this.subject = subject;
	}
	public void setSubBiz(SM2SubjectBiz subBiz) {
		this.subBiz = subBiz;
	}
	
}

package com.nbcedu.function.schoolmaster2.action;


import com.nbcedu.function.schoolmaster2.biz.SM2SubjectBiz;
import com.nbcedu.function.schoolmaster2.core.action.BaseAction;
import com.nbcedu.function.schoolmaster2.data.model.TSm2Subject;
import com.nbcedu.function.schoolmaster2.vo.SubjectVo;

@SuppressWarnings("serial")
public class InvatitionAction extends BaseAction{

	private static final String INV_MODULE_ID = null;

	private TSm2Subject subject;
	private SubjectVo subjectVo = new SubjectVo();

	private SM2SubjectBiz subBiz;
	
	public void add(){
		
	}
	
	public String teacherList(){
		subjectVo.setCreaterId(getUserId());
		this.pm =  this.subBiz.findByCreaterId(subjectVo);
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

	public SubjectVo getSubjectVo() {
		return subjectVo;
	}

	public void setSubjectVo(SubjectVo subjectVo) {
		this.subjectVo = subjectVo;
	}
	
}

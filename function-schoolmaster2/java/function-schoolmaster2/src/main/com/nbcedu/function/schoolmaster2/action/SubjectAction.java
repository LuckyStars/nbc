package com.nbcedu.function.schoolmaster2.action;


import com.nbcedu.function.schoolmaster2.biz.SM2SubjectBiz;
import com.nbcedu.function.schoolmaster2.core.action.BaseAction;
import com.nbcedu.function.schoolmaster2.data.model.TSm2Subject;

@SuppressWarnings("serial")
public class SubjectAction extends BaseAction{
	
	private String moduleId;
	
	private TSm2Subject subject = new TSm2Subject(); 
	
	private SM2SubjectBiz sm2SubjectBiz;

	public String add(){
		return "refreshTeacherList";
	}
	
	public String find(){
//		判断角色 如果是主管则查询所有自己的，否则只查看主管指定执行者可看
		if(1==1){
			this.sm2SubjectBiz.findByCreaterId(this.getUserId(),moduleId);
		}else{
			this.sm2SubjectBiz.findByExceuteUserId(this.getUserId(),moduleId);
		}
		
		return "list";
	}
	
	public String remove(){
		return "refreshTeacherList";
	}
	
	public String modify(){
		return "refreshTeacherList";
	}
	
	/////////////////////////
	/////getters&setters/////
	/////////////////////////

	public TSm2Subject getSubject() {
		return subject;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public void setSubject(TSm2Subject subject) {
		this.subject = subject;
	}

	public void setSm2SubjectBiz(SM2SubjectBiz sm2SubjectBiz) {
		this.sm2SubjectBiz = sm2SubjectBiz;
	}
	
}
 
package com.nbcedu.function.schoolmaster2.action;

import java.util.Date;

import com.nbcedu.function.schoolmaster2.core.action.BaseAction;
import com.nbcedu.function.schoolmaster2.data.model.TSm2Subject;

@SuppressWarnings("serial")
public class SubjectAction extends BaseAction{
	
	private Date start;
	private Date end;
	
	private TSm2Subject subject = new TSm2Subject(); 
	

	public String add(){
		return "refreshTeacherList";
	}
	
	public String listByType(){
		return "listStatistic";
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
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
}
 
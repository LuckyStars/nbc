package com.nbcedu.function.schoolmaster2.invitation.action;

import com.nbcedu.function.schoolmaster2.core.action.BaseAction;

@SuppressWarnings("serial")
public class InvitationAction extends BaseAction{
	
	
	public String add(){
		
		return "refreshTeacher";
	}
	
	public String teacherList(){
		
		return "teacherList";
	}
}

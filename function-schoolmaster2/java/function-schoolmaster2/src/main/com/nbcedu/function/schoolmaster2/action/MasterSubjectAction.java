package com.nbcedu.function.schoolmaster2.action;

import com.nbcedu.function.schoolmaster2.biz.SM2MasterSubBiz;
import com.nbcedu.function.schoolmaster2.core.action.BaseAction;

/**
 * 校长
 * @author xuechong
 */
public class MasterSubjectAction extends BaseAction{
	
	private String moduleId;
	
	private SM2MasterSubBiz subBiz;
	
	public String list(){
		
		
		return "";
	}


	
	
	///////////////////////
	////getters&setters////
	///////////////////////
	
	public String getModuleId() {
		return moduleId;
	}
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
}

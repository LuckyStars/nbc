package com.nbcedu.function.schoolmaster2.action;

import org.apache.commons.lang.xwork.StringUtils;

import com.nbcedu.function.schoolmaster2.biz.SM2MasterSubBiz;
import com.nbcedu.function.schoolmaster2.core.action.BaseAction;

/**
 * 校长
 * @author xuechong
 */
@SuppressWarnings("serial")
public class MasterSubjectAction extends BaseAction{
	
	private String moduleId;
	
	private SM2MasterSubBiz subBiz;
	
	public String list(){
		if(StringUtils.isNotBlank(moduleId)){
			this.pm = this.subBiz.findByMaster(moduleId, this.getUserId());
			return moduleId + "List";
		}else{
			return "404";
		}
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
	public void setSubBiz(SM2MasterSubBiz subBiz) {
		this.subBiz = subBiz;
	}
	
}

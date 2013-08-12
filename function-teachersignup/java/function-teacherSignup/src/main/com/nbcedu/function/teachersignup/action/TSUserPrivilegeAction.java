package com.nbcedu.function.teachersignup.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.xwork.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;

import com.nbcedu.function.teachersignup.biz.TSUserPrivilegeBiz;
import com.nbcedu.function.teachersignup.core.action.BaseAction;
import com.nbcedu.function.teachersignup.core.util.struts2.Struts2Utils;
import com.nbcedu.function.teachersignup.model.TSUserPrivilege;
import com.nbcedu.function.teachersignup.util.UcService;

@SuppressWarnings("serial")
public class TSUserPrivilegeAction extends BaseAction{
	private static final Logger logger = Logger.getLogger(TSUserPrivilegeAction.class);
	
	private TSUserPrivilegeBiz priBiz;

	private String userUids;
	
	public String add(){
		if(StringUtils.isNotBlank(this.userUids)){
			this.priBiz.addByUid(Arrays.asList(userUids.split(",")));
		}
		return RELOAD;
	}
	
	public String remove(){
		this.priBiz.removeById(this.id);
		return RELOAD;
	}
	
	public String list(){
		this.pm.setDatas(this.priBiz.findAll());
		return LIST;
	}
	
	public void tree(){
		List<TSUserPrivilege> priList = this.priBiz.findAll();
		String result = null;
		if (!CollectionUtils.isEmpty(priList)) {
			Collection<String> checkUids = new ArrayList<String>(priList.size());
			for (TSUserPrivilege pri : priList) {
				checkUids.add(pri.getUserUid());
			}
			result = UcService.getPersonJson(checkUids);
		}else{
			result = UcService.getPersonJsonString();
		}
		Struts2Utils.renderJson(result);
	}
	///////////////////////////
	////////getters&setters////
	///////////////////////////
	public void setPriBiz(TSUserPrivilegeBiz priBiz) {
		this.priBiz = priBiz;
	}
	public String getUserUids() {
		return userUids;
	}
	public void setUserUids(String userUids) {
		this.userUids = userUids;
	}
	
}

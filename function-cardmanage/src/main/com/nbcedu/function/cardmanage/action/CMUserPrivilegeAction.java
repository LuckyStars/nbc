package com.nbcedu.function.cardmanage.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.xwork.StringUtils;
import org.springframework.util.CollectionUtils;

import com.nbcedu.function.cardmanage.biz.CMUserPrivilegeBiz;
import com.nbcedu.function.cardmanage.core.action.BaseAction;
import com.nbcedu.function.cardmanage.core.util.struts2.Struts2Utils;
import com.nbcedu.function.cardmanage.model.CMUserPrivilege;
import com.nbcedu.function.cardmanage.util.UcService;

@SuppressWarnings("serial")
public class CMUserPrivilegeAction extends BaseAction{
	
	private CMUserPrivilegeBiz priBiz;

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
		List<CMUserPrivilege> priList = this.priBiz.findAll();
		String result = null;
		if (!CollectionUtils.isEmpty(priList)) {
			Collection<String> checkUids = new ArrayList<String>(priList.size());
			for (CMUserPrivilege pri : priList) {
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
	public void setPriBiz(CMUserPrivilegeBiz priBiz) {
		this.priBiz = priBiz;
	}
	public String getUserUids() {
		return userUids;
	}
	public void setUserUids(String userUids) {
		this.userUids = userUids;
	}
	
}

package com.nbcedu.function.schoolmaster2.action;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.nbcedu.function.schoolmaster2.core.action.BaseAction;
import com.nbcedu.function.schoolmaster2.core.util.StringUtil;
import com.nbcedu.function.schoolmaster2.data.model.TSm2Module;


public class ModuleAction extends BaseAction{
	
	private static final long serialVersionUID = 1L;
	private TSm2Module module = new TSm2Module();
	
	private ModuleBiz moduleBiz;
	
	private String id;
	private String name;
	
	public String findAll(){
		this.pager = this.getModuleBiz().getAll(module);
		return "moduleList";
	}
	public String findById() throws IOException{
		if(StringUtil.isEmpty(id)){
			module = this.getModuleBiz().getModule(id);
		}
		return "moduleAdd";
	}
	
	public void delete(){
		this.getModuleBiz().deleteById(id);
	}
	public void add(){
		module.setCreateTime(new Date());
		module.setCreateUserId(this.getCurUserUid());
		String usersId = this.getRequest().getParameter(
		"executeUsersId");
		Set<ModuleUser> s = new HashSet<ModuleUser>();
		for(String uid : usersId.split(",")){
			ModuleUser mu = new  ModuleUser();
			mu.setModule(module.getParentId());
			mu.setUserID(uid);
			s.add(mu);
		}
		module.setUsers(s);
		this.getModuleBiz().addOrUpdateModule(module);
		try {
			AjaxHelper.writeSuccessJSON(this.getResponse());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void isExist() throws IOException{
		List<Module> list = this.moduleBiz.getByName(name);
		if(list.size()>0){
			AjaxHelper.writeFailurJSON(this.getResponse(), "");
		}else{
			AjaxHelper.writeSuccessJSON(this.getResponse());
		}
	}
	/////////////////////
	/////private////////
	////////////////////
	private String getCurUserUid(){
		return  (String)ActionContext.getContext().getSession().get("sns_init");
	}
	
	//////////////////////////
	/////getters&setters//////
	//////////////////////////
	public ModuleBiz getModuleBiz() {
		return moduleBiz;
	}
	public void setModuleBiz(ModuleBiz moduleBiz) {
		this.moduleBiz = moduleBiz;
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public Module getModule() {
		return module;
	}
	public void setModule(Module module) {
		this.module = module;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

}

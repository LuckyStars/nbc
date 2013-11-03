package com.nbcedu.function.schoolmaster2.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.nbcedu.function.schoolmaster2.biz.SM2ModuleBiz;
import com.nbcedu.function.schoolmaster2.biz.SM2SubjectBiz;
import com.nbcedu.function.schoolmaster2.biz.Sm2TypeBiz;
import com.nbcedu.function.schoolmaster2.core.action.BaseAction;
import com.nbcedu.function.schoolmaster2.core.util.Struts2Util;
import com.nbcedu.function.schoolmaster2.core.util.strings.StringUtil;
import com.nbcedu.function.schoolmaster2.data.model.SM2SubjectMaster;
import com.nbcedu.function.schoolmaster2.data.model.TSm2Module;
import com.nbcedu.function.schoolmaster2.data.model.TSm2Subject;
import com.nbcedu.function.schoolmaster2.data.model.TSm2SubjectUser;
import com.nbcedu.function.schoolmaster2.data.model.TSm2Type;
import com.nbcedu.function.schoolmaster2.utils.UCService;
import com.nbcedu.function.schoolmaster2.utils.Utils;
import com.nbcedu.function.schoolmaster2.vo.SubjectVo;

@SuppressWarnings("serial")
public class SubjectAction extends BaseAction{
	
	private TSm2Subject subject = new TSm2Subject(); 
	private SubjectVo subjectVo = new SubjectVo();
	private TSm2Module module = new TSm2Module();
	
	private String moduleId;
	
	private SM2SubjectBiz sm2SubjectBiz;
	private Sm2TypeBiz sm2TypeBiz;
	private SM2ModuleBiz moduleBiz;
	
	public String toAdd(){
		List<TSm2Type> types = this.sm2TypeBiz.findByUserId(this.getUserId());
		List<TSm2Subject> subjects = new ArrayList<TSm2Subject>();
		if(moduleId.equals("lssx")|| moduleId.equals("ndzx")){
			subjects = this.sm2SubjectBiz.findBYModuleId(subjectVo.getModuleId());
		}
		this.getRequest().setAttribute("types", types);
		this.getRequest().setAttribute("subjects", subjects);
		return "subjectAdd";
	}
	
	public String toUpdate(){
		List<TSm2Type> types = this.sm2TypeBiz.findByUserId(this.getUserId());
		List<TSm2Subject> subjects = new ArrayList<TSm2Subject>();
		if("lssx".equals(moduleId)|| "ndzx".equals(moduleId)){
			subjects = this.sm2SubjectBiz.findBYModuleId(moduleId);
		}
		subject = this.sm2SubjectBiz.findById(id);
		this.getRequest().setAttribute("types", types);
		this.getRequest().setAttribute("subjects", subjects);
		return "subjectUpdate";
	}
	public void add(){
		subject.setCreateTime(new Date());
		
		String usersId = this.getRequest().getParameter("executeUsersId");
		Set<TSm2SubjectUser> users = new HashSet<TSm2SubjectUser>();
		for(String u : usersId.split(",")){
			TSm2SubjectUser user =  new TSm2SubjectUser();
			user.setUserId(u);
			user.setUserName(UCService.findNameByUid(u));
			users.add(user);
		}
		subject.setExcuteUsers(users);
		String checkusersId = this.getRequest().getParameter("checkUsers");
		if(!StringUtil.isEmpty(checkusersId)){
			Set<SM2SubjectMaster> checkUsers = new HashSet<SM2SubjectMaster>();
			for(String u : checkusersId.split(",")){
				SM2SubjectMaster user =  new SM2SubjectMaster();
				user.setUserUid(u);
				checkUsers.add(user);
			}
			subject.setCheckUsers(checkUsers);
		}
		subject.setCreaterId(this.getUserId());
		subject.setCreaterName(UCService.findNameByUid(this.getUserId()));
		this.sm2SubjectBiz.add(subject);
		Struts2Util.renderText("0", "encoding:UTF-8");
	}
	
	public void update(){
		String usersId = this.getRequest().getParameter("executeUsersId");
		Set<TSm2SubjectUser> users = new HashSet<TSm2SubjectUser>();
		for(String u : usersId.split(",")){
			TSm2SubjectUser user =  new TSm2SubjectUser();
			user.setUserId(u);
			user.setUserName(UCService.findNameByUid(u));
			users.add(user);
		}
		String checkusersId = this.getRequest().getParameter("checkUsers");
		Set<SM2SubjectMaster> checkUsers = new HashSet<SM2SubjectMaster>();
		for(String u : checkusersId.split(",")){
			SM2SubjectMaster user =  new SM2SubjectMaster();
			user.setUserUid(u);
			checkUsers.add(user);
		}
		subject.setCheckUsers(checkUsers);
		this.sm2SubjectBiz.update(subject);
		Struts2Util.renderJson("{'result':0}", "encoding:UTF-8");
	}
	public String find(){
//		判断角色 如果是主管则查询所有自己的，否则只查看主管指定执行者可看
		if(Utils.isManager()){
			subjectVo.setCreaterId(this.getUserId());
			pm = this.sm2SubjectBiz.findByCreaterId(subjectVo);
		}else{
			subjectVo.setExcuteUserId(this.getUserId());
			pm = this.sm2SubjectBiz.findByExceuteUserId(subjectVo);
		}
		return "list";
	}
	
	public void delete(){
		this.sm2SubjectBiz.removeById(id);
		Struts2Util.renderJson("{'result':0}", "encoding:UTF-8");
	}
	/**
	 * 判断重名
	 * 
	 * @return
	 * @throws IOException
	 */
	public String isExist() throws IOException {
//		String subjectTile = this.getRequest().getParameter("subjectTitle");
//		if (this.sm2SubjectBiz.f) {
//			AjaxHelper.writeFailurJSON(getResponse(), "主题重名");
//		} else {
//			AjaxHelper.writeSuccessJSON(getResponse());
//		}
		return null;
	}
	/////////////////////////
	/////getters&setters/////
	/////////////////////////

	public TSm2Subject getSubject() {
		return subject;
	}

	public void setSubject(TSm2Subject subject) {
		this.subject = subject;
	}

	public void setSm2SubjectBiz(SM2SubjectBiz sm2SubjectBiz) {
		this.sm2SubjectBiz = sm2SubjectBiz;
	}

	public void setSm2TypeBiz(Sm2TypeBiz sm2TypeBiz) {
		this.sm2TypeBiz = sm2TypeBiz;
	}

	public SubjectVo getSubjectVo() {
		return subjectVo;
	}

	public void setSubjectVo(SubjectVo subjectVo) {
		this.subjectVo = subjectVo;
	}

	public void setModuleBiz(SM2ModuleBiz moduleBiz) {
		this.moduleBiz = moduleBiz;
	}

	public TSm2Module getModule() {
		return module;
	}

	public void setModule(TSm2Module module) {
		this.module = module;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
	
}
 
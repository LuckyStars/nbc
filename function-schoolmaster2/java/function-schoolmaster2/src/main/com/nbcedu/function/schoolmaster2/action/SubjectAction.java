package com.nbcedu.function.schoolmaster2.action;


import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import com.google.gson.JsonParser;
import com.nbcedu.common.json.JSONTool;
import com.nbcedu.function.schoolmaster2.biz.SM2SubjectBiz;
import com.nbcedu.function.schoolmaster2.biz.Sm2TypeBiz;
import com.nbcedu.function.schoolmaster2.core.action.BaseAction;
import com.nbcedu.function.schoolmaster2.core.util.Struts2Util;
import com.nbcedu.function.schoolmaster2.data.model.TSm2Subject;
import com.nbcedu.function.schoolmaster2.data.model.TSm2SubjectUser;
import com.nbcedu.function.schoolmaster2.data.model.TSm2Type;
import com.nbcedu.function.schoolmaster2.utils.UCService;

@SuppressWarnings("serial")
public class SubjectAction extends BaseAction{
	
	private String moduleId;
	
	private TSm2Subject subject = new TSm2Subject(); 
	
	private SM2SubjectBiz sm2SubjectBiz;
	private Sm2TypeBiz sm2TypeBiz;
	
	public String toAdd(){
		List<TSm2Type> types = this.sm2TypeBiz.findByModUseId(moduleId, this.getUserId(),0);
//		if(){
		List<TSm2Subject> subjects = this.sm2SubjectBiz.findBYModuleId(moduleId);
//		}
		this.getRequest().setAttribute("types", types);
		this.getRequest().setAttribute("subjects", subjects);
		return "subjectAdd";
	}
	public void add(){
		subject.setCreateTime(new Date());
		
		String usersId = this.getRequest().getParameter("executeUsersId");
		Set<TSm2SubjectUser> users = new HashSet<TSm2SubjectUser>();
		for(String u : usersId.split(",")){
			TSm2SubjectUser user =  new TSm2SubjectUser();
			user.setUserId(u);
			users.add(user);
		}
		subject.setExcuteUsers(users);
		subject.setCreaterId(this.getUserId());
		subject.setCreaterName(UCService.findNameByUid(this.getUserId()));
		this.sm2SubjectBiz.add(subject);
		Struts2Util.renderJson("{'result':0}", "encoding:UTF-8");
	}
	public String find(){
//		判断角色 如果是主管则查询所有自己的，否则只查看主管指定执行者可看
		if(1==1){
			pm = this.sm2SubjectBiz.findByCreaterId(this.getUserId(),moduleId);
		}else{
			pm = this.sm2SubjectBiz.findByExceuteUserId(this.getUserId(),moduleId);
		}
		return "list";
	}
	public void delete(){
		this.sm2SubjectBiz.removeById(id);
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

	public void setSm2TypeBiz(Sm2TypeBiz sm2TypeBiz) {
		this.sm2TypeBiz = sm2TypeBiz;
	}
	
}
 
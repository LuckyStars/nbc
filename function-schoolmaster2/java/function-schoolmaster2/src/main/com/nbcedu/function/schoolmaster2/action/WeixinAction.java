package com.nbcedu.function.schoolmaster2.action;

import java.util.List;

import com.google.common.reflect.TypeToken;
import com.nbcedu.function.schoolmaster2.biz.SM2MasterSubBiz;
import com.nbcedu.function.schoolmaster2.biz.SM2SubjectBiz;
import com.nbcedu.function.schoolmaster2.core.action.BaseAction;
import com.nbcedu.function.schoolmaster2.core.exception.DBException;
import com.nbcedu.function.schoolmaster2.core.pager.SystemContext;
import com.nbcedu.function.schoolmaster2.core.util.DateUtil;
import com.nbcedu.function.schoolmaster2.core.util.Struts2Util;
import com.nbcedu.function.schoolmaster2.data.model.SM2SubjectMaster;
import com.nbcedu.function.schoolmaster2.data.model.TSm2Subject;
import com.nbcedu.function.schoolmaster2.data.model.TSm2SubjectUser;
import com.nbcedu.function.schoolmaster2.utils.Utils;
import com.nbcedu.function.schoolmaster2.vo.StepVo;
import com.nbcedu.function.schoolmaster2.vo.SubjectVo;
import com.nbcedu.function.schoolmaster2.weixin.biz.Sm2WeixinUserBiz;
import com.nbcedu.function.schoolmaster2.weixin.model.Sm2WeixinUser;

public class WeixinAction extends BaseAction{
	
	private static final long serialVersionUID = 1L;
	private Sm2WeixinUser wxuser = new Sm2WeixinUser();
	private TSm2Subject subject;
	private Sm2WeixinUserBiz wxUserBiz ;
	private String username;
	private String password;
	private SM2MasterSubBiz subBiz;
	private SM2SubjectBiz sm2SubjectBiz;
	private String pageSize;
	
	private static final String LINSHI_MODULEID = "linshishixiang";
	
	public String login(){
		boolean b = this.wxUserBiz.findLoginByPassUserName(username.trim(), password);
		if(b){
			double d  = Math.random();
			wxuser.setCreateTime(DateUtil.getCurrentDate());
			wxuser.setUid(this.getUserId());
			wxuser.setLastLoginTime(DateUtil.getCurrentDate());
			wxuser.setWeixinId(String.valueOf(d));//getWxOpenId());
			this.wxUserBiz.addUpdateWeixinUser(wxuser);
		}
		return "list";
	}
	@SuppressWarnings("unchecked")
	public void findLinshi(){
		boolean b = Utils.isMaster();
		SubjectVo subject = new SubjectVo();
		subject.setModuleId(LINSHI_MODULEID);		
		List<TSm2Subject> list = null;
		int size = Integer.parseInt(pageSize);
		SystemContext.setPagesize(getMax(size));
		SystemContext.setOffset(getMin(size));
		if(b){
			list = subBiz.findByMasterAndCount(LINSHI_MODULEID, getUsername(),getMin(size),getMax(size));
		}else if(Utils.isManager()){
			subject.setCreaterId(getUserId());
			list = this.sm2SubjectBiz.findByCreaterId(subject).getDatas();
		}else{
			subject.setExcuteUserId(this.getUserId());
			list = this.sm2SubjectBiz.findByExceuteUserId(subject).getDatas();
		}
	
		Struts2Util.renderJson(Utils.gson.toJson(list, new TypeToken<List<TSm2Subject>>(){}.getType()),"encoding:UTF-8");
	}
	
	/**
	 * 查看详细
	 * @return
	 */
	public String detail(){
		this.subject = this.subBiz.findById(this.id);
		
		List<StepVo> steps = this.subBiz.findAllSteps(this.id);
		this.getRequestMap().put("steps", steps);
		return "subjectDetail";
	}
	private int getMax(int page){
		return page*10;
	}
	private int getMin(int page){
		return (page-1)*10;
	}
	/*
	 * setter\getter 
	 */
	public Sm2WeixinUser getWxuser() {
		return wxuser;
	}
	public void setWxuser(Sm2WeixinUser wxuser) {
		this.wxuser = wxuser;
	}

	public Sm2WeixinUserBiz getWxUserBiz() {
		return wxUserBiz;
	}

	public void setWxUserBiz(Sm2WeixinUserBiz wxUserBiz) {
		this.wxUserBiz = wxUserBiz;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public SM2SubjectBiz getSm2SubjectBiz() {
		return sm2SubjectBiz;
	}
	public void setSm2SubjectBiz(SM2SubjectBiz sm2SubjectBiz) {
		this.sm2SubjectBiz = sm2SubjectBiz;
	}
	public SM2MasterSubBiz getSubBiz() {
		return subBiz;
	}
	public void setSubBiz(SM2MasterSubBiz subBiz) {
		this.subBiz = subBiz;
	}
	public String getPageSize() {
		return pageSize;
	}
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
	public TSm2Subject getSubject() {
		return subject;
	}
	public void setSubject(TSm2Subject subject) {
		this.subject = subject;
	}

}

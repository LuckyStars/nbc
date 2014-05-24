package com.nbcedu.function.schoolmaster2.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.xwork.StringUtils;
import org.apache.taglibs.standard.tag.common.core.Util;
import org.junit.runner.Request;

import com.google.common.reflect.TypeToken;
import com.nbcedu.function.schoolmaster2.biz.SM2MasterSubBiz;
import com.nbcedu.function.schoolmaster2.biz.SM2SubjectBiz;
import com.nbcedu.function.schoolmaster2.biz.Sm2ProgressBiz;
import com.nbcedu.function.schoolmaster2.core.action.BaseAction;
import com.nbcedu.function.schoolmaster2.core.pager.SystemContext;
import com.nbcedu.function.schoolmaster2.core.util.DateUtil;
import com.nbcedu.function.schoolmaster2.core.util.StringUtil;
import com.nbcedu.function.schoolmaster2.core.util.Struts2Util;
import com.nbcedu.function.schoolmaster2.data.model.TSm2Disscus;
import com.nbcedu.function.schoolmaster2.data.model.TSm2Progress;
import com.nbcedu.function.schoolmaster2.data.model.TSm2Subject;
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
	private String openId;
	private SM2MasterSubBiz subBiz;
	private SM2SubjectBiz sm2SubjectBiz;
	private String pageSize;
	private String stepId;
	private Sm2ProgressBiz progressBiz;
	private  static final String SERVICENAME = "sm2_init";
	
	private static final String LINSHI_MODULEID = "linshishixiang";
	
	public String login(){
		//首先查找uid放入session
		String uid = this.wxUserBiz.findLoginByPassUserName(username.trim(), password);
		String type = this.getRequest().getParameter("type");
		if(!StringUtil.isEmpty(uid)){
			if("list".equals(type)){
				this.getRequest().getSession().setAttribute(SERVICENAME, uid);
				wxuser.setCreateTime(DateUtil.getCurrentDate());
				wxuser.setUid(this.getUserId());
				wxuser.setLastLoginTime(DateUtil.getCurrentDate());
				wxuser.setWeixinId(openId);//getWxOpenId());
				this.wxUserBiz.addUpdateWeixinUser(wxuser);
			}
			if("welcome".equals(type)){
				this.getRequest().setAttribute("username", username);
				this.getRequest().setAttribute("password",password);
				return "welcome";
			}else{
				return "list";
			}
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
		//获取主题
		this.subject = this.subBiz.findById(this.id);
		//根据主题获取所有的进展
		List<StepVo> steps = this.subBiz.findAllSteps(this.id);
		this.getRequestMap().put("steps", steps);
		return "subjectDetail";
	}
	
	public void findProgress(){
		if(StringUtils.isNotBlank(stepId)){
			List<TSm2Progress> progressList = this.subBiz.findProgressByStepId(stepId);
			Struts2Util.renderJson(Utils.gson.toJson(progressList, new TypeToken<List<TSm2Progress>>(){}.getType()),"encoding:UTF-8");
		}
	}
	
	public void findDiss(){
		//根据进展获取进展下所有的步骤
		String proId = this.getRequest().getParameter("progressId");
		if(StringUtils.isNotBlank(proId)){
			int size = Integer.parseInt(pageSize);
			List<TSm2Disscus> disList = this.subBiz.findDisscusByProgressId(proId, getMin(size),getMax(size));
			List<TSm2Disscus> newList = new ArrayList<TSm2Disscus>();
			for(TSm2Disscus value : disList){
				TSm2Disscus disscus = new TSm2Disscus();
				disscus.setId(value.getId());
				disscus.setCreaterId(value.getCreaterId());
				disscus.setCreateTime(value.getCreateTime());
				disscus.setLastUpdateTime(value.getLastUpdateTime());
				disscus.setProgressId(value.getProgressId());
				disscus.setUserName(value.getUserName());
				disscus.setContent(Util.escapeXml(value.getContent()));
				newList.add(disscus);
			}
//			if(StringUtils.isNotBlank(stepId)){
//				TSm2Progress progress = this.progressBiz.findById(proId);
//			}
			Struts2Util.renderJson(Utils.gson.toJson(newList, new TypeToken<List<TSm2Disscus>>(){}.getType()),"encoding:UTF-8");
			this.getRequestMap().put("disList", disList);
		}
	}
	public Sm2ProgressBiz getProgressBiz() {
		return progressBiz;
	}
	public void setProgressBiz(Sm2ProgressBiz progressBiz) {
		this.progressBiz = progressBiz;
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
	public String getStepId() {
		return stepId;
	}
	public void setStepId(String stepId) {
		this.stepId = stepId;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	
}

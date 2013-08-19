package com.nbcedu.function.teachersignup.action;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.xwork.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.nbcedu.function.teachersignup.biz.TSActivityBiz;
import com.nbcedu.function.teachersignup.constants.ActStatus;
import com.nbcedu.function.teachersignup.core.action.BaseAction;
import com.nbcedu.function.teachersignup.model.TSActivity;

/**
 * 报名事件ACTION
 * @author xuechong
 */
@SuppressWarnings("serial")
public class TSActivityAction extends BaseAction{
	
	private TSActivityBiz actBiz;
	
	private File atta;
	private String attaFileName;
	private String subjectName;
	private String rewardName;
	private TSActivity act;
	
	private Integer month;//search month
	private Integer actStatu;//search status
	
	/**
	 * 新增
	 * @return
	 * @throws IOException
	 * @author xuechong
	 */
	public String add() throws IOException{
		if(atta!=null){
			String savePath = this.savePath(atta);
			FileUtils.copyFile(atta, new File(savePath));
			this.act.setFileName(this.attaFileName);
			this.act.setFilePath(savePath);
		}
		
		this.act.setComment(URLDecoder.decode(this.act.getComment(), "utf-8"));
		this.act.setName(URLDecoder.decode(this.act.getName(), "utf-8"));
		String[] sub = StringUtils.isNotBlank(subjectName)?subjectName.split(","):null;
		String[] rew = StringUtils.isNotBlank(rewardName)?rewardName.split(","):null;
		this.act.setStatus(ActStatus.EDITING.getId());
		this.act.setCreateDate(new Date());
		this.actBiz.addOrUpdate(act, sub, rew);
		return RELOAD_ADMIN;
	}
	
	private String savePath(File file){
		StringBuilder result = new StringBuilder(
				ServletActionContext.getServletContext().
				getRealPath("/function/function-teachersignup/upload"));
			
		result.append(File.separator);
		result.append(
				new SimpleDateFormat("yyyyMM").format(new Date())
		);
		
		result.append(new Date().getTime());
		result.append(file.getName());
		return result.toString();
	}
	
	/**
	 * 鹳狸猿列表
	 * @return
	 * @author xuechong
	 */
	public String adminList(){
		this.pm = this.actBiz.findByMonthStatus(month, actStatu);
		return "adminList";
	}
	
	/**
	 * (鹳狸猿)删除
	 * @return
	 * @author xuechong
	 */
	public String remove(){
		this.actBiz.removeById(this.id);
		return this.adminList();
	}
	
	/**
	 * 手动取消
	 * @return
	 * @author xuechong
	 */
	public String manCancel(){
		this.act = this.actBiz.findById(this.id);
		if(act!=null&&act.getOpenDate().after(new Date())){
			this.actBiz.modifyStatus(this.id,ActStatus.EDITING);
		}
		return RELOAD_ADMIN;
	}
	
	/**
	 * 发布
	 * @return
	 * @author xuechong
	 */
	public String pub(){
		this.act = this.actBiz.findById(this.id);
		if(act!=null){
			this.actBiz.modifyStatus(this.id, ActStatus.PUBLISHED);
		}
		return RELOAD_ADMIN;
	}
	
	/**
	 * 暂停发布
	 * @return
	 * @author xuechong
	 */
	public String pause(){
		this.actBiz.modifyStatus(this.id, ActStatus.PAUSED);
		return RELOAD_ADMIN;
	}
	/**
	 * 普通列表
	 * @return
	 * @author xuechong
	 */
	public String commonList(){
		this.pm = this.actBiz.findByMonthStatus(null, ActStatus.PUBLISHED.getId());
		return "commonList";
	}
	
	public String del(){
		this.actBiz.removeById(this.id);
		return RELOAD_ADMIN;
	}
	
	private static final String RELOAD_ADMIN = "reloadAdmin";
	////////////////////////
	////getters&setters/////
	////////////////////////
	public void setActBiz(TSActivityBiz actBiz) {
		this.actBiz = actBiz;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getRewardName() {
		return rewardName;
	}
	public void setRewardName(String rewardName) {
		this.rewardName = rewardName;
	}
	public TSActivity getAct() {
		return act;
	}
	public void setAct(TSActivity act) {
		this.act = act;
	}
	public File getAtta() {
		return atta;
	}
	public void setAtta(File atta) {
		this.atta = atta;
	}
	public String getAttaFileName() {
		return attaFileName;
	}
	public void setAttaFileName(String attaFileName) {
		this.attaFileName = attaFileName;
	}
	public Integer getActStatu() {
		return actStatu;
	}
	public void setActStatu(Integer actStatu) {
		this.actStatu = actStatu;
	}
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	
}

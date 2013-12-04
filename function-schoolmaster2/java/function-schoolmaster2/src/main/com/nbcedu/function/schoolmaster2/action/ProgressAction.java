package com.nbcedu.function.schoolmaster2.action;

import java.util.Date;
import java.util.List;

import com.nbcedu.function.schoolmaster2.biz.Sm2ProgressBiz;
import com.nbcedu.function.schoolmaster2.core.action.BaseAction;
import com.nbcedu.function.schoolmaster2.core.util.struts2.Struts2Utils;
import com.nbcedu.function.schoolmaster2.data.model.TSm2Progress;
import com.nbcedu.function.schoolmaster2.data.vo.ProgressVo;

/**
 * 工作进展action
 * @author wl
 */
@SuppressWarnings("serial")
public class ProgressAction extends BaseAction{

	private Sm2ProgressBiz progBiz;
	
	private TSm2Progress progress = new TSm2Progress();
	private String stepId;
	private String name;
	private String subjectId;
	private ProgressVo progressVo = new ProgressVo();
	/**
	 * 增加工作进展
	 */
	public void add(){
		progress.setCreaterId(this.getUserId());
		progress.setCreateTime(new Date());
		boolean b = this.progBiz.addPro(progress,subjectId);
		if(b){
			Struts2Utils.renderText("0","encoding:UTF-8");
		}else{
			Struts2Utils.renderText("1","encoding:UTF-8");
		}
	}
	public void isExist(){
		progressVo.setName(name);
		progressVo.setStepId(stepId);
		progressVo.setId(id);
		List<TSm2Progress> p = this.progBiz.findByProgressVo(progressVo);
		if(p==null||p.size()<1){
			Struts2Utils.renderText("0","encoding:UTF-8");
		}else{
			Struts2Utils.renderText("1","encoding:UTF-8");
		}
	}
	
	public String changeStep(){
		this.progBiz.modifyStep(this.stepId, this.id);
		this.stepId = this.getRequest().getParameter("originStepId").toString();
		return "refreshStep";
	}
	
	public void delete(){
		boolean b = this.progBiz.removeById1(id);
		if(b){
			Struts2Utils.renderText("0","encoding:UTF-8");
		}else{
			Struts2Utils.renderText("1","encoding:UTF-8");
		}
	}
	////////////////////////
	////getters&setters////
	//////////////////////
	public TSm2Progress getProgress() {
		return progress;
	}
	public void setProgress(TSm2Progress progress) {
		this.progress = progress;
	}
	public String getStepId() {
		return stepId;
	}
	public void setStepId(String stepId) {
		this.stepId = stepId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setProgBiz(Sm2ProgressBiz progBiz) {
		this.progBiz = progBiz;
	}
	public String getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}
	public ProgressVo getProgressVo() {
		return progressVo;
	}
	public void setProgressVo(ProgressVo progressVo) {
		this.progressVo = progressVo;
	}
	
}

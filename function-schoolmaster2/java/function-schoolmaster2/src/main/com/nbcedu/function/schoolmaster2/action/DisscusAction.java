package com.nbcedu.function.schoolmaster2.action;

import java.util.Date;

import com.nbcedu.function.schoolmaster2.biz.SM2DisscusBiz;
import com.nbcedu.function.schoolmaster2.core.action.BaseAction;
import com.nbcedu.function.schoolmaster2.core.util.struts2.Struts2Utils;
import com.nbcedu.function.schoolmaster2.data.model.TSm2Disscus;

/**
 * 评论action
 * @author wl
 */
@SuppressWarnings("serial")
public class DisscusAction extends BaseAction{

	private SM2DisscusBiz disscusBiz;
	
	private TSm2Disscus disscus = new TSm2Disscus();
	private String progId;
	private String stepId;
	
	/**
	 * 增加评论
	 */
	public String add(){
		disscus.setCreaterId(this.getUserId());
		disscus.setCreateTime(new Date());
		this.disscusBiz.add(disscus);
		return "refreshStep";
	}
	
	
	////////////////////////
	////getters&setters////
	//////////////////////
	public TSm2Disscus getDisscus() {
		return disscus;
	}
	public void setDisscus(TSm2Disscus disscus) {
		this.disscus = disscus;
	}
	public void setDisscusBiz(SM2DisscusBiz disscusBiz) {
		this.disscusBiz = disscusBiz;
	}
	public String getProgId() {
		return progId;
	}
	public void setProgId(String progId) {
		this.progId = progId;
	}
	public String getStepId() {
		return stepId;
	}
	public void setStepId(String stepId) {
		this.stepId = stepId;
	}

}

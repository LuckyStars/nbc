package com.nbcedu.function.schoolmaster2.action;

import java.util.Date;

import com.nbcedu.function.schoolmaster2.biz.Sm2DisscusBiz;
import com.nbcedu.function.schoolmaster2.core.action.BaseAction;
import com.nbcedu.function.schoolmaster2.core.util.struts2.Struts2Utils;
import com.nbcedu.function.schoolmaster2.data.model.TSm2Disscus;

/**
 * 评论action
 * @author wl
 */
@SuppressWarnings("serial")
public class DisscusAction extends BaseAction{

	private Sm2DisscusBiz disscusBiz;
	
	private TSm2Disscus disscus = new TSm2Disscus();
	private String progressId;
	/**
	 * 增加评论
	 */
	public void add(){
		disscus.setCreaterId(this.getUserId());
		disscus.setCreateTime(new Date());
		this.disscusBiz.add(disscus);
		Struts2Utils.renderText("0","encoding:UTF-8");
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

	public String getProgressId() {
		return progressId;
	}

	public void setProgressId(String progressId) {
		this.progressId = progressId;
	}

	public void setDisscusBiz(Sm2DisscusBiz disscusBiz) {
		this.disscusBiz = disscusBiz;
	}

}

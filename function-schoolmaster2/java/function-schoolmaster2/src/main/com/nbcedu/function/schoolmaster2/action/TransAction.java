package com.nbcedu.function.schoolmaster2.action;

import java.util.Date;
import java.util.List;

import com.nbcedu.function.schoolmaster2.biz.Sm2ProgressBiz;
import com.nbcedu.function.schoolmaster2.biz.Sm2TransBiz;
import com.nbcedu.function.schoolmaster2.core.action.BaseAction;
import com.nbcedu.function.schoolmaster2.core.util.struts2.Struts2Utils;
import com.nbcedu.function.schoolmaster2.data.model.SM2SubjectTrans;
import com.nbcedu.function.schoolmaster2.data.model.TSm2Progress;

/**
 * 转发action
 * @author wl
 */
@SuppressWarnings("serial")
public class TransAction extends BaseAction{

	private Sm2TransBiz transBiz;
	
	private SM2SubjectTrans trans = new SM2SubjectTrans();
	private String transUids;

	
	public void add(){
		for(String uid : transUids.split(",")){
			trans.setUserUid(uid);
		}
		this.transBiz.add(trans);
		Struts2Utils.renderText("0","encoding:UTF-8");
	}
	
	////////////////////////
	////getters&setters////
	//////////////////////
	
}

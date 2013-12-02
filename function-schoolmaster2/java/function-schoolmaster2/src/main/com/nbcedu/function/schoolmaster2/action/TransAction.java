package com.nbcedu.function.schoolmaster2.action;

import org.apache.commons.lang.xwork.StringUtils;
import com.nbcedu.function.schoolmaster2.biz.SM2TransBiz;
import com.nbcedu.function.schoolmaster2.core.action.BaseAction;
import com.nbcedu.function.schoolmaster2.core.util.struts2.Struts2Utils;
import com.nbcedu.function.schoolmaster2.data.model.SM2SubjectTrans;

/**
 * 转发action
 * @author wl
 */
@SuppressWarnings("serial")
public class TransAction extends BaseAction{

	private SM2TransBiz transBiz;
	
	private SM2SubjectTrans trans = new SM2SubjectTrans();
	private String transUids;
	private String transNames;
	private String content;
	private String subjectId;

	
	public void add(){
		if(StringUtils.isNotBlank(transUids) && StringUtils.isNotBlank(transNames)){
			String[] ids = transUids.split(",");
			String[] names = transNames.split(",");
			SM2SubjectTrans trans1 ;
			for(int i=0;i<ids.length;i++){
				trans1 = new SM2SubjectTrans();
				trans1.setSubId(subjectId);
				trans1.setUserName(names[0]);
				trans1.setUserUid(ids[0]);
				trans1.setContent(content);
				this.transBiz.add(trans1);
			}
			Struts2Utils.renderText("0","encoding:UTF-8");
		}
	}


	////////////////////////
	////getters&setters////
	//////////////////////
	public SM2SubjectTrans getTrans() {
		return trans;
	}

	public void setTrans(SM2SubjectTrans trans) {
		this.trans = trans;
	}

	public String getTransUids() {
		return transUids;
	}

	public void setTransUids(String transUids) {
		this.transUids = transUids;
	}

	public String getTransNames() {
		return transNames;
	}

	public void setTransNames(String transNames) {
		this.transNames = transNames;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setTransBiz(SM2TransBiz transBiz) {
		this.transBiz = transBiz;
	}


	public String getSubjectId() {
		return subjectId;
	}


	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}
	
}

package com.nbcedu.function.schoolmaster2.tags.operation;

import static org.apache.commons.lang.xwork.StringUtils.isNotBlank;
import static org.apache.commons.lang.xwork.StringUtils.trimToEmpty;

import javax.servlet.jsp.JspException;

import com.nbcedu.function.schoolmaster2.biz.SM2MasterSubBiz;
import com.nbcedu.function.schoolmaster2.data.model.SM2SubjectMaster;
import com.nbcedu.function.schoolmaster2.data.model.TSm2Subject;
import com.nbcedu.function.schoolmaster2.data.model.TSm2SubjectUser;
import com.nbcedu.function.schoolmaster2.tags.display.AbstractDisplayTag;
import com.nbcedu.function.schoolmaster2.utils.Utils;

@SuppressWarnings("serial")
public abstract class AbstractOperationTag extends AbstractDisplayTag{
	
	protected String subjectId;
	protected String stepId;
	
	abstract boolean show(OperationContext opCtx) ;

	protected boolean display() {
		boolean showResult = show(getOpCtx());
		this.subjectId = null;
		this.stepId = null;
		return showResult;
	}
	
	protected final OperationContext getOpCtx(){
		OperationContext ctx = 
			(OperationContext) pageContext.getAttribute("OperationContext");
		
		if(pageContext.getAttribute("OperationContext")==null){
			ctx = new OperationContext(subjectId,stepId);
		}
		return ctx;
	}
	
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}
	public void setStepId(String stepId) {
		this.stepId = stepId;
	}


	protected static final class OperationContext{
		
		/***自己是否是执行者***/
		private Boolean selfIsOperator = null;
		/***自己是否是发送人***/
		private Boolean selfIsSender = null;
		/***自己是否是接收者***/
		private Boolean selfIsReceiver = null;
		/***自己是否是主任***/
		private Boolean selfIsManager = null;
		/***自己是否是校长***/
		private Boolean selfIsMaster = null;
		/***发送人是否是校长***/
		private Boolean senderIsMaster = null;
		/***发送人是否是主任***/
		private Boolean senderIsManager = null;
		
		private TSm2Subject sub = null;
		private String selfUid = Utils.curUserUid();
		private String subId = null;
		private String stepId = null;
		
		protected SM2MasterSubBiz subBiz = 
			(SM2MasterSubBiz) Utils.Beans.getSpringBeanByName("masterSubBiz");
	
		private OperationContext(String subId,String stepId){
			this.selfIsMaster = Utils.isMaster();
			this.selfIsManager = Utils.isManager();
			this.subId = subId;
			this.stepId = stepId;
		}
		
		private void findSub(){
			if(this.sub == null){
				if(isNotBlank(this.subId)){
					this.sub = this.subBiz.findById(subId);
				}else if(isNotBlank(stepId)){
					this.sub = this.subBiz.findSubByStepId(stepId);
				}else{
					this.sub = new TSm2Subject();
				}
			}
		}
		
		/**加载相关**/
		private void findResults (){
			findSub();

			this.senderIsManager = Utils.getAllManagerUids().contains(this.sub.getCreaterId());
			this.senderIsMaster = Utils.getAllMasterUids().contains(this.sub.getCreaterId());
			
			this.selfIsSender = trimToEmpty(this.sub.getCreaterId()).equals(selfUid);
			
			this.selfIsReceiver = Boolean.FALSE;
			for (SM2SubjectMaster ma : this.sub.getCheckUsers()) {
				if(ma.getUserUid().equals(selfUid)){
					selfIsReceiver = Boolean.TRUE;
				}
			}
			
			this.selfIsOperator = Boolean.FALSE;
			for (TSm2SubjectUser su : this.sub.getExcuteUsers()) {
				if(su.getUserId().equals(selfUid)){
					selfIsOperator = Boolean.TRUE;
				}
			}
			
		}
		
		private Boolean getResult(Boolean bool){
			if(bool==null){
				findResults();
			}
			return bool;
		}
		////////////////////////
		/////getters&setters////
		////////////////////////
		public Boolean selfIsSender(){
			return getResult(selfIsSender);
		}
		public Boolean selfIsOperator() {
			return getResult(selfIsOperator);
		}
		public Boolean selfIsReceiver() {
			return getResult(selfIsReceiver);
		}
		public Boolean senderIsMaster() {
			return getResult(senderIsMaster);
		}
		public Boolean senderIsManager() {
			return getResult(senderIsManager);
		}
		public Boolean selfIsManager() {
			return selfIsManager;
		}
		public Boolean selfIsMaster() {
			return selfIsMaster;
		}
		
	}
	
	
}

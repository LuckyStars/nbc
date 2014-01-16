package com.nbcedu.function.schoolmaster2.tags.operation;

import static org.apache.commons.lang.xwork.StringUtils.isNotBlank;
import static org.apache.commons.lang.xwork.StringUtils.trimToEmpty;


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
	protected static final String CTX_KEY = "OperationContext";
	
	abstract boolean show(OperationContext opCtx) ;

	protected boolean display() {
		boolean showResult = show(getOpCtx());
		this.subjectId = null;
		this.stepId = null;
		return showResult;
	}
	
	
	protected final OperationContext getOpCtx(){
		OperationContext ctx = 
			(OperationContext) pageContext.getAttribute(CTX_KEY);
		
		if(pageContext.getAttribute(CTX_KEY)==null){
			ctx = new OperationContext(subjectId,stepId);
			pageContext.setAttribute(CTX_KEY, ctx);
		}
		return ctx;
	}
	
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}
	public void setStepId(String stepId) {
		this.stepId = stepId;
	}


	public static final class OperationContext{
		
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
		/**自己是否是大校长-.-**/
		private Boolean selfIsBigBoss = null;
		
		private TSm2Subject sub = null;
		private String selfUid = Utils.curUserUid();
		private String subId = null;
		private String stepId = null;
		
		private SM2MasterSubBiz subBiz = 
			(SM2MasterSubBiz) Utils.Beans.getSpringBeanByName("masterSubBiz");
	
		private OperationContext(String subId,String stepId){
			this.selfIsMaster = Utils.isMaster();
			this.selfIsManager = Utils.isManager();
			this.selfIsBigBoss = Utils.getDefaultMasterUids().contains(Utils.curUserUid());
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
		public Boolean getSelfIsOperator() {
			if(selfIsOperator==null){findResults();}
			return selfIsOperator;
		}
		public Boolean getSelfIsSender() {
			if(selfIsSender==null){findResults();}
			return selfIsSender;
		}
		public Boolean getSelfIsReceiver() {
			if(selfIsReceiver==null){findResults();}
			return selfIsReceiver;
		}
		public Boolean getSenderIsMaster() {
			if(senderIsMaster==null){findResults();}
			return senderIsMaster;
		}
		public Boolean getSenderIsManager() {
			if(senderIsManager==null){findResults();}
			return senderIsManager;
		}
		public Boolean getSelfIsManager() {
			return selfIsManager;
		}
		public Boolean getSelfIsMaster() {
			return selfIsMaster;
		}
		public Boolean getSelfIsBigBoss() {
			return selfIsBigBoss;
		}
		
	}
	
	
}

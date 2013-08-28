package com.nbcedu.function.cardmanage.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.nbcedu.function.cardmanage.biz.CMCardApplyBiz;
import com.nbcedu.function.cardmanage.biz.CMCardTypeBiz;
import com.nbcedu.function.cardmanage.biz.CMUserPrivilegeBiz;
import com.nbcedu.function.cardmanage.constants.CardStatus;
import com.nbcedu.function.cardmanage.core.action.BaseAction;
import com.nbcedu.function.cardmanage.core.exception.DBException;
import com.nbcedu.function.cardmanage.core.util.StringUtil;
import com.nbcedu.function.cardmanage.core.util.Struts2Util;
import com.nbcedu.function.cardmanage.core.util.exl.ExlAnnotationUtil;
import com.nbcedu.function.cardmanage.model.CMCardApply;
import com.nbcedu.function.cardmanage.model.CMCardType;
import com.nbcedu.function.cardmanage.model.CMUserPrivilege;
import com.nbcedu.function.cardmanage.util.PortalMsgUtil;
import com.nbcedu.function.cardmanage.util.UcService;
import com.nbcedu.function.cardmanage.vo.CMApply;
import com.nbcedu.function.cardmanage.vo.GradeClass;
import com.nbcedu.function.functionsupport.mapping.PortalMessage;

/**
 * 处理申请的action
 * @author xuechong
 */
@SuppressWarnings("serial")
public class CMCardApplyAction extends BaseAction{
	
	private CMCardApplyBiz cardApplyBiz;
	private CMCardTypeBiz cardTypeBiz ;
	private CMApply cmApply = new CMApply();
	private CMCardApply app = new CMCardApply();
	private CMUserPrivilegeBiz priBiz;
	private List<CMCardType> cardTypelist;
	
	public void setPriBiz(CMUserPrivilegeBiz priBiz) {
		this.priBiz = priBiz;
	}

	public List<CMCardType> getCardTypelist() {
		return cardTypelist;
	}

	public void setCardTypelist(List<CMCardType> cardTypelist) {
		this.cardTypelist = cardTypelist;
	}

	public String toApply(){
		boolean b = UcService.isClassMaster(getCurUserUid());
		GradeClass c;
		if(b){
			 cardTypelist = cardTypeBiz.findAll();
			 c =  UcService.findClassByUid(getCurUserUid());
		}else{
			cardTypelist = cardTypeBiz.findByType(0);
			c = null;
		}
		this.getRequest().setAttribute("gradeClass", c);
		return "apply";
	}
	
	public String add(){
		this.cmApply.setApplyUserUid(getCurUserUid());
		this.cmApply.setStatus(CardStatus.APPLIED.getId());
		this.cardApplyBiz.add(cmApply);
		return RELOAD;
	}
	
	public String save(){
		this.cmApply.setApplyUserUid(getCurUserUid());
		this.cmApply.setStatus(CardStatus.WAITING.getId());
		this.cardApplyBiz.add(cmApply);
		return RELOAD;
	}
	
	public String toUpdate(){
		boolean b = UcService.isClassMaster(getCurUserUid());
		GradeClass c;
		if(b){
			 cardTypelist = cardTypeBiz.findAll();
			 c =  UcService.findClassByUid(getCurUserUid());
		}else{
			cardTypelist = cardTypeBiz.findByType(0);
			c = null;
		}
		this.getRequest().setAttribute("gradeClass", c);
		
		this.app = this.cardApplyBiz.findById(this.id);
		return "toEdit";
	}
	public void find(){
		CMCardApply cm = this.cardApplyBiz.findById(this.id);
		String[] names = cm.getCardUserName().split(",");
		String className = UcService.findClassByID(cm.getCardClassId());
		StringBuffer sb = new StringBuffer();
		sb.append("{").append("\"total\":").append(names.length).append(",\"rows\":[");
		for(int i=0;i<names.length;i++){
			if(i!=names.length-1){
				sb.append("{\"name\":\"");
				sb.append(names[i]);
				sb.append("\",\"className\":\"");
				sb.append(className);
				sb.append("\"},");
			}else{
				sb.append("{\"name\":\"");
				sb.append(names[i]);
				sb.append("\",\"className\":\"");
				sb.append(className);
				sb.append("\"}");
			}
			
		}
		sb.append("]}");
		Struts2Util.renderJson(sb.toString(), "encoding:utf-8");
	}
	public String update(){
		cmApply.setApplyUserUid(getCurUserUid());
		cardApplyBiz.modifyApply(cmApply);
		return RELOAD;
	}
	public void updateStatus(){
		try {
			String id = Struts2Util.getRequest().getParameter("id");
			String status = Struts2Util.getRequest().getParameter("status");
			StringBuffer sb= new StringBuffer();
			sb.append("update t_cardmanage_cardapply set status=? ");
			if(status.equals("1")||status=="1"){
				sb.append(",createDate=now() ");
			}
			sb.append("where PK_CARDAPPLY_ID=?");
			cardApplyBiz.modifyBySql(sb.toString(),status,id);
			
			//发送通知
			sendMessage(id , status);
			
			Struts2Util.renderText("success", "encoding:utf-8");
		} catch (DBException e) {
			Struts2Util.renderText("fales", "encoding:utf-8");
			e.printStackTrace();
		}
	}
	private boolean sendMessage(String id,String status){
		CMCardApply c = this.cardApplyBiz.findById(id);
		PortalMessage message = new PortalMessage();
		message.setFunctionName("cardManage");
		message.setModuleName("cardManage");
		Collection<String> coll = new ArrayList<String>();
		
		if(c.getStatus()==0){
			message.setTitle(c.getApplyUserName()+"将"+c.getCardType().getName()+"取消了申请！");
			message.setIntroduction(c.getApplyUserName()+"将"+c.getCardType().getName()+"取消了申请！");
			List<CMUserPrivilege> users = this.priBiz.findAll();
			for(CMUserPrivilege user : users){
				coll.add(user.getUserUid());
			}
			message.setContent("取消补卡。");
			message.setUrl("cardManage/manageList_apply.action?cmApply.id="+id);
		}
		if(c.getStatus()==1){
			List<CMUserPrivilege> users = this.priBiz.findAll();
			for(CMUserPrivilege user : users){
				coll.add(user.getUserUid());
			}
			message.setTitle(c.getApplyUserName()+"老师进行了"+c.getCardType().getName()+"补卡申请!");
			message.setIntroduction(c.getApplyUserName()+"老师进行了"+c.getCardType().getName()+"补卡申请!");
			message.setUrl("cardManage/manageList_apply.action?cmApply.id="+id);
		}
		if(c.getStatus()==2){
			coll.add(c.getApplyUserUid());
				message.setTitle("您申请的补卡正在办理!");
				message.setIntroduction("您申请的补卡正在办理!");
				message.setUrl("cardManage/list_apply.action?cmApply.id="+id);
		}
		if(c.getStatus()==3){
			coll.add(c.getApplyUserUid());
				message.setTitle("您申请的补卡已经办理完成，请您领取!");
				message.setIntroduction("您申请的补卡已经办理完成，请您领取!");
				message.setUrl("cardManage/list_apply.action?cmApply.id="+id);
		}
		if(StringUtil.isEmpty(status)){
			coll.add(c.getApplyUserUid());
				message.setTitle("您申请的补卡已经办理完成，请您尽快领取!");
				message.setIntroduction("您申请的补卡已经办理完成，请您尽快领取!");
				message.setUrl("cardManage/list_apply.action?cmApply.id="+id);
		}
		if(c.getStatus()==4){
			List<CMUserPrivilege> users = this.priBiz.findAll();
			for(CMUserPrivilege user : users){
				coll.add(user.getUserUid());
			}
			message.setTitle(c.getApplyUserName()+"老师进行了"+c.getCardType().getName()+"补卡领取确认!");
			message.setIntroduction(c.getApplyUserName()+"老师进行了"+c.getCardType().getName()+"补卡领取确认!");
			message.setUrl("cardManage/manageList_apply.action?cmApply.id="+id);
		}
		message.setContent("补卡");
		message.setReceiverUids(coll);
		message.setMessageId(id);
		
		PortalMsgUtil p = new PortalMsgUtil();
		p.send(message);
		return true;
	}
	
	private boolean sendApplyMessage(CMCardApply o){
		PortalMessage message = new PortalMessage();
		message.setFunctionName("cardManage");
		message.setModuleName("cardManage");
		Collection<String> coll = new ArrayList<String>();
		List<CMUserPrivilege> users = this.priBiz.findAll();
		for(CMUserPrivilege user : users){
			coll.add(user.getUserUid());
		}
		if(o.getCardType().getType()==0){
			message.setTitle(o.getApplyUserName()+"申请教师补卡，请您处理！");
			message.setIntroduction(o.getApplyUserName()+"申请教师补卡，请您处理！");
		}else{
			message.setTitle(o.getApplyUserName()+"申请学生补卡，请您处理！");
			message.setIntroduction(o.getApplyUserName()+"申请学生补卡，请您处理！");
		}
		message.setReceiverUids(coll);
		message.setMessageId(o.getId());
		message.setContent("申请补卡.");
		message.setUrl("cardManage/manageList_apply.action?cmApply.id="+o.getId());
		PortalMsgUtil p = new PortalMsgUtil();
		p.send(message);
		return true;
	}
	public String delete(){
		String id = Struts2Util.getRequest().getParameter("applyId");
		this.cardApplyBiz.removeById(id);
		return RELOAD;
	}
	
	public String list(){
		List<CMCardType> cardTypelist = cardTypeBiz.findAll();
		this.getRequest().setAttribute("cardTypelist", cardTypelist);
		setPm(this.cardApplyBiz.findAllBy(cmApply,this.getCurUserUid()));
		return LIST;
	}
	
	public String manageList(){
		List<CMCardType> cardTypelist = cardTypeBiz.findAll();
		this.getRequest().setAttribute("cardTypelist", cardTypelist);
		setPm(this.cardApplyBiz.findAllManageBy(cmApply));
		return "manageList";
	}
	
	@SuppressWarnings("unchecked")
	public void exportExl(){
		List<CMApply> exlData =this.cardApplyBiz.findAllManageBy(cmApply).getDatas();
		ExlAnnotationUtil.export("补卡列表.xls",exlData);
	}
	public void notice(){
		String id = this.getRequest().getParameter("id");
		CMCardApply c = this.cardApplyBiz.findById(id);
		PortalMessage message = new PortalMessage();
		message.setFunctionName("cardManage");
		message.setModuleName("cardManage");
		if( c.getCardType().getType()==0){
			message.setTitle("您申请的教师卡"+c.getCardType().getName()+"已制卡完毕，请速取！");
			message.setIntroduction("您申请的教师卡"+c.getCardType().getName()+"已制卡完毕，请速取！");
		}else{
			message.setTitle("您申请的学生卡"+c.getCardType().getName()+"已制卡完毕，请速取！");
			message.setIntroduction("您申请的学生卡"+c.getCardType().getName()+"已制卡完毕，请速取！");
		}
		Collection<String> coll = new ArrayList<String>();
		coll.add(c.getApplyUserUid());
		message.setReceiverUids(coll);
		message.setMessageId(c.getId());
		message.setContent("请取卡。");
		message.setUrl("cardManage/list_apply.action?cmApply.id="+c.getId());
		PortalMsgUtil p = new PortalMsgUtil();
		p.send(message);
		Struts2Util.renderText("success", "encoding:utf-8");
	}
	////////////////////
	/////getters&setters////
	/////////////////////////
	public void setCardApplyBiz(CMCardApplyBiz cardApplyBiz) {
		this.cardApplyBiz = cardApplyBiz;
	}
	public CMApply getCmApply() {
		return cmApply;
	}
	public void setCmApply(CMApply cmApply) {
		this.cmApply = cmApply;
	}
	public void setCardTypeBiz(CMCardTypeBiz cardTypeBiz) {
		this.cardTypeBiz = cardTypeBiz;
	}
	public CMCardApply getApp() {
		return app;
	}
	public void setApp(CMCardApply app) {
		this.app = app;
	}
	
	
}

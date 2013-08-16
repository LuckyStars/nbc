package com.nbcedu.function.cardmanage.action;

import java.util.List;

import com.nbcedu.function.cardmanage.biz.CMCardApplyBiz;
import com.nbcedu.function.cardmanage.biz.CMCardTypeBiz;
import com.nbcedu.function.cardmanage.constants.CardStatus;
import com.nbcedu.function.cardmanage.core.action.BaseAction;
import com.nbcedu.function.cardmanage.core.exception.DBException;
import com.nbcedu.function.cardmanage.core.util.Struts2Util;
import com.nbcedu.function.cardmanage.model.CMCardApply;
import com.nbcedu.function.cardmanage.model.CMCardType;
import com.nbcedu.function.cardmanage.util.UcService;
import com.nbcedu.function.cardmanage.vo.CMApply;


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
	
	public String toApply(){
		List<CMCardType> cardTypelist = cardTypeBiz.findAll();
		this.getRequest().setAttribute("cardTypelist", cardTypelist);
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
		this.app = this.cardApplyBiz.findById(this.id);
		List<CMCardType> cardTypelist = cardTypeBiz.findAll();
		this.getRequest().setAttribute("cardTypelist", cardTypelist);
		this.getRequest().setAttribute("claName", UcService.findClassByID(app.getCardClassId()));
		return "toEdit";
	}
	
	public void updateStatus(){
		try {
			String status = Struts2Util.getRequest().getParameter("status");
			this.cardApplyBiz.modifyBySql("update t_cardmanage_cardapply set status=?",status);
			Struts2Util.renderText("success", "utf-8");
		} catch (DBException e) {
			Struts2Util.renderText("fales", "utf-8");
			e.printStackTrace();
		}
	}
	
	public String delete(){
		String id = Struts2Util.getRequest().getParameter("applyId");
		this.cardApplyBiz.removeById(id);
		return RELOAD;
	}
	
	public String list(){
		List<CMCardType> cardTypelist = cardTypeBiz.findAll();
		this.getRequest().setAttribute("cardTypelist", cardTypelist);
		setPm(this.cardApplyBiz.findAllBy(cmApply));
		return LIST;
	}
	
	public String manageList(){
		List<CMCardType> cardTypelist = cardTypeBiz.findAll();
		this.getRequest().setAttribute("cardTypelist", cardTypelist);
		setPm(this.cardApplyBiz.findAllManageBy(cmApply));
		return "manageList";
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

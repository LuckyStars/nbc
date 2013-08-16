package com.nbcedu.function.cardmanage.action;

import java.util.List;

import com.nbcedu.function.cardmanage.biz.CMCardApplyBiz;
import com.nbcedu.function.cardmanage.biz.CMCardTypeBiz;
import com.nbcedu.function.cardmanage.core.action.BaseAction;
import com.nbcedu.function.cardmanage.core.exception.DBException;
import com.nbcedu.function.cardmanage.core.util.Struts2Util;
import com.nbcedu.function.cardmanage.model.CMCardType;
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
	public String toApply(){
		List<CMCardType> cardTypelist = cardTypeBiz.findAll();
		this.getRequest().setAttribute("cardTypelist", cardTypelist);
		return "apply";
	}
	public String apply(){
		cmApply.setApplyUserUid("252b6864cd3411e0a92500e04c16ddaa");//getCurUserUid());
		this.getCardApplyBiz().add(cmApply);
		return RELOAD;
	}
	public String toUpdate(){
		
		
		return "apply";
	}
	public void update(){
		
	}
	public void updateStatus(){
		try {
			String status = Struts2Util.getRequest().getParameter("status");
			this.getCardApplyBiz().modifyBySql("update t_cardmanage_cardapply set status=?",status);
			Struts2Util.renderText("success", "utf-8");
		} catch (DBException e) {
			Struts2Util.renderText("fales", "utf-8");
			e.printStackTrace();
		}
	}
	public String delete(){
		String id = Struts2Util.getRequest().getParameter("applyId");
		this.getCardApplyBiz().removeById(id);
		return RELOAD;
	}
	public String list(){
		List<CMCardType> cardTypelist = cardTypeBiz.findAll();
		this.getRequest().setAttribute("cardTypelist", cardTypelist);
		setPm(this.getCardApplyBiz().findAllBy(cmApply));
		return LIST;
	}
	public String manageList(){
		List<CMCardType> cardTypelist = cardTypeBiz.findAll();
		this.getRequest().setAttribute("cardTypelist", cardTypelist);
		setPm(this.getCardApplyBiz().findAllManageBy(cmApply));
		return "manageList";
	}
	public CMCardApplyBiz getCardApplyBiz() {
		return cardApplyBiz;
	}
	public void setCardApplyBiz(CMCardApplyBiz cardApplyBiz) {
		this.cardApplyBiz = cardApplyBiz;
	}
	public CMApply getCmApply() {
		return cmApply;
	}
	public void setCmApply(CMApply cmApply) {
		this.cmApply = cmApply;
	}
	public CMCardTypeBiz getCardTypeBiz() {
		return cardTypeBiz;
	}
	public void setCardTypeBiz(CMCardTypeBiz cardTypeBiz) {
		this.cardTypeBiz = cardTypeBiz;
	}
	
}

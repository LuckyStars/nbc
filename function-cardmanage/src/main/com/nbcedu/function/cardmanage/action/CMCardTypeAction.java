package com.nbcedu.function.cardmanage.action;

import java.util.List;

import com.nbcedu.function.cardmanage.biz.CMCardTypeBiz;
import com.nbcedu.function.cardmanage.core.action.BaseAction;
import com.nbcedu.function.cardmanage.core.util.Struts2Util;
import com.nbcedu.function.cardmanage.core.util.strings.StringUtil;
import com.nbcedu.function.cardmanage.model.CMCardType;

/**
 * 补卡类型维护
 * @author xuechong
 */
public class CMCardTypeAction extends BaseAction{
	private static final long serialVersionUID = -193356823919600420L;
	private CMCardTypeBiz cardTypeBiz;
	private CMCardType cmCardType = new CMCardType();
	
	public void saveORupdate(){
		String type = this.getRequest().getParameter("productid");
		String name = this.getRequest().getParameter("name1");
		String id= this.getRequest().getParameter("id1");
		cmCardType.setName(name);
		cmCardType.setType(Integer.parseInt(type));
		if(!StringUtil.isEmpty(id)){
			cmCardType.setId(id);
		}
		this.getCardTypeBiz().addOrUpdate(cmCardType);
	}
	public void findAll(){
		List<CMCardType> list = this.getCardTypeBiz().findAll();
		StringBuffer sb = new StringBuffer();
		sb.append("{").append("\"total\":").append(list.size()).append(",\"rows\":[");
		for(int i=0;i<list.size();i++){
			if(i!=list.size()-1){
				sb.append("{\"");
				sb.append("name1\":\"");
				sb.append(list.get(i).getName());
				
				sb.append("\",\"");
				sb.append("id1\":\"");
				sb.append(list.get(i).getId());
				
				sb.append("\",\"");
				sb.append("productid\":\"");
				sb.append(list.get(i).getType());
				sb.append("\"},");
			}else{
				sb.append("{\"");
				sb.append("name1\":\"");
				sb.append(list.get(i).getName());
				
				sb.append("\",\"");
				sb.append("id1\":\"");
				sb.append(list.get(i).getId());
				
				sb.append("\",\"");
				sb.append("productid\":\"");
				sb.append(list.get(i).getType());
				sb.append("\"}");
			}
			
		}
		sb.append("]}");
		Struts2Util.renderJson(sb.toString(), "encoding:utf-8");
	}
	public void delete(){
		try{
			this.getCardTypeBiz().removeById(id);
			Struts2Util.renderText("0", "encoding:utf-8");
		}catch(Exception e){
			Struts2Util.renderText("1", "encoding:utf-8");
			//e.printStackTrace();
		}
	}
	public CMCardTypeBiz getCardTypeBiz() {
		return cardTypeBiz;
	}

	public void setCardTypeBiz(CMCardTypeBiz cardTypeBiz) {
		this.cardTypeBiz = cardTypeBiz;
	}

	public CMCardType getCmCardType() {
		return cmCardType;
	}
	public void setCmCardType(CMCardType cmCardType) {
		this.cmCardType = cmCardType;
	}
	
}

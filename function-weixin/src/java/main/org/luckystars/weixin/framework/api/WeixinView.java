package org.luckystars.weixin.framework.api;


/**
 * @author xuechong
 */
@SuppressWarnings("serial")
public abstract class WeixinView implements View {
	
	@Override
	public ViewType getViewType() {
		return View.ViewType.WeixinView;
	}
	
	@Override
	public String toViewString() {
		return toWeixinStr();
	}
	
	public abstract String toWeixinStr();
}

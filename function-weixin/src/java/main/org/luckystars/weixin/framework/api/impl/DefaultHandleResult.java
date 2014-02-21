package org.luckystars.weixin.framework.api.impl;


import org.luckystars.weixin.framework.api.HandleResult;
import org.luckystars.weixin.framework.api.WeixinView;

public class DefaultHandleResult implements HandleResult {

	private WeixinView view ;
	
	@Override
	public WeixinView getView() {
		return view;
	}

	@Override
	public void setView(WeixinView view) {
		this.view = view;
	}

}

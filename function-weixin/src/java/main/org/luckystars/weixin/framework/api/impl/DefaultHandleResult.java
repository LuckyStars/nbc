package org.luckystars.weixin.framework.api.impl;


import org.luckystars.weixin.framework.api.HandleResult;
import org.luckystars.weixin.framework.api.View;

public class DefaultHandleResult implements HandleResult {

	private View view ;
	
	@Override
	public View getView() {
		return view;
	}

	@Override
	public void setView(View view) {
		this.view = view;
	}


}

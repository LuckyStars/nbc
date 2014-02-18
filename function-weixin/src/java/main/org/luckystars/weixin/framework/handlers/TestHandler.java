package org.luckystars.weixin.framework.handlers;

import org.luckystars.weixin.framework.HandlerInvocation;
import org.luckystars.weixin.framework.api.HandleResult;
import org.luckystars.weixin.framework.api.Handler;
import org.luckystars.weixin.framework.api.WeixinView;

public class TestHandler implements Handler{
	@Override
	public HandleResult handle(HandlerInvocation invocation) {
		System.out.println("test");
		
		final WeixinView view = new WeixinView() {
			@Override
			public String toWeixinStr() {
				return "asdasd";
			}
		};
		
		return new HandleResult() {
			public void setView(WeixinView view) {
			}
			public WeixinView getView() {
				return view;
			}
		};
	}
}

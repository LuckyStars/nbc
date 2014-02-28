package org.luckystars.weixin.framework.handlers;


import org.apache.log4j.Logger;
import org.luckystars.weixin.framework.HandlerInvocation;
import org.luckystars.weixin.framework.api.HandleResult;
import org.luckystars.weixin.framework.api.Handler;
import org.luckystars.weixin.framework.api.IncomeMessage;
import org.luckystars.weixin.framework.api.WeixinView;
import org.luckystars.weixin.transfer.incomemsg.WeixinMsg;
import org.luckystars.weixin.transfer.view.TextView;

public class TestHandler implements Handler{

	
	private static final Logger logger = Logger.getLogger(TestHandler.class);
	@Override
	public HandleResult handle(HandlerInvocation invocation) throws Exception {
		System.out.println("test");
		IncomeMessage msg = invocation.getInvocationContext().getMsg();
		
		if(msg instanceof WeixinMsg){
			WeixinMsg m = (WeixinMsg)msg;
			logger.info("\n\n" + m.getRawXml() + "\n\n");
			String toUsr = m.getFromUserName();
			String serverName = m.getToUserName();
			
			final WeixinView view = new TextView("这只是个测试\n你发送的数据是\n" + m.toString(), toUsr, serverName);
			
			return new HandleResult() {
				public void setView(WeixinView view) {
				}
				public WeixinView getView() {
					return view;
				}
			};
		}
		return invocation.invokeNext();
	}
	
}

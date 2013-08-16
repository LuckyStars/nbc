package com.nbcedu.function.cardmanage.util;

import com.nbcedu.function.cardmanage.constants.CardStatus;
import com.nbcedu.function.functionsupport.core.PortalMessageUtil;
import com.nbcedu.function.functionsupport.core.SupportManager;
import com.nbcedu.function.functionsupport.mapping.PortalMessage;

public class PortalMsgUtil {
	
	private static final class UtilHolder{
		private static final PortalMessageUtil msgUtil;
		static{
			msgUtil =  SupportManager.getPortalMessageUtil();
		}
		static PortalMessageUtil getUtil(){
			return msgUtil;
		}
	}
	public void send (CardStatus status){
		PortalMessage message = new PortalMessage();
		message.setFunctionName("cardManage");
		message.setModuleName("cardManage");
		message.setTitle("");
		
		UtilHolder.getUtil().sendPortalMsg(message, null);
	}
}

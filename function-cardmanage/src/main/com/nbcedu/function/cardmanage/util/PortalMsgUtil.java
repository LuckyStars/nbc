package com.nbcedu.function.cardmanage.util;


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
	public void send (PortalMessage message){
		UtilHolder.getUtil().sendPortalMsg(message, null);
	}
	
//	public void deleteMessage(CMCardApply c){
//		PortalMessage message = new PortalMessage();
//		Collection<String> coll = new ArrayList<String>();
//		for(){
//			coll.add(c.getApplyUserUid());
//		}
//		message.setReceiverUids(coll);
//		message.setMessageId(c.getId());
//		UtilHolder.getUtil().removeMessageByReceiverUidsWithNewThread(message, null);
//	}
}

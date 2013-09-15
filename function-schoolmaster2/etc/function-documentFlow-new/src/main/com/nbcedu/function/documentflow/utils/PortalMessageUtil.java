package com.nbcedu.function.documentflow.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.nbcedu.common.support.PropertyAccessorSupport;
import com.nbcedu.function.functionsupport.core.FunctionSupportUtil;
import com.nbcedu.function.functionsupport.core.SupportManager;
import com.nbcedu.function.functionsupport.mapping.PortalMessage;

public class PortalMessageUtil {
	private static final String DOCUMENT_APP_NAME = "DOCUMENTFLOW";
	
	public static String messageType = "";
	public static String messageName = "";
	public static boolean isPortalOK = false; //是否推送到门户
	public static boolean isIMOK = false; //是否推送到协同
	
	static {
		messageType = PropertyAccessorSupport.getValue(DOCUMENT_APP_NAME + ".TYPE");
		messageName = PropertyAccessorSupport.getValue(DOCUMENT_APP_NAME + ".CNNAME");
		
		try {
			isPortalOK = "true".equals(PropertyAccessorSupport.getValue(DOCUMENT_APP_NAME + ".PORTAL.SWICH"));
		}catch (Exception e) {}
		
		try {
			isIMOK = "true".equals(PropertyAccessorSupport.getValue(DOCUMENT_APP_NAME + ".XIETONG.SWICH"));
		} catch (Exception e) {}
	}
	
	public static boolean sendMessage(String id, String title, String summary, String url, String[] uids) {
		PortalMessage message = new PortalMessage();
		message.setMessageId(id);
		message.setTitle(title);
		message.setIntroduction(summary);
		message.setUrl(url);
		message.setMessageType(messageType);
		message.setFunctionName("documentFlow");
		message.setModuleName(messageName);
		message.setReceiverUids(Arrays.asList(uids));
		
		FunctionSupportUtil functionSupport = SupportManager.getFunctionSupportUtil();
		functionSupport.sendPortalMsg(message, null);
		
		return true;
	}
	/**
	 * 删除单条门户消息
	 * @param msgId
	 * @author xuechong
	 */
	public static void removeSingleMessage(String msgId){
		PortalMessage message = new PortalMessage();
		message.setMessageId(msgId);
		message.setModuleName(messageName);
		com.nbcedu.function.functionsupport.core.PortalMessageUtil portalUtil = SupportManager.getPortalMessageUtil();
		portalUtil.removeSingleMessage(message, null);
	}
	/**
	 * 删除单条门户消息
	 * @param id
	 * @return
	 */
	public static boolean delPortalPush(String msgId, String uid){
		List<String> uids = new ArrayList<String>();
		uids.add(uid);
		
		PortalMessage message = new PortalMessage();
		message.setMessageId(msgId);
		message.setReceiverUids(uids);
		
		FunctionSupportUtil functionSupport = SupportManager.getFunctionSupportUtil();
		functionSupport.removeMessageByReceiverUids(message, null);
		
        return true;
	}
	
	/**
	 * 2期门户消息 删除
	 * @param id
	 * @param moduleName
	 */
	public static boolean delAllPortalPush(){
		FunctionSupportUtil functionSupport = SupportManager.getFunctionSupportUtil();
		functionSupport.deleteAllByMsgType(messageType, null);
		
	    return true;
	}
	
	/**
	 * 2期门户消息 删除
	 * @param id
	 * @param moduleName
	 */
	public static boolean delPortalPushByDocId(String docId){
		PortalMessage message = new PortalMessage();
		message.setMessageId(docId);
		message.setModuleName(messageName);
		
		FunctionSupportUtil functionSupport = SupportManager.getFunctionSupportUtil();
		functionSupport.removeSingleMessage(message, null);
		
		return true;
	}
	
	
}

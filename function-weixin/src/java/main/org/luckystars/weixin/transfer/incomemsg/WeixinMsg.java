package org.luckystars.weixin.transfer.incomemsg;

import java.util.HashMap;
import java.util.Map;

import org.luckystars.weixin.framework.api.IncomeMessage;

@SuppressWarnings("serial")
public abstract class WeixinMsg implements IncomeMessage{
	
	public static final String MSG_TYPE_KEY = "MsgType";
	
	
	
	public static final String MSG_TYPE_EVENT = "event";
	
	public static final String MSG_TYPE_IMAGE = "image";
	
	public static final String MSG_TYPE_LINK = "link";
	
	public static final String MSG_TYPE_LOCATION = "location";
	
	public static final String MSG_TYPE_TEXT ="text";
		
	public static final String MSG_TYPE_UNKNOWN ="unknown";
	
	/** 原始数据*/
	protected String rawXML;

	protected Map<String, String> contents = new HashMap<String, String>();
	
	@Override
	public String getSessionId() {
		return fromUserName();
	}
	
	@Override
	public String getMsgType(){
		return contents.get(MSG_TYPE_KEY);
	}
	@Override
	public String get(String attr) {
		return contents.get(attr);
	}
	
	public String getRawXml(){
		return rawXML;
	}
	
	public String toUserName (){
		return contents.get("ToUserName");
	}
	
	public String fromUserName(){
		return contents.get("FromUserName");
	}
	
	public String createTime(){
		return contents.get("CreateTime");
	}
	
	public void setProperties(Map<String, String> props){
		this.contents = props;
	}
	
//	@Test
//	public void test() {
//		WeixinMsg m = new WeixinMsg() {
//			@Override
//			public String getSessionId() {
//				return fromUserName();
//			}};
//		String s = " <xml> <ToUserName><![CDATA[toUser]]></ToUserName>"+
//				 "<FromUserName><![CDATA[fromUser]]></FromUserName> "+
//				 "<CreateTime>1348831860</CreateTime>"+
//				 "<MsgType><![CDATA[text]]></MsgType>"+
//				 "<Content><![CDATA[this is a test]]></Content>"+
//				 "<MsgId>1234567890123456</MsgId>"+
//				 "</xml>";
//		
//		for (Map.Entry<String, String> en : m.contents.entrySet()) {
//			System.out.println(en.getKey() + "===" + en.getValue());
//		}
//		
//	}
}

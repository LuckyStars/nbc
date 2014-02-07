package org.luckystars.weixin.transfer.msg;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public abstract class Msg {
	
	public static final String MSG_TYPE_KEY = "MsgType";
	
	/** 原始数据*/
	protected String rawXML;

	protected Map<String, String> contents = new HashMap<String, String>();
	
	public String getRawXml(){
		return rawXML;
	}
	
	public String toUserName (){
		return contents.get("ToUserName");
	}
	
	public String fromUserName(){
		return contents.get("FromUserName");
	}
	
	public String msgId(){
		return contents.get("MsgId");
	}
	
	public String createTime(){
		return contents.get("CreateTime");
	}
	
	public String msgType(){
		return contents.get(MSG_TYPE_KEY);
	}
	
	
	@Test
	public void test() {
		Msg m = new Msg() {};
		String s = " <xml> <ToUserName><![CDATA[toUser]]></ToUserName>"+
				 "<FromUserName><![CDATA[fromUser]]></FromUserName> "+
				 "<CreateTime>1348831860</CreateTime>"+
				 "<MsgType><![CDATA[text]]></MsgType>"+
				 "<Content><![CDATA[this is a test]]></Content>"+
				 "<MsgId>1234567890123456</MsgId>"+
				 "</xml>";
		
		for (Map.Entry<String, String> en : m.contents.entrySet()) {
			System.out.println(en.getKey() + "===" + en.getValue());
		}
		
	}
}

package com.nbcedu.function.weixin.transfer.msg;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public abstract class Msg {
	/** 原始数据*/
	protected String rawXML;

	protected static Map<String, String> contents = new HashMap<String, String>();
	
	protected static void init(final String rawXml) throws DocumentException{
		Document dom = new SAXReader().read(new StringReader(rawXml));
		Element root = dom.getRootElement();
		Iterator<Element> rootIter = root.elementIterator();
		if(rootIter!=null&&rootIter.hasNext()){
			while(rootIter.hasNext()) {
				Element child = rootIter.next();
				contents.put(child.getName(), child.getTextTrim());
			}
		}
	}
	
	
	public static void main(String[] args) throws DocumentException {
		String s = " <xml> <ToUserName><![CDATA[toUser]]></ToUserName>"+
				 "<FromUserName><![CDATA[fromUser]]></FromUserName> "+
				 "<CreateTime>1348831860</CreateTime>"+
				 "<MsgType><![CDATA[text]]></MsgType>"+
				 "<Content><![CDATA[this is a test]]></Content>"+
				 "<MsgId>1234567890123456</MsgId>"+
				 "</xml>";
		init(s);
		for (Map.Entry<String, String> en : contents.entrySet()) {
			System.out.println(en.getKey() + "===" + en.getValue());
		}
		
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
	
	public String msgId(){
		return contents.get("MsgId");
	}
	
	public String createTime(){
		return contents.get("CreateTime");
	}
	
	public String msgType(){
		return contents.get("MsgType");
	}
}

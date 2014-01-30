package org.luckystars.weixin.transfer.msg;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

public abstract class Msg {
	/** 原始数据*/
	protected String rawXML;

	protected Map<String, String> contents = new HashMap<String, String>();
	
	@SuppressWarnings("unchecked")
	protected void init(final String rawXml) throws DocumentException{
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
	
	
	@Test
	public void test() throws DocumentException {
		Msg m = new Msg() {};
		String s = " <xml> <ToUserName><![CDATA[toUser]]></ToUserName>"+
				 "<FromUserName><![CDATA[fromUser]]></FromUserName> "+
				 "<CreateTime>1348831860</CreateTime>"+
				 "<MsgType><![CDATA[text]]></MsgType>"+
				 "<Content><![CDATA[this is a test]]></Content>"+
				 "<MsgId>1234567890123456</MsgId>"+
				 "</xml>";
		m.init(s);
		for (Map.Entry<String, String> en : m.contents.entrySet()) {
			System.out.println(en.getKey() + "===" + en.getValue());
		}
		
	}
}

package org.luckystars.weixin.transfer.msg;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.luckystars.weixin.framework.api.IncomeMessage;

public class WeixinMsgFactory {
	
	private static final Logger logger = Logger.getLogger(WeixinMsgFactory.class);
	
	private static Map<String, Class<? extends WeixinMsg>> msgTypeMappings = 
		new HashMap<String, Class<? extends WeixinMsg>>();
	
	static{
		msgTypeMappings.put("text", TextMsg.class);
		msgTypeMappings.put("event", EventMsg.class);
		msgTypeMappings.put("image", ImageMsg.class);
		msgTypeMappings.put("location", LocationMsg.class);
	}

	public static IncomeMessage build(String rawContent) {
		WeixinMsg result =null;
		Map<String, String> contents = newContentMap(rawContent);
		String msgType = contents.get(WeixinMsg.MSG_TYPE_KEY);
		result = createMsg(msgType);
		result.setProperties(contents);
		return result;
	}
	
	
	private static WeixinMsg createMsg(String msgType) {
		WeixinMsg result = null;
		Class<? extends WeixinMsg> cla = msgTypeMappings.get(msgType);
		
		if(cla==null){
			result = new DefaultUnknowMsg();
			return result;
			//throw new NullPointerException("没有与" + msgType + "对应的消息类型");
		}
		try {
			result = cla.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	private static Map<String, String> newContentMap(final String rawXml){
		Map<String, String> contentsMap = new HashMap<String, String>();
		try {
			Document dom = new SAXReader().read(new StringReader(rawXml.trim()));
			Element root = dom.getRootElement();
			Iterator<Element> rootIter = root.elementIterator();
			if (rootIter != null && rootIter.hasNext()) {
				while (rootIter.hasNext()) {
					Element child = rootIter.next();
					contentsMap.put(child.getName(), child.getTextTrim());
				}
			}
		} catch (DocumentException e) {
			logger.error("解析消息出现异常", e);
		}
		return contentsMap;
	}
	
	@SuppressWarnings("serial")
	private static class DefaultUnknowMsg extends WeixinMsg{
	}
	
}

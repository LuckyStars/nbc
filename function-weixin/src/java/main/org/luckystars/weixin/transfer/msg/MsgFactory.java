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

public class MsgFactory {
	
	private static final Logger logger = Logger.getLogger(MsgFactory.class);

	public static IncomeMessage build(String rawContent) {
		
		Map<String, String> contents = newContentMap(rawContent);
		String msgType = contents.get(WeixinMsg.MSG_TYPE_KEY);
		
		return null;
	}
	
	
	@SuppressWarnings("unchecked")
	static Map<String, String> newContentMap(final String rawXml){
		Map<String, String> contentsMap = new HashMap<String, String>();
		try {
			Document dom = new SAXReader().read(new StringReader(rawXml));
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
}

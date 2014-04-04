package org.luckystars.weixin.framework.config.xml;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class XmlConfigUtils {
	
	private static final Logger logger = Logger.getLogger(XmlConfigUtils.class);
	/**
	 * 加载xml
	 * @param xmlPath
	 * @return
	 * @author xuechong
	 */
	public static Document buildDoument(String xmlPath) {
		Document result = null;
		
		try {
			InputStream in = Thread.currentThread().
				getContextClassLoader().getResourceAsStream(xmlPath);
			if(in==null){
				throw new NullPointerException("配置文件:" + xmlPath + "不存在");
			}
			
			DocumentBuilderFactory fac = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = fac.newDocumentBuilder();
			
			result = builder.parse(Thread.currentThread().getContextClassLoader().
					getResourceAsStream(xmlPath));
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		
		return result;
	}
	
	/**
	 * 没有值时返回空字符
	 * @param valueKey
	 * @param node
	 * @return
	 * @author xuechong
	 */
	public static String trimNodeAttr(String valueKey,Node node){
		Node attr = node.getAttributes().getNamedItem(valueKey);
		if(attr==null){return "";}
		return attr.getNodeValue()!=null?attr.getNodeValue():"";
	}
	
	/**
	 * 加载nodeValue 如果没有此节点抛出异常
	 * @param valueKey
	 * @param node
	 * @return
	 * @author xuechong
	 */
	public static String loadNodeAttr(String valueKey,Node node){
		Node attr = node.getAttributes().getNamedItem(valueKey);
		if(attr==null||attr.getNodeValue()==null||attr.getNodeValue().trim().isEmpty()){
			throw new NullPointerException("no such value" + valueKey);
		}
		return attr.getNodeValue().trim();
	}
}

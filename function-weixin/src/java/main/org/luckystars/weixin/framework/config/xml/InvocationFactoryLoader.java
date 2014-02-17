package org.luckystars.weixin.framework.config.xml;

import org.apache.log4j.Logger;
import org.luckystars.weixin.framework.AppContext;
import org.luckystars.weixin.framework.api.AppContextLoader;
import org.luckystars.weixin.framework.api.InvocationFactoryBean;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 将InvocationFactoryBean 加载到 appContext中
 * @author xuechong
 */
class InvocationFactoryLoader implements AppContextLoader{

	private static final Logger logger = Logger.getLogger(InvocationFactoryLoader.class);
	
	private final String xmlTag = "invocationFactoryBean";
	
	private Document doc;
	
	InvocationFactoryLoader (Document document){
		this.doc = document;
	}
	
	@Override
	public void loadIntoContext(AppContext ctx) {
		NodeList nodes = doc.getDocumentElement().getElementsByTagName(xmlTag);
		valiExists(nodes);
		Node node = nodes.item(0);
		String facClassName = node.getTextContent();
		InvocationFactoryBean fac = getFactoryBean(facClassName);
		ctx.put(InvocationFactoryBean.INVOCATION_FACTORY_BEAN, fac);
	}

	@SuppressWarnings("rawtypes")
	private InvocationFactoryBean getFactoryBean(String facClassName) {
		
		if(facClassName==null||facClassName.trim().isEmpty()){
			throw new NullPointerException("没有配置invocationFactoryBean");
		}
		InvocationFactoryBean result = null;
		try {
			Class fac = Thread.currentThread().getContextClassLoader().loadClass(facClassName);
			result = (InvocationFactoryBean) fac.newInstance();
			
		} catch (ClassNotFoundException e) {
			logger.error(e);
			e.printStackTrace();
		} catch (InstantiationException e) {
			logger.error(e);
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			logger.error(e);
			e.printStackTrace();
		}
		if(result==null){
			throw new NullPointerException("无法加载InvocationFactoryBean");
		}
		return result;
	}

	private void valiExists(NodeList nodes) {
		if(nodes==null||nodes.getLength()<=0){
			throw new NullPointerException("没有配置invocationFactoryBean");
		}
	}
	
	
}

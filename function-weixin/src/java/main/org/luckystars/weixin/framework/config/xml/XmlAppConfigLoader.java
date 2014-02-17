package org.luckystars.weixin.framework.config.xml;


import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.luckystars.weixin.framework.AppContext;
import org.luckystars.weixin.framework.api.AppContextLoader;
import org.w3c.dom.Document;

public class XmlAppConfigLoader implements AppContextLoader{

	private static final Logger logger = Logger.getLogger(XmlAppConfigLoader.class);
	
	@Override
	public void loadIntoContext(AppContext ctx) {
		String xmlPath = ctx.getConfigLocation();
		Document document = buildDoument(xmlPath);
		loadInvocationFactory(ctx,document);
		loadHandlerChains(ctx, document);
	}
	
	private void loadInvocationFactory(AppContext ctx, Document document) {
		new InvocationFactoryLoader(document).loadIntoContext(ctx);
	}

	private void loadHandlerChains(AppContext ctx, Document document) {
		AppContextLoader handlerChainLoader = new ChainConfigLoader(document);
		handlerChainLoader.loadIntoContext(ctx);
	}

	
	
	/**
	 * 加载xml
	 * @param xmlPath
	 * @return
	 * @author xuechong
	 */
	private Document buildDoument(String xmlPath) {
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
	
}

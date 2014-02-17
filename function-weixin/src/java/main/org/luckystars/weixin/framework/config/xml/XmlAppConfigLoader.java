package org.luckystars.weixin.framework.config.xml;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.io.FileUtils;
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
			debug:{
				
				System.out.print(loadFileToString(xmlPath));
			}
			
			DocumentBuilderFactory fac = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = fac.newDocumentBuilder();
//			result = builder.parse(in);
			
			result = builder.parse(new File(
					Thread.currentThread().getContextClassLoader().
					getResource(xmlPath).toURI()));
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		
		return result;
	}
	
	public static String loadFileToString(String filePath){
		try {
			return FileUtils.readFileToString(
					new File(
							Thread.currentThread().getContextClassLoader().
							getResource(filePath).toURI()),"UTF-8");
		} catch (IOException e) {
			logger.error("读取" + filePath  + "出错", e);
			return "";
		} catch (URISyntaxException e) {
			logger.error("读取" + filePath  + "出错", e);
			return "";
		}
	}

}
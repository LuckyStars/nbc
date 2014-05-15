package org.luckystars.weixin.framework.config.xml;

import static org.luckystars.weixin.framework.config.xml.XmlConfigUtils.*;

import org.apache.log4j.Logger;
import org.luckystars.weixin.framework.AppContext;
import org.w3c.dom.Document;

/**
 * 加载appUrl
 * 既访问web应用的上下文地址
 * @author xuechong
 */
public class AppUrlLoader extends AbstractXmlContextLoader{
	
	public static final String APP_URL_KEY = "org.luckystars.weixin.framework.config.xml.APP_URL";
	
	private final String tagName = "appUrl";
	
	private static final Logger logger = Logger.getLogger(AppUrlLoader.class);
	
	public AppUrlLoader(){super();}
	
	public AppUrlLoader(Document doc){
		super();
		super.doc = doc;
	}

	@Override
	protected void doLoadIntoContext(AppContext ctx) {
		ctx.put(APP_URL_KEY, loadValueByTagName(tagName, this.doc));
		logger.info("AppUrl Loaded");
	}

}

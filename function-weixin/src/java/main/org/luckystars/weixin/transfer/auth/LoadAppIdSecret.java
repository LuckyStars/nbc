package org.luckystars.weixin.transfer.auth;

import static org.luckystars.weixin.framework.config.xml.XmlConfigUtils.*;

import org.apache.log4j.Logger;
import org.luckystars.weixin.framework.AppContext;
import org.luckystars.weixin.framework.config.xml.AbstractXmlContextLoader;
import org.w3c.dom.Document;


/**
 * 读取appid和appsecret
 * @author xuechong
 */
public class LoadAppIdSecret extends AbstractXmlContextLoader{
	
	private static final Logger logger = Logger.getLogger(LoadAppIdSecret.class);
	
	private String appIdTag = "appId";
	private String appSecretTag = "appSecret";
	
	public LoadAppIdSecret(){}
	
	public LoadAppIdSecret(Document doc){
		super.doc = doc;
	}

	@Override
	protected void doLoadIntoContext(AppContext ctx) {
		ctx.put(Constants.APPID_CONTEXT_KEY, loadValueByTagName(appIdTag,this.doc));
		logger.info("AppId Loaded");
		ctx.put(Constants.APPSECRET_CONTEXT_KEY, loadValueByTagName(appSecretTag,this.doc));
		logger.info("AppSecret Loaded");
	}
	
}

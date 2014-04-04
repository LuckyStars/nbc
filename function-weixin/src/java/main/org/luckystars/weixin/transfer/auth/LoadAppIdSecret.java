package org.luckystars.weixin.transfer.auth;

import static org.luckystars.weixin.framework.config.xml.XmlConfigUtils.*;

import org.luckystars.weixin.framework.AppContext;
import org.luckystars.weixin.framework.config.xml.AbstractXmlContextLoader;
import org.w3c.dom.Document;


/**
 * 读取appid和appsecret
 * @author xuechong
 */
public class LoadAppIdSecret extends AbstractXmlContextLoader{
	
	private String appIdTag = "appId";
	private String appSecretTag = "appSecret";
	
	public LoadAppIdSecret(){}
	
	public LoadAppIdSecret(Document doc){
		this.doc = doc;
	}

	@Override
	protected void doLoadIntoContext(AppContext ctx) {
		String appId = loadNodeAttr(appIdTag, this.doc);
		String appSecret = loadNodeAttr(appSecretTag, doc);
		ctx.put(Constants.APPID_CONTEXT_KEY, appId);
		ctx.put(Constants.APPSECRET_CONTEXT_KEY, appSecret);
	}

}

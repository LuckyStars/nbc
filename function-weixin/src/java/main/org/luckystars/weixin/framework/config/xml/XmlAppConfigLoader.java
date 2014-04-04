package org.luckystars.weixin.framework.config.xml;

import static org.luckystars.weixin.framework.config.xml.XmlConfigUtils.*;

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
		new ChainConfigLoader(document).loadIntoContext(ctx);
	}

	
	
	
}

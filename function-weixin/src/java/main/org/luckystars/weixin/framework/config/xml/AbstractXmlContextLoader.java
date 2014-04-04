package org.luckystars.weixin.framework.config.xml;

import static org.luckystars.weixin.framework.config.xml.XmlConfigUtils.buildDoument;

import org.luckystars.weixin.framework.AppContext;
import org.luckystars.weixin.framework.api.AppContextLoader;
import org.w3c.dom.Document;

public abstract class AbstractXmlContextLoader implements AppContextLoader{
	protected Document doc;

	@Override
	public void loadIntoContext(AppContext ctx) {
		initDocIfNull(ctx);
		doLoadIntoContext(ctx);
	}

	/**
	 * 如果doc为null时初始化doc
	 * @param ctx
	 * @author xuechong
	 */
	protected void initDocIfNull(AppContext ctx) {
		if(this.doc==null){
			String xmlPath = ctx.getConfigLocation();
			this.doc = buildDoument(xmlPath);
		}
	}
	
	protected abstract void doLoadIntoContext(AppContext ctx) ;
	
}

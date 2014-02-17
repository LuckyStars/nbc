package org.luckystars.weixin.framework.config.xml;

import org.luckystars.weixin.framework.AppContext;
import org.luckystars.weixin.framework.api.AppContextLoader;
import org.w3c.dom.Document;

/**
 * 读取handler chain内容
 * @author xuechong
 *
 */
class ChainConfigLoader implements AppContextLoader{

	private Document doc;
	
	ChainConfigLoader (Document document){
		this.doc = document;
	}
	
	@Override
	public void loadIntoContext(AppContext ctx) {
		
		
	
	}
	

}

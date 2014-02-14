package org.luckystars.weixin.framework.config.xml;

import org.luckystars.weixin.framework.AppContext;
import org.luckystars.weixin.framework.api.AppContextLoader;
import org.w3c.dom.Document;

class InvocationFactoryLoader implements AppContextLoader{

	private Document doc;
	
	InvocationFactoryLoader (Document document){
		this.doc = document;
	}
	
	@Override
	public void loadIntoContext(AppContext ctx) {
		// TODO Auto-generated method stub
		
	}
	
}

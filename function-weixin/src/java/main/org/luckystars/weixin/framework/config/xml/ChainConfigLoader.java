package org.luckystars.weixin.framework.config.xml;

import org.luckystars.weixin.framework.AppContext;
import org.luckystars.weixin.framework.api.AppContextLoader;
import org.luckystars.weixin.framework.config.HandlerChainConfig;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 读取handler chain内容
 * @author xuechong
 *
 */
class ChainConfigLoader implements AppContextLoader{

	private final String XML_TAG = "handlerChain";
	private Document doc;
	
	ChainConfigLoader (Document document){
		this.doc = document;
	}
	
	@Override
	public void loadIntoContext(AppContext ctx) {
		NodeList nodes = doc.getDocumentElement().getElementsByTagName(XML_TAG);
		valiExists(nodes);
		Node chainNode = nodes.item(0);
		
		HandlerChainConfig config = buildConfig(chainNode);
		
		ctx.put(HandlerChainConfig.HANDLER_CHAIN_CONFIG, config);
	}
	
	private HandlerChainConfig buildConfig(Node chainNode) {
		
		
		
		return null;
	}

	private void valiExists(NodeList nodes) {
		if(nodes==null||nodes.getLength()<=0){
			throw new NullPointerException("没有配置handlerChain");
		}
	}

}

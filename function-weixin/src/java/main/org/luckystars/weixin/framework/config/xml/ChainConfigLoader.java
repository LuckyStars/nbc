package org.luckystars.weixin.framework.config.xml;

import java.util.HashMap;
import java.util.Map;

import org.luckystars.weixin.framework.AppContext;
import org.luckystars.weixin.framework.api.AppContextLoader;
import org.luckystars.weixin.framework.config.ChainMapping;
import org.luckystars.weixin.framework.config.HandlerChainConfig;
import org.python.antlr.PythonParser.lambdef_return;
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
		NodeList handlerChains = doc.getDocumentElement().getElementsByTagName(XML_TAG);
		valiExists(handlerChains);
		
		for (int i = 0;i<handlerChains.getLength();i++) {
			System.out.println(handlerChains.item(i).getAttributes().getNamedItem("id").getNodeValue());
			HandlerChainConfig config = buildConfig(handlerChains.item(0));
			ctx.put(config.getName(), config);
		}
		
	}
	
	
	private HandlerChainConfig buildConfig(Node handlerChain) {
		HandlerChainConfig config = new HandlerChainConfig();
		
		String chainName = handlerChain.getAttributes().getNamedItem("id").getNodeValue();
		config.setName(chainName);
		
		Map<String, ChainMapping> chains = buildChainMappings(handlerChain);
		config.setAllChains(chains);
		
		return config;
	}
	

	private Map<String, ChainMapping> buildChainMappings(Node handlerChain) {
		Map<String, ChainMapping> result = new HashMap<String, ChainMapping>();
		NodeList chainsList = handlerChain.getChildNodes();
		
		for (int i =0,end = chainsList.getLength();i<end;i++) {
			Node chainNode = chainsList.item(i);
			//System.out.println(chain.getNodeType() + chain.getNodeName());
			if(chainNode.getNodeType()==Node.ELEMENT_NODE
					&&chainNode.getNodeName().equals("chain")){
				
				String chainId = chainNode.getAttributes().getNamedItem("id").getNodeValue();
				System.out.println(chainId);
			}
			System.out.println("------");
			
		}
		
		return result;
	}

	private void valiExists(NodeList nodes) {
		if(nodes==null||nodes.getLength()<=0){
			throw new NullPointerException("没有配置handlerChain");
		}
	}
	
	public static void main(String[] args) {
		String configLocation = "appConfig.xml";
		AppContext ctx = AppContext.initContext(configLocation);
		XmlAppConfigLoader loader = new XmlAppConfigLoader();
		loader.loadIntoContext(ctx);
	}

}

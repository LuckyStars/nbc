package org.luckystars.weixin.framework;

import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.luckystars.weixin.framework.api.AppContextLoader;
import org.luckystars.weixin.framework.config.xml.XmlAppConfigLoader;
import org.luckystars.weixin.framework.util.BeanUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * 应用全局上下文
 * @author xuechong
 */
public class AppContext implements Serializable{

	private static final long serialVersionUID = -5759981728727750618L;
	
	private static final Logger logger = Logger.getLogger(AppContext.class);
	
	private static AppContext context = new AppContext();
	
	private static String DEFAULT_CONFIG_LOCATION = "appConfig.xml";
	
	/**全局配置文件地址*/
	private String configLocation = DEFAULT_CONFIG_LOCATION;
	
	private Map<String, Object> ctx = new HashMap<String, Object>();
	
	
	private AppContext(){}
	
	public static AppContext getContext(){
		return context;
	}
	
	public Object get(String key){
		return ctx.get(key);
	}
	
	public Object put(String key,Object value){
		return ctx.put(key, value);
	}
	
	public String getConfigLocation(){
		return configLocation;
	}
	
	public static AppContext initContext(){
		return initContext(DEFAULT_CONFIG_LOCATION);
	}
	
	public static AppContext initContext(String cxtConfigLocation){
		context = new AppContext();
		context.configLocation = cxtConfigLocation;
		//new XmlAppConfigLoader().loadIntoContext(context);
		for (AppContextLoader loader : getLoaders(cxtConfigLocation)) {
			loader.loadIntoContext(context);
		}
		return context;
	}
	
	@SuppressWarnings("unchecked")
	static List<AppContextLoader> getLoaders(String configLocation){
		List<AppContextLoader> loaderList = Collections.EMPTY_LIST;
		Document document = buildDoument(configLocation);
		NodeList nodes = document.getDocumentElement().getElementsByTagName("configLoaders");
		if(valiConfig(nodes)){
			loaderList = buildLoaders(nodes);
		}
		return loaderList;
	}

	
	private static List<AppContextLoader> buildLoaders(NodeList nodes) {
		List<AppContextLoader> result = new ArrayList<AppContextLoader>();
		for(int i=0,end = nodes.getLength();i<end;i++){
			Node loaderNode = nodes.item(i);
			String loaderClass = loaderNode.getTextContent();
			if(loaderClass!=null&&!loaderClass.trim().isEmpty()){
				for(String loaderCla :loaderClass.split(",")){
					try {
						AppContextLoader loader = (AppContextLoader) BeanUtil.getBeanByClassName(loaderCla);
						result.add(loader);
						if(logger.isInfoEnabled()){
							logger.info("add ctxloader:" + loaderCla);
						}
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (InstantiationException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return result;
	}

	private static boolean valiConfig(NodeList nodes) {
		if(nodes==null||nodes.getLength()<=0){
			logger.info("没有配置appcontext loader");
		}
		return true;
	}

	private static Document buildDoument(String xmlPath) {
		Document result = null;
		
		try {
			InputStream in = Thread.currentThread().
				getContextClassLoader().getResourceAsStream(xmlPath);
			if(in==null){
				throw new NullPointerException("配置文件:" + xmlPath + "不存在");
			}
			
			DocumentBuilderFactory fac = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = fac.newDocumentBuilder();
			
			result = builder.parse(Thread.currentThread().getContextClassLoader().
					getResourceAsStream(xmlPath));
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		
		return result;
	}
	
	///////////////////////
	////PRIVATE METHODS////
	///////////////////////
}

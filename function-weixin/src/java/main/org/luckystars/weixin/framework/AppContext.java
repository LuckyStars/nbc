package org.luckystars.weixin.framework;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.luckystars.weixin.framework.config.xml.XmlAppConfigLoader;


/**
 * 应用全局上下文
 * @author xuechong
 */
public class AppContext implements Serializable{

	private static final long serialVersionUID = -5759981728727750618L;
	
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
		new XmlAppConfigLoader().loadIntoContext(context);
		return context;
	}

	
	///////////////////////
	////PRIVATE METHODS////
	///////////////////////
}

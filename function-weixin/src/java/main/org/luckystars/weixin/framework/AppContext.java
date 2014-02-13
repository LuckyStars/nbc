package org.luckystars.weixin.framework;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 应用全局上下文
 * @author xuechong
 */
public class AppContext implements Serializable{

	private static final long serialVersionUID = -5759981728727750618L;
	
	private static AppContext context ;
	
	private Map<String, Object> ctx = new HashMap<String, Object>();
	
	private AppContext(){
		
	}
	
	public AppContext getContext(){
		return context;
	}
	
	public Object get(String key){
		return ctx.get(key);
	}
	
	
	
	
	
	
}

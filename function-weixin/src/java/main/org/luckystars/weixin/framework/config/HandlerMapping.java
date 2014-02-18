package org.luckystars.weixin.framework.config;

public class HandlerMapping {
	
	public static final String SCOPE_PROTOTYPE = "prototype";
	public static final String SCOPE_SINGLETON = "singleton";
	
	/**单例或多例*/
	private final String scope;
	private final String className;
	
	/**
	 * 
	 * @param className
	 * @param scope
	 */
	public HandlerMapping( String className,String scope) {
		super();
		this.scope = scope;
		this.className = className;
	}
	//////////////////////////
	//////GETTERS&SETTERS////
	//////////////////////////
	public String getScope() {
		return scope;
	}
	public String getClassName() {
		return className;
	}
	public boolean isSingleton(){
		return this.scope !=null && scope.equalsIgnoreCase(SCOPE_PROTOTYPE);
	}
	
}

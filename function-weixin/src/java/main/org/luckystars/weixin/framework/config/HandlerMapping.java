package org.luckystars.weixin.framework.config;

public class HandlerMapping {

	/**单例或多例*/
	private String scope;
	private String className;
	
	//////////////////////////
	//////GETTERS&SETTERS////
	//////////////////////////
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public boolean isSingleton(){
		return this.scope !=null && scope.equalsIgnoreCase("prototype");
	}
	
}

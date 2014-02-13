package org.luckystars.weixin.framework.api;

import org.luckystars.weixin.framework.AppContext;

public abstract class AbstractContextLoader implements AppContextLoader{

	@Override
	public void loadIntoContext(AppContext ctx) {
		ctx.put(getId(), getValue());
	}
	
	abstract String getId();
	
	abstract Object getValue();
}

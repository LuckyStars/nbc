package org.luckystars.weixin.framework.api;

import org.luckystars.weixin.framework.AppContext;

/**
 * 加载内容到 Appcontext中
 * @author xuechong
 */
public interface AppContextLoader {
	void loadIntoContext(AppContext ctx);
}

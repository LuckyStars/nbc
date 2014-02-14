package org.luckystars.weixin.framework.api;

import org.luckystars.weixin.framework.config.HandlerMapping;

/**
 * 
 * @author xuechong
 */
public interface HandlerFactory {
	
	Handler build(HandlerMapping mapping);
	
}

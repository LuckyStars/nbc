package org.luckystars.weixin.framework.api;

import java.io.Serializable;

/**
 * 进入的消息
 * @author xuechong
 */
public interface IncomeMessage extends Serializable{
	public String get(String attr);
}

package org.luckystars.weixin.transfer.interfaces;

import org.luckystars.weixin.framework.api.WeixinView;

@SuppressWarnings("serial")
public abstract class XmlReply extends WeixinView{
	public abstract String toXmlString();
}

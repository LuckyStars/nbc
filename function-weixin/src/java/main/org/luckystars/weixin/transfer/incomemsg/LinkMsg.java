package org.luckystars.weixin.transfer.incomemsg;

@SuppressWarnings("serial")
public class LinkMsg extends WeixinMsg{
	
	/**
	 * 消息描述
	 * @return
	 * @author xuechong
	 */
	public String description(){
		return this.contents.get("Description");
	}
	
	/**
	 * 消息链接
	 * @return
	 * @author xuechong
	 */
	public String url(){
		return this.contents.get("Url");
	}
	
}

package org.luckystars.weixin.transfer.incomemsg;


@SuppressWarnings("serial")
public class TextMsg extends WeixinMsg{
	
	public String msgId(){
		return contents.get("MsgId");
	}
	
	public String content(){
		return this.contents.get("Content");
	}
	
}

package org.luckystars.weixin.transfer.msg;


@SuppressWarnings("serial")
public class TextMsg extends WeixinMsg{
	
	public String content(){
		return this.contents.get("Content");
	}
	
}

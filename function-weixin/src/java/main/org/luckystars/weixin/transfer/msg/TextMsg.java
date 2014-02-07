package org.luckystars.weixin.transfer.msg;


public class TextMsg extends WeixinMsg{
	
	public String content(){
		return this.contents.get("Content");
	}
	
}

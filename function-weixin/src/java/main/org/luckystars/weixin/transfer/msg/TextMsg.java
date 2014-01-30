package org.luckystars.weixin.transfer.msg;


public class TextMsg extends Msg{
	
	public String content(){
		return this.contents.get("Content");
	}
	
}

package org.luckystars.weixin.transfer.msg;

@SuppressWarnings("serial")
public class EventMsg extends WeixinMsg{

	public String getEventKey(){
		return this.contents.get("eventKey");
	}
	
	public String getEvent(){
		return this.contents.get("event");
	}
	
}

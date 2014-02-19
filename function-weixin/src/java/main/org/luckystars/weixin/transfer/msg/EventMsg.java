package org.luckystars.weixin.transfer.msg;

@SuppressWarnings("serial")
public class EventMsg extends WeixinMsg{

	@Override
	public String getSessionId() {
		return fromUserName();
	}

	public String getEventKey(){
		return this.contents.get("eventKey");
	}
	
	public String getEvent(){
		return this.contents.get("event");
	}
}

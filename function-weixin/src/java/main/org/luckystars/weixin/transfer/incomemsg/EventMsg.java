package org.luckystars.weixin.transfer.incomemsg;

@SuppressWarnings("serial")
public class EventMsg extends WeixinMsg{

	public String getEventKey(){
		return this.contents.get("EventKey");
	}
	
	public String getEvent(){
		return this.contents.get("Event");
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("fromUser:" + this.getFromUserName() + "\n");
		sb.append("toUser:" + this.getToUserName() + "\n");
		sb.append("sessionId:" + this.getSessionId() + "\n");
		sb.append("createTime:" + this.getCreateTime() + "\n");
		sb.append("msgType:" + this.getMsgType() + "\n");
		sb.append("event:" + this.getEvent() + "\n");
		sb.append("eventKey:" + this.getEventKey() + "\n");
		return sb.toString();
	}
}

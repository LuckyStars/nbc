package org.luckystars.weixin.transfer.incomemsg;


@SuppressWarnings("serial")
public class TextMsg extends WeixinMsg{
	
	public String msgId(){
		return contents.get("MsgId");
	}
	
	public String content(){
		return this.contents.get("Content");
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("");
		sb.append("fromUser:" + this.getFromUserName() + "\n");
		sb.append("toUser:" + this.getToUserName() + "\n");
		sb.append("sessionId:" + this.getSessionId() + "\n");
		sb.append("createTime:" + this.getCreateTime() + "\n");
		sb.append("msgType:" + this.getMsgType() + "\n");
		sb.append("text:" + this.content() + "\n");
		sb.append("msgId:" + this.msgId() + "\n");
		
		return sb.toString();
	}
}

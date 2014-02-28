package org.luckystars.weixin.transfer.incomemsg;

@SuppressWarnings("serial")
public class LinkMsg extends WeixinMsg{
	
	/**
	 * 消息描述
	 * @return
	 * @author xuechong
	 */
	public String getDescription(){
		return this.contents.get("Description");
	}
	
	/**
	 * 消息链接
	 * @return
	 * @author xuechong
	 */
	public String getUrl(){
		return this.contents.get("Url");
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("");
		sb.append("fromUser:" + this.getFromUserName() + "\n");
		sb.append("toUser:" + this.getToUserName() + "\n");
		sb.append("sessionId:" + this.getSessionId() + "\n");
		sb.append("createTime:" + this.getCreateTime() + "\n");
		sb.append("msgType:" + this.getMsgType() + "\n");
		sb.append("url:" + this.getUrl() + "\n");
		sb.append("description:" + this.getDescription() + "\n");
		return sb.toString();
	}
}

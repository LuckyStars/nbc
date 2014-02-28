package org.luckystars.weixin.transfer.incomemsg;

@SuppressWarnings("serial")
public class ImageMsg extends WeixinMsg{

	/**
	 * 图片地址
	 * @return
	 * @author xuechong
	 */
	public String getPicUrl(){
		return this.contents.get("PicUrl");
	}
	
	public String msgId(){
		return contents.get("MsgId");
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("");
		sb.append("fromUser:" + this.getFromUserName() + "\n");
		sb.append("toUser:" + this.getToUserName() + "\n");
		sb.append("sessionId:" + this.getSessionId() + "\n");
		sb.append("createTime:" + this.getCreateTime() + "\n");
		sb.append("msgType:" + this.getMsgType() + "\n");
		sb.append("picUrl:" + this.getPicUrl() + "\n");
		sb.append("msgId:" + this.msgId() + "\n");
		return sb.toString();
	}
}

package org.luckystars.weixin.transfer.incomemsg;

@SuppressWarnings("serial")
public class EventMsg extends WeixinMsg{
	
	public static final String EVENT_TYPE_VIEW="view";
	
	public static final String EVENT_TYPE_ClICK="click";
	/**
	 * 扫描带场景值二维码事件<br>
	 * 触发条件：用户已关注该公众帐号，扫描了带场景值的二维码。<br>
	 * EventKey	 事件KEY值，是一个32位无符号整数。
	 */
	public static final String EVENT_TYPE_SCAN="scan";
	
	/**
	 * 通过带场景值二维码订阅事件<br>
	 * 触发条件：用户通过带场景值二维码订阅公众号。<br>
	 * EventKey	 事件KEY值，qrscene_为前缀，后面为二维码的场景值。
	 */
	public static final String EVENT_TYPE_SUBSCRIBE="subscribe";


	public String getEventKey(){
		return this.contents.get("EventKey");
	}
	
	public String getEvent(){
		return this.contents.get("Event");
	}
	/**
	 * 二维码的ticket，可用来换取二维码图片<br>
	 * 用户扫描带场景值二维码时，可能推送以下两种事件：<br>
	 * 如果用户还未关注公众号，则用户可以关注公众号，关注后微信会将带场景值关注事件推送给开发者。<br>
	 * 如果用户已经关注公众号，则微信会将带场景值扫描事件推送给开发者。<br>
	 * @return
	 * @author xuechong
	 */
	public String getTicket(){
		return this.contents.get("Ticket");
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

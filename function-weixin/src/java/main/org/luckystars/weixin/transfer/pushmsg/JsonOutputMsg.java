package org.luckystars.weixin.transfer.pushmsg;

import org.luckystars.weixin.framework.api.OutputMsg;

@SuppressWarnings("serial")
public abstract class JsonOutputMsg implements OutputMsg{
	
	public static final String MSG_TYPE_TEXT = "text";
	
	public static final String MSG_TYPE_IMAGE = "image";
	/***语音*/
	public static final String MSG_TYPE_VOICE = "voice";
	public static final String MSG_TYPE_VIDEO = "video";
	/**音乐消息**/
	public static final String MSG_TYPE_MUSIC = "music";
	/**图文消息**/
	public static final String MSG_TYPE_NEWS = "news";
	
	@Override
	public String toMsgString() {
		return toJsonString();
	}
	
	public abstract String toJsonString();
	
	public abstract String getTouser();
	
	public abstract void setTouser(String touser);
}

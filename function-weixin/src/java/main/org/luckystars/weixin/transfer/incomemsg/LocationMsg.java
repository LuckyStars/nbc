package org.luckystars.weixin.transfer.incomemsg;

@SuppressWarnings("serial")
public class LocationMsg extends WeixinMsg{
	
	public String getLocationX(){
		return this.contents.get("Location_X");
	}
	
	public String getLocationY(){
		return this.contents.get("Location_Y");
	}
	/**
	 * 地图缩放大小
	 * @return
	 * @author xuechong
	 */
	public String getScale(){
		return this.contents.get("Scale");
	}
	
	/**
	 * 地理位置信息
	 * @return
	 * @author xuechong
	 */
	public String getLabel(){
		return this.contents.get("Label");
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("");
		sb.append("fromUser:" + this.getFromUserName() + "\n");
		sb.append("toUser:" + this.getToUserName() + "\n");
		sb.append("sessionId:" + this.getSessionId() + "\n");
		sb.append("createTime:" + this.getCreateTime() + "\n");
		sb.append("msgType:" + this.getMsgType() + "\n");
		sb.append("label:" + this.getLabel() + "\n");
		sb.append("LocationX:" + this.getLocationX() + "\n");
		sb.append("LocationY:" + this.getLocationY() + "\n");
		return sb.toString();
	}
}

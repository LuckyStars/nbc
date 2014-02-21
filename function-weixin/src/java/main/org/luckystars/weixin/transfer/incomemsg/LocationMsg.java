package org.luckystars.weixin.transfer.incomemsg;

@SuppressWarnings("serial")
public class LocationMsg extends WeixinMsg{
	
	public String locationX(){
		return this.contents.get("Location_X");
	}
	
	public String locationY(){
		return this.contents.get("Location_Y");
	}
	/**
	 * 地图缩放大小
	 * @return
	 * @author xuechong
	 */
	public String scale(){
		return this.contents.get("Scale");
	}
	
	/**
	 * 地理位置信息
	 * @return
	 * @author xuechong
	 */
	public String label(){
		return this.contents.get("Label");
	}
}

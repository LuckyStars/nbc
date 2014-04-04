package org.luckystars.weixin.transfer.pushmsg;

import org.luckystars.weixin.utils.json.GsonUtil;

@SuppressWarnings("serial")
public class ImageMsg extends JsonOutputMsg{

	private String touser;
	private String msgtype = JsonOutputMsg.MSG_TYPE_IMAGE;
	private Image media_id;//为了gson转化方便
	
	
	@Override
	public String getMsgType() {
		return this.msgtype;
	}
	@Override
	public String toJsonString() {
		return GsonUtil.gson.toJson(this);
	}
	@Override
	public String getTouser() {
		return this.touser;
	}
	@Override
	public void setTouser(String touser) {
		this.touser = touser;
	}
	public Image getMedia_id() {
		return media_id;
	}
	public void setMedia_id(Image media_id) {
		this.media_id = media_id;
	}
	public void putMedia_id(String mId){
		this.media_id = new Image(mId);
	}

	public static class Image{
		private String media_id;

		public Image(){super();}
		public Image(String media){this.media_id = media;}
		public String getMedia_id() {
			return media_id;
		}
		public void setMedia_id(String media_id) {
			this.media_id = media_id;
		}
	}
	
}

package org.luckystars.weixin.transfer.msg;

@SuppressWarnings("serial")
public class ImageMsg extends WeixinMsg{

	public String picUrl(){
		return this.contents.get("PicUrl");
	}
}

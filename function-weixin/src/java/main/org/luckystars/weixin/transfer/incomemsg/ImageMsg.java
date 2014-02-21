package org.luckystars.weixin.transfer.incomemsg;

@SuppressWarnings("serial")
public class ImageMsg extends WeixinMsg{

	/**
	 * 图片地址
	 * @return
	 * @author xuechong
	 */
	public String picUrl(){
		return this.contents.get("PicUrl");
	}
}

package org.luckystars.weixin.transfer.msg;

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

package org.luckystars.weixin.transfer.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.luckystars.weixin.transfer.interfaces.XmlReply;

@SuppressWarnings("serial")
public class NewsView implements XmlReply{

	private String fromUserName;
	private String toUserName;
	private String createTime;
	private String msgType = "news";
	/**图文消息个数，限制为10条以内***/
	private String articleCount;
	
	private List<NewsView.Item> items = new ArrayList<NewsView.Item>(10);
	
	@Override
	public String toWeixinStr() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toXmlString() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static class Item implements Serializable{
		private String title;
		private String description;
		private String picUrl;
		private String url;
	}
}



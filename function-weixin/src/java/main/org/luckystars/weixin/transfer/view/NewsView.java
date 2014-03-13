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
		return toXmlString();
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
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getPicUrl() {
			return picUrl;
		}
		public void setPicUrl(String picUrl) {
			this.picUrl = picUrl;
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
	}
	//////////////////
	
	public String getFromUserName() {
		return fromUserName;
	}
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}
	public String getToUserName() {
		return toUserName;
	}
	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public List<NewsView.Item> getItems() {
		return items;
	}
	public void setItems(List<NewsView.Item> items) {
		this.items = items;
	}
	public String getArticleCount() {
		return articleCount;
	}
}



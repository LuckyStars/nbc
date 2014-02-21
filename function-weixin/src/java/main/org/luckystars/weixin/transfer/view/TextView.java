package org.luckystars.weixin.transfer.view;

import org.luckystars.weixin.transfer.interfaces.XmlReply;

/**
 * 文本类型数据
 * @author xuechong
 */
@SuppressWarnings("serial")
/*
<xml>
<ToUserName><![CDATA[toUser]]></ToUserName>
<FromUserName><![CDATA[fromUser]]></FromUserName>
<CreateTime>12345678</CreateTime>
<MsgType><![CDATA[text]]></MsgType>
<Content><![CDATA[content]]></Content>
<FuncFlag>0</FuncFlag>
</xml>  
private static final String xml = "<xml>"+
					"<ToUserName><![CDATA[${toUser}]]></ToUserName>"+
					"<FromUserName><![CDATA[${fromUser}]]></FromUserName>"+
					"<CreateTime>${12345678}</CreateTime>"+
					"<MsgType><![CDATA[text]]></MsgType>"+
					"<Content><![CDATA[${content}]]></Content>"+
					"<FuncFlag>0</FuncFlag>"+
					"</xml>";
*/
public class TextView implements XmlReply {
	
	private String toUserName;
	private String fromUserName;
	/**消息创建时间 （整型）*/
	private String createTime;
	private String content = "";
	private String funcFlag="0";
	
	
	public TextView(String content,String toUserName,String serverName){
		this.content = content;
		this.toUserName = toUserName;
		this.fromUserName = serverName;
		this.createTime = String.valueOf(System.currentTimeMillis());
	}
	
	@Override
	public String toWeixinStr() {
		return toXmlString();
	}

	@Override
	public String toXmlString() {
		StringBuilder result = new StringBuilder();
		
		result.append("<xml>");
		
		result.append("<ToUserName><![CDATA[");
		result.append(this.toUserName);
		result.append("]]></ToUserName>");
		
		result.append("<FromUserName><![CDATA[");
		result.append(this.fromUserName);
		result.append("]]></FromUserName>");
		
		result.append("<CreateTime>");
		result.append(this.createTime);
		result.append("</CreateTime>");
		
		result.append("<MsgType><![CDATA[text]]></MsgType>");
		
		result.append("<Content><![CDATA[");
		result.append(this.content);
		result.append("]]></Content>");
		
		result.append("<FuncFlag>");
		result.append(this.funcFlag);
		result.append("</FuncFlag>");
		
		result.append("</xml>");
		
		return result.toString();
	}
	
	
}

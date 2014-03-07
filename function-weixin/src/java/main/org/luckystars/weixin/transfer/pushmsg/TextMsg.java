package org.luckystars.weixin.transfer.pushmsg;

import org.luckystars.weixin.utils.json.GsonUtil;

@SuppressWarnings("serial")
public class TextMsg extends JsonOutputMsg{

	private String touser;
	private String msgtype = JsonOutputMsg.MSG_TYPE_TEXT;
	private Text text;
	
	@Override
	public String getMsgType() {
		return msgtype;
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
	
	public Text getText() {
		return text;
	}
	public void setText(Text text) {
		this.text = text;
	}
	public void putText(String text){
		this.text = new Text(text);
	}

	public static class Text{
		public Text(){super();};
		public Text(String content) {
			super();
			this.content = content;
		}
		private String content;
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
	}
	
	public static void main(String[] args) {
		TextMsg t = new TextMsg();
		t.setText(new Text("aasdsadasd"));
		t.setTouser("touserId11234");
		System.out.println(t.toJsonString());
	}

	
}


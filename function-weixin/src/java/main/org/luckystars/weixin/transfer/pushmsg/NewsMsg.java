package org.luckystars.weixin.transfer.pushmsg;

import org.luckystars.weixin.utils.json.GsonUtil;

@SuppressWarnings("serial")
public class NewsMsg extends JsonOutputMsg{

	private String touser;
	
	@Override
	public String getMsgType() {
		return MSG_TYPE_NEWS;
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

}

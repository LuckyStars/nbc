package com.nbcedu.function.weixin.transfer.interfaces;

import com.nbcedu.function.weixin.transfer.MsgContext;

public interface MsgHandler {
	public String handle(MsgContext msg);
}

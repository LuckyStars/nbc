package org.luckystars.weixin.framework.api;

import java.io.Serializable;

public interface OutputMsg extends Serializable {
	
	String toMsgString();
	
	String getMsgType();
}

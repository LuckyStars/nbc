package org.luckystars.weixin.transfer.view;

import java.io.Serializable;
import java.util.List;

import org.luckystars.weixin.transfer.incomemsg.WeixinMsg;

public interface NewsViewBuilder {

	NewsView build(List<? extends Serializable> contents, WeixinMsg incomeMsg);

}

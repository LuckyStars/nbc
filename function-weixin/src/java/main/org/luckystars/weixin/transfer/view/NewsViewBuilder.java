package org.luckystars.weixin.transfer.view;

import java.io.Serializable;
import java.util.List;

public interface NewsViewBuilder {
	
	public NewsView build(List<? extends Serializable> contents);

}

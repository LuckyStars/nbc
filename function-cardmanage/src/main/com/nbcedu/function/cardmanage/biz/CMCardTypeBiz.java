package com.nbcedu.function.cardmanage.biz;

import java.util.List;

import com.nbcedu.function.cardmanage.core.biz.BaseBiz;
import com.nbcedu.function.cardmanage.model.CMCardType;

public interface CMCardTypeBiz extends BaseBiz<CMCardType>{
	
	public List<CMCardType> findByType(int type);
}

package com.nbcedu.function.schoolmaster2.biz;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.nbcedu.function.schoolmaster2.core.biz.BaseBiz;
import com.nbcedu.function.schoolmaster2.data.model.TSm2Disscus;

public interface SM2DisscusBiz extends BaseBiz<TSm2Disscus> {

	public List<TSm2Disscus> findByProgIds(Collection<String> progIds);
	
	public List<TSm2Disscus> findByProgIds(Collection<String> progIds,Integer size);
	
	public Map<String, List<TSm2Disscus>> findMapByProgIds(Collection<String> progIds);
}

package com.nbcedu.function.schoolmaster2.biz.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.xwork.StringUtils;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;
import com.nbcedu.function.schoolmaster2.biz.SM2DisscusBiz;
import com.nbcedu.function.schoolmaster2.core.biz.impl.BaseBizImpl;
import com.nbcedu.function.schoolmaster2.dao.SM2DisscusDao;
import com.nbcedu.function.schoolmaster2.data.model.TSm2Disscus;

public class SM2DisscusBizImpl extends BaseBizImpl<TSm2Disscus> implements SM2DisscusBiz{
	private SM2DisscusDao disDao;

	@SuppressWarnings("unchecked")
	@Override
	public List<TSm2Disscus> findByProgIds(Collection<String> progIds) {
		if(CollectionUtils.isEmpty(progIds)){
			return Lists.newArrayList();
		}
		String inStr = StringUtils.join(progIds, ",");
		StringBuilder hql = new StringBuilder("");
		hql.append("FROM TSm2Disscus d WHERE d.progressId in (");
		hql.append(inStr);
		hql.append(")");
		return this.disDao.createQuery(hql.toString()).list();
	}
	
	@Override
	public Map<String, List<TSm2Disscus>> findMapByProgIds(
			Collection<String> progIds) {
		// TODO Auto-generated method stub
		return null;
	}
	///////////////////////////
	//////getters&setters//////
	///////////////////////////
	public void setDisDao(SM2DisscusDao disDao) {
		super.setDao(disDao);
		this.disDao = disDao;
	}
	
}

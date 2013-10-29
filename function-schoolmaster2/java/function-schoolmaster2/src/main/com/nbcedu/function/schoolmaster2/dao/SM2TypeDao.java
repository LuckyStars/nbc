package com.nbcedu.function.schoolmaster2.dao;

import java.util.List;

import com.nbcedu.function.schoolmaster2.core.dao.BaseDAO;
import com.nbcedu.function.schoolmaster2.data.model.TSm2Type;

public interface SM2TypeDao extends BaseDAO<TSm2Type>{
	
	public List<TSm2Type> findByModUseId(String moduleId, String userId,int mold) ;

	public List<TSm2Type> findByUserId( String userId);
}

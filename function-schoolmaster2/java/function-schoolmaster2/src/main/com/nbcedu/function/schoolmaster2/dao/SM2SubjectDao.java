package com.nbcedu.function.schoolmaster2.dao;

import java.util.List;

import com.nbcedu.function.schoolmaster2.core.dao.BaseDAO;
import com.nbcedu.function.schoolmaster2.core.exception.DBException;
import com.nbcedu.function.schoolmaster2.core.pager.PagerModel;
import com.nbcedu.function.schoolmaster2.data.model.TSm2Subject;
import com.nbcedu.function.schoolmaster2.vo.SubjectVo;

public interface SM2SubjectDao extends BaseDAO<TSm2Subject>{
	public PagerModel findByExceuteUserId(SubjectVo subject);
	
	public List<TSm2Subject> findByModuleIdExceuteUserId(String moduleId,
			String exceuteUserId);

	PagerModel findAllTrans(String userId, String typeId) throws DBException;
}

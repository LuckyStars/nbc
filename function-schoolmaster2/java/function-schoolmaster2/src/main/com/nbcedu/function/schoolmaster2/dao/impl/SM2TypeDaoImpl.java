package com.nbcedu.function.schoolmaster2.dao.impl;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;

import com.nbcedu.function.schoolmaster2.core.dao.impl.SimpleBaseDAOImpl;
import com.nbcedu.function.schoolmaster2.dao.SM2TypeDao;
import com.nbcedu.function.schoolmaster2.data.model.TSm2Type;

public class SM2TypeDaoImpl extends SimpleBaseDAOImpl<TSm2Type> implements SM2TypeDao {

	@SuppressWarnings("unchecked")
	public List<TSm2Type> findByModUseId(String moduleId, String userId,int mold) {
		Criteria c = this.getSession().createCriteria(TSm2Type.class);
		c.add(Expression.eq("moduleId",moduleId ));
		Criteria addc = c.createCriteria("typeUsers");
		addc.add(Expression.eq("userId", userId));
		return c.list();
	}
}

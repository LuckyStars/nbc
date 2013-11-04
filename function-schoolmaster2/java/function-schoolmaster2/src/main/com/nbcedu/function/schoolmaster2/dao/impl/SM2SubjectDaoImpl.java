package com.nbcedu.function.schoolmaster2.dao.impl;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Expression;

import com.nbcedu.function.schoolmaster2.core.dao.impl.SimpleBaseDAOImpl;
import com.nbcedu.function.schoolmaster2.core.pager.PagerModel;
import com.nbcedu.function.schoolmaster2.core.util.StringUtil;
import com.nbcedu.function.schoolmaster2.dao.SM2SubjectDao;
import com.nbcedu.function.schoolmaster2.data.model.TSm2Subject;
import com.nbcedu.function.schoolmaster2.vo.SubjectVo;

public class SM2SubjectDaoImpl extends SimpleBaseDAOImpl<TSm2Subject> implements SM2SubjectDao {

	public PagerModel findByExceuteUserId(SubjectVo subject){
		Criteria c = this.getSession().createCriteria(TSm2Subject.class);
		c.add(Expression.eq("moduleId",subject.getModuleId() ));
		if(!StringUtil.isEmpty(subject.getTitle())){
			c.add(Expression.like("title","%"+subject.getTitle().trim()+"%" ));
		}
		if(subject.getBeginDate()!=null&&!StringUtil.isEmpty(subject.getBeginDate().toString())){
			
		}
		Criteria addc = c.createCriteria("excuteUsers");
		addc.add(Expression.eq("userId", subject.getExcuteUserId()));

		return this.searchPaginated(c);
	}

	@Override
	public List<TSm2Subject> findByModuleIdExceuteUserId(String moduleId,
			String exceuteUserId) {
		StringBuilder hql = new StringBuilder("");
		hql.append("FROM TSm2Subject sub WHERE sub.moduleId =? ");
		hql.append("AND sub.id in (SELECT subjectId FROM TSm2SubjectUser m WHERE m.userId = ?) ");
		hql.append("ORDER BY sub.createTime DESC");
		return this.find(hql.toString(), new Object[]{moduleId,exceuteUserId});
	}

}

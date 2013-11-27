package com.nbcedu.function.schoolmaster2.dao.impl;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.sql.Types;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.nbcedu.function.schoolmaster2.core.comment.Constants;
import com.nbcedu.function.schoolmaster2.core.dao.impl.SimpleBaseDAOImpl;
import com.nbcedu.function.schoolmaster2.core.exception.DBException;
import com.nbcedu.function.schoolmaster2.core.pager.PagerModel;
import com.nbcedu.function.schoolmaster2.core.pager.SystemContext;
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
	
	@SuppressWarnings("unchecked")
	@Override
	public PagerModel findAllTrans(SubjectVo subject,String curUserId) throws DBException{
		StringBuffer buff = new StringBuffer();
		StringBuffer buff1 = new StringBuffer();
		buff.append(" from t_sm2_subject s,t_sm2_subject_trans t where t.sub_id=s.id ");
		buff.append(" and t.user_uid='"+curUserId+"'");
		if(!StringUtil.isEmpty(subject.getTitle())){
			buff.append(" and s.title like %");
			buff.append(subject.getTitle());
			buff.append("%");
		}
		buff1 = buff;
		buff.insert(0, "select s.id,s.createTime,s.title,s.moduleId,s.createrName,s.departmentName" );
		buff1.insert(0, "select count(s.id) ");
		final String sql = buff.toString();
				
		final String sql1 = buff1.toString();
		List result = null;
		result = (List) getHibernateTemplate().executeWithNativeSession(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
					return session.createSQLQuery(sql)
					 .addScalar("id", Hibernate.STRING)
					 .addScalar("createTime", Hibernate.DATE)
					 .addScalar("title",Hibernate.STRING)
					 .addScalar("moduleId", Hibernate.STRING)
					 .addScalar("createrName", Hibernate.STRING)
					 .addScalar("departmentName", Hibernate.STRING)
					 .setResultTransformer(Transformers.aliasToBean(TSm2Subject.class))
					 .setFirstResult(SystemContext.getOffset())
					 .setMaxResults(SystemContext.getPagesize()).list();
			}
		});
		PagerModel pagerModel = new PagerModel();
		// 查询总的记录数
		int total = this.getRecordCountBySQL(sql1);
		// 显示结果
		pagerModel.setTotal(total);
		pagerModel.setDatas(result);

		return pagerModel;
	}
}

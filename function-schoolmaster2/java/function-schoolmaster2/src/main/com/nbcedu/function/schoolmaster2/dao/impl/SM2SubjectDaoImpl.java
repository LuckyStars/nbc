package com.nbcedu.function.schoolmaster2.dao.impl;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.sql.Types;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.transform.ResultTransformer;
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
	public PagerModel findAllTrans( String userId,String typeId) throws DBException{
		final String sql = "select s.* from t_sm2_subject s,t_sm2_subject_trans t " +
					"where t.sub_id=s.id and t.user_uid='"+userId+"'";
		final String sql1 = "select count(s.id) from t_sm2_subject s,t_sm2_subject_trans t " +
					"where t.sub_id=s.id and t.user_uid='"+userId+"'";
		List result = null;
		result = (List) getHibernateTemplate().executeWithNativeSession(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
					return session.createSQLQuery(sql).
					setFirstResult(SystemContext.getOffset()).
					setMaxResults(SystemContext.getPagesize()).list();
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

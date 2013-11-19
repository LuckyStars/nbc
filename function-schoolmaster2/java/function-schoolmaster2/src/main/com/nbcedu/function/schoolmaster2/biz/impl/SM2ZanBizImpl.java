package com.nbcedu.function.schoolmaster2.biz.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.xwork.StringUtils;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.nbcedu.function.schoolmaster2.biz.SM2ZanBiz;
import com.nbcedu.function.schoolmaster2.core.biz.impl.BaseBizImpl;
import com.nbcedu.function.schoolmaster2.dao.SM2ZanDao;
import com.nbcedu.function.schoolmaster2.data.model.Sm2Zan;
import com.nbcedu.function.schoolmaster2.utils.Utils;
import com.nbcedu.function.schoolmaster2.vo.ZanVo;

public class SM2ZanBizImpl extends BaseBizImpl<Sm2Zan> implements SM2ZanBiz {

	private SM2ZanDao zanDao;

	@Override
	@SuppressWarnings("serial")
	public Sm2Zan findByProgUid(final String progId, final String uid) {
		return (Sm2Zan) this.zanDao.createCriteria(
				Restrictions.allEq(
					new HashMap<String, String>(2) {{
						put("progressId", progId);
						put("userUid", uid);
					}}
				)
			).uniqueResult();
	}

	@Override
	public Object addOrUpdate(Sm2Zan zan) {
		Sm2Zan temp = this.findByProgUid(zan.getProgressId(), zan.getUserUid());
		return temp == null ? this.add(zan) : temp;
	}

	////////////////////////
	///getters&setters/////
	/////////////////////
	public void setZanDao(SM2ZanDao zanDao) {
		super.setDao(zanDao);
		this.zanDao = zanDao;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ZanVo> findByProg(String progId,Integer size) {
		StringBuilder hql = new StringBuilder("");
		hql.append("SELECT z.userName,z.createTime ");
		hql.append("FROM Sm2Zan z ");
		hql.append("WHERE z.progressId=? ");
		hql.append("ORDER BY z.createTime DESC ");
		Query q = this.zanDao.createQuery(hql.toString(),new Object[]{progId});
		if(size!=null){
			q.setMaxResults(size);
		}
		List<Object[]> resultSet = q.list();
		
		return resultSet==null?
				new ArrayList<ZanVo>(1):
				Lists.transform(resultSet,new Function<Object[], ZanVo>() {
					@Override
					public ZanVo apply(Object[] input) {
						return new ZanVo(input[0].toString(), (Date) input[1]);
					}
				});
	}

	@Override
	public void removeByProg(String progId) {
		this.zanDao.createQuery("delete from Sm2Zan where progressId=?", progId).executeUpdate();
	}
	
	@Override
	public void removeByUserProg(String progId) {
		this.zanDao.createQuery("DELETE FROM Sm2Zan z WHERE z.progressId=? AND z.userUid=?",
				new Object[]{progId,Utils.curUserUid()}).executeUpdate();
	}
	
	@Override
	public String findSubIdByZan(String zanId) {
		StringBuilder sql =new StringBuilder("");
		sql.append("SELECT steps.subjectId ");
		sql.append("FROM t_sm2_step steps,( ");
			sql.append("SELECT stepId ");
			sql.append("FROM t_sm2_progress,( ");
				sql.append("SELECT progress_id ");
				sql.append("FROM t_sm2_zan ");
				sql.append("WHERE id=? ");
				sql.append(") zan ");
			sql.append("WHERE t_sm2_progress.id = zan.progress_id ");
		sql.append(") stepId ");
		sql.append("WHERE steps.id = stepId.stepId ");
		Query q = this.zanDao.createSqlQuery(sql.toString(), zanId);
		Object result = q.uniqueResult();
		return result==null?"":result.toString().trim();
	}
}

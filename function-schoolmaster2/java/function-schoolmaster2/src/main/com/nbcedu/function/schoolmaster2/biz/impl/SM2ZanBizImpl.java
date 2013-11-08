package com.nbcedu.function.schoolmaster2.biz.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.nbcedu.function.schoolmaster2.biz.SM2ZanBiz;
import com.nbcedu.function.schoolmaster2.core.biz.impl.BaseBizImpl;
import com.nbcedu.function.schoolmaster2.dao.SM2ZanDao;
import com.nbcedu.function.schoolmaster2.data.model.Sm2Zan;
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
	public void deleteByProg(String progId) {
		this.zanDao.createQuery("delete Sm2Zan where progressId=?", progId);
		
	}

}

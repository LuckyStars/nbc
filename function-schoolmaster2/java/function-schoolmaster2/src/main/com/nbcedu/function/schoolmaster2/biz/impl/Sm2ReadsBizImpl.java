package com.nbcedu.function.schoolmaster2.biz.impl;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.xwork.StringUtils;
import org.hibernate.Query;
import org.springframework.util.CollectionUtils;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.nbcedu.function.schoolmaster2.biz.Sm2ReadsBiz;
import com.nbcedu.function.schoolmaster2.core.biz.impl.BaseBizImpl;
import com.nbcedu.function.schoolmaster2.dao.Sm2ReadsDao;
import com.nbcedu.function.schoolmaster2.data.model.SM2Reads;
import com.nbcedu.function.schoolmaster2.data.model.TSm2Progress;
import com.nbcedu.function.schoolmaster2.utils.Utils;
import com.nbcedu.function.schoolmaster2.vo.ReadsVo;

public class Sm2ReadsBizImpl extends BaseBizImpl<SM2Reads> implements Sm2ReadsBiz{

	private Sm2ReadsDao readsDao;
	
	@Override
	@SuppressWarnings("unchecked")
	public synchronized void addByStep(String stepId, String userId) {
		
		StringBuilder hql = new StringBuilder("FROM TSm2Progress p WHERE p.stepId = ?");
		Query proq = this.readsDao.createQuery(hql.toString(),stepId);
		List<TSm2Progress> progList = proq.list();
		
		if(!CollectionUtils.isEmpty(progList)){
			
			Query transQuery = this.readsDao.getNamedQuery("trans_query");
			transQuery.setString("stepId", stepId);
			transQuery.setString("uid", userId);
			Object result = transQuery.uniqueResult();
			boolean isTrans = result!=null
				&&Integer.valueOf(transQuery.uniqueResult().toString())>0;
			
			String inStr = StringUtils.join(
					Lists.transform(progList, 
							new Function<TSm2Progress, String>() {
								@Override
								public String apply(TSm2Progress input) {
									return "'"+input.getId() + "'";
								}
							}),
						",");
			
			Query delete = this.readsDao.createQuery(
					"DELETE FROM SM2Reads r WHERE r.progressId in ("+inStr+") AND userUid=:uid");
			delete.setString("uid", userId);
			delete.executeUpdate();
			
			for (TSm2Progress pro : progList) {
				SM2Reads read = new SM2Reads();
				read.setCreateTime(new Date());
				read.setProgressId(pro.getId());
				read.setUserName(Utils.curUserName());
				read.setUserUid(Utils.curUserUid());
				read.setTrans(isTrans?1:0);
				this.readsDao.save(read);
			}
			
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ReadsVo> findByProg(String progId, Integer size) {
		StringBuilder hql = new StringBuilder("");
		hql.append("SELECT r.userName,r.createTime,r.trans ");
		hql.append("FROM SM2Reads r ");
		hql.append("WHERE r.progressId=? ");
		Query q = this.readsDao.createQuery(hql.toString(), progId);
		if(size!=null){
			q.setMaxResults(size);
		}
		List<Object[]> resultSet = q.list();
		if(!CollectionUtils.isEmpty(resultSet)){
			return Lists.transform(resultSet, new Function<Object[], ReadsVo>() {
				@Override
				public ReadsVo apply(Object[] input) {
					return new ReadsVo(
							input[0].toString(), 
							(Date)input[1], 
							Integer.valueOf(input[2].toString()));
				}
			});
		}
		return new LinkedList<ReadsVo>();
	}
	
	/////////////////////////
	/////getters&setters//////
	/////////////////////////
	public void setReadsDao(Sm2ReadsDao readsDao) {
		this.readsDao = readsDao;
	}

}

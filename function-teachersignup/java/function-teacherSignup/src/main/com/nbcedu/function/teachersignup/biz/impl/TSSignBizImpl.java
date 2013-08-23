package com.nbcedu.function.teachersignup.biz.impl;

import static org.springframework.util.CollectionUtils.isEmpty;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;
import org.apache.commons.lang.xwork.StringUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.nbcedu.function.teachersignup.biz.TSSignBiz;
import com.nbcedu.function.teachersignup.core.biz.impl.BaseBizImpl;
import com.nbcedu.function.teachersignup.core.pager.PagerModel;
import com.nbcedu.function.teachersignup.dao.TSActivityDao;
import com.nbcedu.function.teachersignup.dao.TSRewardDao;
import com.nbcedu.function.teachersignup.dao.TSSignDao;
import com.nbcedu.function.teachersignup.model.TSActivity;
import com.nbcedu.function.teachersignup.model.TSReward;
import com.nbcedu.function.teachersignup.model.TSSign;
import com.nbcedu.function.teachersignup.model.TSSubject;
import com.nbcedu.function.teachersignup.vo.TSSignVo;
import com.nbcedu.function.teachersignup.vo.TSUser;

public class TSSignBizImpl extends BaseBizImpl<TSSign> implements TSSignBiz{
	private TSSignDao signDao;
	private TSActivityDao actDao;
	private TSRewardDao rewDao;
	
	public void setSignDao(TSSignDao signDao) {
		super.setDao(signDao);
		this.signDao = signDao;
	}
	
	@Override
	public void addNewSign(TSUser tsUser, String[] subjectIds) {
		if(subjectIds!=null&&subjectIds.length>0){
			for (String subId : subjectIds) {
				TSSign sign = new TSSign();
				sign.setSubjectId(subId);
				sign.setUserName(tsUser.getUserName());
				sign.setUserUid(tsUser.getUserUid());
				this.signDao.save(sign);
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public PagerModel pageByAct(String actId,String subjectId,String rewId,String userName) {
		TSActivity act = this.actDao.findUniqueBy("id", actId);
		ArrayList<Criterion> cris = new ArrayList<Criterion>(4);

		if(act!=null){
			Set<TSSubject> subSet = act.getSubjects();
			List<String> subIdList = new ArrayList<String>();
			for (TSSubject sub : subSet) {
				subIdList.add(sub.getId());
			}
			cris.add(Restrictions.in("subjectId", subIdList));
		}
		
		if(StringUtils.isNotBlank(subjectId)){
			cris.add(Restrictions.eq("subjectId", subjectId));
		}
		
		if(StringUtils.isNotBlank(rewId)){
			cris.add(Restrictions.eq("rewardId", rewId));
		}
		
		if(StringUtils.isNotBlank(userName)){
			cris.add(Restrictions.ilike("userName", userName, MatchMode.ANYWHERE));
		}
		
		PagerModel pm = this.signDao.searchPaginated(
				this.signDao.createCriteria(cris.toArray(new Criterion[1]))
		);
		
		List<TSSign> signList = pm.getDatas();
		
		if(isEmpty(signList)){
			return pm;
		}else{
			pm.setDatas(
				TSSignVo.Factory.build(
					signList, 
					new ArrayList<TSSubject>(act.getSubjects()), 
					isEmpty(act.getRewards())?null:new ArrayList<TSReward>(act.getRewards())
				)
			);
			return pm;
		}
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<TSSign> findAllByUidActId(String uid, String actId) {
		StringBuilder hql = new StringBuilder("FROM TSSign si ")
								.append("WHERE si.userUid=? ")
								.append("AND si.subjectId in ")
								.append("(SELECT sub.id FROM TSSubject sub ")
								.append("WHERE sub.activityId=?)");
		return this.signDao.createQuery(hql.toString(),uid,actId).list();
	}
	
	@Override
	public void addRew(String signId, String rewId) {
		TSSign sign = this.findById(signId);
		if(sign!=null){
			TSReward rew = this.rewDao.findUniqueBy("id", rewId);
			sign.setRewardId(rew==null?"":rewId);
			this.signDao.update(sign);
		}
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<TSSign> findByActId(String actId) {
		StringBuilder hql = new StringBuilder("FROM TSSign si ")
		.append("WHERE si.subjectId in ")
		.append("(SELECT sub.id FROM TSSubject sub ")
		.append("WHERE sub.activityId=?)");
		return this.signDao.createQuery(hql.toString(),actId).list();
	}
	
	//////////////////////
	///setters////
	///////////
	public void setActDao(TSActivityDao actDao) {
		this.actDao = actDao;
	}
	public void setRewDao(TSRewardDao rewDao) {
		this.rewDao = rewDao;
	}
	
	
	
}

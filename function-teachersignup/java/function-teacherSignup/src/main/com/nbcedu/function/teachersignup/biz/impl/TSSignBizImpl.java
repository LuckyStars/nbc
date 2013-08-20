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
			Set subSet = act.getSubjects();
			CollectionUtils.transform(subSet, new Transformer() {
				@Override
				public Object transform(Object obj) {
					TSSubject sub = (TSSubject)obj;
					return sub.getId();
				}
			});
			cris.add(Restrictions.in("subjectId", subSet));
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
	
	
}

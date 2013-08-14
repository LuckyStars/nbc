package com.nbcedu.function.teachersignup.biz.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashSet;

import org.apache.commons.lang.xwork.StringUtils;
import java.util.List;

import com.nbcedu.function.teachersignup.biz.TSActivityBiz;
import com.nbcedu.function.teachersignup.core.biz.impl.BaseBizImpl;
import com.nbcedu.function.teachersignup.core.pager.PagerModel;
import com.nbcedu.function.teachersignup.dao.TSActivityDao;
import com.nbcedu.function.teachersignup.model.TSActivity;
import com.nbcedu.function.teachersignup.model.TSReward;
import com.nbcedu.function.teachersignup.model.TSSubject;


public class TSActivityBizImpl extends BaseBizImpl<TSActivity> implements TSActivityBiz{
	private TSActivityDao actDao;
	
	
	public void setActDao(TSActivityDao actDao) {
		super.setDao(actDao);
		this.actDao = actDao;
	}

	
	@Override
	public void addOrUpdate(TSActivity act, String[] subs,String[] rews) {
		
		if(StringUtils.isNotBlank(act.getId())){
			this.actDao.createQuery("DELETE FROM TSReward r WHERE r.activityId=?",act.getId()).executeUpdate();
			this.actDao.createQuery("DELETE FROM TSSubject s WHERE s.activityId=?", act.getId());
		}
		
		act.setSubjects(new HashSet<TSSubject>());		
		act.setRewards(new HashSet<TSReward>());
		
		for (String re :rews) {
			if(StringUtils.isNotBlank(re)){
				TSReward rewa = new TSReward();
				try {
					rewa.setName(URLDecoder.decode(re,"utf-8"));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				act.getRewards().add(rewa);
			}
		}
		
		if(rews!=null&&rews.length>0){
			for(String su:subs){
				if(StringUtils.isNotBlank(su)){

					TSSubject subj = new TSSubject();
					try {
						subj.setName(URLDecoder.decode(su, "utf-8"));
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
					act.getSubjects().add(subj);
				}
			}
		}
		
		this.actDao.saveOrUpdate(act);
	}
	
	public List<TSActivity> findAll(){
		return actDao.getAll();
	}
	
	@Override
	public PagerModel findAllByPage() {
		return this.actDao.searchPaginated("FROM TSActivity a ORDER BY a.createDate DESC");
	}
}

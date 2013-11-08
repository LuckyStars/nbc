package com.nbcedu.function.schoolmaster2.biz.impl;

import static org.springframework.util.CollectionUtils.isEmpty;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.nbcedu.function.schoolmaster2.biz.SM2CommentBiz;
import com.nbcedu.function.schoolmaster2.biz.SM2DisscusBiz;
import com.nbcedu.function.schoolmaster2.biz.SM2MasterCommentBiz;
import com.nbcedu.function.schoolmaster2.biz.SM2MasterReplyBiz;
import com.nbcedu.function.schoolmaster2.biz.SM2ResourceBiz;
import com.nbcedu.function.schoolmaster2.biz.SM2ZanBiz;
import com.nbcedu.function.schoolmaster2.biz.Sm2ProgressBiz;
import com.nbcedu.function.schoolmaster2.biz.Sm2ReadsBiz;
import com.nbcedu.function.schoolmaster2.core.biz.impl.BaseBizImpl;
import com.nbcedu.function.schoolmaster2.dao.Sm2ProgressDao;
import com.nbcedu.function.schoolmaster2.data.model.TSm2Progress;
import com.nbcedu.function.schoolmaster2.data.vo.ProgressVo;

public class Sm2ProgressBizImpl extends BaseBizImpl<TSm2Progress> implements Sm2ProgressBiz {
	
	private Sm2ProgressDao progressDao;
	private SM2ZanBiz zanBiz;
	private SM2ResourceBiz resourceBiz;
	private Sm2ReadsBiz readsBiz;
	private SM2MasterReplyBiz masterReplyBiz;
	private SM2MasterCommentBiz masterCommentBiz;
	private SM2DisscusBiz disscusBiz;
	private SM2CommentBiz commentBiz;

	@Override
	@SuppressWarnings("unchecked")
	public List<TSm2Progress> findAllByStepId(String stepId) {
		Criteria cri = this.progressDao.createCriteria();
		cri.add(Restrictions.eq("stepId", stepId));
		return cri.list();
	}
	@Override
	public List<TSm2Progress> findByNameStepId(String stepId, String name) {
		Criteria cri = this.progressDao.createCriteria();
		cri.add(Restrictions.eq("stepId", stepId));
		cri.add(Expression.eq("name",name));
		return cri.list();
	}
	
	@Override
	public void modifyStep(String stepId, String progId) {
		TSm2Progress prog = this.progressDao.findUniqueBy("id", progId);
		if(prog!=null){
			prog.setStepId(stepId);
			this.progressDao.update(prog);
		}
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<ProgressVo> findVoByStepId(String stepId) {
		
		String sql = this.progressDao.getNamedQuery("find_progress_by_step").getQueryString();
		SQLQuery query = (SQLQuery) this.progressDao.createSqlQuery(sql);
		query.setString("stepId", stepId);
		
		query.addScalar("id", Hibernate.STRING);
		query.addScalar("name", Hibernate.STRING);
		query.addScalar("content", Hibernate.STRING);
		query.addScalar("createrId", Hibernate.STRING);
		query.addScalar("createTime", Hibernate.TIMESTAMP);
		query.addScalar("lastUpdateTime", Hibernate.TIMESTAMP);
		query.addScalar("stepId", Hibernate.STRING);
		query.addScalar("rcou", Hibernate.INTEGER);
		query.addScalar("zcou", Hibernate.INTEGER);
		
		List<Object[]> resultSet = query.list();
		if(isEmpty(resultSet)){
			return Collections.EMPTY_LIST;
		}
		
		return Lists.transform(resultSet, new Function<Object[], ProgressVo>() {
			@Override
			public ProgressVo apply(Object[] in) {
				ProgressVo result = new ProgressVo();
				result.setId(in[0].toString());
				result.setName(in[1].toString());
				result.setContent(in[2].toString());
				result.setCreaterId(in[3].toString());
				result.setCreateTime((Date)in[4]);
				result.setLastUpdateTime((Date)in[5]);
				result.setStepId(in[6].toString());
				result.setReadCount(objToInteger(in[7]));
				result.setZanCount(objToInteger(in[8]));
				return result;
			}
		});
		
	}
	@Override
	public void deleteById(String id) {
		this.zanBiz.deleteByProg(id);
		this.resourceBiz.deleteByProgId(id);
		readsBiz.deleteByProgId(id);
		masterReplyBiz.deleteByProgId(id);
		masterCommentBiz.deleteByProgId(id);
		disscusBiz.deleteByProgId(id);
		commentBiz.deleteByProgId(id);
		this.progressDao.removeById(id);
	}
	
	private Integer objToInteger(Object obj){
		return obj==null?0:Integer.valueOf(obj.toString());
	}
	//////////////////////////
	//////getters&setters///////
	///////////////////////
	public void setProgressDao(Sm2ProgressDao progressDao) {
		super.setDao(progressDao);
		this.progressDao = progressDao;
	}
	public void setZanBiz(SM2ZanBiz zanBiz) {
		this.zanBiz = zanBiz;
	}
	public void setResourceBiz(SM2ResourceBiz resourceBiz) {
		this.resourceBiz = resourceBiz;
	}
	public void setReadsBiz(Sm2ReadsBiz readsBiz) {
		this.readsBiz = readsBiz;
	}
	public void setMasterReplyBiz(SM2MasterReplyBiz masterReplyBiz) {
		this.masterReplyBiz = masterReplyBiz;
	}
	public void setMasterCommentBiz(SM2MasterCommentBiz masterCommentBiz) {
		this.masterCommentBiz = masterCommentBiz;
	}
	public void setDisscusBiz(SM2DisscusBiz disscusBiz) {
		this.disscusBiz = disscusBiz;
	}
	public void setCommentBiz(SM2CommentBiz commentBiz) {
		this.commentBiz = commentBiz;
	}

}

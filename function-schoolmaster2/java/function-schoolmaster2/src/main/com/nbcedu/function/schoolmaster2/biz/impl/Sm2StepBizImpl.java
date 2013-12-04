package com.nbcedu.function.schoolmaster2.biz.impl;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.util.CollectionUtils;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.apache.commons.lang.xwork.StringUtils;
import com.nbcedu.function.schoolmaster2.biz.SM2SubjectBiz;
import com.nbcedu.function.schoolmaster2.biz.Sm2StepBiz;
import com.nbcedu.function.schoolmaster2.core.biz.impl.BaseBizImpl;
import com.nbcedu.function.schoolmaster2.core.exception.DBException;
import com.nbcedu.function.schoolmaster2.core.util.StringUtil;
import com.nbcedu.function.schoolmaster2.dao.Sm2StepDao;
import com.nbcedu.function.schoolmaster2.data.model.TSm2Step;
import com.nbcedu.function.schoolmaster2.vo.StepVo;

public class Sm2StepBizImpl extends BaseBizImpl<TSm2Step> implements Sm2StepBiz{

	private Sm2StepDao stepDao;
	private SM2SubjectBiz subjectBiz;
	private static Logger logger = Logger.getLogger(Sm2StepBizImpl.class);  
	public void setStepDao(Sm2StepDao stepDao) {
		super.setDao(stepDao);
		this.stepDao = stepDao;
	}

	public void setSubjectBiz(SM2SubjectBiz subjectBiz) {
		this.subjectBiz = subjectBiz;
	}

	@Override
	public boolean findByName(String name ,String id) {
		StringBuilder hql = new StringBuilder();
		hql.append("select count(id) from TSm2Step where subjectId= ");
		hql.append("'").append(id).append("'");
		if(!StringUtil.isEmpty(name)){
			hql.append(" and name=? ");
			List l = this.stepDao.find(hql.toString(), name);
			return ((Long)l.get(0)).intValue()>0 ;
		}
		
		List l = this.stepDao.find(hql.toString(), null);
		return ((Long)l.get(0)).intValue()>0 ;
	}

	@Override
	public List<StepVo> findBySubId(String subjectId) {
		StringBuilder hql = new StringBuilder("");
		hql.append("SELECT s.id,s.name ");
		hql.append("FROM TSm2Step s ");
		hql.append("WHERE s.subjectId ");
		List<Object[]> resultSet = this.stepDao.findByHQL(hql.toString(), subjectId);
		if(CollectionUtils.isEmpty(resultSet)){
			return new LinkedList<StepVo>();
		}
		return transResult(resultSet);
	}

	@Override
	public List<StepVo> findByStepId(String stepId) {
		StringBuilder hql = new StringBuilder("");
		hql.append("SELECT s.id,s.name ");
		hql.append("FROM TSm2Step s ");
		hql.append("WHERE s.subjectId = (SELECT st.subjectId FROM TSm2Step st WHERE st.id=?)");
		List<Object[]> resultSet = this.stepDao.findByHQL(hql.toString(), stepId);
		if(CollectionUtils.isEmpty(resultSet)){
			return new LinkedList<StepVo>();
		}
		return transResult(resultSet);
	}

	
	@Override
	@SuppressWarnings("unchecked")
	public List<StepVo> findByProgId(String progId) {
		Query q  = this.stepDao.getNamedQuery("find_step_by_prog");
		q.setString("progId", progId);
		List<Object[]> resultSet = q.list();
		return transResult(resultSet);
	}
	///////////////////////
	//////PRIVATE//////////
	///////////////////////
	private List<StepVo> transResult(List<Object[]> resultSet){
		return Lists.transform(resultSet, new Function<Object[], StepVo>() {
			@Override
			public StepVo apply(Object[] input) {
				StepVo result = new StepVo();
				result.setId(input[0].toString());
				result.setName(input[1].toString());
				return result;
			}
		});
	}

	@Override
	public boolean removeById1(String id) {
		TSm2Step s = this.stepDao.removeById(id);
		if(s!=null&&StringUtils.isNotBlank(s.getId())){
			try {
				this.subjectBiz.updateMasterFlagAll(2, s.getSubjectId());
			} catch (DBException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
	@Override
	public boolean updateBySubId(TSm2Step step) {
			try {
				this.stepDao.updateByHql("update TSm2Step set name=? , lastUpdateTime=? where id=?",step.getName(),new Date(),step.getId());
				this.subjectBiz.updateMasterFlagAll(2,step.getSubjectId());
			} catch (DBException e) {
				e.printStackTrace();
				return false;
			}
		return true;
	}

	@Override
	public boolean addStep(TSm2Step step) {
		try {
			this.stepDao.save(step);
			this.subjectBiz.updateMasterFlagAll(2,step.getSubjectId());
		} catch (DBException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	
}

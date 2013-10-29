package com.nbcedu.function.schoolmaster2.biz.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.xwork.StringUtils;


import com.nbcedu.function.schoolmaster2.biz.SM2SubjectBiz;
import com.nbcedu.function.schoolmaster2.core.biz.impl.BaseBizImpl;
import com.nbcedu.function.schoolmaster2.core.pager.PagerModel;
import com.nbcedu.function.schoolmaster2.core.util.strings.StringUtil;
import com.nbcedu.function.schoolmaster2.dao.SM2SubjectDao;
import com.nbcedu.function.schoolmaster2.data.model.TSm2Subject;
import com.nbcedu.function.schoolmaster2.vo.SubjectVo;

public class SM2SubjectBizImpl extends BaseBizImpl<TSm2Subject> implements SM2SubjectBiz{

	protected SM2SubjectDao sm2SubjectDao ; 
	@Override
	public PagerModel findByModel(TSm2Subject subject) {
		StringBuffer hql = new StringBuffer("from TSm2Subject where 1=1 ");
		List<Object> list = new ArrayList<Object>();
		if(StringUtil.isEmpty(subject.getCreaterId())){
			hql.append(" and createrId =? ");
			list.add(subject.getCreaterId());
		}
		hql.append(" order by createDate desc");
		Object[] params = new Object[list.size()];
		list.toArray(params);
		return this.sm2SubjectDao.searchPaginated(hql.toString(),params);
	}
	public void setSm2SubjectDao(SM2SubjectDao sm2SubjectDao) {
		super.setDao(sm2SubjectDao);
		this.sm2SubjectDao = sm2SubjectDao;
	}
	@Override
	public PagerModel findByExceuteUserId(SubjectVo subject) {
		StringBuffer hql = new StringBuffer("select TSm2Subject from TSm2Subject s,TSm2SubjectUser u " +
				"where u.subjectId=s.id and u.userId=? AND s.moduleId = ?");
	
		List<Object> list = new ArrayList<Object>();
		list.add(subject.getExcuteUserId());
		list.add(subject.getModuleId());
		if(!StringUtil.isEmpty(subject.getTitle())){
			hql.append(" and s.title like ?");
			list.add("%"+subject.getTitle().trim()+"%");
		}
		if(subject.getBeginDate()!=null&&StringUtils.isNotBlank(subject.getBeginDate().toString())){
			hql.append(" and createTime >? ");
			list.add(subject.getBeginDate());
		}
		if(subject.getEndDate()!=null&&StringUtils.isNotBlank(subject.getEndDate().toString())){
			hql.append(" and createTime <? ");
			list.add(subject.getEndDate());
		}
		Object[] params = new Object[list.size()];
		list.toArray(params);
		hql.append(" order by createTime desc");
		return this.sm2SubjectDao.searchPaginated(hql.toString(),params);
	}
	@Override
	public PagerModel findByCreaterId(SubjectVo subject) {
		StringBuffer hql = new StringBuffer("from TSm2Subject s " +
				"where s.createrId=? AND s.moduleId = ? ");
		List<Object> list = new ArrayList<Object>();
		list.add(subject.getCreaterId());
		list.add(subject.getModuleId());
		if(!StringUtil.isEmpty(subject.getTitle())){
			hql.append(" and s.title like ?");
			list.add("%"+subject.getTitle().trim()+"%");
		}
		if(subject.getBeginDate()!=null&&StringUtils.isNotBlank(subject.getBeginDate().toString())){
			hql.append(" and createTime >? ");
			list.add(subject.getBeginDate());
		}
		if(subject.getEndDate()!=null&&StringUtils.isNotBlank(subject.getEndDate().toString())){
			hql.append(" and createTime <? ");
			list.add(subject.getEndDate());
		}
		Object[] params = new Object[list.size()];
		list.toArray(params);
		hql.append(" order by createTime desc");
		return this.sm2SubjectDao.searchPaginated(hql.toString(),params);
	}
	
	@Override
	public PagerModel findByModule(String moduleId) {
		String hql = "FROM TSm2Subject t WHERE t.moduleId = ? ORDER BY createTime DESC";
		return this.sm2SubjectDao.searchPaginated(hql,moduleId);
	}
	@Override
	public List<TSm2Subject> findBYModuleId(String moduleId) {
		String hql = "FROM TSm2Subject t WHERE t.moduleId = ? ORDER BY createTime DESC";
		return this.sm2SubjectDao.find(hql,moduleId );
	}
	@Override
	public void update(TSm2Subject subject) {
		TSm2Subject s = this.sm2SubjectDao.load(subject.getId());
		s.setContent(subject.getContent());
		s.setLastUpdateTime(new Date());
		s.setTitle(subject.getTitle());
		s.setModuleId(subject.getModuleId());
		s.setTypeId(subject.getTypeId());
		s.setExcuteUsers(subject.getExcuteUsers());
		s.setCheckUsers(subject.getCheckUsers());
		this.sm2SubjectDao.update(s);
	}
	
}

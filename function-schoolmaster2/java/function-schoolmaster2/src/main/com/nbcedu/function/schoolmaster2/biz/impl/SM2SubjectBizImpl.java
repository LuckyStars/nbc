package com.nbcedu.function.schoolmaster2.biz.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.xwork.StringUtils;

import com.nbcedu.function.schoolmaster2.biz.SM2SubjectBiz;
import com.nbcedu.function.schoolmaster2.core.biz.impl.BaseBizImpl;
import com.nbcedu.function.schoolmaster2.core.exception.DBException;
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
		return this.sm2SubjectDao.findByExceuteUserId(subject);
		
		
//		StringBuffer hql = new StringBuffer("select TSm2Subject from TSm2Subject s,TSm2SubjectUser u " +
//				"where u.subjectId=s.id and u.userId=? AND s.moduleId = ?");
//	
//		List<Object> list = new ArrayList<Object>();
//		list.add(subject.getExcuteUserId());
//		list.add(subject.getModuleId());
//		if(!StringUtil.isEmpty(subject.getTitle())){
//			hql.append(" and s.title like ?");
//			list.add("%"+subject.getTitle().trim()+"%");
//		}
//		if(subject.getBeginDate()!=null&&StringUtils.isNotBlank(subject.getBeginDate().toString())){
//			hql.append(" and createTime >? ");
//			list.add(subject.getBeginDate());
//		}
//		if(subject.getEndDate()!=null&&StringUtils.isNotBlank(subject.getEndDate().toString())){
//			hql.append(" and createTime <? ");
//			list.add(subject.getEndDate());
//		}
//		Object[] params = new Object[list.size()];
//		list.toArray(params);
//		hql.append(" order by createTime desc");
//		return this.sm2SubjectDao.searchPaginated(hql.toString(),params);
	}
	@Override
	public PagerModel findByCreaterId(SubjectVo subject) {
		StringBuffer hql = new StringBuffer("from TSm2Subject s " +
				"where s.createrId=? AND s.moduleId = ? ");
		List<Object> list = new ArrayList<Object>();
		list.add(subject.getCreaterId());
		list.add(subject.getModuleId());
		if(StringUtils.isNotBlank(subject.getTitle())){
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
	public List<TSm2Subject> findByTypeUser(String userId,String typeId,String moduleId) {
		String hql = "select new TSm2Subject(id ,title) FROM TSm2Subject s WHERE s.moduleId = ? and s.typeId=? and s.typeId in " +
				"(select t.id from TSm2Type t inner join t.typeUsers u where u.userId=? " +
				") ORDER BY createTime DESC";
		return this.sm2SubjectDao.find(hql,moduleId ,typeId,userId);
	}
	@Override
	public void update(TSm2Subject subject) {
		this.sm2SubjectDao.update(subject);
	}
	@Override
	public List<TSm2Subject> findByModuleIdExceuteUserId(String moduleId,
			String exceuteUserId) {
		return this.sm2SubjectDao.findByModuleIdExceuteUserId(moduleId, exceuteUserId);
	}
	@Override
	public PagerModel findBySubjectMaster(SubjectVo subject) {
		List<Object> list = new ArrayList<Object>();
		StringBuilder hql = new StringBuilder("");
		hql.append("FROM TSm2Subject sub WHERE sub.moduleId =? ");
		list.add(subject.getModuleId());
		if(subject.getBeginDate()!=null&&StringUtils.isNotBlank(subject.getBeginDate().toString())){
			hql.append(" and createTime >? ");
			list.add(subject.getBeginDate());
		}
		if(subject.getEndDate()!=null&&StringUtils.isNotBlank(subject.getEndDate().toString())){
			hql.append(" and createTime <? ");
			list.add(subject.getEndDate());
		}
		if(!StringUtil.isEmpty(subject.getTitle())){
			hql.append(" and s.title like ?");
			list.add("%"+subject.getTitle().trim()+"%");
		}
		
		hql.append("AND sub.id in (SELECT subId FROM SM2SubjectMaster m WHERE m.userUid = ?) ");
		list.add(subject.getCheckUserId());
		
		hql.append("ORDER BY sub.createTime DESC");
		Object[] params = new Object[list.size()];
		list.toArray(params);
		return this.sm2SubjectDao.searchPaginated(hql.toString(),params);
	}
	@Override
	public void updateMasterFlag(int flag, String subId,String userUid) throws DBException {
		this.sm2SubjectDao.updateByHql("update SM2SubjectMaster set flag=? where subId=? and userUid=?",flag,subId,userUid);
	}
	@Override
	public void updateMasterFlagAll(int flag, String subId) throws DBException {
		this.sm2SubjectDao.updateByHql("update TSm2Subject set lastUpdateTime=?",new Date());
		this.sm2SubjectDao.updateByHql("update SM2SubjectMaster set flag=? where subId=? and flag<>1 ",flag,subId);
	}
	@Override
	public PagerModel findAlltrans(SubjectVo subject,String curUserId) throws DBException{
		return sm2SubjectDao.findAllTrans(subject,curUserId);
	}
}

package com.nbcedu.function.schoolmaster2.biz.impl;


import java.util.ArrayList;
import java.util.List;


import com.nbcedu.function.schoolmaster2.biz.SM2SubjectBiz;
import com.nbcedu.function.schoolmaster2.core.biz.impl.BaseBizImpl;
import com.nbcedu.function.schoolmaster2.core.pager.PagerModel;
import com.nbcedu.function.schoolmaster2.core.util.strings.StringUtil;
import com.nbcedu.function.schoolmaster2.dao.SM2SubjectDao;
import com.nbcedu.function.schoolmaster2.data.model.TSm2Subject;

public class SM2SubjectBizImpl extends BaseBizImpl<TSm2Subject> implements SM2SubjectBiz{

	private SM2SubjectDao sm2DataDao ; 
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
		return this.sm2DataDao.searchPaginated(hql.toString(),params);
	}
	public void setSm2DataDao(SM2SubjectDao sm2DataDao) {
		super.setDao(sm2DataDao);
		this.sm2DataDao = sm2DataDao;
	}
	@Override
	public PagerModel findByExceuteUserId(String userId,String moduleId) {
		StringBuffer hql = new StringBuffer("select TSm2Subject from TSm2Subject s,TSm2SubjectUser u " +
				"where u.subjectId=s.id and u.userId=? AND s.moduleId = ?");
		hql.append(" order by createDate desc");
		return this.sm2DataDao.searchPaginated(hql.toString(),new String[]{userId,moduleId});
	}
	@Override
	public PagerModel findByCreaterId(String createrId,String moduleId) {
		StringBuffer hql = new StringBuffer("select TSm2Subject from TSm2Subject s,TSm2SubjectUser u " +
				"where u.subjectId=s.id and u.createrId=? AND s.moduleId = ?");
		hql.append(" order by createDate desc");
		return this.sm2DataDao.searchPaginated(hql.toString(),new String[]{createrId,moduleId});
	}
	
	@Override
	public PagerModel findByModule(String moduleId) {
		String hql = "FROM TSm2Subject t WHERE t.moduleId = ? ORDER BY createDate DESC";
		return this.sm2DataDao.searchPaginated(hql,moduleId);
	}
	
}

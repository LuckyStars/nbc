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
	public PagerModel findByExceuteUserId(String userId,String moduleId) {
		StringBuffer hql = new StringBuffer("select TSm2Subject from TSm2Subject s,TSm2SubjectUser u " +
				"where u.subjectId=s.id and u.userId=? AND s.moduleId = ?");
		hql.append(" order by createTime desc");
		return this.sm2SubjectDao.searchPaginated(hql.toString(),new String[]{userId,moduleId});
	}
	@Override
	public PagerModel findByCreaterId(String createrId,String moduleId) {
		StringBuffer hql = new StringBuffer("from TSm2Subject s " +
				"where s.createrId=? AND s.moduleId = ?");
		hql.append(" order by createTime desc");
		return this.sm2SubjectDao.searchPaginated(hql.toString(),new String[]{createrId,moduleId});
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
	
}

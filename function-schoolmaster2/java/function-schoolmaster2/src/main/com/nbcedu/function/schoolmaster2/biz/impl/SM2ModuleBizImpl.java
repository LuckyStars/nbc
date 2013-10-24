package com.nbcedu.function.schoolmaster2.biz.impl;


import java.util.ArrayList;
import java.util.List;


import com.nbcedu.function.schoolmaster2.biz.SM2ModuleBiz;
import com.nbcedu.function.schoolmaster2.biz.SM2SubjectBiz;
import com.nbcedu.function.schoolmaster2.core.biz.impl.BaseBizImpl;
import com.nbcedu.function.schoolmaster2.core.pager.PagerModel;
import com.nbcedu.function.schoolmaster2.core.util.strings.StringUtil;
import com.nbcedu.function.schoolmaster2.dao.SM2ModuleDao;
import com.nbcedu.function.schoolmaster2.dao.SM2SubjectDao;
import com.nbcedu.function.schoolmaster2.data.model.TSm2Module;
import com.nbcedu.function.schoolmaster2.data.model.TSm2Subject;

public class SM2ModuleBizImpl extends BaseBizImpl<TSm2Module> implements SM2ModuleBiz{

	private SM2ModuleDao sm2ModuleDao ; 
	@Override
	public PagerModel findByModel(TSm2Module module) {
		StringBuffer hql = new StringBuffer("from TSm2Module where 1=1 ");
		List<Object> list = new ArrayList<Object>();
		if(StringUtil.isEmpty(module.getCreaterId())){
			hql.append(" and createrId =? ");
			list.add(module.getCreaterId());
		}
		hql.append(" order by createDate desc");
		Object[] params = new Object[list.size()];
		list.toArray(params);
		return this.sm2ModuleDao.searchPaginated(hql.toString(),params);
	}
	public void setSm2ModuleDao(SM2ModuleDao sm2ModuleDao) {
		super.setDao(sm2ModuleDao);
		this.sm2ModuleDao = sm2ModuleDao;
	}
	
}

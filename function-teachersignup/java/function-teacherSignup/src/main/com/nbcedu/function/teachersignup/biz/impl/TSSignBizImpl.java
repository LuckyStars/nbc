package com.nbcedu.function.teachersignup.biz.impl;


import com.nbcedu.function.teachersignup.biz.TSSignBiz;
import com.nbcedu.function.teachersignup.core.biz.impl.BaseBizImpl;
import com.nbcedu.function.teachersignup.dao.TSSignDao;
import com.nbcedu.function.teachersignup.model.TSSign;
import com.nbcedu.function.teachersignup.vo.TSUser;

public class TSSignBizImpl extends BaseBizImpl<TSSign> implements TSSignBiz{
	private TSSignDao signDao;

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
	
	
}

package com.nbcedu.function.schoolmaster2.biz.impl;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ibm.icu.util.Calendar;
import com.nbcedu.function.schoolmaster2.biz.SM2InvatitionBiz;
import com.nbcedu.function.schoolmaster2.core.biz.impl.BaseBizImpl;
import com.nbcedu.function.schoolmaster2.core.pager.PagerModel;
import com.nbcedu.function.schoolmaster2.dao.SM2InvatitionDao;
import com.nbcedu.function.schoolmaster2.data.model.TSm2Invatition;

public class SM2InvatitionBizImpl extends BaseBizImpl<TSm2Invatition> implements SM2InvatitionBiz{

	protected SM2InvatitionDao sm2InvatitionDao ; 

	public void setSm2InvatitionDao(SM2InvatitionDao sm2InvatitionDao) {
		super.setDao(sm2InvatitionDao);
		this.sm2InvatitionDao = sm2InvatitionDao;
	}

	/* 
	 * @see com.nbcedu.function.schoolmaster2.biz.SM2InvatitionBiz#findByCreaterId(java.lang.String)
	 */
	@Override
	public PagerModel findByCreaterId(String createrId, String createtime, String title, String user) throws ParseException {
		StringBuffer buffer = new StringBuffer();
		List<Object> pam = new ArrayList<Object>();
		buffer.append("from TSm2Invatition s where s.createrId = ?");
		pam.add(createrId);
		if (createtime!=null && createtime.trim().length() > 0) {
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			Date searchStartTime = sf.parse(createtime);
			Calendar c = Calendar.getInstance();
			c.setTime(searchStartTime);
			c.add(Calendar.DATE,1); 
			buffer.append(" and s.createTime between ? and ?");
			pam.add(searchStartTime);
			pam.add(c.getTime());
		}
		if (title!=null && title.trim().length() > 0) {
			buffer.append(" and s.title like ?");
			pam.add('%'+title+'%');
		}
		if (user!=null && user.trim().length() > 0) {
			buffer.append(" and s.invatId = ?");
			pam.add(user);
		}
		buffer.append(" order by s.createTime desc");
		return this.sm2InvatitionDao.searchPaginated(buffer.toString(),pam.toArray());
	}

	/* 
	 * @see com.nbcedu.function.schoolmaster2.biz.SM2InvatitionBiz#findByInvatId(java.lang.String)
	 */
	@Override
	public PagerModel findByInvatId(String invatId, String createtime, String title, String user) throws ParseException {
		StringBuffer buffer = new StringBuffer();
		List<Object> pam = new ArrayList<Object>();
		buffer.append("from TSm2Invatition s where s.invatId = ? and s.status='1'");
		pam.add(invatId);
		if (createtime!=null && createtime.trim().length() > 0) {
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			Date searchStartTime = sf.parse(createtime);
			Calendar c = Calendar.getInstance();
			c.setTime(searchStartTime);
			c.add(Calendar.DATE,1); 
			buffer.append(" and s.createTime between ? and ?");
			pam.add(searchStartTime);
			pam.add(c.getTime());
		}
		if (title!=null && title.trim().length() > 0) {
			buffer.append(" and s.title like ?");
			pam.add('%'+title+'%');
		}
		if (user!=null && user.trim().length() > 0) {
			buffer.append(" and s.createrId = ?");
			pam.add(user);
		}
		buffer.append(" order by s.createTime desc");
		return this.sm2InvatitionDao.searchPaginated(buffer.toString(),pam.toArray());
	}

	/* 
	 * @see com.nbcedu.function.schoolmaster2.biz.SM2InvatitionBiz#findCreaterIds(java.lang.String)
	 */
	@Override
	public List<TSm2Invatition> findCreaterIds(String invatId) throws ParseException {
		String hql="select distinct new com.nbcedu.function.schoolmaster2.data.model.TSm2Invatition(s.createrId) from TSm2Invatition s where s.invatId = ? and s.status='1'";
		return this.sm2InvatitionDao.find(hql, invatId);
	}

	/* 
	 * @see com.nbcedu.function.schoolmaster2.biz.SM2InvatitionBiz#findInvatIds(java.lang.String)
	 */
	@Override
	public List<TSm2Invatition> findInvatIds(String createrId) throws ParseException {
		String hql="select distinct new com.nbcedu.function.schoolmaster2.data.model.TSm2Invatition(s.invatId) from TSm2Invatition s where s.createrId = ?";
		return this.sm2InvatitionDao.find(hql, createrId);
	}
}

package com.nbcedu.function.schoolmaster2.biz.impl;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import java.util.Calendar;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.util.CollectionUtils;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.nbcedu.function.schoolmaster2.biz.SM2InvatitionBiz;
import com.nbcedu.function.schoolmaster2.core.biz.impl.BaseBizImpl;
import com.nbcedu.function.schoolmaster2.core.pager.PagerModel;
import com.nbcedu.function.schoolmaster2.core.pager.SystemContext;
import com.nbcedu.function.schoolmaster2.dao.SM2InvatitionDao;
import com.nbcedu.function.schoolmaster2.data.model.TSm2Invatition;
import com.nbcedu.function.schoolmaster2.vo.InvatitionVo;

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
//		if (user!=null && user.trim().length() > 0) {
//			buffer.append(" and s.invatId = ?");
//			pam.add(user);
//		}
		buffer.append(" order by s.createTime desc");
		return this.sm2InvatitionDao.searchPaginated(buffer.toString(),pam.toArray());
	}

	/* 
	 * @see com.nbcedu.function.schoolmaster2.biz.SM2InvatitionBiz#findByInvatId(java.lang.String)
	 */
	@Override
	public PagerModel findByInvatId(String invatId, String createtime, String title, String user) throws ParseException {
		StringBuffer buffer = new StringBuffer();
		buffer.append("select * from t_sm2_invatition s ,(select invatitionid from t_sm2_invatition_user where userId='");
		buffer.append(invatId).append("') u where s.id= u.invatitionid ");
		if(createtime!=null && createtime.trim().length() > 0){
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			Date searchStartTime = sf.parse(createtime);
			Calendar c = Calendar.getInstance();
			c.setTime(searchStartTime);
			c.add(Calendar.DATE,1); 
			buffer.append(" and s.createTime between ");
			buffer.append(searchStartTime) ;
			buffer.append(" and ");
			buffer.append(c.getTime());
		}
		if (title!=null && title.trim().length() > 0) {
			buffer.append(" and s.title like '%");
			buffer.append(title);
			buffer.append("%'");
		}
		if (user!=null && user.trim().length() > 0) {
			buffer.append(" and s.createrId = '");
			buffer.append(user);
			buffer.append("'");
		}
		buffer.append(" order by s.createTime desc");
		List<InvatitionVo> l = toVo(buffer.toString());
		String countsql = parseCountSql(buffer.toString());
		Query query = this.getDao().createSqlQuery(countsql);
		int total = Integer.parseInt(query.uniqueResult().toString());
		PagerModel pagerModel = new PagerModel();
		// 显示结果
		pagerModel.setTotal(total);
		pagerModel.setDatas(l);
		pagerModel.setTotalPageNo(total % SystemContext.getPagesize() == 0 ? total / SystemContext.getPagesize() : total/SystemContext.getPagesize()+1);
		pagerModel.setPageIndex(SystemContext.getOffset() == 0 ? 1 :  SystemContext.getOffset() / SystemContext.getPagesize()+1 );
		pagerModel.setPagesize(SystemContext.getPagesize());

//		buffer.append("from TSm2Invatition s where s.invatId = ? and s.status='1'");
//		pam.add(invatId);
//		if (createtime!=null && createtime.trim().length() > 0) {
//			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
//			Date searchStartTime = sf.parse(createtime);
//			Calendar c = Calendar.getInstance();
//			c.setTime(searchStartTime);
//			c.add(Calendar.DATE,1); 
//			buffer.append(" and s.createTime between ? and ?");
//			pam.add(searchStartTime);
//			pam.add(c.getTime());
//		}
//		if (title!=null && title.trim().length() > 0) {
//			buffer.append(" and s.title like ?");
//			pam.add('%'+title+'%');
//		}
//		if (user!=null && user.trim().length() > 0) {
//			buffer.append(" and s.createrId = ?");
//			pam.add(user);
//		}
//		buffer.append(" order by s.createTime desc");
		return pagerModel;
	}
	protected String parseCountSql(String sql) {
		if (!sql.matches(".* (count|COUNT)\\(.+\\) .+"))// 如果语句中已包含count函数，则不作任务解析(当一条统计语句比较复杂时，可自定义统计语句)，否则，按一般规则解析统计语句
		{
			sql = sql.replaceAll("(\\s|　)+", " ");
			int startIndex = sql.indexOf("from ");
			if (startIndex == -1)
				startIndex = sql.indexOf("FROM ");
			int endIndex = sql.lastIndexOf(" group by ");
			if (endIndex == -1) {
				endIndex = sql.lastIndexOf(" GROUP BY ");
			}
			if (endIndex == -1) {
				endIndex = sql.lastIndexOf(" order by ");
			}
			if (endIndex == -1) {
				endIndex = sql.lastIndexOf(" ORDER BY ");
			}
			if (endIndex == -1)
				sql = sql.substring(startIndex);
			else
				sql = sql.substring(startIndex, endIndex);

			sql = "select count(*) " + sql;
		}
		return sql;
	}
	@SuppressWarnings("unchecked")
	private List<InvatitionVo> toVo(String sql){
		SQLQuery query = (SQLQuery) this.sm2InvatitionDao.createSqlQuery(sql);
		query.addScalar("id", Hibernate.STRING);
		query.addScalar("createrId", Hibernate.STRING);
		query.addScalar("createTime", Hibernate.DATE);
		query.addScalar("content", Hibernate.STRING);
		query.addScalar("flag", Hibernate.STRING);
		query.addScalar("title", Hibernate.STRING);
		query.addScalar("status", Hibernate.STRING);
		query.addScalar("link", Hibernate.STRING);
		query.addScalar("score", Hibernate.STRING);
		
		query.setFirstResult( SystemContext.getOffset());
		query.setMaxResults(SystemContext.getPagesize());
		
		List<Object[]> resultSet = query.list();
		if(CollectionUtils.isEmpty(resultSet)){
			return Collections.EMPTY_LIST;
		}
		
		return Lists.transform(resultSet, new Function<Object[], InvatitionVo>() {
			@Override
			public InvatitionVo apply(Object[] in) {
				InvatitionVo result = new InvatitionVo();
				result.setId((String)in[0]);
				result.setCreaterId((String)in[1]);
				result.setCreateTime((Date)in[2]);
				result.setContent((String)in[3]);
				result.setFlag(in[4].toString());
				result.setTitle(in[5].toString());
				result.setStatus((String)in[6]);
				result.setLink(in[7].toString());
				result.setScore(Integer.parseInt(in[8].toString()));
				return result;
			}
		});
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

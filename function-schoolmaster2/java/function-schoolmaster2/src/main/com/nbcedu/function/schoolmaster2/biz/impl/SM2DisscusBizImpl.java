package com.nbcedu.function.schoolmaster2.biz.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.xwork.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.CollectionUtils;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.nbcedu.function.schoolmaster2.biz.SM2DisscusBiz;
import com.nbcedu.function.schoolmaster2.core.biz.impl.BaseBizImpl;
import com.nbcedu.function.schoolmaster2.core.exception.DBException;
import com.nbcedu.function.schoolmaster2.dao.SM2DisscusDao;
import com.nbcedu.function.schoolmaster2.data.model.TSm2Disscus;

public class SM2DisscusBizImpl extends BaseBizImpl<TSm2Disscus> implements SM2DisscusBiz{
	
	private SM2DisscusDao disDao;

	@SuppressWarnings("unchecked")
	@Override
	public List<TSm2Disscus> findByProgIds(Collection<String> progIds) {
		if(CollectionUtils.isEmpty(progIds)){
			return Lists.newArrayList();
		}
		Criteria cri = this.disDao.createCriteria(Restrictions.in("progressId", progIds));
		cri.addOrder(Order.desc("createTime"));
		return cri.list();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<TSm2Disscus> findByProgIds(Collection<String> progIds,
			Integer size) {
		if(CollectionUtils.isEmpty(progIds)|| size==null || size <=0){
			return Lists.newArrayList();
		}
		
		StringBuilder sql = new StringBuilder("");
		ArrayList<String> tempList= Lists.newArrayList(progIds);
		for (int i= 0; i< tempList.size() ; i ++ ) {
			
			sql.append("SELECT id ,createrId ,user_name ,createTime ,lastUpdateTime ,progressId,content ");
			sql.append("FROM ");
			sql.append("(SELECT ");
			sql.append("id ,createrId ,user_name ,createTime ,lastUpdateTime ,progressId,content ");
			sql.append("FROM t_sm2_disscus ");
			sql.append("WHERE progressId='" + tempList.get(i) +"' " );
			sql.append("ORDER BY createTime DESC ");
			sql.append("limit 0,${size}) ");
			sql.append("t" + tempList.get(i));
			sql.append(" UNION ALL ");
		}
		sql.delete(sql.lastIndexOf("UNION ALL"),sql.length());
		String sqltoRun = sql.toString().replace("${size}", size.toString());
		
		SQLQuery q = (SQLQuery) this.disDao.createSqlQuery(sqltoRun);
		q.addScalar("id",Hibernate.STRING);
		q.addScalar("createrId",Hibernate.STRING);
		q.addScalar("user_name",Hibernate.STRING);
		q.addScalar("createTime",Hibernate.DATE);
		q.addScalar("lastUpdateTime",Hibernate.DATE);
		q.addScalar("progressId", Hibernate.STRING);
		q.addScalar("content", Hibernate.STRING);
		
		List<Object[]> resultSet = q.list();
		
		if(CollectionUtils.isEmpty(resultSet)){
			return Collections.EMPTY_LIST;
		}
		return Lists.transform(resultSet, new Function<Object[], TSm2Disscus>() {
			@Override
			public TSm2Disscus apply(Object[] in) {
				TSm2Disscus diss = new TSm2Disscus();
				diss.setId(in[0].toString());
				diss.setCreaterId(in[1].toString());
				diss.setUserName(in[2].toString());
				diss.setCreateTime((Date)in[3]);
				diss.setLastUpdateTime((Date)in[4]);
				diss.setProgressId(in[5].toString());
				diss.setContent(in[6].toString());
				return diss;
			}
		});
	}
	
	
	///////////////////////////
	//////getters&setters//////
	///////////////////////////
	public void setDisDao(SM2DisscusDao disDao) {
		super.setDao(disDao);
		this.disDao = disDao;
	}

	@Override
	public void removeByProgId(String progId) {
		this.disDao.createQuery("delete from TSm2Disscus where progressId=?", progId).executeUpdate();
		
	}
	
}

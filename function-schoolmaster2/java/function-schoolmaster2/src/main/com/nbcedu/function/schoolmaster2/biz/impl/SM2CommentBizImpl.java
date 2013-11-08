package com.nbcedu.function.schoolmaster2.biz.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.springframework.util.CollectionUtils;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.nbcedu.function.schoolmaster2.biz.SM2CommentBiz;
import com.nbcedu.function.schoolmaster2.core.biz.impl.BaseBizImpl;
import com.nbcedu.function.schoolmaster2.dao.SM2CommentDao;
import com.nbcedu.function.schoolmaster2.data.model.TSm2Comment;

public class SM2CommentBizImpl extends BaseBizImpl<TSm2Comment> implements SM2CommentBiz {

	private SM2CommentDao comDao;
	
	@Override
	@SuppressWarnings("unchecked")
	public List<TSm2Comment> findByProgIds(Collection<String> progIds,
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
			sql.append("FROM t_sm2_comment ");
			sql.append("WHERE progressId='" + tempList.get(i) +"' " );
			sql.append("ORDER BY createTime DESC ");
			sql.append("limit 0,${size}) ");
			sql.append("t" + tempList.get(i));
			sql.append(" UNION ALL ");
		}
		sql.delete(sql.lastIndexOf("UNION ALL"),sql.length());
		String sqltoRun = sql.toString().replace("${size}", size.toString());
		SQLQuery q = (SQLQuery) this.comDao.createSqlQuery(sqltoRun);
		q.addScalar("id",Hibernate.STRING);
		q.addScalar("createrId",Hibernate.STRING);
		q.addScalar("user_name",Hibernate.STRING);
		q.addScalar("createtime",Hibernate.DATE);
		q.addScalar("lastUpdateTime",Hibernate.DATE);
		q.addScalar("progressId", Hibernate.STRING);
		q.addScalar("content", Hibernate.STRING);
		
		List<Object[]> resultSet = q.list();
		
		if(CollectionUtils.isEmpty(resultSet)){
			return Collections.EMPTY_LIST;
		}
		return Lists.transform(resultSet, new Function<Object[], TSm2Comment>() {
			@Override
			public TSm2Comment apply(Object[] in) {
				TSm2Comment comm = new TSm2Comment();
				comm.setId(in[0].toString());
				comm.setCreaterId(in[1].toString());
				comm.setUserName(in[2].toString());
				comm.setCreatetime((Date)in[3]);
				comm.setLastUpdateTime((Date)in[4]);
				comm.setProgressId(in[5].toString());
				comm.setContent(in[6].toString());
				return comm;
			}
		});
	}
	
	////////////////////////////
	////getters&setters///////
	///////////////////////////
	public void setComDao(SM2CommentDao comDao) {
		super.setDao(comDao);
		this.comDao = comDao;
	}

	@Override
	public void removeByProgId(String progId) {
		this.comDao.createQuery("delete from TSm2Comment where progressId=?", progId);
	}

	
	
}

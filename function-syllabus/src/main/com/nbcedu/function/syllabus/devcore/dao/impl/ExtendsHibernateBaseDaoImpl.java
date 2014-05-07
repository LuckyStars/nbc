package com.nbcedu.function.syllabus.devcore.dao.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.transform.Transformers;

import com.nbcedu.function.syllabus.devcore.dao.HibernateBaseDao;
import com.nbcedu.function.syllabus.devcore.util.OrderModel;
import com.nbcedu.function.syllabus.devcore.util.PagerModel;


/**
 * 原生SQL查询扩展
 * 
 * @author qinyuan
 * @version 1.0
 */
@SuppressWarnings("unchecked")
public class ExtendsHibernateBaseDaoImpl<T extends Serializable> extends HibernateBaseDaoImpl<T> implements HibernateBaseDao<T> {
	protected final Logger logger = Logger.getLogger(this.getClass());

	/**
	 * SQL更新：insert | update | delete
	 * 
	 * @param params 参数
	 * @return 返回影响的记录条数
	 */
	@Override
	public int updateBySQL(final String sql, final List<Object> params) {
		Query query = createSQLQuery(this.getSession(), sql, params);
		return query.executeUpdate();
	}

	/**
	 * SQL，返回指定clazz集合
	 * 
	 * @param clazz 指定返回的类型
	 * @return
	 */
	@Override
	public <V> List<V> findBySQL(final Class<V> clazz, final String sql, final List<Object> params) {
		Query query = createSQLQuery(this.getSession(), sql, params);
		return (List<V>) (query.setResultTransformer(new AliasToBeanResultTransformer(clazz)).list());
	}

	/**
	 * SQL，返回指定map集合
	 * 
	 * @return
	 */
	@Override
	public List<Map> findMapListBySQL(final String sql, final List<Object> params) {
		Query query = createSQLQuery(this.getSession(), sql, params);
		return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	}

	/**
	 * SQL，返回单个map
	 * 
	 * @return
	 */
	@Override
	public Map getSingleMapBySQL(String sql, final List<Object> params) {
		List list = findMapListBySQL(sql, params);
		if (list != null && list.size() > 0) {
			return (Map) list.get(0);
		}
		return null;
	}


	/**
	 * 分页 SQL, 返回map类型的列表
	 * 
	 * @param sql
	 * @param countsql
	 *            获取总记录数sql,可不填写，若不写，则解析sql
	 * @param page
	 *            分页实体，其如下2属性须有值：pageNum 当前页码,pageSize 页面大小
	 * @param params
	 * @return
	 */
	@Override
	public List<Map> searchPaginatedMapBySQL(final String sql,
			final String countsql, final PagerModel page,
			final List<Object> params) {
		// 获取记录总数
		String sqlStr = countsql;
		if (sqlStr == null) {
			sqlStr = parseCountSql(sql);
		}
		logger.debug("--- page recordCount ---: " + sqlStr);
		Query query = createSQLQuery(this.getSession(), sqlStr, params);
		int recordCount = ((Number) query.uniqueResult()).intValue();
		page.setRecordCount(recordCount);

		// 获取结果集
		logger.debug("--- page recordList ---: " + sql);
		query = createSQLQuery(this.getSession(), sql, params);
		int begin = PagerModel.getFirstResult(page.getCurrentPage(), page
				.getPageSize());
		query.setFirstResult(begin) //
				.setMaxResults(page.getPageSize()) //
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP) //
				.list();
		List recordList = query.list();
		page.setRecordList(recordList);

		// 返回
		return recordList;
	}

	/**
	 * 创建SQLQuery
	 */
	protected Query createSQLQuery(Session session, final String sql, final List<Object> params) {
		Query query = session.createSQLQuery(sql);
		if (params != null && params.size() > 0) {
			for (int i = 0; i < params.size(); i++) {
				query.setParameter(i, params.get(i));
			}
		}
		return query;
	}

	@Override
	public List<T> find(Collection ids, OrderModel... orders) {
		return find(new Criterion[] {Restrictions.in(getIdName(), ids)}, orders);
	}

}

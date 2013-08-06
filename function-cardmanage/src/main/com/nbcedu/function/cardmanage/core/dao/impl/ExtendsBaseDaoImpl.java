package com.nbcedu.function.cardmanage.core.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.nbcedu.function.cardmanage.core.dao.BaseDao;
import com.nbcedu.function.cardmanage.core.util.PagerModel;



/**
 * 原生SQL查询扩展
 * 
 * @version 1.0
 */
@SuppressWarnings("unchecked")
public abstract class ExtendsBaseDaoImpl<T extends Serializable> extends BaseDaoImpl<T> implements BaseDao<T> {
	protected final Logger logger = Logger.getLogger(this.getClass());

	/**
	 * SQL更新：insert | update | delete
	 * 
	 * @param params 参数
	 * @return 返回影响的记录条数
	 */
	public int updateBySQL(final String sql, final List<Object> params) {
		return (Integer) getHibernateTemplate().execute(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createSQLQuery(sql);
				if (params != null && params.size() > 0) {
					for (int i = 0; i < params.size(); i++) {
						query.setParameter(i, params.get(i));
					}
				}
				return query.executeUpdate();
			}
		});
	}

	/**
	 * SQL，返回指定clazz集合
	 * 
	 * @param clazz 指定返回的类型
	 * @return
	 */
	public <V> List<V> findBySQL(final Class<V> clazz, final String sql, final List<Object> params) {
		return (List<V>) getHibernateTemplate().executeFind(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException {
				Query query = session.createSQLQuery(sql);
				if (params != null && params.size() > 0) {
					for (int i = 0; i < params.size(); i++) {
						query.setParameter(i, params.get(i));
					}
				}

				return (List<V>) (query.setResultTransformer(new AliasToBeanResultTransformer(clazz)).list());
			}
		});
	}

	/**
	 * SQL，返回指定clazz单个对象
	 * 
	 * @param clazz 指定返回的类型
	 * @return
	 */
	public <V> V getSingleBySQL(Class<V> clazz, String sql, List<Object> params) {
		List list = findBySQL(clazz, sql, params);
		if (list != null && list.size() > 0) {
			return (V) list.get(0);
		}
		return null;
	}

	/**
	 * SQL，返回指定map集合
	 * 
	 * @return
	 */
	public List<Map> findMapListBySQL(final String sql, final List<Object> params) {
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException {
				Query query = session.createSQLQuery(sql);
				if (params != null && params.size() > 0) {
					for (int i = 0; i < params.size(); i++) {
						query.setParameter(i, params.get(i));
					}
				}

				return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
			}
		});
	}

	/**
	 * SQL，返回单个map
	 * 
	 * @return
	 */
	public Map getSingleMapBySQL(String sql, final List<Object> params) {
		List list = findMapListBySQL(sql, params);
		if (list != null && list.size() > 0) {
			return (Map) list.get(0);
		}
		return null;
	}

	/**
	 * 分页SQL, 返回指定clazz类型的列表
	 * 
	 * @param clazz 指定返回的类型
	 * @param sql
	 * @param countsql 获取总记录数sql,可不填写，若不写，则解析sql
	 * @param page 分页实体，其如下2属性须有值：pageNum 当前页码,pageSize 页面大小
	 * @param params
	 * @return
	 */
	public <V> List<V> searchPaginatedBySQL(final Class<V> clazz, final String sql, final String countsql, final PagerModel page,
			final List<Object> params) {
		return (List<V>) getHibernateTemplate().execute(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException {
				// 获取记录总数
				String sqlStr = countsql;
				if (sqlStr == null) {
					sqlStr = parseCountSql(sql);
				}
				logger.debug("--- page recordCount ---: " + sqlStr);
				Query query = session.createSQLQuery(sqlStr);
				if (params != null && params.size() > 0) {
					for (int i = 0; i < params.size(); i++) {
						query.setParameter(i, params.get(i));
					}
				}
				int recordCount = ((Number) query.uniqueResult()).intValue();
				page.setRecordCount(recordCount);

				// 获取结果集
				logger.debug("--- page recordList ---: " + sql);
				query = session.createSQLQuery(sql);
				if (params != null && params.size() > 0) {
					for (int i = 0; i < params.size(); i++) {
						query.setParameter(i, params.get(i));
					}
				}
				int begin = PagerModel.getFirstResult(page.getCurrentPage(), page.getPageSize());
				query.setFirstResult(begin) //
						.setMaxResults(page.getPageSize()) //
						.setResultTransformer(new AliasToBeanResultTransformer(clazz)) //
						.list();
				List recordList = query.list();
				page.setRecordList(recordList);

				// 返回
				return recordList;
			}
		});
	}

	/**
	 * 分页 SQL, 返回map类型的列表
	 * 
	 * @param sql
	 * @param countsql 获取总记录数sql,可不填写，若不写，则解析sql
	 * @param page 分页实体，其如下2属性须有值：pageNum 当前页码,pageSize 页面大小
	 * @param params
	 * @return
	 */
	public List<Map> searchPaginatedMapBySQL(final String sql, final String countsql, final PagerModel page,
			final List<Object> params) {
		return (List<Map>) getHibernateTemplate().execute(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException {
				// 获取记录总数
				String sqlStr = countsql;
				if (sqlStr == null) {
					sqlStr = parseCountSql(sql);
				}
				logger.debug("--- page recordCount ---: " + sqlStr);
				Query query = session.createSQLQuery(sqlStr);
				if (params != null && params.size() > 0) {
					for (int i = 0; i < params.size(); i++) {
						query.setParameter(i, params.get(i));
					}
				}
				int recordCount = ((Number) query.uniqueResult()).intValue();
				page.setRecordCount(recordCount);

				// 获取结果集
				logger.debug("--- page recordList ---: " + sql);
				query = session.createSQLQuery(sql);
				if (params != null && params.size() > 0) {
					for (int i = 0; i < params.size(); i++) {
						query.setParameter(i, params.get(i));
					}
				}
				int begin = PagerModel.getFirstResult(page.getCurrentPage(), page.getPageSize());
				query.setFirstResult(begin) //
						.setMaxResults(page.getPageSize()) //
						.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP) //
						.list();
				List recordList = query.list();
				page.setRecordList(recordList);

				// 返回
				return recordList;
			}
		});
	}

}

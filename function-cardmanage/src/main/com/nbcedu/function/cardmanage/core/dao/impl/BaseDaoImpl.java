package com.nbcedu.function.cardmanage.core.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.util.Assert;

import com.nbcedu.function.cardmanage.core.dao.BaseDao;
import com.nbcedu.function.cardmanage.core.exception.DBException;
import com.nbcedu.function.cardmanage.core.util.PagerModel;



/**
 * HQL实现
 * 
 * @version 1.0
 */
@SuppressWarnings("unchecked")
public abstract class BaseDaoImpl<T extends Serializable> extends HibernateDaoSupport implements BaseDao<T> {
	protected final Logger logger = Logger.getLogger(this.getClass());

	// 当前泛型实际实体类的CLASS对象
	private Class<T> persistentClass;

	public BaseDaoImpl() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	/**
	 * 获取clazz
	 * 
	 * @return
	 */
	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	/**
	 * 保存
	 * 
	 * @param entity
	 * @return
	 */
	public T save(T entity) {
		getHibernateTemplate().save(entity);
		return entity;
	}

	/**
	 * 修改
	 * 
	 * @param entity
	 * @return
	 */
	public T update(T entity) {
		getHibernateTemplate().update(entity);
		return entity;
	}

	/**
	 * 修改或者保存
	 * 
	 * @param entity
	 * @return
	 */
	public T saveOrUpdate(T entity) {
		getHibernateTemplate().saveOrUpdate(entity);
		return entity;
	}

	 /** 
	 * @param entity
	 * @return
	 */
	public T merger(T entity){
		getHibernateTemplate().merge(entity);
		return entity;
	}
	
	
	/**
	 * 创建Criteria对象.
	 * 
	 * @param criterions 可变的Restrictions条件列表,见{@link #createQuery(String,Object...)}
	 */
	public Criteria createCriteria(Criterion... criterions) {
		Criteria criteria = getSession().createCriteria(this.persistentClass);
		for (Criterion c : criterions) {
			criteria.add(c);
		}
		return criteria;
	}
	
	/**
	 * 根据属性名和属性值查询唯一对象.
	 * 
	 * @return 符合条件的唯一对象 or null if not found.
	 */
	public T findUniqueBy(String propertyName, Object value) {
		Assert.hasText(propertyName);
		return (T) createCriteria(Restrictions.eq(propertyName, value)).uniqueResult();
	}
	
	/**

	/**
	 * 根据ID查询。没有返回null
	 * 
	 * @param id
	 * @return
	 */
	public T get(Serializable id) {
		return (T) this.getHibernateTemplate().get(this.persistentClass, id);
	}

	/**
	 * 根据ID查询。没有抛异常
	 * 
	 * @param id
	 * @return
	 */
	public T load(Serializable id) {
		return (T) getHibernateTemplate().load(this.persistentClass, id);
	}

	/**
	 * 删除
	 * 
	 * @param entity
	 */
	public void remove(T entity) {
		getHibernateTemplate().delete(entity);
	}

	/**
	 * 根据ID删除
	 * 
	 * @param id
	 * @return
	 */
	public void removeById(Serializable id) {
		T entity = load(id);
		getHibernateTemplate().delete(entity);
	}

	/**
	 * HQL更新：insert | update | delete
	 * 
	 * @param hql
	 * @param params
	 * @return 返回影响的记录条数
	 */
	public int update(final String hql, final List<Object> params) {
		return (Integer) getHibernateTemplate().execute(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
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
	 * 批量插入、更新
	 * 
	 * @param objList
	 */
	public void batchUpdate(final List objList) {
		getHibernateTemplate().execute(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				for (int i = 0; i < objList.size(); i++) {
					session.saveOrUpdate(objList.get(i));
					if (i % 20 == 0) {
						session.flush();
						session.clear();
					}
				}
				return null;
			}
		});
	}

	/**
	 * 批量删除
	 * 
	 * @param entities
	 */
	public void batchDelete(Collection entities) {
		getHibernateTemplate().deleteAll(entities);
	}
	
	/**
	 * 获取全部对象
	 * 
	 * @param entityClass
	 */
	public <V> List<V> findAll(Class<V> entityClass) {
		return getHibernateTemplate().loadAll(entityClass);
	}
	
	/**
	 * 获取全部对象
	 */
	public List<T> findAll() {
		return findAll(getPersistentClass());
	}
	
	/**
	 * 获取总记录数
	 * 
	 * @return
	 */
	public int getTotalRecords() {
		return (Integer) this.getHibernateTemplate().execute(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery("select count(*) from " + getPersistentClass());
				return ((Number) query.uniqueResult()).intValue();
			}
		});
	}

	/**
	 * HQL查询
	 * 
	 * @return
	 */
	public List find(String hql, Object[] params) {
		return getHibernateTemplate().find(hql, params);
	}
	
	/**
	 * HQL查询
	 * @return
	 */
	public List find(String hql, Object params) {
		return find(hql, new Object[]{params});
	}
	/**
	 * HQL查询
	 * @return
	 */
	public List find(String hql) {
		return find(hql, null);
	}

	/**
	 * HQL，注意HQL中须写as，返回指定clazz集合
	 * 
	 * @param clazz 指定返回的类型
	 * @return
	 */
	public <V> List<V> find(final Class<V> clazz, final String hql, final List<Object> params) {
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(hql);
				if (params != null && params.size() > 0) {
					for (int i = 0; i < params.size(); i++) {
						query.setParameter(i, params.get(i));
					}
				}

				return query.setResultTransformer(new AliasToBeanResultTransformer(clazz)).list();
			}
		});
	}

	/**
	 * HQL，注意HQL中须写as，返回指定clazz单个对象
	 * 
	 * @param clazz 指定返回的类型
	 * @return
	 */
	public <V> V getSingle(Class<V> clazz, String hql, List<Object> params) {
		List list = find(clazz, hql, params);
		if (list != null && list.size() > 0) {
			return (V) list.get(0);
		}
		return null;
	}

	/**
	 * HQL，返回指定map集合
	 * 
	 * @return
	 */
	public List<Map> findMapList(final String hql, final List<Object> params) {
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(hql);
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
	 * HQL，返回单个map
	 * 
	 * @return
	 */
	public Map getSingleMap(String hql, List<Object> params) {
		List list = findMapList(hql, params);
		if (list != null && list.size() > 0) {
			return (Map) list.get(0);
		}
		return null;
	}
	
	/**
	 * 分页 HQL
	 * 
	 * @param hql
	 * @param page 分页实体，其如下2属性须有值：pageNum 当前页码,pageSize 页面大小
	 * @return
	 */
	public List searchPaginated(final String hql, final PagerModel page) {
		return searchPaginated(hql,null,page,null);
	}
	
	/**
	 * 分页 HQL
	 * 
	 * @param hql
	 * @param page 分页实体，其如下2属性须有值：pageNum 当前页码,pageSize 页面大小
	 * @param object
	 * @return
	 */
	public List searchPaginated(final String hql, final PagerModel page, final Object object) {
		List<Object> objects = new ArrayList<Object>();
		objects.add(object);
		return searchPaginated(hql,null,page, objects);
	}
	
	/**
	 * 分页 HQL
	 * 
	 * @param hql
	 * @param page 分页实体，其如下2属性须有值：pageNum 当前页码,pageSize 页面大小
	 * @param params
	 * @return
	 */
	public List searchPaginated(final String hql, final PagerModel page, final List<Object> params) {
		return searchPaginated(hql,null,page,params);
	}
	/**
	 * 分页 HQL
	 * 
	 * @param hql
	 * @param countHql 获取总记录数hql,可不填写，若不写，则解析hql
	 * @param page 分页实体，其如下2属性须有值：pageNum 当前页码,pageSize 页面大小
	 * @param params
	 * @return
	 */
	public List searchPaginated(final String hql, final String countHql, final PagerModel page, final List<Object> params) {
		return (List) getHibernateTemplate().execute(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException {
				// 获取记录总数
				String hqlStr = countHql;
				if (hqlStr == null) {
					hqlStr = parseCountSql(hql);
				}
				logger.debug("--- page recordCount ---: " + hqlStr);
				Query query = session.createQuery(hqlStr);
				if (params != null && params.size() > 0) {
					for (int i = 0; i < params.size(); i++) {
						query.setParameter(i, params.get(i));
					}
				}
				int recordCount = ((Number) query.uniqueResult()).intValue();
				page.setRecordCount(recordCount);

				// 获取结果集
				logger.debug("--- page recordList ---: " + hql);
				query = session.createQuery(hql);
				if (params != null && params.size() > 0) {
					for (int i = 0; i < params.size(); i++) {
						query.setParameter(i, params.get(i));
					}
				}
				int begin = PagerModel.getFirstResult(page.getCurrentPage(), page.getPageSize());
				query.setFirstResult(begin);
				query.setMaxResults(page.getPageSize());
				List recordList = query.list();
				page.setRecordList(recordList);

				return recordList;
			}
		});
	}

	/**
	 * 分页 HQL, HQL中须用as别名, 返回指定clazz类型的列表
	 * 
	 * @param clazz 指定返回的类型
	 * @param hql
	 * @param countHql 获取总记录数hql,可不填写，若不写，则解析hql
	 * @param page 分页实体，其如下2属性须有值：pageNum 当前页码,pageSize 页面大小
	 * @param params
	 * @return
	 */
	public <V> List<V> searchPaginated(final Class<V> clazz, final String hql, final String countHql, final PagerModel page,
			final List<Object> params) {
		return (List<V>) getHibernateTemplate().execute(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException {
				// 获取记录总数
				String hqlStr = countHql;
				if (hqlStr == null) {
					hqlStr = parseCountSql(hql);
				}
				logger.debug("--- page recordCount ---: " + hqlStr);
				Query query = session.createQuery(hqlStr);
				if (params != null && params.size() > 0) {
					for (int i = 0; i < params.size(); i++) {
						query.setParameter(i, params.get(i));
					}
				}
				int recordCount = ((Number) query.uniqueResult()).intValue();
				page.setRecordCount(recordCount);

				// 获取结果集
				logger.debug("--- page recordList ---: " + hql);
				query = session.createQuery(hql);
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

				return recordList;
			}
		});
	}

	/**
	 * 分页 HQL, HQL中须用as别名, 返回map类型的列表
	 * 
	 * @param hql
	 * @param countHql 获取总记录数hql,可不填写，若不写，则解析hql
	 * @param page 分页实体，其如下2属性须有值：pageNum 当前页码,pageSize 页面大小
	 * @param params
	 * @return
	 */
	public List<Map> searchPaginatedMap(final String hql, final String countsql, final PagerModel page, final List<Object> params) {
		return (List<Map>) getHibernateTemplate().execute(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException {
				// 获取记录总数
				String hqlStr = countsql;
				if (hqlStr == null) {
					hqlStr = parseCountSql(hql);
				}
				logger.debug("--- page recordCount ---: " + hqlStr);
				Query query = session.createQuery(hqlStr);
				if (params != null && params.size() > 0) {
					for (int i = 0; i < params.size(); i++) {
						query.setParameter(i, params.get(i));
					}
				}
				int recordCount = ((Number) query.uniqueResult()).intValue();
				page.setRecordCount(recordCount);

				// 获取结果集
				logger.debug("--- page recordList ---: " + hql);
				query = session.createQuery(hql);
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

				return recordList;
			}
		});
	}

	/**
	 * 解析查询总数sql <br/>
	 * 如果语句中已包含count函数，则不作任务解析，否则，按一般规则解析统计语句
	 * 
	 * @param sql
	 */
	protected String parseCountSql(String sql) {
		Pattern p = Pattern.compile("^.+count.+from.+$", Pattern.CASE_INSENSITIVE); 
		Matcher m = p.matcher(sql);
		if(!m.matches()){
			sql = sql.replaceAll("\\s+", " ");
			int startIndex = sql.toUpperCase().indexOf("FROM ");
			if (startIndex == -1){
				String info = "不合法的分页查询总数SQL/HQL： " + sql;
				logger.debug(info);
				throw new DBException(info);
			}
			
			sql = "SELECT COUNT(*) " + sql.substring(startIndex);
		}
		
		return sql;
	}
}

package com.nbcedu.function.syllabus.devcore.dao.impl;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;

import com.nbcedu.function.syllabus.devcore.dao.HibernateBaseDao;
import com.nbcedu.function.syllabus.devcore.exception.SystemException;
import com.nbcedu.function.syllabus.devcore.util.MatchType;
import com.nbcedu.function.syllabus.devcore.util.OrderModel;
import com.nbcedu.function.syllabus.devcore.util.PagerModel;


/**
 * HQL实现
 * 
 * @author qinyuan
 * @version 1.0
 */
@SuppressWarnings("unchecked")
public abstract class HibernateBaseDaoImpl<T extends Serializable> implements HibernateBaseDao<T> {
	protected final Logger logger = Logger.getLogger(this.getClass());
	@Autowired(required=true)
	protected SessionFactory sessionFactory;
	protected Session getSession(){
		return this.sessionFactory.getCurrentSession();
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	// 当前泛型实际实体类的CLASS对象
	private Class<T> persistentClass;

	public HibernateBaseDaoImpl() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	/**
	 * 获取clazz
	 */
	@Override
	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	/**
	 * 取得对象的主键名
	 */
	public String getIdName() {
		ClassMetadata meta = this.getSession().getSessionFactory()
				.getClassMetadata(persistentClass);
		return meta.getIdentifierPropertyName();
	}

	/**
	 * 初始化代对象Proxy，例：<br/>
	 * Hibernate.initialize(user.getRoles())，初始化User的直接属性和关联集合
	 */
	public void initProxyObject(Object proxy) {
		Hibernate.initialize(proxy);
	}

	/**
	 * 保存
	 */
	@Override
	public T save(T entity) {
		this.getSession().save(entity);
		return entity;
	}

	/**
	 * 修改
	 */
	@Override
	public T update(T entity) {
		this.getSession().update(entity);
		return entity;
	}

	/**
	 * 修改或者保存
	 */
	@Override
	public T saveOrUpdate(T entity) {
		this.getSession().saveOrUpdate(entity);
		return entity;
	}

	/**
	 * merge
	 */
	@Override
	public T merge(T entity) {
		this.getSession().merge(entity);
		return entity;
	}
	/**
	 * 根据ids删除
	 */
	public void removeByIds(Serializable... ids) {
		List<Object> tempIds = new ArrayList<Object>();
		for (Serializable id : ids) {
			tempIds.add(id);
		}

		update("DELETE FROM " + this.persistentClass + " o WHERE o." + getIdName() + " in (?) ", tempIds);
	}

	/**
	 * flush
	 */
	@Override
	public void flush() {
		this.getSession().flush();
	}

	/**
	 * refresh刷新
	 */
	@Override
	public void refresh(T entity) {
		this.getSession().refresh(entity);
	}

	/**
	 * clear
	 */
	@Override
	public void clear() {
		this.getSession().clear();
	}

	/**
	 * evict
	 */
	@Override
	public void evict(T entity) {
		this.getSession().evict(entity);
	}

	/**
	 * 根据ID查询。没有返回null
	 */
	@Override
	public T get(Serializable id) {
		return (T) this.getSession().get(this.persistentClass, id);
	}

	/**
	 * 根据ID查询。没有抛异常
	 */
	@Override
	public T load(Serializable id) {
		return (T) this.getSession().load(this.persistentClass, id);
	}

	/**
	 * 删除
	 */
	@Override
	public void remove(T entity) {
		this.getSession().delete(entity);
	}

	/**
	 * 根据ID删除
	 */
	@Override
	public void removeById(Serializable id) {
		this.getSession().delete(load(id));
	}

	/**
	 * 按id列表，获取对象列表
	 */
	public List<T> get(final Collection ids) {
		return find(Restrictions.in(getIdName(), ids));
	}

	/**
	 * 获取全部对象
	 */
	@Override
	public List<T> findAll() {
		return find();
	}

	/**
	 * HQL查询
	 */
	@Override
	public List find(String hql, Object[] params) {
		Query queryObject = this.getSession().createQuery(hql);
		if (params != null && params.length > 0) {
			for (int i = 0, end = params.length; i < end; i++) {
				queryObject.setParameter(i, params[i]);
			}
		}
		return queryObject.list();
	}
	/**
	 * 按Criteria查询对象列表.
	 */
	public List<T> find(Criterion[] criterions, OrderModel... orders) {
		return createCriteria(criterions, orders).list();
	}

	/**
	 * HQL查询
	 */
	@Override
	public List find(String hql, Object params) {
		return find(hql, new Object[] {params});
	}

	/**
	 * HQL查询
	 */
	@Override
	public List find(String hql) {
		return find(hql, null);
	}

	/**
	 * 按Criteria查询对象列表.
	 */
	public List<T> find(final Criterion... criterions) {
		return createCriteria(criterions).list();
	}

	/**
	 * 按属性查找对象列表,支持多种匹配方式.
	 * 
	 * @param matchType 匹配方式enum
	 */
	public List<T> findBy(final String propertyName, final Object value, final MatchType matchType) {
		Criterion criterion = buildCriterion(propertyName, value, matchType);
		return find(criterion);
	}

	/**
	 * 按属性查找对象列表, 匹配方式为相等.
	 */
	public List<T> findBy(final String propertyName, final Object value) {
		return findBy(propertyName, value, MatchType.EQ);
	}

	/**
	 * 按属性查找唯一对象
	 */
	public T findUniqueBy(String propertyName, Object value) {
		List list = findBy(propertyName, value);
		if (list != null && list.size() > 0) {
			return (T) list.get(0);
		}
		return null;
	}

	/**
	 * 根据对象已赋值的属性值，判断对象在数据库内是否唯一。返回true:唯一，false:不唯一
	 */
	public boolean isPropertyUnique(final T entity) {
		Criteria criteria = this.getSession().createCriteria(persistentClass);
		String idName = getIdName();
		try {
			BeanInfo bif = Introspector.getBeanInfo(persistentClass);
			PropertyDescriptor[] pds = bif.getPropertyDescriptors();
			for (PropertyDescriptor pd : pds) {
				String propertyName = pd.getName();
				Object propertyValue = pd.getReadMethod().invoke(entity);
				// 如果id!=null,说明对象已存在,加入排除自身的判断
				if (propertyValue != null) {
					if (propertyName.equals(idName)) {
						criteria.add(Restrictions.not(Restrictions.eq(
								propertyName, propertyValue)));
					} else {
						if (!"class".equals(propertyName)) {
							criteria.add(Restrictions.eq(propertyName,
									propertyValue));
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("解析对象属性错误！" + e.getMessage());
			throw new SystemException(e.getMessage());
		}

		return criteria.list().size() <= 0;
	}

	/**
	 * HQL添加/更新/删除：insert | update | delete
	 */
	@Override
	public int update(final String hql, final List<Object> params) {
		Query queryObject = this.getSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (int i = 0, end = params.size(); i < end; i++) {
				queryObject.setParameter(i, params.get(i));
			}
		}
		return queryObject.executeUpdate();
	}

	/**
	 * 批量:插入/更新
	 */
	@Override
	public void batchUpdate(final List objList) {
		for (int i = 0, end = objList.size(); i < end; i++) {
			this.getSession().saveOrUpdate(objList.get(i));
			if (i % 20 == 0) {
				this.getSession().flush();
				this.getSession().clear();
			}
		}
	}

	/**
	 * 批量删除
	 */
	@Override
	public void batchDelete(Collection entities) {
		for (Object object : entities) {
			this.getSession().delete(object);
		}
	}

	/**
	 * 获取总记录数
	 */
	@Override
	public int getTotalRecords() {
		Query query = this.getSession().createQuery(
				"select count(*) from " + getPersistentClass());
		return ((Number) query.uniqueResult()).intValue();
	}



	/**
	 * HQL，返回指定map集合
	 */
	@Override
	public List<Map> findMapList(final String hql, final List<Object> params) {
		Query query = createQuery(this.getSession(), hql, params);
		return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	}

	/**
	 * HQL，返回单个map。否则返回null
	 */
	@Override
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
	 * @param countHql 获取总记录数hql,可不填写，若不写，则解析hql
	 * @param page 分页实体，其如下2属性须有值：pageNum 当前页码,pageSize 页面大小
	 * @param params
	 */
	@Override
	public List searchPaginated(final String hql, final String countHql, final PagerModel page, final List<Object> params) {
		// 获取记录总数
		String hqlStr = countHql;
		if (hqlStr == null) {
			hqlStr = parseCountSql(hql);
		}
		logger.debug("--- page recordCount ---: " + hqlStr);
		Query query = createQuery(this.getSession(), hqlStr, params);
		int recordCount = ((Number) query.uniqueResult()).intValue();
		page.setRecordCount(recordCount);

		// 获取结果集
		logger.debug("--- page recordList ---: " + hql);
		query = createQuery(this.getSession(), hql, params);
		int begin = PagerModel.getFirstResult(page.getCurrentPage(), page
				.getPageSize());
		query.setFirstResult(begin);
		query.setMaxResults(page.getPageSize());
		List recordList = query.list();
		page.setRecordList(recordList);

		return recordList;
	}


	/**
	 * 分页 HQL, HQL中须用as别名, 返回map类型的列表
	 * 
	 * @param hql
	 * @param countHql 获取总记录数hql,可不填写，若不写，则解析hql
	 * @param page 分页实体，其如下2属性须有值：pageNum 当前页码,pageSize 页面大小
	 * @param params
	 */
	@Override
	public List<Map> searchPaginatedMap(final String hql, final String countsql, final PagerModel page, final List<Object> params) {
		// 获取记录总数
		String hqlStr = countsql;
		if (hqlStr == null) {
			hqlStr = parseCountSql(hql);
		}
		logger.debug("--- page recordCount ---: " + hqlStr);
		Query query = createQuery(this.getSession(), hqlStr, params);
		int recordCount = ((Number) query.uniqueResult()).intValue();
		page.setRecordCount(recordCount);

		// 获取结果集
		logger.debug("--- page recordList ---: " + hql);
		query = createQuery(this.getSession(), hql, params);
		int begin = PagerModel.getFirstResult(page.getCurrentPage(), page
				.getPageSize());
		query.setFirstResult(begin) //
				.setMaxResults(page.getPageSize()) //
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP) //
				.list();
		List recordList = query.list();
		page.setRecordList(recordList);

		return recordList;
	}

	/**
	 * 按属性条件参数创建Criterion,辅助函数.
	 */
	protected Criterion buildCriterion(final String propertyName, final Object propertyValue, final MatchType matchType) {
		Criterion criterion = null;
		switch (matchType) {
		case EQ:
			criterion = Restrictions.eq(propertyName, propertyValue);
			break;
		case LIKE:
			criterion = Restrictions.like(propertyName, (String) propertyValue, MatchMode.ANYWHERE);
			break;
		case LE:
			criterion = Restrictions.le(propertyName, propertyValue);
			break;
		case LT:
			criterion = Restrictions.lt(propertyName, propertyValue);
			break;
		case GE:
			criterion = Restrictions.ge(propertyName, propertyValue);
			break;
		case GT:
			criterion = Restrictions.gt(propertyName, propertyValue);
		}
		return criterion;
	}

	/**
	 * 解析查询总数sql，不能适用于有子查询的sql语句 <br/>
	 * 如果语句中已包含count函数，则不作任务解析，否则，按一般规则解析统计语句
	 */
	protected String parseCountSql(String sql) {
		Pattern p = Pattern.compile("^.+count.+from.+$", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(sql);
		if (!m.matches()) {
			sql = sql.replaceAll("\\s+", " ");
			int startIndex = sql.toUpperCase().indexOf("FROM ");
			if (startIndex == -1) {
				logger.error("不合法的分页查询总数SQL/HQL： " + sql);
				throw new SystemException("不合法的分页查询总数SQL/HQL： " + sql);
			}

			sql = "SELECT COUNT(*) " + sql.substring(startIndex);
		}

		return sql;
	}

	/**
	 * 创建Query对象
	 */
	protected Query createQuery(Session session, final String hql, final List<Object> params) {
		Query query = session.createQuery(hql);
		if (params != null && params.size() > 0) {
			for (int i = 0; i < params.size(); i++) {
				query.setParameter(i, params.get(i));
			}
		}
		return query;
	}

	/**
	 * 创建Criteria
	 */
	protected Criteria createCriteria(final Criterion... criterions) {
		Criteria criteria = this.getSession().createCriteria(persistentClass);
		for (Criterion c : criterions) {
			criteria.add(c);
		}
		return criteria;
	}

	/**
	 * 创建Criteria
	 * 
	 * @param orders 排序字段
	 */
	public Criteria createCriteria(final Criterion[] criterions,
			final OrderModel... orders) {
		Criteria criteria = getSession().createCriteria(persistentClass);
		if (criterions != null && criterions.length > 0) {
			for (Criterion c : criterions) {
				criteria.add(c);
			}
		}

		for (OrderModel c : orders) {
			if (OrderModel.ASC.equals(c.getOrder().toLowerCase())) {
				criteria.addOrder(Order.asc(c.getOrderBy()));
			} else if (OrderModel.DESC.equals(c.getOrder().toLowerCase())) {
				criteria.addOrder(Order.desc(c.getOrderBy()));
			} else {
				String errorInfo = "不合法的排序字段 " + c.getOrderBy() + " : "
						+ c.getOrder();
				logger.error(errorInfo);
				throw new SystemException(errorInfo);
			}
		}

		return criteria;
	}
}

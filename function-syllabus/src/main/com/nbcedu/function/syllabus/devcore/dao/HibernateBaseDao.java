package com.nbcedu.function.syllabus.devcore.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Criterion;

import com.nbcedu.function.syllabus.devcore.util.MatchType;
import com.nbcedu.function.syllabus.devcore.util.OrderModel;
import com.nbcedu.function.syllabus.devcore.util.PagerModel;

/**
 * BaseDao
 * 
 * @author qinyuan
 * @version 1.0
 */
@SuppressWarnings("unchecked")
public interface HibernateBaseDao<T extends Serializable> {
	/**
	 * 获取clazz
	 */
	Class<T> getPersistentClass();

	/**
	 * 取得对象的主键名
	 */
	String getIdName();

	/**
	 * 初始化代对象Proxy，例：<br/>
	 * Hibernate.initialize(user.getRoles())，初始化User的直接属性和关联集合
	 */
	void initProxyObject(Object proxy);

	/**
	 * 保存
	 */
	T save(T entity);

	/**
	 * 修改
	 */
	T update(T entity);

	/**
	 * 修改或者保存
	 */
	T saveOrUpdate(T entity);

	/**
	 * merge
	 */
	T merge(T entity);

	/**
	 * flush
	 */
	void flush();

	/**
	 * refresh刷新
	 */
	void refresh(T entity);

	/**
	 * clear
	 */
	void clear();

	/**
	 * evict
	 */
	void evict(T entity);

	/**
	 * 根据ID查询。没有返回null
	 */
	T get(Serializable id);

	/**
	 * 根据ID查询。没有抛异常
	 */
	T load(Serializable id);

	/**
	 * 删除
	 */
	void remove(T entity);

	/**
	 * 根据ID删除
	 */
	void removeById(Serializable id);

	/**
	 * 按id列表，获取对象列表
	 */
	List<T> get(final Collection ids);

	/**
	 * 获取全部对象
	 */
	List<T> findAll();

	/**
	 * HQL查询
	 */
	List find(String hql, Object[] params);

	/**
	 * HQL查询
	 */
	List find(String hql, Object params);

	/**
	 * HQL查询
	 */
	List find(String hql);

	/**
	 * 按Criteria查询对象列表.
	 */
	List<T> find(final Criterion... criterions);

	/**
	 * 按属性查找对象列表,支持多种匹配方式.
	 * 
	 * @param matchType 匹配方式enum
	 */
	List<T> findBy(final String propertyName, final Object value, final MatchType matchType);

	/**
	 * 按属性查找对象列表, 匹配方式为相等.
	 */
	List<T> findBy(final String propertyName, final Object value);

	/**
	 * 按属性查找唯一对象
	 */
	public T findUniqueBy(String propertyName, Object value);

	/**
	 * 根据对象已赋值的属性值，判断对象在数据库内是否唯一。返回true:唯一，false:不唯一
	 */
	public boolean isPropertyUnique(final T entity);

	/**
	 * HQL添加/更新/删除：insert | update | delete
	 */
	int update(final String hql, final List<Object> params);

	/**
	 * 批量:插入/更新
	 */
	void batchUpdate(final List objList);

	/**
	 * 批量删除
	 */
	void batchDelete(Collection entities);

	/**
	 * 获取总记录数
	 */
	int getTotalRecords();

	/**
	 * HQL，返回指定map集合
	 */
	List<Map> findMapList(final String hql, final List<Object> params);

	/**
	 * HQL，返回单个map。否则返回null
	 */
	Map getSingleMap(String hql, List<Object> params);

	/**
	 * 分页 HQL
	 * 
	 * @param hql
	 * @param countHql 获取总记录数hql,可不填写，若不写，则解析hql
	 * @param page 分页实体，其如下2属性须有值：pageNum 当前页码,pageSize 页面大小
	 * @param params
	 */
	List searchPaginated(final String hql, final String countHql, final PagerModel page, final List<Object> params);


	/**
	 * 分页 HQL, HQL中须用as别名, 返回map类型的列表
	 * 
	 * @param hql
	 * @param countHql 获取总记录数hql,可不填写，若不写，则解析hql
	 * @param page 分页实体，其如下2属性须有值：pageNum 当前页码,pageSize 页面大小
	 * @param params
	 */
	List<Map> searchPaginatedMap(final String hql, final String countsql, final PagerModel page, final List<Object> params);

	/******************************************************************************************************************/
	/****************************************************** 原生SQL扩展 ************************************************/
	/******************************************************************************************************************/

	/**
	 * SQL更新：insert | update | delete
	 * 
	 * @param params 参数
	 * @return 返回影响的记录条数
	 */
	int updateBySQL(final String sql, final List<Object> params);

	/**
	 * SQL，返回指定clazz集合
	 * 
	 * @param clazz 指定返回的类型
	 * @return
	 */
	<V> List<V> findBySQL(final Class<V> clazz, final String sql, final List<Object> params);

	/**
	 * SQL，返回指定map集合
	 * 
	 * @return
	 */
	List<Map> findMapListBySQL(final String sql, final List<Object> params);

	/**
	 * SQL，返回单个map
	 * 
	 * @return
	 */
	Map getSingleMapBySQL(String sql, final List<Object> params);


	/**
	 * 分页 SQL, 返回map类型的列表
	 * 
	 * @param sql
	 * @param countsql 获取总记录数sql,可不填写，若不写，则解析sql
	 * @param page 分页实体，其如下2属性须有值：pageNum 当前页码,pageSize 页面大小
	 * @param params
	 * @return
	 */
	List<Map> searchPaginatedMapBySQL(final String sql, final String countsql, final PagerModel page, final List<Object> params);

	void removeByIds(Serializable... ids);
	/**
	 * 按id列表，获取对象列表
	 * 
	 * @param orders 排序字段
	 */
	List<T> find(Collection ids,OrderModel... orders);
}
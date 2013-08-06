package com.nbcedu.function.cardmanage.core.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.nbcedu.function.cardmanage.core.util.PagerModel;

@SuppressWarnings("unchecked")
public interface BaseDao<T extends Serializable> {
	/**
	 * 获取clazz
	 * 
	 * @return
	 */
	Class<T> getPersistentClass();

	/**
	 * 保存
	 * 
	 * @param entity
	 * @return
	 */
	T save(T entity);

	/**
	 * 修改
	 * 
	 * @param entity
	 * @return
	 */
	T update(T entity);

	/**
	 * 修改或者保存
	 * 
	 * @param entity
	 * @return
	 */
	T saveOrUpdate(T entity);
	
	/**
	 * 修改或者保存
	 * 
	 * @param entity
	 * @return
	 */
	T merger(T entity);
	/**
	 * 唯一属性查询
	 * @author 黎青春
	 * @param propertyName
	 * @param value
	 * @return
	 */
	T findUniqueBy(String propertyName, Object value);
	/**
	 * 根据ID查询。没有返回null
	 * 
	 * @param id
	 * @return
	 */
	T get(Serializable id);

	/**
	 * 根据ID查询。没有抛异常
	 * 
	 * @param id
	 * @return
	 */
	T load(Serializable id);

	/**
	 * 删除
	 * 
	 * @param entity
	 */
	void remove(T entity);

	/**
	 * 根据ID删除
	 * 
	 * @param id
	 * @return
	 */
	void removeById(Serializable id);

	/**
	 * HQL更新：insert | update | delete
	 * 
	 * @param hql
	 * @param params
	 * @return 返回影响的记录条数
	 */
	int update(final String hql, final List<Object> params);

	/**
	 * 批量插入、更新
	 * 
	 * @param objList
	 */
	void batchUpdate(final List objList);

	/**
	 * 批量删除
	 * 
	 * @param entities
	 */
	void batchDelete(Collection entities);
	
	/**
	 * 获取全部对象
	 * 
	 * @param entityClass
	 */
	public <V> List<V> findAll(Class<V> entityClass);
	
	/**
	 * 获取全部对象
	 */
	public List<T> findAll();
	
	/**
	 * 获取总记录数
	 * 
	 * @return
	 */
	int getTotalRecords();

	/**
	 * HQL查询
	 * 
	 * @return
	 */
	List find(String hql, Object[] params);
	/**
	 * HQL查询
	 * @return
	 */
	List find(String hql, Object param);
	/**
	 * HQL查询
	 * @return
	 */
	List find(String hql);

	/**
	 * HQL，注意HQL中须写as，返回指定clazz集合
	 * 
	 * @param clazz 指定返回的类型
	 * @return
	 */
	<V> List<V> find(final Class<V> clazz, final String hql, final List<Object> params);

	/**
	 * HQL，注意HQL中须写as，返回指定clazz单个对象
	 * 
	 * @param clazz 指定返回的类型
	 * @return
	 */
	<V> V getSingle(Class<V> clazz, String hql, List<Object> params);

	/**
	 * HQL，返回指定map集合
	 * 
	 * @return
	 */
	List<Map> findMapList(final String hql, final List<Object> params);

	/**
	 * HQL，返回单个map
	 * 
	 * @return
	 */
	Map getSingleMap(String hql, List<Object> params);

	/**
	 * 分页 HQL
	 * 
	 * @param hql
	 * @param countHql 获取总记录数hql,可不填写，若不写，则解析hql
	 * @param page 分页实体，其如下2属性须有值：pageNum 当前页码,pageSize 页面大小
	 * @param params
	 * @return
	 */
	List searchPaginated(final String hql, final String countHql, final PagerModel page, final List<Object> params);

	/**
	 * 分页 HQL
	 * @param hql
	 * @param page 分页实体，其如下2属性须有值：pageNum 当前页码,pageSize 页面大小
	 * @return
	 */
	public List searchPaginated(final String hql, final PagerModel page);
	
	/**
	 * 分页 HQL
	 * 
	 * @param hql
	 * @param page 分页实体，其如下2属性须有值：pageNum 当前页码,pageSize 页面大小
	 * @param object
	 * @return
	 */
	public List searchPaginated(final String hql, final PagerModel page, final Object object);
	
	/**
	 * 分页 HQL
	 * 
	 * @param hql
	 * @param page 分页实体，其如下2属性须有值：pageNum 当前页码,pageSize 页面大小
	 * @param params
	 * @return
	 */
	List searchPaginated(final String hql, final PagerModel page, final List<Object> params);

	
	/**
	 * 分页 HQL, <span style="color:red">HQL中须用as别名</span>, 返回指定clazz类型的列表
	 * 
	 * @param clazz 指定返回的类型
	 * @param hql
	 * @param countHql 获取总记录数hql,可不填写，若不写，则解析hql
	 * @param page 分页实体，其如下2属性须有值：pageNum 当前页码,pageSize 页面大小
	 * @param params
	 * @return
	 */
	<V> List<V> searchPaginated(final Class<V> clazz, final String hql, final String countHql, final PagerModel page,
			final List<Object> params);

	/**
	 * 分页 HQL, <span style="color:red">HQL中须用as别名</span>, 返回map类型的列表
	 * 
	 * @param hql
	 * @param countHql 获取总记录数hql,可不填写，若不写，则解析hql
	 * @param page 分页实体，其如下2属性须有值：pageNum 当前页码,pageSize 页面大小
	 * @param params
	 * @return
	 */
	List<Map> searchPaginatedMap(final String hql, final String countsql, final PagerModel page, final List<Object> params);

	/****************************************************** 原生SQL扩展 ************************************************/

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
	 * SQL，返回指定clazz单个对象
	 * 
	 * @param clazz 指定返回的类型
	 * @return
	 */
	<V> V getSingleBySQL(Class<V> clazz, String sql, List<Object> params);

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
	 * 分页SQL, 返回指定clazz类型的列表
	 * 
	 * @param clazz 指定返回的类型
	 * @param sql
	 * @param countsql 获取总记录数sql,可不填写，若不写，则解析sql
	 * @param page 分页实体，其如下2属性须有值：pageNum 当前页码,pageSize 页面大小
	 * @param params
	 * @return
	 */
	<V> List<V> searchPaginatedBySQL(final Class<V> clazz, final String sql, final String countsql, final PagerModel page,
			final List<Object> params);
	
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
}
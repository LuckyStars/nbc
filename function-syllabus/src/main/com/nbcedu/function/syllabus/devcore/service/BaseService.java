package com.nbcedu.function.syllabus.devcore.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * BaseBiz
 * 
 * @author qinyuan
 * @version 1.0
 */
@SuppressWarnings("unchecked")
public interface BaseService<T extends Serializable> {

	/**
	 * 初始化代对象Proxy
	 */
	void initProxyObject(Object proxy);

	/**
	 * 保存
	 */
	T add(T entity);

	/**
	 * 修改
	 */
	T modify(T entity);

	/**
	 * 修改或者保存
	 */
	T saveOrUpdate(T entity);

	/**
	 * merge
	 */
	T merge(T entity);

	/**
	 * 根据ID查询。没有返回null
	 */
	T get(Serializable id);

	/**
	 * 删除
	 */
	void remove(T entity);

	/**
	 * 根据ID删除
	 */
	void removeById(Serializable id);

	/**
	 * flush
	 */
	public void flush();

	/**
	 * refresh刷新
	 */
	public void refresh(T entity);

	/**
	 * clear
	 */
	public void clear();

	/**
	 * evict
	 */
	public void evict(T entity);

	/**
	 * 获取所有LIst集合
	 */
	List<T> findAll();

	/**
	 * 按属性查找对象列表, 匹配方式为相等
	 */
	List<T> findBy(String propertyName, Object value);

	/**
	 * 按属性查找唯一对象
	 */
	public T findUniqueBy(String propertyName, Object value);

	/**
	 * 根据对象已赋值的属性值，判断对象在数据库内是否唯一。返回true:唯一，false:不唯一
	 */
	boolean getBooleanUnique(T entity);

	/**
	 * 根据属性删除对象
	 */
	public void removeByProperty(String property, Object value);

	/**
	 * 批量:插入/更新
	 */
	void modifyBatch(List objList);

	/**
	 * 批量删除
	 */
	void deleteBatch(Collection entities);

	/**
	 * 获取总记录数
	 */
	int getTotalRecords();

	void removeInLogicByIds(Serializable[] id);

	void removeInLogicById(Serializable id);

	List<T> findAllAvaliable();

}
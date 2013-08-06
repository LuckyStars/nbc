package com.nbcedu.function.cardmanage.core.biz;

import java.io.Serializable;
import java.util.List;

public interface BaseBiz<T extends Serializable> {

	/**
	 * 保存
	 * 
	 * @param entity
	 * @return
	 */
	T add(T entity);

	/**
	 * 修改
	 * 
	 * @param entity
	 * @return
	 */
	T modify(T entity);

	/**
	 * 根据ID查询。没有返回null
	 * 
	 * @param id
	 * @return
	 */
	T get(Serializable id);

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
	
	void removeByProperty(String property,Object value);
	
	/**
	 * 获取所有LIst集合
	 * 
	 * @return
	 */
	public List<T> findAll();
	/**
	 * 批量增加
	 * @param list
	 * @author xuechong
	 */
	public void addAll(List<T> list);
	
}
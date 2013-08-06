package com.nbcedu.function.cardmanage.core.biz.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;

import com.nbcedu.function.cardmanage.core.biz.BaseBiz;
import com.nbcedu.function.cardmanage.core.dao.BaseDao;


/**
 * 常用业务方法
 * 
 * @param <T>
 */
public abstract class BaseBizImpl<T extends Serializable> implements BaseBiz<T> {
	protected final Logger logger = Logger.getLogger(this.getClass());

	private BaseDao<T> dao;

	/**
	 * 保存
	 * 
	 * @param entity
	 * @return
	 */
	public T add(T entity) {
		return dao.save(entity);
	}

	/**
	 * 修改
	 * 
	 * @param entity
	 * @return
	 */
	public T modify(T entity) {
		return dao.update(entity);
	}

	/**
	 * 根据ID查询。没有返回null
	 * 
	 * @param id
	 * @return
	 */
	public T get(Serializable id) {
		return dao.get(id);
	}

	/**
	 * 删除
	 * 
	 * @param entity
	 */
	public void remove(T entity) {
		dao.remove(entity);
	}

	/**
	 * 根据ID删除
	 * 
	 * @param id
	 * @return
	 */
	public void removeById(Serializable id) {
		dao.removeById(id);
	}
	
	/**
	 * 获取所有LIst集合
	 * 
	 * @return
	 */
	public List<T> findAll(){
		return dao.findAll();
	}
	@Override
	public void removeByProperty(String property, Object value) {
		this.dao.remove(this.dao.findUniqueBy(property, value));
	}
	@Override
	public void addAll(List<T> list) {
		for (T t :list) {
			dao.saveOrUpdate(t);
		}
	}
	/********* getter/setter *********/
	public BaseDao<T> getDao() {
		return dao;
	}

	public void setDao(BaseDao<T> dao) {
		this.dao = dao;
	}

}

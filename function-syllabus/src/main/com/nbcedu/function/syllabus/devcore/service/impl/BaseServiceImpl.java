package com.nbcedu.function.syllabus.devcore.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nbcedu.function.syllabus.devcore.dao.HibernateBaseDao;
import com.nbcedu.function.syllabus.devcore.model.Deleteable;
import com.nbcedu.function.syllabus.devcore.service.BaseService;

/**
 * 常用业务方法
 * 
 * @author qinyuan
 * @version 1.0
 */
@SuppressWarnings("unchecked")
@Transactional(rollbackFor=Exception.class)
public class BaseServiceImpl<T extends Serializable> implements BaseService<T> {
	protected final Logger logger = Logger.getLogger(this.getClass());

	private HibernateBaseDao<T> dao;

	/**
	 * 初始化代对象Proxy
	 */
	@Transactional(readOnly=true)
	public void initProxyObject(Object proxy) {
		dao.initProxyObject(proxy);
	}

	/**
	 * 保存
	 */
	public T add(T entity)  {
		return dao.save(entity);
	}
	
	/**
	 * 修改
	 */
	public T modify(T entity) {
		return dao.update(entity);
	}

	/**
	 * 修改或者保存
	 */
	public T saveOrUpdate(T entity) {
		return dao.saveOrUpdate(entity);
	}

	/**
	 * merge
	 */
	public T merge(T entity) {
		return dao.merge(entity);
	}

	/**
	 * 根据ID查询。没有返回null
	 */
	@Transactional(readOnly=true)
	public T get(Serializable id) {
		return dao.get(id);
	}

	/**
	 * 删除
	 */
	public void remove(T entity) {
		dao.remove(entity);
	}

	/**
	 * 根据ID删除
	 */
	public void removeById(Serializable id) {
		dao.removeById(id);
	}
	
	@Override
	public void removeInLogicById(Serializable id) {
		T obj = dao.findUniqueBy("id", id);
		if(obj==null){return;}
		if(obj instanceof Deleteable){
			Deleteable deleteAbleObj = (Deleteable)obj;
			deleteAbleObj.setDeleteFlag(Deleteable.REMOVED);
			dao.update((T) deleteAbleObj);
		}else{
			throw new IllegalArgumentException(obj.getClass() + "逻辑删除单个时参数不合法,此类型没有实现Deleteable接口");
		}
	}
	/**
	 * 根据ids删除
	 */
	public void removeByIds(Serializable... ids) {
		dao.removeByIds(ids);
	}
	@Override
	public void removeInLogicByIds(Serializable... id) {
		if(id==null||id.length<=0){return;}
		for (Serializable serId : id) {
			removeById(serId);
		}
	}
	/**
	 * flush
	 */
	@Transactional(readOnly=true)
	public void flush() {
		dao.flush();
	}

	/**
	 * refresh刷新
	 */
	public void refresh(T entity) {
		dao.refresh(entity);
	}

	/**
	 * clear
	 */
	public void clear() {
		dao.clear();
	}

	/**
	 * evict
	 */
	public void evict(T entity) {
		dao.evict(entity);
	}

	/**
	 * 按id列表，获取对象列表
	 */
	@Transactional(readOnly=true)
	public List<T> find(Collection ids) {
		return dao.find(ids);
	}

	/**
	 * 获取所有LIst集合
	 */
	@Transactional(readOnly=true)
	public List<T> findAll() {
		return dao.findAll();
	}
	@Override
	@Transactional(readOnly=true)
	public List<T> findAllAvaliable() {
		return dao.findBy("deleteFlag", Deleteable.ENABLED, null);
	}

	/**
	 * 按属性查找唯一对象
	 */
	@Transactional(readOnly=true)
	public T findUniqueBy(String propertyName, Object value) {
		return dao.findUniqueBy(propertyName, value);
	}

	/**
	 * 根据对象已赋值的属性值，判断对象在数据库内是否唯一。返回true:唯一，false:不唯一
	 */
	@Transactional(readOnly=true)
	public boolean getBooleanUnique(T entity) {
		return dao.isPropertyUnique(entity);
	}

	/**
	 * 根据属性删除对象
	 */
	public void removeByProperty(String property, Object value) {
		List<T> list = dao.findBy(property, value);
		if (list != null && list.size() > 0) {
			dao.batchDelete(list);
		}
	}

	/**
	 * 批量:插入/更新
	 */
	public void modifyBatch(List objList) {
		dao.batchUpdate(objList);
	}

	/**
	 * 批量删除
	 */
	public void deleteBatch(Collection entities) {
		dao.batchDelete(entities);
	}

	/**
	 * 获取总记录数
	 */
	@Transactional(readOnly=true)
	public int getTotalRecords() {
		return dao.getTotalRecords();
	}
	@Transactional(readOnly=true)
	public HibernateBaseDao<T> getDao() {
		return dao;
	}
	@Transactional(readOnly=true)
	public void setDao(HibernateBaseDao<T> dao) {
		this.dao = dao;
	}

	@Override
	@Transactional(readOnly=true)
	public List<T> findBy(String propertyName, Object value) {
		return this.getDao().findBy(propertyName, value);
	}
	/********* getter/setter *********/

}

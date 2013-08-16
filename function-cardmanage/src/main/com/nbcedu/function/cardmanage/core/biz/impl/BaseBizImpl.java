package com.nbcedu.function.cardmanage.core.biz.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.nbcedu.function.cardmanage.core.biz.BaseBiz;
import com.nbcedu.function.cardmanage.core.dao.BaseDao;
import com.nbcedu.function.cardmanage.core.exception.DBException;

@SuppressWarnings("unchecked")
public class BaseBizImpl<T extends Serializable> implements BaseBiz<T> {

	protected final Logger logger = Logger.getLogger(this.getClass());
	
	private BaseDao<T> dao;

	public void setDao(BaseDao<T> dao) {
		this.dao = dao;
	}

	protected BaseDao<T> getDao() {
		return this.dao;
	}

	@Transactional(readOnly = true)
	public T findById(Serializable id) {
		return dao.get(id);
	}

	@Transactional(readOnly = true)
	public T load(Serializable id) {
		return dao.load(id);
	}

	@Transactional(readOnly = true)
	public List<T> findAll() {
		return dao.getAll();
	}

	/**
	 * 表里面是否有相同名字的数据
	 * 李斌
	 */
	public boolean getEquesName(Class<T> c,String name)
	{
		try {
			List list=dao.find("from "+c.getName()+" s where s.name=?",name);
			if (list.size()>0) {
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	 
	public T add(T entity) {
		return (T)dao.save(entity);
	}

	public T addAndRefresh(T entity) {
		this.add(entity);
		getDao().refresh(entity);
		return entity;
	}

	public Object addOrUpdate(T o) {
		return dao.saveOrUpdate(o);
	}

	public void remove(T o) {
		dao.remove(o);
	}

	public Object modify(T o) {
		return dao.update(o);
	}

	public Object modifyMerge(T o) {
		return dao.merge(o);
	}

	public T removeById(Serializable id) {
		if (id == null) {
			return null;
		}
		return dao.removeById(id);
	}

	public T removeById(String id) {
		if (id == null) {
			return null;
		}
		return dao.removeById(id);
	}

	public List<T> removeById(Serializable[] ids) {
		List<T> dts = new ArrayList<T>();
		T del = null;
		if (ids != null && ids.length > 0) {
			for (Serializable id : ids) {
				del = removeById(id);
				if (del != null) {
					dts.add(del);
				}
			}
		}
		return dts;
	}
	
	public String getIdName(){
		return dao.getIdName();
	}

	@Override
	public void modifyBySql(String sql, Object... params) throws DBException {
		dao.updateBySql(sql, params);
	}


}

/*
 * @Title: DocumentFlowBaseBizImpl.java
 * @Package com.nbcedu.function.documentflow.biz.impl
 * @Description: BaseBiz实现类
 * @author Wang Zhuoxuan wangzhuoxuan@nbcedu.com
 * @date 2011-8-4 上午12:43:28
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2011-8-4                          
 */
package com.nbcedu.function.documentflow.biz.impl;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.nbcedu.common.utils.PagerUtils;
import com.nbcedu.function.documentflow.biz.DocumentFlowBaseBiz;
import com.nbcedu.function.documentflow.dao.HibernateDAO;

/** 
 * <p>BaseBiz实现类</p>
 * 
 * @author Wang Zhuoxuan
 * Create at:2011-8-4 上午12:43:28
 */
public class DocumentFlowBaseBizImpl implements DocumentFlowBaseBiz {
	
	/**
	 * 数据访问对象
	 */
	private HibernateDAO hibernateDao;

	/* 
	 * @see com.nbcedu.function.documentflow.biz.DocumentFlowBaseBiz#add(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void add(Object vo, Object po) {
		try {
			BeanUtils.copyProperties(po, vo);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		hibernateDao.create(po);
	}

	/* 
	 * @see com.nbcedu.function.documentflow.biz.DocumentFlowBaseBiz#findAll(java.lang.Class)
	 */
	@Override
	public List<?> findAll(Class<?> clazz) {
		return hibernateDao.retrieveAll(clazz);
	}

	/* 
	 * @see com.nbcedu.function.documentflow.biz.DocumentFlowBaseBiz#findById(java.lang.Class, java.io.Serializable)
	 */
	@Override
	public Object findById(Class<?> clazz, Serializable id) {
		return hibernateDao.get(clazz, id);
	}

	/* 
	 * @see com.nbcedu.function.documentflow.biz.DocumentFlowBaseBiz#findPagedResults(java.lang.String, java.lang.Object[], com.nbcedu.common.utils.PagerUtils)
	 */
	@Override
	public List<?> findPagedResults(String queryString, Object[] params, PagerUtils pagerUtils) {
		return hibernateDao.retrievePageByQuery(queryString, params, pagerUtils);
	}

	/* 
	 * @see com.nbcedu.function.documentflow.biz.DocumentFlowBaseBiz#modify(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void modify(Object vo, Object po) {
		try {
			BeanUtils.copyProperties(po, vo);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		hibernateDao.update(po);
	}

	/* 
	 * @see com.nbcedu.function.documentflow.biz.DocumentFlowBaseBiz#remove(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void remove(Object vo, Object po) {
		try {
			BeanUtils.copyProperties(po, vo);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		hibernateDao.delete(po);
	}

	/* 
	 * @see com.nbcedu.function.documentflow.biz.DocumentFlowBaseBiz#remove(java.lang.Class, java.io.Serializable)
	 */
	@Override
	public void remove(Class<?> clazz, Serializable id) {
		hibernateDao.delete(clazz, id);
	}

	/* 
	 * @see com.nbcedu.function.documentflow.biz.DocumentFlowBaseBiz#remove(java.lang.Class, java.util.List)
	 */
	@Override
	public Integer remove(Class<?> clazz, List<Serializable> vos) {
		int num = vos.size();
		for (Serializable id : vos) {
			hibernateDao.delete(clazz, id);
		}
		return num;
	}

	/* 
	 * @see com.nbcedu.function.documentflow.biz.DocumentFlowBaseBiz#removeAll(java.lang.Class)
	 */
	@Override
	public Integer removeAll(Class<?> clazz) {
		return hibernateDao.deleteAll(clazz);
	}

	/**
	 * @return the hibernateDao
	 */
	public HibernateDAO getHibernateDao() {
		return hibernateDao;
	}

	/**
	 * @param hibernateDao the hibernateDao to set
	 */
	public void setHibernateDao(HibernateDAO hibernateDao) {
		this.hibernateDao = hibernateDao;
	}
}

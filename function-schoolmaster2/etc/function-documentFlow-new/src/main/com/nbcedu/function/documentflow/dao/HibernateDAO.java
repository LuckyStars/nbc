/*
 * @Title: HibernateDAO.java
 * @Package com.nbcedu.function.documentflow.dao
 * @Description: 数据访问对象基类接口，该接口提供了公共的数据操作方法
 * @author Wang Zhuoxuan wangzhuoxuan@nbcedu.com
 * @date 2011-8-3 下午01:41:12
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2011-8-3                          
 */
package com.nbcedu.function.documentflow.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.nbcedu.common.utils.PagerUtils;

/** 
 * <p>数据访问对象基类接口</p>
 * 
 * @author Wang Zhuoxuan
 * Create at:2011-8-3 下午01:41:12
 */
public interface HibernateDAO {
	/**
	 * 保存一个对象
	 * 
	 * @param obj 要保存的对象
	 */
	void create(Object obj);
	/**
	 * 更新一个对象
	 * 
	 * @param obj 要更新的对象
	 */
	void update(Object obj);
	/**
	 * 通过HQL更新对象
	 * 
	 * @param queryString HQL语句
	 * @param params 参数
	 */
	void update(final String queryString, final Object[] params);
	/**
	 * 删除一个对象
	 * 
	 * @param obj 要删除的对象
	 */
	void delete(Object obj);
	/**
	 * 删除一个对象
	 * 
	 * @param clazz 要删除的对象类型
	 * @param id 要删除的对象的id
	 */
	void delete(Class<?> clazz, Serializable id);
	/**
	 * 删除所有对象
	 * 
	 * @param clazz 要删除的对象类型
	 * @return 删除的对象个数
	 */
	Integer deleteAll(final Class<?> clazz);
	/**
	 * 根据指定条件和参数删除对象
	 * 
	 * @param queryString 指定的删除条件
	 * @param params 参数数组
	 * @return 删除的对象个数
	 */
	Integer delete(final String queryString, final Object[] params);
	/**
	 * 查询所有对象
	 * 
	 * @param clazz 要查询的对象类型
	 * @return 对象的列表
	 */
	List<?> retrieveAll(Class<?> clazz);
	/**
	 * 根据条件查询对象
	 * 
	 * @param queryString 查询语句
	 * @return 对象列表
	 */
	List<?> retrieve(final String queryString);
	/**
	 * 根据条件和参数查询对象
	 * 
	 * @param queryString 查询条件
	 * @param params 查询参数
	 * @return 对象列表
	 */
	List<?> retrieve(final String queryString, final Object[] params);
	/**
	 * 根据对象id和对象类型载入一个实例
	 * 
	 * @param clazz 对象类型
	 * @param id 对象id
	 * @return 对象实例
	 */
	Object load(Class<?> clazz, Serializable id);
	/**
	 * 根据对象id和对象类型载入一个实例
	 * 
	 * @param clazz 对象类型
	 * @param id 对象id
	 * @return 对象实例
	 */
	Object get(Class<?> clazz, Serializable id);
	/**
	 * 根据查询语句和查询参数从数据库取得一个对象
	 * 
	 * @param queryString 查询语句
	 * @param params 参数
	 * @return 对象实例
	 */
	Object get(final String queryString, final Object[] params);
	/** 
	 * 批量创建对象
	 * 
	 * @param objList 要批量创建的对象列表
	 * @param batchSize 批量添加时，每执行一次的记录条数
	 * @return 批量添加的总记录数
	 */ 
	Integer batchCreate(final List<?> objList, final int batchSize);
	/**
	 * 分页查询
	 * 
	 * @param queryString
	 *            查询语句
	 * @param params
	 *            参数
	 * @param pagerUtils
	 *            分页信息        
	 * @return 对象列表
	 */
	List<?> retrievePageByQuery(final String queryString,
			final Object[] params, final PagerUtils pagerUtils);
	/**计算数量
	 * @param queryString
	 *            查询语句
	 * @param params
	 *            参数
	 * @param pagerUtils
	 */
	public int retrieveCountByQuery(final String queryString, final Object[] params);
	/**
	 * 分页查询
	 * 
	 * @param queryString
	 *            查询语句
	 * @param params
	 *            参数
	 * @param pagerUtils
	 *            分页信息        
	 * @return 对象列表
	 */
	List<?> retrievePageByQuery(final String queryString,
			final int status,final int num);
	
	HibernateTemplate getHibernateTemplate();
	void buildPager(final PagerUtils pager, Integer maxResults);
}
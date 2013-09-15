/*
 * @Title: DocumentFlowBaseBiz.java
 * @Package com.nbcedu.function.documentflow.biz
 * @Description: 业务逻辑基类接口，该接口包含了公共的业务处理方法。
 * @author Wang Zhuoxuan wangzhuoxuan@nbcedu.com
 * @date 2011-8-3 下午02:18:49
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2011-8-3                          
 */
package com.nbcedu.function.documentflow.biz;

import java.io.Serializable;
import java.util.List;

import com.nbcedu.common.utils.PagerUtils;

/** 
 * <p>业务逻辑基类接口</p>
 * 
 * @author Wang Zhuoxuan
 * Create at:2011-8-3 下午02:18:49
 */
public interface DocumentFlowBaseBiz {
	/** 
	 * 新增一个对象
	 * 
	 * @param vo 要新增的对象对应的值对象
	 * @param po 要新增的对象
	 */ 
	void add(Object vo, Object po);
	/** 
	 * 修改一个对象
	 * 
	 * @param vo 要修改的对象对应的值对象
	 * @param po 要修改的对象
	 */ 
	void modify(Object vo, Object po);
	/** 
	 * 删除一个对象
	 * 
	 * @param vo 要删除的对象对应的值对象
	 * @param po 要删除的对象
	 */ 
	void remove(Object vo, Object po);
	/** 
	 * 删除一个对象
	 * 
	 * @param clazz 要删除的对象的类型
	 * @param id 要删除的对象对应的值对象的唯一标识
	 */ 
	void remove(Class<?> clazz, Serializable id);
	/** 
	 * 删除多个对象
	 * 
	 * @param clazz 要删除的对象的类型
	 * @param vos 要删除的对象对应的值对象唯一标识的列表
	 * @return 删除的对象个数
	 */ 
	Integer remove(Class<?> clazz, List<Serializable> vos);
	/** 
	 * 删除所有对象
	 * 
	 * @param clazz 要删除的对象的类型
	 * @return 删除的对象个数
	 */ 
	Integer removeAll(Class<?> clazz);
	/** 
	 * 查询指定对象对应的值对象
	 * 
	 * @param clazz 要查询的对象的类型
	 * @param id 要查询的指定对象对应的值对象的唯一标识
	 * @return 返回查询到的指定对象
	 */ 
	Object findById(Class<?> clazz, Serializable id);
	/** 
	 * 查询所有对象
	 * 
	 * @param clazz 要查询的对象的类型
	 * @return 返回所有查询到的指定对象对应的值对象的列表
	 */ 
	List<?> findAll(Class<?> clazz);
	/** 
	 * 根据条件和参数进行分页查询
	 * 
	 * @param queryString 查询条件
	 * @param params 参数
	 * @param pagerUtils 分页信息
	 * @return 对象列表
	 */ 
	List<?> findPagedResults(final String queryString, final Object[] params,
			final PagerUtils pagerUtils);
}

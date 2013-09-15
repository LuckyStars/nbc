/*
 * @Title: NotifyTimeBizImpl.java
 * @Package com.nbcedu.function.documentflow.biz.impl
 * @Description: NotifyTimeBiz实现类。
 * @author Wang Zhuoxuan wangzhuoxuan@nbcedu.com
 * @date 2011-8-8 下午05:23:15
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2011-8-8                          
 */
package com.nbcedu.function.documentflow.biz.impl;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.nbcedu.function.documentflow.biz.NotifyTimeBiz;
import com.nbcedu.function.documentflow.model.NotifyTime;
import com.nbcedu.function.documentflow.vo.NotifyTimeVO;

/** 
 * <p>NotifyTimeBiz实现类</p>
 * 
 * @author Wang Zhuoxuan
 * Create at:2011-8-8 下午05:23:15
 */
public class NotifyTimeBizImpl extends DocumentFlowBaseBizImpl implements NotifyTimeBiz {
	
	/* 
	 * @see com.nbcedu.function.documentflow.biz.NotifyTimeBiz#findNotifyTimes()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<NotifyTimeVO> findNotifyTimes() {
		List<NotifyTime> notifyTimes = (List<NotifyTime>) getHibernateDao().retrieve("FROM NotifyTime nt ORDER BY nt.orderId");
		
		return convertPoToVoList(notifyTimes);
	}

	/* 
	 * @see com.nbcedu.function.documentflow.biz.NotifyTimeBiz#findNotifyTimes(java.io.Serializable)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<NotifyTimeVO> findNotifyTimes(Serializable id) {
		List<NotifyTime> notifyTimes = (List<NotifyTime>) getHibernateDao().retrieve("FROM NotifyTime nt WHERE nt.orderId > " 
				+ "(SELECT nt2.orderId FROM NotifyTime nt2 WHERE nt2.id = ?)", new Object[]{id});
		
		return convertPoToVoList(notifyTimes);
	}

	/* 
	 * @see com.nbcedu.function.documentflow.biz.NotifyTimeBiz#findNotifyTime(java.io.Serializable)
	 */
	@Override
	public NotifyTimeVO findNotifyTime(Serializable id) {
		NotifyTimeVO notifyTimeVo = new NotifyTimeVO();
		NotifyTime notifyTime = (NotifyTime) getHibernateDao().get(NotifyTime.class, id);
		
		notifyTimeVo.setId(notifyTime.getId());
		notifyTimeVo.setDisplayName(notifyTime.getDisplayName());
		notifyTimeVo.setName(notifyTime.getName());
		notifyTimeVo.setOrderId(notifyTime.getOrderId());
		
		return notifyTimeVo;
	}

	/** 
	 * 将NotifyTime的持久化对象列表转化为值对象列表
	 * 
	 * @param notifyTimes NotifyTime的持久化对象列表
	 * @return NotifyTime的值对象列表
	 */ 
	private List<NotifyTimeVO> convertPoToVoList(List<NotifyTime> notifyTimes) {
		List<NotifyTimeVO> notifyTimeVos = new ArrayList<NotifyTimeVO>();

		for (NotifyTime notifyTime : notifyTimes) {
			NotifyTimeVO notifyTimeVo = new NotifyTimeVO();
			try {
				BeanUtils.copyProperties(notifyTimeVo, notifyTime);
				notifyTimeVos.add(notifyTimeVo);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}

		return notifyTimeVos;
	}
}

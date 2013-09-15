/*
 * @Title: NotifyProfileBizImpl.java
 * @Package com.nbcedu.function.documentflow.biz.impl
 * @Description: NotifyProfileBiz实现类。
 * @author Wang Zhuoxuan wangzhuoxuan@nbcedu.com
 * @date 2011-8-8 下午02:23:38
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
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.beanutils.BeanUtils;

import com.nbcedu.function.documentflow.biz.NotifyProfileBiz;
import com.nbcedu.function.documentflow.model.NotifyProfile;
import com.nbcedu.function.documentflow.model.NotifyTime;
import com.nbcedu.function.documentflow.utils.NotifyTimeComparator;
import com.nbcedu.function.documentflow.vo.NotifyProfileVO;
import com.nbcedu.function.documentflow.vo.NotifyTimeVO;

/** 
 * <p>NotifyProfileBiz实现类</p>
 * 
 * @author Wang Zhuoxuan
 * Create at:2011-8-8 下午02:23:38
 */
public class NotifyProfileBizImpl extends DocumentFlowBaseBizImpl implements NotifyProfileBiz {
	
	/* 
	 * @see com.nbcedu.function.documentflow.biz.NotifyProfileBiz#addNotifyProfile(com.nbcedu.function.documentflow.vo.NotifyProfileVO)
	 */
	@Override
	public String addNotifyProfile(NotifyProfileVO notifyProfileVo) {
		NotifyProfile notifyProfile = new NotifyProfile();
		Set<NotifyTime> notifyTimes = new TreeSet<NotifyTime>(new NotifyTimeComparator());
		
		notifyProfile.setIsDefault(notifyProfileVo.getIsDefault());
		if (notifyProfileVo.getIsEdit() != null) {
			if (notifyProfileVo.getIsEdit()) {
				notifyProfile.setIsEdit(notifyProfileVo.getIsEdit());
			} else {
				notifyProfile.setIsEdit(false);
			}
		}
		
		notifyProfile.setContent(notifyProfileVo.getContent());
		notifyProfile.setProfileName(notifyProfileVo.getProfileName());
		
		if (notifyProfileVo.getNotifyTimeVos() != null) {
			for (NotifyTimeVO notifyTimeVo : notifyProfileVo.getNotifyTimeVos()) {
				notifyTimes.add((NotifyTime) findById(NotifyTime.class, notifyTimeVo.getId()));
			}
		}
		
		notifyProfile.setNotifyTimes(notifyTimes);
		notifyProfile.setStatus("1");
		
		getHibernateDao().create(notifyProfile);
		
		return notifyProfile.getId();
	}

	/* 
	 * @see com.nbcedu.function.documentflow.biz.NotifyProfileBiz#findNotifyProfiles()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<NotifyProfileVO> findNotifyProfiles() {
		List<NotifyProfileVO> notifyProfileVos = new ArrayList<NotifyProfileVO>();
		List<NotifyProfile> notifyProfiles = (List<NotifyProfile>) getHibernateDao().retrieve("FROM NotifyProfile np WHERE np.status = '1'");
		
		for (NotifyProfile notifyProfile : notifyProfiles) {
			NotifyProfileVO notifyProfileVo = new NotifyProfileVO();
			
			try {
				BeanUtils.copyProperties(notifyProfileVo, notifyProfile);
				//将NotifyTime集合转换成VO数组
				NotifyTime[] notifyTimes = notifyProfile.getNotifyTimes().toArray(
						new NotifyTime[notifyProfile.getNotifyTimes().size()]);
				Arrays.sort(notifyTimes, new NotifyTimeComparator());
				List<NotifyTimeVO> notifyTimeVos = new ArrayList<NotifyTimeVO>();
				for (NotifyTime notifyTime : notifyTimes) {
					NotifyTimeVO notifyTimeVo = new NotifyTimeVO();
					BeanUtils.copyProperties(notifyTimeVo, notifyTime);
					notifyTimeVos.add(notifyTimeVo);
				}
				notifyProfileVo.setNotifyTimeVos(notifyTimeVos.toArray(new NotifyTimeVO[notifyTimeVos.size()]));
				//如果是默认配置，将其放在列表的第一个元素中
				if (notifyProfileVo.getIsDefault()) {
					notifyProfileVos.add(0, notifyProfileVo);
				} else {
					notifyProfileVos.add(notifyProfileVo);
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			
		}
		
		return notifyProfileVos;
	}

	/* 
	 * @see com.nbcedu.function.documentflow.biz.NotifyProfileBiz#findNotifyProfile(java.io.Serializable)
	 */
	@Override
	public NotifyProfileVO findNotifyProfile(Serializable id) {
		NotifyProfile notifyProfile = (NotifyProfile) getHibernateDao().get(NotifyProfile.class, id);
		NotifyProfileVO notifyProfileVo = new NotifyProfileVO();
		
		try {
			BeanUtils.copyProperties(notifyProfileVo, notifyProfile);
			//将NotifyTime集合转换成数组并排序，最后转换成VO数组
			NotifyTime[] notifyTimes = notifyProfile.getNotifyTimes().toArray(
					new NotifyTime[notifyProfile.getNotifyTimes().size()]);
			Arrays.sort(notifyTimes, new NotifyTimeComparator());
			List<NotifyTimeVO> notifyTimeVos = new ArrayList<NotifyTimeVO>();
			for (NotifyTime notifyTime : notifyTimes) {
				NotifyTimeVO notifyTimeVo = new NotifyTimeVO();
				BeanUtils.copyProperties(notifyTimeVo, notifyTime);
				notifyTimeVos.add(notifyTimeVo);
			}
			notifyProfileVo.setNotifyTimeVos(notifyTimeVos.toArray(new NotifyTimeVO[notifyTimeVos.size()]));
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		return notifyProfileVo;
	}

	/* 
	 * @see com.nbcedu.function.documentflow.biz.NotifyProfileBiz#findNotifyProfileDefault()
	 */
	@Override
	public NotifyProfileVO findNotifyProfileDefault() {
		NotifyProfile notifyProfile = (NotifyProfile) getHibernateDao().get(
				"FROM NotifyProfile np WHERE np.isDefault = ?", new Object[]{true});
		NotifyProfileVO notifyProfileVo = new NotifyProfileVO();
		
		try {
			BeanUtils.copyProperties(notifyProfileVo, notifyProfile);
			//将NotifyTime集合转换成数组并排序，最后转换成VO数组
			NotifyTime[] notifyTimes = notifyProfile.getNotifyTimes().toArray(
					new NotifyTime[notifyProfile.getNotifyTimes().size()]);
			Arrays.sort(notifyTimes, new NotifyTimeComparator());
			List<NotifyTimeVO> notifyTimeVos = new ArrayList<NotifyTimeVO>();
			for (NotifyTime notifyTime : notifyTimes) {
				NotifyTimeVO notifyTimeVo = new NotifyTimeVO();
				BeanUtils.copyProperties(notifyTimeVo, notifyTime);
				notifyTimeVos.add(notifyTimeVo);
			}
			notifyProfileVo.setNotifyTimeVos(notifyTimeVos.toArray(new NotifyTimeVO[notifyTimeVos.size()]));
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		return notifyProfileVo;
	}

	/* 
	 * @see com.nbcedu.function.documentflow.biz.NotifyProfileBiz#modifyNotifyProfile(com.nbcedu.function.documentflow.vo.NotifyProfileVO)
	 */
	@Override
	public void modifyNotifyProfile(NotifyProfileVO notifyProfileVo) {
		NotifyProfile notifyProfile = (NotifyProfile) findById(NotifyProfile.class, notifyProfileVo.getId());
		Set<NotifyTime> notifyTimes = new TreeSet<NotifyTime>(new NotifyTimeComparator());
		
		notifyProfile.setIsDefault(notifyProfileVo.getIsDefault());
		notifyProfile.setIsEdit(notifyProfileVo.getIsEdit());
		notifyProfile.setContent(notifyProfileVo.getContent());
		notifyProfile.setProfileName(notifyProfileVo.getProfileName());
		for (NotifyTimeVO notifyTimeVo : notifyProfileVo.getNotifyTimeVos()) {
			notifyTimes.add((NotifyTime) findById(NotifyTime.class, notifyTimeVo.getId()));
		}
		
		notifyProfile.setNotifyTimes(notifyTimes);
		
		getHibernateDao().update(notifyProfile);
	}

	/* 
	 * @see com.nbcedu.function.documentflow.biz.NotifyProfileBiz#removeNotifyProfile(com.nbcedu.function.documentflow.vo.NotifyProfileVO)
	 */
	@Override
	public boolean removeNotifyProfile(NotifyProfileVO notifyProfileVo) {
		if (isInuse(notifyProfileVo)) {
			return false;
		}
		NotifyProfile notifyProfile = (NotifyProfile) findById(NotifyProfile.class, notifyProfileVo.getId());
		notifyProfile.setStatus("0");
		getHibernateDao().update(notifyProfile);
		
		return true;
	}

	/** 
	 * 判断指定提醒模式是否在使用中，true:在使用中，false：不在使用中
	 * 
	 * @param notifyProfileVo 要进行判断的提醒模式对象
	 * @return true:在使用中，false：不在使用中
	 */ 
	private boolean isInuse(NotifyProfileVO notifyProfileVo) {
		int count = ((Long) getHibernateDao().get(
				"SELECT COUNT(*) FROM Document d WHERE d.notifyProfileId = ? AND d.status = ?", 
				new Object[]{notifyProfileVo.getId(), 1})).intValue();
		if (count > 0) {
			return true;
		}
		return false;
	}
}

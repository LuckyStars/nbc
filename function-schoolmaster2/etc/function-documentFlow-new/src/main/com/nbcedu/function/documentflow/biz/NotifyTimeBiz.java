/*
 * @Title: NotifyTimeBiz.java
 * @Package com.nbcedu.function.documentflow.biz
 * @Description: NotifyTime实体业务逻辑接口，该接口包含了NotifyTime实体的所有业务方法。
 * @author Wang Zhuoxuan wangzhuoxuan@nbcedu.com
 * @date 2011-8-8 下午05:04:45
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2011-8-8                          
 */
package com.nbcedu.function.documentflow.biz;

import java.io.Serializable;
import java.util.List;

import com.nbcedu.function.documentflow.vo.NotifyTimeVO;

/** 
 * <p>NotifyTime实体业务逻辑接口</p>
 * 
 * @author Wang Zhuoxuan
 * Create at:2011-8-8 下午05:04:45
 */
public interface NotifyTimeBiz extends DocumentFlowBaseBiz {
	/** 
	 * 返回所有的提醒时间的列表
	 * 
	 * @return 提醒时间的列表
	 */ 
	List<NotifyTimeVO> findNotifyTimes();
	/** 
	 * 返回指定提醒时间之后的提醒时间的列表
	 * 
	 * @param id 指定的提醒时间的唯一标识
	 * @return 提醒时间的列表
	 */ 
	List<NotifyTimeVO> findNotifyTimes(Serializable id);
	/** 
	 * 返回符合条件的提醒时间
	 * 
	 * @param id 提醒时间的唯一标识
	 * @return 符合条件的提醒时间
	 */ 
	NotifyTimeVO findNotifyTime(Serializable id);
}

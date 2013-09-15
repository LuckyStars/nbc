/*
 * @Title: NotifyProfileBiz.java
 * @Package com.nbcedu.function.documentflow.biz
 * @Description: NotifyProfile实体业务逻辑接口，该接口包含了NotifyProfile实体的所有业务方法。
 * @author Wang Zhuoxuan wangzhuoxuan@nbcedu.com
 * @date 2011-8-8 上午10:24:25
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

import com.nbcedu.function.documentflow.vo.NotifyProfileVO;

/** 
 * <p>NotifyProfile实体业务逻辑接口</p>
 * 
 * @author Wang Zhuoxuan
 * Create at:2011-8-8 上午10:24:25
 */
public interface NotifyProfileBiz extends DocumentFlowBaseBiz {
	/** 
	 * 新增短信提醒方案
	 * 
	 * @param notifyProfileVo 短信提醒方案的值对象
	 * @return 新增提醒方案的唯一标识
	 */ 
	String addNotifyProfile(NotifyProfileVO notifyProfileVo);
	/** 
	 * 查询所有短信提醒方案
	 * 
	 * @return 所有短信提醒方案的值对象列表
	 */ 
	List<NotifyProfileVO> findNotifyProfiles();
	/** 
	 * 获取指定的短信提醒方案
	 * 
	 * @param id 短信提醒方案的唯一标识
	 * @return 指定的短信提醒方案的值对象
	 */ 
	NotifyProfileVO findNotifyProfile(Serializable id);
	/** 
	 * 返回默认的短信提醒方案
	 * 
	 * @return 默认的短信提醒方案的值对象
	 */ 
	NotifyProfileVO findNotifyProfileDefault();
	/** 
	 * 修改指定的短信提醒方案
	 * 
	 * @param notifyProfileVo 指定的短信提醒方案的值对象
	 */ 
	void modifyNotifyProfile(NotifyProfileVO notifyProfileVo);
	/** 
	 * 删除指定的短信提醒方案
	 * 
	 * @param notifyProfileVo 指定的短信提醒方案的值对象
	 * @return 删除成功返回true，否则返回false
	 */ 
	boolean removeNotifyProfile(NotifyProfileVO notifyProfileVo);
}

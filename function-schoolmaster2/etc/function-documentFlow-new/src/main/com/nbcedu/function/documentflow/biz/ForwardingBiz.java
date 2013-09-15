/*
 * @Title: ForwardingBiz.java
 * @Package com.nbcedu.function.documentflow.biz
 * @Description: Forwarding实体业务逻辑接口，该接口包含了Forwarding实体的所有业务方法。
 * @author Wang Zhuoxuan wangzhuoxuan@nbcedu.com
 * @date 2011-8-20 下午10:52:00
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2011-8-20                          
 */
package com.nbcedu.function.documentflow.biz;

import com.nbcedu.function.documentflow.vo.ForwardingVO;

/** 
 * <p>Forwarding实体业务逻辑接口</p>
 * 
 * @author Wang Zhuoxuan
 * Create at:2011-8-20 下午10:52:00
 */
public interface ForwardingBiz extends DocumentFlowBaseBiz {
	/** 
	 * 添加一个转发对象
	 * 
	 * @param forwardingVo 转发实体对应的值对象
	 */ 
	void addForwarding(ForwardingVO forwardingVo);
}

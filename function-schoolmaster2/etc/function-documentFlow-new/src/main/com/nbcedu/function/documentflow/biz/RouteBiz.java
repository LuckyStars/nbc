/*
 * @Title: RouteBiz.java
 * @Package com.nbcedu.function.documentflow.biz
 * @Description: Route实体业务逻辑接口，该接口包含了Route实体的所有业务方法。
 * @author Wang Zhuoxuan wangzhuoxuan@nbcedu.com
 * @date 2011-10-8 下午10:57:32
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2011-10-8                          
 */
package com.nbcedu.function.documentflow.biz;

import java.io.Serializable;
import java.util.List;

import com.nbcedu.function.documentflow.vo.RouteVO;

/** 
 * <p>Route实体业务逻辑接口</p>
 * 
 * @author Wang Zhuoxuan
 * Create at:2011-10-8 下午10:57:32
 */
public interface RouteBiz extends DocumentFlowBaseBiz {

	/** 
	 * 保存新的流转路径
	 * 
	 * @param routeVo 要保存的流转路径的业务实体
	 */ 
	void addRoute(RouteVO routeVo);
	/** 
	 * 返回指定用户保存的流转路径列表
	 * 
	 * @param creatorId 指定用户的唯一标识
	 * @return 流转路径列表
	 */ 
	List<RouteVO> findRoutes(String creatorId);
	/** 
	 * 返回指定的流转路径
	 * 
	 * @param id 指定流转路径的唯一标识
	 * @return 指定的流转路径
	 */ 
	RouteVO findRoute(Serializable id);
	/** 
	 * 删除指定的流转路径
	 * 
	 * @param id 指定流转路径的唯一标识
	 */ 
	void removeRoute(Serializable id);
}

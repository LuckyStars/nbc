/*
 * @Title: RouteBizImpl.java
 * @Package com.nbcedu.function.documentflow.biz.impl
 * @Description: RouteBiz实现类。
 * @author Wang Zhuoxuan wangzhuoxuan@nbcedu.com
 * @date 2011-10-8 下午11:11:17
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2011-10-8                          
 */
package com.nbcedu.function.documentflow.biz.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.nbcedu.function.documentflow.biz.RouteBiz;
import com.nbcedu.function.documentflow.model.Route;
import com.nbcedu.function.documentflow.vo.RouteVO;

/** 
 * <p>RouteBiz实现类</p>
 * 
 * @author Wang Zhuoxuan
 * Create at:2011-10-8 下午11:11:17
 */
public class RouteBizImpl extends DocumentFlowBaseBizImpl implements RouteBiz {

	/* 
	 * @see com.nbcedu.function.documentflow.biz.RouteBiz#addRoute(com.nbcedu.function.documentflow.vo.RouteVO)
	 */
	@Override
	public void addRoute(RouteVO routeVo) {
		Route route = new Route();
		route.setId(routeVo.getId());
		route.setRouteName(routeVo.getRouteName());
		route.setCreatorId(routeVo.getCreatorId());
		Set<String> flow = new HashSet<String>(Arrays.asList(routeVo.getFlow().split(",")));
		route.setFlow(flow);

		getHibernateDao().create(route);
	}

	/* 
	 * @see com.nbcedu.function.documentflow.biz.RouteBiz#findRoute(java.io.Serializable)
	 */
	@Override
	public RouteVO findRoute(Serializable id) {
		RouteVO routeVo = new RouteVO();
		Route route = (Route) getHibernateDao().get(Route.class, id);
		routeVo.setId(route.getId());
		routeVo.setRouteName(route.getRouteName());
		routeVo.setCreatorId(route.getCreatorId());
		Set<String> flow = route.getFlow();
		if (flow != null) {
			StringBuilder users = new StringBuilder();
			for (String userId : flow) {
				users.append(userId).append(",");
			}
			users.substring(0, users.lastIndexOf(","));
			routeVo.setFlow(users.toString());
		}
		
		return routeVo;
	}

	/* 
	 * @see com.nbcedu.function.documentflow.biz.RouteBiz#findRoutes(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	//v2
	public List<RouteVO> findRoutes(String creatorId) {
		List<RouteVO> routeVoList = new ArrayList<RouteVO>();
		List<Route> routeList = (List<Route>) getHibernateDao().retrieve("FROM Route r WHERE r.creatorId = ?", new Object[]{creatorId});
		
		for (Route route : routeList) {
			RouteVO routeVo = new RouteVO();
			routeVo.setId(route.getId());
			routeVo.setRouteName(route.getRouteName());
			routeVo.setCreatorId(route.getCreatorId());
			Set<String> flow = route.getFlow();
			if (flow != null) {
				StringBuilder users = new StringBuilder();
				for (String userId : flow) {
					users.append(userId).append(",");
				}
				users.substring(0, users.lastIndexOf(","));
				routeVo.setFlow(users.toString());
			}
			
			routeVoList.add(routeVo);
		}
		
		return routeVoList;
	}

	/* 
	 * @see com.nbcedu.function.documentflow.biz.RouteBiz#removeRoute(java.io.Serializable)
	 */
	@Override
	public void removeRoute(Serializable id) {
		getHibernateDao().delete(Route.class, id);
	}

}

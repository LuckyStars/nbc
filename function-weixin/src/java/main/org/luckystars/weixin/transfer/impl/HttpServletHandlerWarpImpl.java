package org.luckystars.weixin.transfer.impl;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.luckystars.weixin.framework.HandlerContext;
import org.luckystars.weixin.framework.HandlerInvocation;
import org.luckystars.weixin.framework.HandlerWarp;
import org.luckystars.weixin.framework.api.HandleResult;
import org.luckystars.weixin.framework.api.IncomeMessage;
import org.luckystars.weixin.framework.api.InvocationFactoryBean;
import org.luckystars.weixin.transfer.msg.MsgFactory;


public class HttpServletHandlerWarpImpl implements HandlerWarp{

	private static final Logger logger = Logger.getLogger(HttpServletHandlerWarpImpl.class);
	
	private HandlerInvocation invocation;
	
	public HttpServletHandlerWarpImpl(HttpServletRequest req,
			HttpServletResponse resp,ServletConfig config){
		
		HandlerContext ctx = createHandlerContext(req,resp);
		
		this.invocation = warpInvocation(ctx,config);
		
	}

	private HandlerInvocation warpInvocation(HandlerContext ctx,
			ServletConfig config) {
		String invocationFactoryBean = config.getInitParameter("invocationFactoryBean");
		InvocationFactoryBean fac = loadInvoFacBean(invocationFactoryBean);
		return fac.buildInvocation(ctx);
	}
	
	private InvocationFactoryBean  loadInvoFacBean(String className){
		InvocationFactoryBean result = null;
		try {
			result = (InvocationFactoryBean) Thread.currentThread().
				getContextClassLoader().loadClass(className).newInstance();
		} catch (InstantiationException e) {
			logger.error(e);
		} catch (IllegalAccessException e) {
			logger.error(e);
		} catch (ClassNotFoundException e) {
			logger.error("找不到InvocationFactoryBean:" + className,e);
		}
		return result;
	}

	private HandlerContext createHandlerContext(HttpServletRequest req,
			HttpServletResponse resp) {
		HandlerContext ctx = null;
		try {
			IncomeMessage msg = MsgFactory.build(getRawStr(req));
			ctx = new HandlerContext(msg, resp.getOutputStream());
			HandlerContext.putContext(ctx);
		} catch (IOException e) {
			logger.error("获取response输出流出错",e);//well ....╮(╯_╰)╭
		}
		return ctx;
	}

	
	private String getRawStr(HttpServletRequest req) {
		StringBuilder rawXml = new StringBuilder();
		try {
			BufferedReader reader = req.getReader();
			for (;;) {
				char[] temp = new char[128];
				int i = reader.read(temp);
				rawXml.append(temp);
				if (i == -1)
					break;
			}
		} catch (IOException e) {
			logger.error("获取请求原始数据出错",e);
		}
		return rawXml.toString();
	}
	

	@Override
	public void handle() {
		try {
			
			HandleResult result = invocation.invokeNext();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			///释放上下文资源
			HandlerContext.cleanContext();
		}
		
	}
	
	
	
}



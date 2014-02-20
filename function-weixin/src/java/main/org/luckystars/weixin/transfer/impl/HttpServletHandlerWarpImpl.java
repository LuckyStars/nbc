package org.luckystars.weixin.transfer.impl;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.luckystars.weixin.framework.AppContext;
import org.luckystars.weixin.framework.HandlerContext;
import org.luckystars.weixin.framework.HandlerInvocation;
import org.luckystars.weixin.framework.HandlerWarp;
import org.luckystars.weixin.framework.api.HandleResult;
import org.luckystars.weixin.framework.api.IncomeMessage;
import org.luckystars.weixin.framework.api.InvocationFactoryBean;
import org.luckystars.weixin.transfer.msg.WeixinMsgFactory;


public class HttpServletHandlerWarpImpl implements HandlerWarp{

	private static final Logger logger = Logger.getLogger(HttpServletHandlerWarpImpl.class);
	
	private HandlerInvocation invocation;
	private HttpServletRequest req ;
	private HttpServletResponse resp;
	
	public HttpServletHandlerWarpImpl(HttpServletRequest req,
			HttpServletResponse resp){
		this.req = req;
		this.resp = resp;
		this.resp.setCharacterEncoding("utf-8");
		HandlerContext ctx = createHandlerContext();
		this.invocation = warpInvocation(ctx);
	}

	private HandlerInvocation warpInvocation(HandlerContext ctx) {
		
		InvocationFactoryBean invocationFactoryBean = 
			(InvocationFactoryBean) AppContext.getContext()
				.get(InvocationFactoryBean.INVOCATION_FACTORY_BEAN);
		
		return invocationFactoryBean.buildInvocation(ctx);
	}
	
	private HandlerContext createHandlerContext() {
		HandlerContext ctx = null;
		try {
			IncomeMessage msg = WeixinMsgFactory.build(getRawStr(this.req));
			ctx = new HandlerContext(msg, this.resp.getOutputStream());
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
			if(result!=null && result.getView()!=null){
				writeResp(result);
			}
			
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}finally{
			///释放上下文资源
			HandlerContext.cleanContext();
		}
		
	}

	private void writeResp(HandleResult result) throws IOException {
		HandlerContext.getContext().getReplyStream().write(result.getView().toWeixinStr().getBytes("utf-8"));
	}
	
	
	
}



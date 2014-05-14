package org.luckystars.weixin.transfer.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.luckystars.weixin.framework.AppContext;
import org.luckystars.weixin.framework.HandlerWarp;
import org.luckystars.weixin.transfer.impl.DefaultValidationImp;
import org.luckystars.weixin.transfer.impl.HttpServletHandlerWarpImpl;
import org.luckystars.weixin.transfer.interfaces.Validation;

@SuppressWarnings("serial")
public class WeixinServlet extends HttpServlet {
	
	private static final Logger logger = Logger.getLogger(WeixinServlet.class);
	private String token = "";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		logger.info("vali:" + req.getRemoteAddr());
		
		if (valiReq(req)){
			resp.setCharacterEncoding("utf-8");
			resp.getWriter().write(req.getParameter("echostr"));
		}else{
			logger.info(req.getRemoteAddr() + " access denied");
		}
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		//if(!valiReq(req)){return;}
		
		HandlerWarp handler = new HttpServletHandlerWarpImpl(req,resp);
		handler.handle();
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		loadToken(config);
		initAppcontext(config);
	}

	/////////////////////
	/////PRIVATE/////////
	/////////////////////
	private void loadToken(ServletConfig config) {
		String initToken = config.getInitParameter("token");
		if(initToken==null||initToken.trim().isEmpty()){
			throw new NullPointerException("没有配置token");
		}
		this.token = initToken;
	}
	
	private void initAppcontext(ServletConfig config) {
		String configLocation = config.getInitParameter("appConfigLocation");
		if(configLocation!=null&&configLocation.trim().isEmpty()){
			AppContext.initContext(config.getInitParameter(configLocation));
		}else{
			AppContext.initContext();
		}
	}
	
	private boolean valiReq(HttpServletRequest req){
		Validation vali = new DefaultValidationImp();
		
		String timestamp = req.getParameter("timestamp");
		String nonce = req.getParameter("nonce");
		String signature = req.getParameter("signature");
		return vali.validate(timestamp, nonce, signature, this.token);
	}
}

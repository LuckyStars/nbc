package org.luckystars.weixin.transfer.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.luckystars.weixin.transfer.HandlerWarp;
import org.luckystars.weixin.transfer.impl.DefaultValidationImp;
import org.luckystars.weixin.transfer.impl.HttpServletHandlerWarpImpl;
import org.luckystars.weixin.transfer.interfaces.Validation;

@SuppressWarnings("serial")
public class WeixinServlet extends HttpServlet {

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Validation vali = new DefaultValidationImp();
		String timestamp = req.getParameter("timestamp");
		String nonce = req.getParameter("nonce");
		String signature = req.getParameter("signature");
		String token = req.getParameter("token");
		if (vali.validate(timestamp, nonce, signature, token)){
			resp.setCharacterEncoding("utf-8");
			resp.getWriter().write(req.getParameter("echostr"));
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		HandlerWarp handler = 
			new HttpServletHandlerWarpImpl(req,resp,this.getServletConfig());
		
		handler.handle();
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
}

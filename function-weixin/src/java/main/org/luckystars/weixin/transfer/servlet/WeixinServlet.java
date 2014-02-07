package org.luckystars.weixin.transfer.servlet;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class WeixinServlet extends HttpServlet {

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		StringBuilder rawXml = new StringBuilder();
		BufferedReader reader = req.getReader();
		for (;;) {
			char[] temp = new char[128];
			int i = reader.read(temp);
			rawXml.append(temp);
			if(i==-1)break;
		}
		
		
	}
}

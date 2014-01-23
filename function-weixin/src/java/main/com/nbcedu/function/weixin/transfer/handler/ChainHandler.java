package com.nbcedu.function.weixin.transfer.handler;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.nbcedu.function.weixin.transfer.HandlerContext;
import com.nbcedu.function.weixin.transfer.MsgContext;
import com.nbcedu.function.weixin.transfer.interfaces.Handler;
import com.nbcedu.function.weixin.transfer.interfaces.MsgHandler;

public class ChainHandler implements HandlerContext{

	
	private static final Map<String, List<Handler>> handlers = new HashMap<String, List<Handler>>(){{
		
	}};
	
	protected List<MsgHandler> chain = null;
	protected final MsgContext msg ;
	
	protected HandlerContext context;
	
	
	private ChainHandler(MsgContext msg){
		this.msg = msg;
	}
	
	
	public void handleMsg(MsgContext msg) {
		newHandlerChain(msg).doChain()	;
	}
	
	public void doChain(){
		Iterator<MsgHandler> it = chain.iterator();
		while(it.hasNext()){
			MsgHandler handler = it.next();
			if(handler.test(msg)){
				//handler.handleMsg(msg);
			}else{
				continue;
			}
		}
	}
	
	
	public static ChainHandler newHandlerChain(MsgContext msg){
		//TODO
		return null;
	}


	@Override
	public void invokeNext() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void stopChain() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public MsgContext getMsg() {
		// TODO Auto-generated method stub
		return null;
	}

}

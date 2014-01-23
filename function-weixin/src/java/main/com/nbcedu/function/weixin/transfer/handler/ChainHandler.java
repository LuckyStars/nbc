package com.nbcedu.function.weixin.transfer.handler;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.nbcedu.function.weixin.transfer.MsgContext;
import com.nbcedu.function.weixin.transfer.interfaces.Handler;
import com.nbcedu.function.weixin.transfer.interfaces.MsgHandler;

public class ChainHandler{

	
	private static final Map<String, List<Handler>> handlers = new HashMap<String, List<Handler>>(){{
		
	}};
	
	protected List<MsgHandler> chain = null;
	protected final MsgContext msg ; 
	
	
	private ChainHandler(MsgContext msg){
		this.msg = msg;
	}
	
	
	@Override
	public void handleMsg(MsgContext msg) {
		newHandlerChain(msg).doHandle()	;
	}
	
	public void doHandle(){
		Iterator<MsgHandler> it = chain.iterator();
		while(it.hasNext()){
			MsgHandler handler = it.next();
			if(handler.test(msg)){
				handler.handleMsg(msg);
			}else{
				continue;
			}
		}
	}
	
	
	public static ChainHandler newHandlerChain(MsgContext msg){
		
	}

}

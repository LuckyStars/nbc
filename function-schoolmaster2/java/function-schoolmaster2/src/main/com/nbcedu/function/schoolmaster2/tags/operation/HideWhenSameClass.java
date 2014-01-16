package com.nbcedu.function.schoolmaster2.tags.operation;


/**
 * 当发送人接收人同级时隐藏
 * @author xuechong
 */
@SuppressWarnings("serial")
public class HideWhenSameClass extends AbstractOperationTag{

	@Override
	boolean show(OperationContext ctx) {
		if((ctx.senderIsManager()&&ctx.selfIsManager())
				||(ctx.senderIsMaster()&&ctx.selfIsMaster())
				){
			
		}
		
		return false;
	}

}

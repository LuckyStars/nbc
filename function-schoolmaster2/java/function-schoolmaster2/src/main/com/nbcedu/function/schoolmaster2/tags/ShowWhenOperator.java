package com.nbcedu.function.schoolmaster2.tags;


import com.nbcedu.function.schoolmaster2.tags.display.AbstractDisplayTag;


/**
 * 当执行者和发送者时显示
 * @author xuechong
 */
@SuppressWarnings("serial")
public class ShowWhenOperator extends AbstractDisplayTag{

	private String subjectId;
	
	@Override
	protected boolean display() {
		return isOperator();
	}
	
	/**
	 * 判断是否是执行者或发起者
	 * @return
	 * @author xuechong
	 */
	boolean isOperator(){
		
		
		return false;
	}
	
}

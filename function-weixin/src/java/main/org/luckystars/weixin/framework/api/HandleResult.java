package org.luckystars.weixin.framework.api;

/**
 * 处理的结果
 * @author xuechong
 */
public interface HandleResult {
	
	/**
	 * stop the rest handlers
	 * 停止余下的handler继续处理
	 */
	public static final HandleResult STOP_CHAIN = new HandleResult() {
		public View getView() {return null;}
		public void setView(View view) {};
	};
	
	/**
	 * 继续执行余下的handler
	 * invoke next handler in the chain
	 */
	public static final HandleResult INVOKE_NEXT = new HandleResult() {
		public WeixinView getView() {return null;}
		@Override
		public void setView(View view) {}
	};
	
	/**
	 * 返回空串
	 */
	public static final HandleResult EMPTY_RESULT = new HandleResult() {
		@SuppressWarnings("serial")
		public View getView() {
			return new WeixinView() {
				@Override
				public String toWeixinStr() {
					return "";
				}
			};
		};
		public void setView(View view) {}
	};
	
	View getView();
	
	void setView(View view);
}

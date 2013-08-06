package com.nbcedu.function.cardmanage.core.exception;

/**
 * Action层异常
 * 
 * @author qinyuan
 * @version 1.0
 */
public class ActionException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ActionException() {
		super();
	}

	public ActionException(String message, Throwable cause) {
		super(message, cause);
	}

	public ActionException(String message) {
		super(message);
	}

	public ActionException(Throwable cause) {
		super(cause);
	}

}

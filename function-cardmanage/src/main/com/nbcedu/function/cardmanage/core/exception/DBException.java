package com.nbcedu.function.cardmanage.core.exception;


/**
 * 数据库操作异常类
 * 
 * @version 1.0
 */
public class DBException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public DBException() {
		super();
	}

	public DBException(String message) {
		super(message);
	}

	public DBException(String message, Throwable cause) {
		super(message, cause);
	}

	public DBException(Throwable cause) {
		super(cause);
	}
}

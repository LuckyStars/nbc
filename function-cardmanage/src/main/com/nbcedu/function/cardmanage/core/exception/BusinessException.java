package com.nbcedu.function.cardmanage.core.exception;

/**
 * 业务异常基类
 *
 * @version 1.0
 */
@SuppressWarnings("serial")
public class BusinessException extends RuntimeException {

	public BusinessException() {
	}

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public BusinessException(Throwable cause) {
		super(cause);
	}
}

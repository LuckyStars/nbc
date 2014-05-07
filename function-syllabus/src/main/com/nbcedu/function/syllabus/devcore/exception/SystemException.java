package com.nbcedu.function.syllabus.devcore.exception;

/**
 * 自定义异常,struts2中配置全局异常处理
 * 原则：系统中各层异常，都抛出，直到Action层也throws
 * 
 * @author qinyuan
 * @version 1.0
 */
public class SystemException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public SystemException() {
		super();
	}

	public SystemException(String message, Throwable cause) {
		super(message, cause);
	}

	public SystemException(String message) {
		super(message);
	}

	public SystemException(Throwable cause) {
		super(cause);
	}

}

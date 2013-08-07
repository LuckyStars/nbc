package com.nbcedu.function.cardmanage.core.exception;

/**
 * 数据库操作异常类
 * 
 * @author Xiedayun
 * 
 */
@SuppressWarnings("unused")
public class DBException extends Exception {
	
	private static final long serialVersionUID = 300047674808718515L;
	
	private static final int LEVEL_LOGINFAIL = 0;
	private static final int LEVEL_ERROR = 1;
	private static final int LEVEL_WARNING = 2;
    
	private int level;
	
	public DBException(String message, int level){
		super(message);
		this.level = level;
	}
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
	
	static public void throwError(String message) throws DBException{
		DBException we = new DBException(message, LEVEL_ERROR);
		we.printStackTrace();		
		throw we;
	}
	
	static public void throwWarning(String message)throws DBException{
		DBException we = new DBException(message, LEVEL_WARNING);
		we.printStackTrace();
		throw we;
	}
}

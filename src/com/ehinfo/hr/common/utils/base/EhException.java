package com.ehinfo.hr.common.utils.base;

public class EhException extends Exception  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void error(String error) throws EhException {
		throw new EhException(error);
	}
	public EhException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EhException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public EhException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public EhException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public EhException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	

	

	
	
	
}

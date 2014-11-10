package com.pratz.exception;

public class TaskException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8911443225731325546L;

	public TaskException() {
		super();
	}
	
	public TaskException(String message){
		super(message);
	}
	
	public TaskException(Throwable e){
		super(e);
	}

}

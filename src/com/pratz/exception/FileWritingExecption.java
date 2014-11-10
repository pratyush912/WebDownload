package com.pratz.exception;

import java.io.IOException;

public class FileWritingExecption extends IOException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 207748305097922827L;

	public FileWritingExecption() {
		super();
	}
	
	public FileWritingExecption(String message) {
		super(message);
	}
	
	public FileWritingExecption(Throwable t){
		super(t);
	}
	

}

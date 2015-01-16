package com.laima.exception;

public class DESException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DESException() {
		super();
	}

	public DESException(String message) {
		super(message);
	}

	public DESException(Throwable th) {
		super(th);
	}

	public DESException(String message, Throwable th) {
		super(message, th);
	}

}

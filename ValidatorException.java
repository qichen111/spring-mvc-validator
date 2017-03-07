package com.dqc.trydaima.web.exception;

public class ValidatorException extends RuntimeException {

	private static final long serialVersionUID = 3683264408286611572L;

	public ValidatorException() {
		super();
	}

	public ValidatorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ValidatorException(String message, Throwable cause) {
		super(message, cause);
	}

	public ValidatorException(String message) {
		super(message);
	}

	public ValidatorException(Throwable cause) {
		super(cause);
	}

}

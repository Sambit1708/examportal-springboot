package com.exam.portal.payload;

public class ErrorCode {

	private String message;
	
	private String errorCode;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public ErrorCode() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ErrorCode(String message, String errorCode) {
		super();
		this.message = message;
		this.errorCode = errorCode;
	}
}

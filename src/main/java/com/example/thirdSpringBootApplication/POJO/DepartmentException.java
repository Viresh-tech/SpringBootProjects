package com.example.thirdSpringBootApplication.POJO;

public class DepartmentException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DepartmentException(Integer errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	private Integer errorCode;
	private String errorMessage;

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getMessag() {
		String error = "The error code is " + errorCode + "  The error message is: " + errorMessage;
		return error;

	}

}

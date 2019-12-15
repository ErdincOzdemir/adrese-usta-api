package com.adreseusta.api.common.exception.constant;

public enum MicroErrorType {

    INVALID_REQUEST(-995L, "invalid request"),
	HANDLER_NOT_FOUND(-996L, "handler not found"),
	METHOD_NOT_ALLOWED(-997L, "http method not allowed"),
	VALIDATION_ERROR(-998L, "validation error"),	
	INTERNAL_ERROR(-999L, "internal error");
	
	private final Long code;
	
	private final String message;

	private MicroErrorType(Long code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public Long getCode() {
		return code;
	}
	
	public String getMessage() {
		return message;
	}
    

}

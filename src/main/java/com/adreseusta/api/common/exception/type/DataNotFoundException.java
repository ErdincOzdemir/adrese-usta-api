package com.adreseusta.api.common.exception.type;

import com.adreseusta.api.common.exception.data.ExceptionData;

import java.util.Map;


/**
 * Refers to {@code 404 Not Found}.
 * 
 * @see <a href="http://tools.ietf.org/html/rfc7231#section-6.5.4">HTTP/1.1: Semantics and Content, section 6.5.4</a>
 */
public class DataNotFoundException extends MicroException {

    private static final long serialVersionUID = 3257860449404230367L;

    public DataNotFoundException(ExceptionData data) {
        super(data);
    }
    
    public DataNotFoundException(ExceptionData data, Map<String, String> parameters) {
        super(data, parameters);
    }
    
    public DataNotFoundException(Long errorCode) {
    	super(errorCode);
    }
    
    public DataNotFoundException(Long errorCode, String errorMessage) {
    	super(errorCode, errorMessage);
    }
}

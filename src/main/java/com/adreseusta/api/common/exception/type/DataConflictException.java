package com.adreseusta.api.common.exception.type;

import com.adreseusta.api.common.exception.data.ExceptionData;

import java.util.Map;

/**
 * Refers to {@code 409 Conflict}.
 * 
 * @see <a href="http://tools.ietf.org/html/rfc7231#section-6.5.8">HTTP/1.1: Semantics and Content, section 6.5.8</a>
 */
public class DataConflictException extends MicroException {

    private static final long serialVersionUID = -2853457787177247416L;

    public DataConflictException(ExceptionData data) {
        super(data);
    }
    
    public DataConflictException(ExceptionData data, Map<String, String> parameters) {
        super(data, parameters);
    }
    
    public DataConflictException(Long errorCode) {
    	super(errorCode);
    }
    
    public DataConflictException(Long errorCode, String errorMessage) {
    	super(errorCode, errorMessage);
    }

}

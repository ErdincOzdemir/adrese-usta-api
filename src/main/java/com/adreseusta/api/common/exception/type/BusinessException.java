package com.adreseusta.api.common.exception.type;

import com.adreseusta.api.common.exception.data.ExceptionData;

import java.util.Map;

/**
 * Refers to {@code 500 Internal Server Error}.
 * 
 * @see <a href="http://tools.ietf.org/html/rfc7231#section-6.6.1">HTTP/1.1: Semantics and Content, section 6.6.1</a>
 */
public class BusinessException extends MicroException {

    private static final long serialVersionUID = -1195893999712677023L;

    public BusinessException(ExceptionData data) {
        super(data);
    }
    
    public BusinessException(ExceptionData data, Map<String, String> parameters) {
        super(data, parameters);
    }
    
    public BusinessException(Long errorCode) {
    	super(errorCode);
    }
    
    public BusinessException(Long errorCode, String errorMessage) {
    	super(errorCode, errorMessage);
    }

}

package com.adreseusta.api.common.exception.type;

import com.adreseusta.api.common.exception.data.ExceptionData;

import java.util.Map;

/**
 * Refers to {@code 405 Method Not Allowed}.
 * 
 * @see <a href="http://tools.ietf.org/html/rfc7231#section-6.5.5">HTTP/1.1: Semantics and Content, section 6.5.5</a>
 */
public class MethodNotAllowedException extends MicroException {

    private static final long serialVersionUID = -2853457787177247416L;

    public MethodNotAllowedException(ExceptionData data) {
        super(data);
    }
    
    public MethodNotAllowedException(ExceptionData data, Map<String, String> parameters) {
        super(data, parameters);
    }
    
    public MethodNotAllowedException(Long errorCode) {
    	super(errorCode);
    }
    
    public MethodNotAllowedException(Long errorCode, String errorMessage) {
    	super(errorCode, errorMessage);
    }

}

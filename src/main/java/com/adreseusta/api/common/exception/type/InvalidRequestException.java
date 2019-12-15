package com.adreseusta.api.common.exception.type;

import com.adreseusta.api.common.exception.data.ExceptionData;

import java.util.Map;


/**
 * Refers to {@code 400 Bad Request}.
 * 
 * @see <a href="http://tools.ietf.org/html/rfc7231#section-6.5.1">HTTP/1.1: Semantics and Content, section 6.5.1</a>
 */
public class InvalidRequestException extends MicroException {

    private static final long serialVersionUID = -4225631386825856253L;

    public InvalidRequestException(ExceptionData data) {
        super(data);
    }
    
    public InvalidRequestException(ExceptionData data, Map<String, String> parameters) {
        super(data, parameters);
    }
    
    public InvalidRequestException(Long errorCode) {
    	super(errorCode);
    }
    
    public InvalidRequestException(Long errorCode, String errorMessage) {
    	super(errorCode, errorMessage);
    }

}

package com.adreseusta.api.common.exception.type;

import com.adreseusta.api.common.exception.data.ExceptionData;

import java.util.Map;

/**
 * Refers to {@code 503 Service Unavailable}.
 * 
 * @see <a href="http://tools.ietf.org/html/rfc7231#section-6.6.4">HTTP/1.1: Semantics and Content, section 6.6.4</a>
 */
public class ServiceCallException extends MicroException {

    private static final long serialVersionUID = -4778895272806863894L;

    public ServiceCallException(ExceptionData data) {
        super(data);
    }

    public ServiceCallException(ExceptionData data, Map<String, String> parameters) {
        super(data, parameters);
    }
 
}

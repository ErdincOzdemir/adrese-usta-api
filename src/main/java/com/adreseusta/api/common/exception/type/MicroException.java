package com.adreseusta.api.common.exception.type;

import com.adreseusta.api.common.exception.data.ExceptionData;
import com.adreseusta.api.common.exception.data.ExceptionDataWithParameter;

import java.util.HashMap;
import java.util.Map;

public abstract class MicroException extends Exception {

    private static final long   serialVersionUID = 1L;

    private ExceptionData data;

    private Map<String, String> parameters       = new HashMap<String, String>();

    public MicroException(ExceptionData data) {
    	super(data.getErrorMessage());
        this.data = data;
    }
    
    public MicroException(ExceptionData data, Exception exception) {
    	super(data.getErrorMessage(), exception);
        this.data = data;
    }

    public MicroException(ExceptionData data, Map<String, String> parameters) {
    	this(data);
        this.parameters = parameters;
    }
    
    public MicroException(Long errorCode) {
    	this(new ExceptionData(errorCode));
    }
    
    public MicroException(Long errorCode, String errorMessage) {
    	this(new ExceptionData(errorCode, errorMessage));
    }
    
    public MicroException(Long errorCode, String errorMessage, Exception exception) {
    	this(new ExceptionData(errorCode, errorMessage), exception);
    } 

    public Long getErrorCode() {
        if (data != null) {
            return data.getErrorCode();
        } else
            return null;
    }

    public ExceptionData getExceptionData() {
        return data;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }
    
    public String getParameter(String key) {
    	return parameters.get(key);
    
    }
    public void setErrorMessage(String errorMessage) {
    	if (data == null) {
    		data = new ExceptionData();
    	}
    }

    public void addParameter(String key, String value) {
        value = value == null ? "" : value;
        this.parameters.put(key, value.toString());
    }

    public MicroException appendParameter(String key, String value) {
        value = value == null ? "" : value;
        this.parameters.put(key, value.toString());
        return this;
    }
    
    public ExceptionDataWithParameter convertToExceptionResponse() {
    	ExceptionData exceptionData = data != null ? new ExceptionData(data) : new ExceptionData();
		return new ExceptionDataWithParameter(exceptionData, parameters);
    }

    public ExceptionData convertToExceptionResponseWithOutParams() {
        ExceptionData exceptionData = data != null ? new ExceptionData(data) : new ExceptionData();
        return exceptionData;
    }

}

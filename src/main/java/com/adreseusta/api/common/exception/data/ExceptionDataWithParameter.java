package com.adreseusta.api.common.exception.data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ExceptionDataWithParameter implements Serializable {

    private static final long   serialVersionUID = 156025529118512642L;

    private ExceptionData       exceptionData;

    private Map<String, String> parameters       = new HashMap<String, String>();
    
    public ExceptionDataWithParameter(ExceptionData exceptionData) {
        super();
        this.exceptionData = exceptionData;
    }
    
    public ExceptionDataWithParameter(ExceptionData exceptionData, Map<String, String> parameters) {
        super();
        this.exceptionData = exceptionData;
        this.parameters = parameters;
    }

    public ExceptionData getExceptionData() {
        return exceptionData;
    }

    public void setExceptionData(ExceptionData exceptionData) {
        this.exceptionData = exceptionData;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }

}

package com.adreseusta.api.common.exception.data;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;

public class ExceptionData implements Serializable {

    private static final long serialVersionUID = 3070164249204674989L;

    private String applicationName;

    private Long errorCode;

    private String errorMessage;

    private String traceId;

    private String detailedErrorMessage;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ValidationErrorData> errors;

    public ExceptionData() {
        super();
    }

    public ExceptionData(Long errorCode) {
        super();
        this.errorCode = errorCode;
    }

    public ExceptionData(Long errorCode, String errorMessage) {
        super();
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public ExceptionData(String applicationName, Long errorCode, String errorMessage) {
        super();
        this.applicationName = applicationName;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public ExceptionData(ExceptionData exceptionData) {
        super();
        this.applicationName = exceptionData.getApplicationName();
        this.errorCode = exceptionData.getErrorCode();
        this.errorMessage = exceptionData.getErrorMessage();
        this.detailedErrorMessage = exceptionData.getDetailedErrorMessage();
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public Long getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Long errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public List<ValidationErrorData> getErrors() {
        return errors;
    }

    public void setErrors(List<ValidationErrorData> errors) {
        this.errors = errors;
    }

    public String getDetailedErrorMessage() {
        return detailedErrorMessage;
    }

    public void setDetailedErrorMessage(String detailedErrorMessage) {
        this.detailedErrorMessage = detailedErrorMessage;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[").append("applicationName:").append(applicationName).append(",").append("errorCode:").append(errorCode).append(",")
                .append("errorMessage:").append(errorMessage).append("traceId:").append(traceId).append("]");

        return sb.toString();
    }

}

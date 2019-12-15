package com.adreseusta.api.common.exception.handler;


import com.adreseusta.api.common.exception.constant.MicroErrorType;
import com.adreseusta.api.common.exception.data.ExceptionData;
import com.adreseusta.api.common.exception.data.ExceptionDataWithParameter;
import com.adreseusta.api.common.exception.data.ValidationErrorData;
import com.adreseusta.api.common.exception.type.*;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.StringUtils;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


@RestControllerAdvice
public class MicroExceptionHandler extends ResponseEntityExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(MicroExceptionHandler.class);
    private MessageSource messageSource;
    private static final String TRACE_ID_KEY = "X-B3-TraceId";
    private static final String GENERIC_ERROR_MESSAGE_KEY = "com.ykb.generic.error";

    @Value("${spring.application.name}")
    public String applicationName;

    public MicroExceptionHandler() {
        super();
    }

    @Autowired
    public MicroExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ExceptionHandler(value = DataConflictException.class)
    public ExceptionData handleDataConflictException(DataConflictException ex, Locale locale) {
        return errorResponse(ex, ex.convertToExceptionResponseWithOutParams(), locale);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = InvalidRequestException.class)
    public ExceptionDataWithParameter handleInvalidRequestException(InvalidRequestException ex, Locale locale) {
        return errorResponse(ex, ex.convertToExceptionResponse(), locale);
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = DataNotFoundException.class)
    public ExceptionData handleDataNotFoundException(DataNotFoundException ex, Locale locale) {
        return errorResponse(ex, ex.convertToExceptionResponseWithOutParams(), locale);

    }

    @ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(value = MethodNotAllowedException.class)
    public ExceptionDataWithParameter handleMethodNotAllowedException(MethodNotAllowedException ex, Locale locale) {
        return errorResponse(ex, ex.convertToExceptionResponse(), locale);
    }

    @ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
    @ExceptionHandler(value = ServiceCallException.class)
    public ExceptionDataWithParameter handleServiceCallException(ServiceCallException ex, Locale locale) {
        return errorResponse(ex, ex.convertToExceptionResponse(), locale);
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = MicroException.class)
    public ExceptionDataWithParameter handleMicroException(MicroException ex, Locale locale) {
        return errorResponse(ex, ex.convertToExceptionResponse(), locale);
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = UndeclaredThrowableException.class)
    public ExceptionDataWithParameter handleUndeclaredThrowableException(UndeclaredThrowableException ex, Locale locale) {
        if (ex.getCause() instanceof MicroException) {
            MicroException exception = (MicroException) ex.getCause();
            return errorResponse(exception, exception.convertToExceptionResponse(), locale);
        }
        ExceptionData exceptionData = new ExceptionData(MicroErrorType.INTERNAL_ERROR.getCode(), ex.getMessage());
        ExceptionDataWithParameter response = new ExceptionDataWithParameter(exceptionData);
        return errorResponse(ex, response, locale);
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public ExceptionDataWithParameter handleException(Exception ex, Locale locale) {
        ExceptionData exceptionData = new ExceptionData(MicroErrorType.INTERNAL_ERROR.getCode(), ex.getMessage());
        ExceptionDataWithParameter response = new ExceptionDataWithParameter(exceptionData);
        return errorResponse(ex, response, locale);
    }

    private ExceptionDataWithParameter errorResponse(Exception ex, ExceptionDataWithParameter exceptionDataWithParameter, Locale locale) {

        //TODO stack trace formatting must be done with logback configuration
        log.error("Exception handler caught an error: " + ExceptionUtils.getStackTrace(ex));
        ExceptionData exceptionData = exceptionDataWithParameter.getExceptionData();
        populateExceptionDataWithServiceInformation(exceptionData, locale);
        return exceptionDataWithParameter;
    }

    private ExceptionData errorResponse(Exception ex, ExceptionData exceptionDataWithParameter, Locale locale) {

        //TODO stack trace formatting must be done with logback configuration
        log.error("Exception handler caught an error: " + ExceptionUtils.getStackTrace(ex));
        populateExceptionDataWithServiceInformation(exceptionDataWithParameter, locale);
        return exceptionDataWithParameter;
    }

    private void populateExceptionDataWithServiceInformation(ExceptionData exceptionData, Locale locale) {
        if (exceptionData != null) {
            if (StringUtils.isEmpty(exceptionData.getApplicationName())) {
                exceptionData.setApplicationName(applicationName);
            }
            if (StringUtils.isEmpty(exceptionData.getTraceId())) {
                exceptionData.setTraceId(MDC.get(TRACE_ID_KEY));
            }
            if (!StringUtils.isEmpty(exceptionData.getErrorMessage())) {
                String formattedMessage = null;
                try {
                    formattedMessage = messageSource.getMessage(exceptionData.getErrorMessage(), null, locale);
                } catch (NoSuchMessageException e) {
                }
                exceptionData.setDetailedErrorMessage(formattedMessage == null ? exceptionData.getErrorMessage() : formattedMessage);
            }
            exceptionData.setErrorMessage(messageSource.getMessage(GENERIC_ERROR_MESSAGE_KEY, null, locale));
        }
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
                                                                  final HttpHeaders headers, final HttpStatus status,
                                                                  final WebRequest request) {
        ExceptionData exceptionData = new ExceptionData(MicroErrorType.VALIDATION_ERROR.getCode(), MicroErrorType.VALIDATION_ERROR.getMessage());

        List<ValidationErrorData> errors = new ArrayList<>();
        for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
            ValidationErrorData fieldError = new ValidationErrorData(error.getField(), error.getDefaultMessage());
            errors.add(fieldError);
        }

        exceptionData.setErrors(errors);

        ExceptionDataWithParameter exceptionDataWithParameter = new ExceptionDataWithParameter(exceptionData);
        errorResponse(ex, exceptionDataWithParameter, request.getLocale());

        return handleExceptionInternal(ex, exceptionDataWithParameter, headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(final HttpRequestMethodNotSupportedException ex,
                                                                         final HttpHeaders headers,
                                                                         final HttpStatus status, final WebRequest request) {

        StringBuilder builder = new StringBuilder();
        builder.append(ex.getMethod());
        builder.append(" method is not supported for this request. Supported methods are ");
        ex.getSupportedHttpMethods().forEach(t -> builder.append(t + " "));

        ExceptionData exceptionData = new ExceptionData(MicroErrorType.METHOD_NOT_ALLOWED.getCode(), builder.toString());
        ExceptionDataWithParameter exceptionDataWithParameter = new ExceptionDataWithParameter(exceptionData);
        errorResponse(ex, exceptionDataWithParameter, request.getLocale());

        return handleExceptionInternal(ex, exceptionDataWithParameter, headers, HttpStatus.METHOD_NOT_ALLOWED, request);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
                                                                   HttpStatus status, WebRequest request) {
        String errorMessage = "No handler found for " + ex.getHttpMethod() + " " + ex.getRequestURL();

        ExceptionData exceptionData = new ExceptionData(MicroErrorType.HANDLER_NOT_FOUND.getCode(), errorMessage);
        ExceptionDataWithParameter exceptionDataWithParameter = new ExceptionDataWithParameter(exceptionData);
        errorResponse(ex, exceptionDataWithParameter, request.getLocale());

        return handleExceptionInternal(ex, exceptionDataWithParameter, headers, HttpStatus.NOT_FOUND, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        String errorMessage = "Invalid request: " + ex.getMessage();

        ExceptionData exceptionData = new ExceptionData(MicroErrorType.INVALID_REQUEST.getCode(), errorMessage);
        ExceptionDataWithParameter exceptionDataWithParameter = new ExceptionDataWithParameter(exceptionData);
        errorResponse(ex, exceptionDataWithParameter, request.getLocale());

        return handleExceptionInternal(ex, exceptionDataWithParameter, headers, HttpStatus.BAD_REQUEST, request);
    }

}
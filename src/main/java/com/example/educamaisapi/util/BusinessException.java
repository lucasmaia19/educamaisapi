package com.example.educamaisapi.util;

//import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BusinessException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private String resourceName;
    private String fieldName;
    private Object fieldValue;

    public BusinessException(String message) {
    	super(message);
    }
    
    public BusinessException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s not found with %s [%s]", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
    
    public BusinessException(String reason, Throwable cause) {
    	super(reason, cause);
//    	log.error("{} -> {}", reason, ExceptionUtils.getRootCauseMessage(cause));
    	cause.printStackTrace();
    }

    public BusinessException(Exception e) {

    	if (e instanceof BusinessException)
    		new BusinessException(e.getMessage());

    	else
    		new BusinessException("Erro, informe ao suporte :(", e);

    }

    public String getResourceName() {
        return resourceName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getFieldValue() {
        return fieldValue;
    }
}

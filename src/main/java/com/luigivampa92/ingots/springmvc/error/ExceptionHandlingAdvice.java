package com.luigivampa92.ingots.springmvc.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandlingAdvice {

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public ResponseEntity onRegistrationException(BusinessException e) {
        ErrorType errorType = e.getErrorType();
        return new ResponseEntity(errorType.getErrorModel(), errorType.getHttpStatus());
    }
}

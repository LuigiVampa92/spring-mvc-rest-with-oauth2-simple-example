package com.luigivampa92.ingots.springmvc.error;

public class BusinessException extends Exception {

    private ErrorType errorType;

    public BusinessException(ErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public ErrorType getErrorType() {
        return errorType;
    }
}

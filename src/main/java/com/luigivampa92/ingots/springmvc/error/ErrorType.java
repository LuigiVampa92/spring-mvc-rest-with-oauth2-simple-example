package com.luigivampa92.ingots.springmvc.error;

import org.springframework.http.HttpStatus;

public enum ErrorType {

    USER_ALREADY_REGISTERED(101, "User with such name or email already registered", HttpStatus.INTERNAL_SERVER_ERROR),
    USER_CONFORMATION_ERROR(102, "Error during registration confirm", HttpStatus.INTERNAL_SERVER_ERROR),
    RECORD_NOT_FOUND(201, "Requested entity not found", HttpStatus.NOT_FOUND);

    private int code;
    private String message;
    private HttpStatus httpStatus;

    ErrorType(int code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public ErrorModel getErrorModel() {
        return new ErrorModel(code, message);
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}

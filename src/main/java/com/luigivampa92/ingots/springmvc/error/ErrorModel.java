package com.luigivampa92.ingots.springmvc.error;

public class ErrorModel {

    public final int code;
    public final String error;

    public ErrorModel(int code, String error) {
        this.code = code;
        this.error = error;
    }
}


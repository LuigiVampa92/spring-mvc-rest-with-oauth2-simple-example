package com.luigivampa92.ingots.springmvc.auth.model;

public class RegistrationRequestResponse {

    private String status;

    public RegistrationRequestResponse() {
        this("OK");
    }

    public RegistrationRequestResponse(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

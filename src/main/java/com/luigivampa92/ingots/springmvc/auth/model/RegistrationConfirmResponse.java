package com.luigivampa92.ingots.springmvc.auth.model;

public class RegistrationConfirmResponse {

    private String status;

    public RegistrationConfirmResponse() {
        this("OK");
    }

    public RegistrationConfirmResponse(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

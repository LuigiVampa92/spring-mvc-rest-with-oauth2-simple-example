package com.luigivampa92.ingots.springmvc.auth.model;

public class RegistrationConfirmRequest {

    private String login;
    private String conformationCode;

    public RegistrationConfirmRequest() {}

    public RegistrationConfirmRequest(String login, String conformationCode) {
        this.login = login;
        this.conformationCode = conformationCode;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getConformationCode() {
        return conformationCode;
    }

    public void setConformationCode(String conformationCode) {
        this.conformationCode = conformationCode;
    }
}

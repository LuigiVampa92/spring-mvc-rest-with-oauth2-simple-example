package com.luigivampa92.ingots.springmvc.auth.model;

public class RegistrationRequestRequest {

    private String login;
    private String password;
    private String email;

    public RegistrationRequestRequest() {}

    public RegistrationRequestRequest(String login, String password, String email) {
        this.email = email;
        this.password = password;
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

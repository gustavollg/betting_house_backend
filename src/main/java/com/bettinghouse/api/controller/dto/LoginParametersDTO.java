package com.bettinghouse.api.controller.dto;

import javax.validation.constraints.NotBlank;

public class LoginParametersDTO {
    
    @NotBlank(message = "LOGIN_IS_NULL")
    private String login;
    
    @NotBlank(message = "PASSWORD_IS_NULL")
    private String password;

    public LoginParametersDTO() {
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
}

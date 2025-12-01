package com.projet.j_and_d.api.request;

import jakarta.validation.constraints.NotBlank;

public class AuthRequest {
    @NotBlank
    private String login;

    @NotBlank
    private String password;

    public String getUsername() {
        return login;
    }

    public void setUsername(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

package com.projet.j_and_d.api.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SubscriptionRequest {
    @NotBlank
    private String login;

    @NotBlank
    private String password;

    @NotNull
    private boolean gm;

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isGm() {
        return this.gm;
    }

    public void setGm(boolean gm) {
        this.gm = gm;
    }

}

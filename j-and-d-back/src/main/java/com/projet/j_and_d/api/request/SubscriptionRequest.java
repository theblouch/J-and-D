package com.projet.j_and_d.api.request;

import jakarta.validation.constraints.NotBlank;

public class SubscriptionRequest {
    @NotBlank
    private String login;

    @NotBlank
    private String password;

    @NotBlank
    private boolean GM;

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

    public boolean isGM() {
        return this.GM;
    }

    public void setGM(boolean GM) {
        this.GM = GM;
    }

}

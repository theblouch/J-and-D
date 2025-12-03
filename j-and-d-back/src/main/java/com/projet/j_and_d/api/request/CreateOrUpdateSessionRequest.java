package com.projet.j_and_d.api.request;

import java.util.List;

import jakarta.validation.constraints.NotNull;

public class CreateOrUpdateSessionRequest {
    private List<String> inscriptionCharacters;

    @NotNull
    private String gmLogin;

    private List<String> npcNames;

    public List<String> getInscriptionCharacters() {
        return this.inscriptionCharacters;
    }

    public void setInscriptionCharacters(List<String> inscriptionCharacters) {
        this.inscriptionCharacters = inscriptionCharacters;
    }

    public String getGmLogin() {
        return this.gmLogin;
    }

    public void setGmLogin(String gmLogin) {
        this.gmLogin = gmLogin;
    }

    public List<String> getNpcNames() {
        return this.npcNames;
    }

    public void setNpcNames(List<String> npcNames) {
        this.npcNames = npcNames;
    }
}

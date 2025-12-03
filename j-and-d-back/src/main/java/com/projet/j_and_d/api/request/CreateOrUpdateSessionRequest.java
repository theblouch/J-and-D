package com.projet.j_and_d.api.request;

import java.util.List;

import jakarta.validation.constraints.NotNull;

public class CreateOrUpdateSessionRequest {
    private List<Integer> inscriptionIds;

    @NotNull
    private String gmLogin;

    private List<Integer> npcIds;

    public List<Integer> getInscriptionIds() {
        return this.inscriptionIds;
    }

    public void setInscriptionIds(List<Integer> inscriptionIds) {
        this.inscriptionIds = inscriptionIds;
    }

    public String getGmLogin() {
        return this.gmLogin;
    }

    public void setGmLogin(String gmLogin) {
        this.gmLogin = gmLogin;
    }

    public List<Integer> getNpcIds() {
        return this.npcIds;
    }

    public void setNpcIds(List<Integer> npcIds) {
        this.npcIds = npcIds;
    }
}

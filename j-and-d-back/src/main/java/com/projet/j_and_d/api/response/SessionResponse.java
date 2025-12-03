package com.projet.j_and_d.api.response;

import java.util.List;

import com.projet.j_and_d.model.NPC;
import com.projet.j_and_d.model.Session;

public class SessionResponse {
    private Integer id;

    private List<String> inscriptionCharacters;

    private String gmLogin;

    private List<String> npcNames;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public static SessionResponse convert(Session session) {
        SessionResponse response = new SessionResponse();

        response.setId(session.getId());
        response.setGmLogin(session.getGm().getLogin());
        response.setInscriptionCharacters(
                session.getInscriptions()
                        .stream()
                        .map(inscription -> inscription.getCharacter().getName())
                        .toList());
        response.setNpcNames(
                session.getNpcs()
                        .stream()
                        .map(NPC::getName)
                        .toList());

        return response;
    }
}

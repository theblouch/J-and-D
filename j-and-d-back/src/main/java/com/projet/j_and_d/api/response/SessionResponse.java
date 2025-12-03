package com.projet.j_and_d.api.response;

import java.util.List;

import com.projet.j_and_d.model.Inscription;
import com.projet.j_and_d.model.NPC;
import com.projet.j_and_d.model.Session;

public class SessionResponse {
    private Integer id;

    private List<Integer> inscriptionIds;

    private String gmLogin;

    private List<Integer> npcIds;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public static SessionResponse convert(Session session) {
        SessionResponse response = new SessionResponse();

        response.setId(session.getId());
        response.setGmLogin(session.getGm().getLogin());
        response.setInscriptionIds(
                session.getInscriptions()
                        .stream()
                        .map(Inscription::getId)
                        .toList());
        response.setNpcIds(
                session.getNpcs()
                        .stream()
                        .map(NPC::getId)
                        .toList());

        return response;
    }
}

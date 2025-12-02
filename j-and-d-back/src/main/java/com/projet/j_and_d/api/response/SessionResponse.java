package com.projet.j_and_d.api.response;

import java.util.List;

import com.projet.j_and_d.model.Inscription;
import com.projet.j_and_d.model.NPC;
import com.projet.j_and_d.model.Session;

public class SessionResponse {
    private List<Integer> inscriptionIds;

    private Integer gmId;

    private List<Integer> npcIds;

    public List<Integer> getInscriptionIds() {
        return this.inscriptionIds;
    }

    public void setInscriptionIds(List<Integer> inscriptionIds) {
        this.inscriptionIds = inscriptionIds;
    }

    public Integer getGmId() {
        return this.gmId;
    }

    public void setGmId(Integer gmId) {
        this.gmId = gmId;
    }

    public List<Integer> getNpcIds() {
        return this.npcIds;
    }

    public void setNpcIds(List<Integer> npcIds) {
        this.npcIds = npcIds;
    }

    public static SessionResponse convert(Session session) {
        SessionResponse response = new SessionResponse();
        System.out.println("---------------------------\n\n\n\n\n objet GM " + session.getGm().getLogin()
                + "\n\n" + session.getGm().getPassword() + "\n\n" + session.getGm().getId() + "\n\n");
        response.setGmId(session.getGm().getId());
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

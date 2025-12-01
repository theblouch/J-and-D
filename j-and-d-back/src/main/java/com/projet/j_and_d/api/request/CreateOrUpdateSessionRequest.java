package com.projet.j_and_d.api.request;

import java.util.List;

public class CreateOrUpdateSessionRequest {
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
}

package com.projet.j_and_d.api.request;

public class CreateOrUpdateInscriptionRequest {

    private Integer characterId;
    private Integer sessionId;

    public Integer getCharacterId() {
        return characterId;
    }

    public void setCharacterId(Integer characterId) {
        this.characterId = characterId;
    }

    public Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }

}

package com.projet.j_and_d.api.response;

import com.projet.j_and_d.model.Inscription;

public class InscriptionResponse {
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

    public static InscriptionResponse convert(Inscription inscription) {
        InscriptionResponse response = new InscriptionResponse();
        response.setCharacterId(inscription.getCharacter().getId());
        response.setSessionId(inscription.getSession().getId());

        return response;
    }
}

package com.projet.j_and_d.api.response;

import com.projet.j_and_d.model.Inscription;

public class InscriptionResponse {
    private Integer characterId;
    private String characterName;
    private Integer sessionId;
    private Integer id;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCharacterId() {
        return characterId;
    }

    public void setCharacterId(Integer characterId) {
        this.characterId = characterId;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }

    public static InscriptionResponse convert(Inscription inscription) {
        InscriptionResponse response = new InscriptionResponse();
        response.setId(inscription.getId());
        response.setCharacterId(inscription.getCharacter().getId());
        response.setCharacterName(inscription.getCharacter().getName());
        response.setSessionId(inscription.getSession().getId());

        return response;
    }
}

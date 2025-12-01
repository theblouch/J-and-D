package com.projet.j_and_d.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "inscription")
public class Inscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Character character;
    private Session session;

    private int posX;
    private int posY;

    public Inscription() {
    }

    public Inscription(Character character, Session session, int posX, int posY) {
        this.character = character;
        this.session = session;
        this.posX = posX;
        this.posY = posY;
    }

    public Character getCharacter() {
        return this.character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public Session getSession() {
        return this.session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public int getPosX() {
        return this.posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return this.posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public Inscription character(Character character) {
        setCharacter(character);
        return this;
    }

    public Inscription session(Session session) {
        setSession(session);
        return this;
    }

    public Inscription posX(int posX) {
        setPosX(posX);
        return this;
    }

    public Inscription posY(int posY) {
        setPosY(posY);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                " character='" + getCharacter() + "'" +
                ", session='" + getSession() + "'" +
                ", posX='" + getPosX() + "'" +
                ", posY='" + getPosY() + "'" +
                "}";
    }

}

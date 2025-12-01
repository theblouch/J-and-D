package com.projet.j_and_d.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "inscription")
public class Inscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @Column(nullable = false)
    private Character character;
    @ManyToOne
    @Column(nullable = false)
    private Session session;

    public Inscription() {
    }

    public Inscription(Character character, Session session) {
        this.character = character;
        this.session = session;
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

    public Inscription character(Character character) {
        setCharacter(character);
        return this;
    }

    public Inscription session(Session session) {
        setSession(session);
        return this;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "{" +
                " character='" + getCharacter() + "'" +
                ", session='" + getSession() + "'" +
                "}";
    }

}

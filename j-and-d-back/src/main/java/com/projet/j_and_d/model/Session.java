package com.projet.j_and_d.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "session")
    private List<Inscription> inscriptions;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gm_id")
    private GM gm;

    @OneToMany(mappedBy = "session")
    private List<NPC> npcs;

    private String name;

    public Session() {
    }

    public Session(List<Inscription> inscriptions, GM gm, List<NPC> npcs, String name) {
        this.inscriptions = inscriptions;
        this.gm = gm;
        this.npcs = npcs;
        this.name = name;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Inscription> getInscriptions() {
        return this.inscriptions;
    }

    public void setInscriptions(List<Inscription> inscriptions) {
        this.inscriptions = inscriptions;
    }

    public GM getGm() {
        return this.gm;
    }

    public void setGm(GM gm) {
        this.gm = gm;
    }

    public List<NPC> getNpcs() {
        return this.npcs;
    }

    public void setNpcs(List<NPC> npcs) {
        this.npcs = npcs;
    }

    public Session id(Integer id) {
        setId(id);
        return this;
    }

    public Session inscriptions(List<Inscription> inscriptions) {
        setInscriptions(inscriptions);
        return this;
    }

    public Session gm(GM gm) {
        setGm(gm);
        return this;
    }

    public Session npcs(List<NPC> npcs) {
        setNpcs(npcs);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", inscriptions='" + getInscriptions() + "'" +
                ", gm='" + getGm() + "'" +
                ", npcs='" + getNpcs() + "'" +
                "}";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

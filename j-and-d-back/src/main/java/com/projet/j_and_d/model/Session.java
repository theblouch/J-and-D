package com.projet.j_and_d.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "session")
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nameMap;

    private String linkMap;

    @OneToMany(mappedBy = "session")
    private List<Inscription> inscriptions;

    @Column(nullable = false)
    private GM gm;

    @OneToMany(mappedBy = "session")
    private List<NPC> npcs;

    public Session() {
    }

    public Session(String nameMap, String linkMap, List<Inscription> inscriptions, List<Player> players, GM gm,
            List<NPC> npcs) {
        this.nameMap = nameMap;
        this.linkMap = linkMap;
        this.inscriptions = inscriptions;
        this.players = players;
        this.gm = gm;
        this.npcs = npcs;
    }

    public String getNameMap() {
        return this.nameMap;
    }

    public void setNameMap(String nameMap) {
        this.nameMap = nameMap;
    }

    public String getLinkMap() {
        return this.linkMap;
    }

    public void setLinkMap(String linkMap) {
        this.linkMap = linkMap;
    }

    public List<Inscription> getInscriptions() {
        return this.inscriptions;
    }

    public void setInscriptions(List<Inscription> inscriptions) {
        this.inscriptions = inscriptions;
    }

    public List<Player> getPlayers() {
        return this.players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
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

    public Session nameMap(String nameMap) {
        setNameMap(nameMap);
        return this;
    }

    public Session linkMap(String linkMap) {
        setLinkMap(linkMap);
        return this;
    }

    public Session inscriptions(List<Inscription> inscriptions) {
        setInscriptions(inscriptions);
        return this;
    }

    public Session players(List<Player> players) {
        setPlayers(players);
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
                " nameMap='" + getNameMap() + "'" +
                ", linkMap='" + getLinkMap() + "'" +
                ", inscriptions='" + getInscriptions() + "'" +
                ", players='" + getPlayers() + "'" +
                ", gm='" + getGm() + "'" +
                ", npcs='" + getNpcs() + "'" +
                "}";
    }

}

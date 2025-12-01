package com.projet.j_and_d.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public abstract class Role implements IDamage, IControl, IHeal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name; // Warrior, Druid, Rgoue, Mage
    @OneToMany
    private List<Spell> spells = new ArrayList<>();

    public List<Spell> getSpells() {
        return spells;
    }

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    public void setSpells(List<Spell> spells) {
        this.spells = spells;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

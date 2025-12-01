package com.projet.j_and_d.model;

import java.util.Random;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Spell {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @Column(length = 180, nullable = false)
    private final String name;
    @Column(length = 180, nullable = false)
    private final String description;
    @Column(nullable = false)
    private final int spellLevel;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    private int[] baseDamage;

    public Spell(String name, String description, int spellLevel, Role role) {
        this.name = name;
        this.description = description;
        this.spellLevel = spellLevel;
        this.role = role;
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

    public String getDescription() {
        return description;
    }

    public int getSpellLevel() {
        return spellLevel;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int calculDamages() {
        Random rd = new Random();
        int sum = 0;
        for (int i = 0; i < this.baseDamage.length; i++) {
            for (int j = 0; j < this.baseDamage[i]; j++) {

                int intermValue = rd.nextInt((2 * i) + 4) + 1;
                sum += intermValue;
            }
        }
        return sum;
    }
}

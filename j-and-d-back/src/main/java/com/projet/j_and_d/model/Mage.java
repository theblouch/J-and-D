package com.projet.j_and_d.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Mage")
public class Mage extends Role {

    public Mage() {
        super();

        this.name = "Mage";
        this.baseHp = 6;
        this.baseMp = 12;
        this.baseMs = 5.0;
        this.baseArmor = 8;
        this.baseIni = 12;

        this.baseStats = new Stats(
                14, // Wisdom
                8, // Strength
                10, // Constitution
                16, // Intelligence
                12, // Dexterity
                10 // Charisma
        );
    }
}

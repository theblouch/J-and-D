package com.projet.j_and_d.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Druid")
public class Druid extends Role {

    public Druid() {
        super();

        this.name = "Druid";
        this.baseHp = 10;
        this.baseMp = 6;
        this.baseMs = 5.0;
        this.baseArmor = 10;
        this.baseIni = 11;

        this.baseStats = new Stats(
                16, // Wisdom
                10, // Strength
                13, // Constitution
                12, // Intelligence
                12, // Dexterity
                12 // Charisma
        );
    }

}

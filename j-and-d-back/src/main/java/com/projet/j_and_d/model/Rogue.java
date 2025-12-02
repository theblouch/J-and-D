package com.projet.j_and_d.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Rogue")
public class Rogue extends Role {

    public Rogue() {
        super();

        this.name = "Rogue";
        this.baseHp = 9;
        this.baseMp = 0;
        this.baseMs = 6.0;
        this.baseArmor = 11;
        this.baseIni = 15;

        // this.armor = ItemFactory.createArmor("Padded Vest", 1);
        // this.weapon = ItemFactory.createWeapon("Rusty Dagger", 3);

        this.baseStats = new Stats(
                10, // Wisdom
                10, // Strength
                12, // Constitution
                12, // Intelligence
                16, // Dexterity
                14 // Charisma
        );
    }
}

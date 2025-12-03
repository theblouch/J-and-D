package com.projet.j_and_d.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Warrior")
public class Warrior extends Role {

    public Warrior() {
        super();
        this.name = "Warrior";
        this.baseHp = 12; // Guerrier niveau 1 ~12 PV
        this.baseMp = 0; // Pas de magie
        this.baseMs = 4.0; // Vitesse standard
        this.baseArmor = 12; // Armure légère + bouclier simple
        this.baseIni = 10; // Initiative neutre

        this.baseStats = new Stats(
                10, // Wisdom
                16, // Strength
                15, // Constitution
                8, // Intelligence
                12, // Dexterity
                10 // Charisma
        );
    }
}

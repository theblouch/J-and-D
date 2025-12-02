package com.projet.j_and_d.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Monster")
public class Monster extends Role {

    public Monster() {
        super();

        this.name = "Monster";
        this.baseHp = 8; // PV moyens pour un monstre de base
        this.baseMp = 0; // Peu ou pas de magie
        this.baseMs = 5.0; // Vitesse standard d’une créature
        this.baseArmor = 10; // Armure naturelle légère
        this.baseIni = 10; // Initiative neutre

        // this.armor = ItemFactory.createArmor("Thick Hide", 1);
        // this.weapon = ItemFactory.createWeapon("Claws", 3);

        this.baseStats = new Stats(
                8, // Wisdom
                13, // Strength
                12, // Constitution
                6, // Intelligence
                12, // Dexterity
                6 // Charisma
        );
    }
}

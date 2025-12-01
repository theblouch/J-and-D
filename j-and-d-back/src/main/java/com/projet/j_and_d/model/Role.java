package com.projet.j_and_d.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Embedded;
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

    private int baseHp;
    private int baseMp;
    private double baseMs;
    private int baseArmor;
    private int baseIni;
    private Item armor;
    private Item weapon;
    @Embedded
    private Stats baseStats;

    public Role() {
    }

    public Role(String name, int baseHp, int baseMp, double baseMs, int baseArmor, int baseIni, Item armor,
            Item weapon, Stats baseStats, List<Spell> spells) {
        this.name = name;
        this.baseHp = baseHp;
        this.baseMp = baseMp;
        this.baseMs = baseMs;
        this.baseArmor = baseArmor;
        this.baseIni = baseIni;
        this.armor = armor;
        this.weapon = weapon;
        this.baseStats = baseStats;
        this.spells = spells;
    }

    @OneToMany
    private List<Spell> spells = new ArrayList<>();

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBaseHp() {
        return this.baseHp;
    }

    public void setBaseHp(int baseHp) {
        this.baseHp = baseHp;
    }

    public int getBaseMp() {
        return this.baseMp;
    }

    public void setBaseMp(int baseMp) {
        this.baseMp = baseMp;
    }

    public double getBaseMs() {
        return this.baseMs;
    }

    public void setBaseMs(double baseMs) {
        this.baseMs = baseMs;
    }

    public int getBaseArmor() {
        return this.baseArmor;
    }

    public void setBaseArmor(int baseArmor) {
        this.baseArmor = baseArmor;
    }

    public int getBaseIni() {
        return this.baseIni;
    }

    public void setBaseIni(int baseIni) {
        this.baseIni = baseIni;
    }

    public Item getArmor() {
        return this.armor;
    }

    public void setArmor(Item armor) {
        this.armor = armor;
    }

    public Item getWeapon() {
        return this.weapon;
    }

    public void setWeapon(Item weapon) {
        this.weapon = weapon;
    }

    public Stats getBaseStats() {
        return this.baseStats;
    }

    public void setBaseStats(Stats baseStats) {
        this.baseStats = baseStats;
    }

    public List<Spell> getSpells() {
        return this.spells;
    }

    public void setSpells(List<Spell> spells) {
        this.spells = spells;
    }

}

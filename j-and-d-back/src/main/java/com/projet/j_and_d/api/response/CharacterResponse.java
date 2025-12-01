package com.projet.j_and_d.api.response;

import java.util.List;

import com.projet.j_and_d.model.Item;
import com.projet.j_and_d.model.Race;
import com.projet.j_and_d.model.Spell;
import com.projet.j_and_d.model.Stats;

import com.projet.j_and_d.model.Character;

public class CharacterResponse {
    private Integer id;
    private String name;
    private double level;
    private int hp;
    private int mp;
    private double speed;
    private boolean alive = true;
    private int armorClass;
    private int initiative;

    private List<Item> itemWorn;

    private List<Item> inventory;

    private Stats stats;

    private Integer roleId;
    private String roleName;
    private List<Spell> roleSpells;

    private Race race;

    public List<Spell> getRoleSpells() {
        return roleSpells;
    }

    public void setRoleSpells(List<Spell> roleSpells) {
        this.roleSpells = roleSpells;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMp() {
        return mp;
    }

    public void setMp(int mp) {
        this.mp = mp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLevel() {
        return level;
    }

    public void setLevel(double level) {
        this.level = level;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public int getArmorClass() {
        return armorClass;
    }

    public void setArmorClass(int armorClass) {
        this.armorClass = armorClass;
    }

    public int getInitiative() {
        return initiative;
    }

    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }

    public List<Item> getItemWorn() {
        return itemWorn;
    }

    public void setItemWorn(List<Item> itemWorn) {
        this.itemWorn = itemWorn;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public void setInventory(List<Item> inventory) {
        this.inventory = inventory;
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public static CharacterResponse convert(Character character) {
        CharacterResponse response = new CharacterResponse();
        response.setName(character.getName());
        response.setLevel(character.getLevel());
        response.setHp(character.getHp());
        response.setMp(character.getMp());
        response.setSpeed(character.getSpeed());
        response.setAlive(character.isAlive());
        response.setArmorClass(character.getArmorClass());
        response.setInitiative(character.getInitiative());

        response.setItemWorn(character.getItemWorn());
        response.setInventory(character.getInventory());
        response.setStats(character.getStats());

        if (character.getRole() != null) {
            response.setRoleId(character.getRole().getId());
            response.setRoleName(character.getRole().getName());
            response.setRoleSpells(character.getRole().getSpells());
        }

        response.setRace(character.getRace());

        return response;
    }
}

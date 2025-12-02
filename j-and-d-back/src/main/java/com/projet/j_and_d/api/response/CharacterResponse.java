package com.projet.j_and_d.api.response;

import java.util.Collections;
import java.util.List;

import com.projet.j_and_d.model.Item;
import com.projet.j_and_d.model.Race;
import com.projet.j_and_d.model.State;
import com.projet.j_and_d.model.Stats;

import com.projet.j_and_d.model.Character;

public class CharacterResponse {
    private String name;
    private double level;
    private int hp;
    private int mp;
    private double speed;
    private boolean alive = true;
    private int armorClass;
    private int initiative;

    private Integer armorId;
    private Integer weaponId;

    private List<Integer> itemWornIds;
    private List<Integer> inventoryIds;

    private Stats stats;

    private Integer roleId;

    private List<String> states;

    private Race race;

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

    public Integer getArmorId() {
        return armorId;
    }

    public void setArmorId(Integer armorId) {
        this.armorId = armorId;
    }

    public Integer getWeaponId() {
        return weaponId;
    }

    public void setWeaponId(Integer weaponId) {
        this.weaponId = weaponId;
    }

    public List<Integer> getItemWornIds() {
        return itemWornIds;
    }

    public void setItemWornIds(List<Integer> itemWornIds) {
        this.itemWornIds = itemWornIds;
    }

    public List<Integer> getInventoryIds() {
        return inventoryIds;
    }

    public void setInventoryIds(List<Integer> inventoryIds) {
        this.inventoryIds = inventoryIds;
    }

    public List<String> getStates() {
        return states;
    }

    public void setStates(List<String> states) {
        this.states = states;
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
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

        // Sécurisation pour éviter NullPointerException
        response.setArmorId(character.getArmor() != null ? character.getArmor().getId() : null);
        response.setWeaponId(character.getWeapon().getId());

        response.setItemWornIds(
                character.getItemWorn() != null
                        ? character.getItemWorn().stream().map(Item::getId).toList()
                        : Collections.emptyList());

        response.setInventoryIds(
                character.getInventory() != null
                        ? character.getInventory().stream().map(Item::getId).toList()
                        : Collections.emptyList());

        response.setStates(
                character.getState() != null
                        ? character.getState().stream().map(State::name).toList()
                        : Collections.emptyList());

        response.setStats(character.getStats());
        response.setRoleId(character.getRole().getId());
        response.setRace(character.getRace());

        return response;
    }
}

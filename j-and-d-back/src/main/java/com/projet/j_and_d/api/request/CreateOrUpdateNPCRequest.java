package com.projet.j_and_d.api.request;

import java.util.List;

import com.projet.j_and_d.model.Stats;

import jakarta.validation.constraints.NotBlank;

public class CreateOrUpdateNPCRequest {
    @NotBlank
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

    private double xP;
    private Integer sessionId;

    public List<String> getStates() {
        return states;
    }

    public void setStateIds(List<String> states) {
        this.states = states;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
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

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public double getXP() {
        return xP;
    }

    public void setXP(double xP) {
        this.xP = xP;
    }

    public Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }
}

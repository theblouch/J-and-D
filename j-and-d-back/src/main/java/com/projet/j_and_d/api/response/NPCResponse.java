package com.projet.j_and_d.api.response;

import java.util.List;

import com.projet.j_and_d.model.Item;
import com.projet.j_and_d.model.NPC;
import com.projet.j_and_d.model.Stats;

public class NPCResponse {
    private Integer id;
    private String name;
    private double level;
    private int hp;
    private int mp;
    private double speed;
    private boolean alive = true;
    private int armorClass;
    private int initiative;

    private List<Integer> itemWornIds;

    private List<Integer> inventoryIds;

    private Stats stats;

    private double xP;
    private Integer sessionId;

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

    public static NPCResponse convert(NPC npc) {
        NPCResponse response = new NPCResponse();
        response.setName(npc.getName());
        response.setLevel(npc.getLevel());
        response.setHp(npc.getHp());
        response.setMp(npc.getMp());
        response.setSpeed(npc.getSpeed());
        response.setAlive(npc.isAlive());
        response.setArmorClass(npc.getArmorClass());
        response.setInitiative(npc.getInitiative());

        response.setItemWornIds(
                npc.getItemWorn()
                        .stream()
                        .map(Item::getId)
                        .toList());

        response.setInventoryIds(
                npc.getInventory()
                        .stream()
                        .map(Item::getId)
                        .toList());

        response.setStats(npc.getStats());
        response.setXP(npc.getXP());
        response.setSessionId(npc.getSession().getId());

        return response;
    }
}

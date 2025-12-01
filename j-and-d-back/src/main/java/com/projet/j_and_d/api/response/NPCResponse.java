package com.projet.j_and_d.api.response;

import java.util.List;

import com.projet.j_and_d.model.Item;
import com.projet.j_and_d.model.NPC;
import com.projet.j_and_d.model.Session;
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

    private List<Item> itemWorn;

    private List<Item> inventory;

    private Stats stats;

    private double xP;
    private Session session;

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

    public double getXP() {
        return xP;
    }

    public void setXP(double xP) {
        this.xP = xP;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
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

        response.setItemWorn(npc.getItemWorn());
        response.setInventory(npc.getInventory());
        response.setStats(npc.getStats());
        response.setXP(npc.getXP());
        response.setSession(npc.getSession());

        return response;
    }
}

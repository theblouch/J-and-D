package com.projet.j_and_d.api.response;

import java.util.List;

import com.projet.j_and_d.model.Role;
import com.projet.j_and_d.model.Spell;
import com.projet.j_and_d.model.Stats;

public class RoleResponse {
    private String name;

    private Integer armorId;
    private Integer weaponId;

    private List<Integer> spellIds;

    private Stats baseStats;

    private int baseHp;
    private int baseMp;
    private double baseMs;
    private int baseArmor;
    private int baseIni;

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

    public Stats getBaseStats() {
        return this.baseStats;
    }

    public void setBaseStats(Stats baseStats) {
        this.baseStats = baseStats;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<Integer> getSpellIds() {
        return spellIds;
    }

    public void setSpellIds(List<Integer> spellIds) {
        this.spellIds = spellIds;
    }

    public static RoleResponse convert(Role role) {
        RoleResponse response = new RoleResponse();
        response.setName(role.getName());

        response.setArmorId(role.getArmor() != null ? role.getArmor().getId() : null);
        response.setWeaponId(role.getWeapon().getId());

        response.setSpellIds(role.getSpells() != null
                ? role.getSpells().stream()
                        .map(Spell::getId)
                        .toList()
                : List.of());

        response.setBaseStats(role.getBaseStats());

        response.setBaseHp(role.getBaseHp());
        response.setBaseMp(role.getBaseMp());
        response.setBaseMs(role.getBaseMs());
        response.setBaseArmor(role.getBaseArmor());
        response.setBaseIni(role.getBaseIni());

        return response;
    }
}

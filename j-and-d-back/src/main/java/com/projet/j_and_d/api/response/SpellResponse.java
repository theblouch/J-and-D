package com.projet.j_and_d.api.response;

import com.projet.j_and_d.model.Role;
import com.projet.j_and_d.model.Spell;

public class SpellResponse {

    private String name;
    private String description;
    private int spellLevel;
    private Integer roleId;
    private int[] baseDamage;
    private Integer id;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSpellLevel() {
        return spellLevel;
    }

    public void setSpellLevel(int spellLevel) {
        this.spellLevel = spellLevel;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public int[] getBaseDamage() {
        return baseDamage;
    }

    public void setBaseDamage(int[] baseDamage) {
        this.baseDamage = baseDamage;
    }

    public static SpellResponse convert(Spell spell) {
        SpellResponse response = new SpellResponse();
        response.setId(spell.getId());
        response.setName(spell.getName());
        response.setDescription(spell.getDescription());
        response.setSpellLevel(spell.getSpellLevel());

        Role role = spell.getRole();
        response.setRoleId(role != null ? role.getId() : null);

        response.setBaseDamage(spell.getBaseDamage());

        return response;
    }

}

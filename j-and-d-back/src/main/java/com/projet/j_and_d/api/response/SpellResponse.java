package com.projet.j_and_d.api.response;

import com.projet.j_and_d.model.Role;
import com.projet.j_and_d.model.Spell;

public class SpellResponse {

    private int id;
    private String name;
    private String description;
    private int spellLevel;
    private Role role;
    private int[] baseDamage;

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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
        response.setRole(spell.getRole());
        response.setBaseDamage(spell.getBaseDamage());

        return response;
    }

    
}

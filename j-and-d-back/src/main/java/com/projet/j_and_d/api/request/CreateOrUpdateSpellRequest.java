package com.projet.j_and_d.api.request;


import com.projet.j_and_d.model.Role;

import jakarta.validation.constraints.NotBlank;


public class CreateOrUpdateSpellRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotBlank
    private int spellLevel;

    @NotBlank
    private Role role;

    @NotBlank
    private int[] BaseDamage;

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
        return BaseDamage;
    }

    public void setBaseDamage(int[] baseDamage) {
        BaseDamage = baseDamage;
    }

    
    
   
}

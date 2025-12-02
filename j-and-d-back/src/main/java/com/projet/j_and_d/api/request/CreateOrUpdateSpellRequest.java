package com.projet.j_and_d.api.request;

import jakarta.validation.constraints.NotBlank;

public class CreateOrUpdateSpellRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String description;

    private int spellLevel;
    private Integer roleId;
    private int[] baseDamage;

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

}

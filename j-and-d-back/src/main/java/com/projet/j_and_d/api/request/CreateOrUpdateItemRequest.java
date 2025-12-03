package com.projet.j_and_d.api.request;

import jakarta.validation.constraints.NotBlank;

public class CreateOrUpdateItemRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String description;
    private boolean basedOnStrength;
    private int[] baseDamage;
    private int armorValue;

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

    public boolean isBasedOnStrength() {
        return this.basedOnStrength;
    }

    public void setBasedOnStrength(boolean basedOnStrength) {
        this.basedOnStrength = basedOnStrength;
    }

    public int[] getBaseDamage() {
        return this.baseDamage;
    }

    public void setBaseDamage(int[] baseDamage) {
        this.baseDamage = baseDamage;
    }

    public int getArmorValue() {
        return armorValue;
    }

    public void setArmorValue(int armorValue) {
        this.armorValue = armorValue;
    }

}

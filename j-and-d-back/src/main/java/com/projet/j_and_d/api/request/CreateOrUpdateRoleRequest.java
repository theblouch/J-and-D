package com.projet.j_and_d.api.request;

import java.util.List;

import jakarta.validation.constraints.NotBlank;

public class CreateOrUpdateRoleRequest {
    @NotBlank
    private String name;

    private Integer armorId;
    private Integer weaponId;

    private List<Integer> spellIds;

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
}

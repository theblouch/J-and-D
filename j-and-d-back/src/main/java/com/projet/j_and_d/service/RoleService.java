package com.projet.j_and_d.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.projet.j_and_d.api.request.CreateOrUpdateRoleRequest;
import com.projet.j_and_d.exception.RoleNotFoundException;
import com.projet.j_and_d.model.Druid;
import com.projet.j_and_d.model.Mage;
import com.projet.j_and_d.model.Rogue;
import com.projet.j_and_d.model.Role;
import com.projet.j_and_d.model.Spell;
import com.projet.j_and_d.model.Warrior;
import com.projet.j_and_d.repo.RoleRepository;
import com.projet.j_and_d.repo.SpellRepository;
import com.projet.j_and_d.repo.ItemRepository;

@Service
public class RoleService {

    private final RoleRepository repository;
    private final SpellRepository spellRepo;
    private final ItemRepository itemRepo;

    public RoleService(RoleRepository repository, SpellRepository spellRepo, ItemRepository itemRepo) {
        this.repository = repository;
        this.spellRepo = spellRepo;
        this.itemRepo = itemRepo;
    }

    public List<Role> findAll() {
        return this.repository.findAll();
    }

    public Role findById(Integer id) {
        return this.repository.findById(id).orElseThrow(RoleNotFoundException::new);
    }

    private Role createRoleFromName(String name) {
        switch (name.toUpperCase()) {
            case "WARRIOR":
                return new Warrior();
            case "MAGE":
                return new Mage();
            case "ROGUE":
                return new Rogue();
            case "DRUID":
                return new Druid();
            default:
                throw new IllegalArgumentException("Unknown role name: " + name);
        }

    }

    public Role save(CreateOrUpdateRoleRequest request) {
        Role role = createRoleFromName(request.getName());
        return this.save(role, request);
    }

    public Role save(Integer id, CreateOrUpdateRoleRequest request) {
        Role role = this.findById(id);

        return this.save(role, request);
    }

    public void deleteById(Integer id) {
        this.repository.deleteById(id);
    }

    private Role save(Role role, CreateOrUpdateRoleRequest request) {
        List<Spell> spells = this.spellRepo.findAllById(request.getSpellIds());

        role.setName(request.getName());

        role.setName(request.getName());
        role.setBaseHp(request.getBaseHp());
        role.setBaseMp(request.getBaseMp());
        role.setBaseMs(request.getBaseMs());
        role.setBaseArmor(request.getBaseArmor());
        role.setBaseIni(request.getBaseIni());

        role.setArmor(this.itemRepo.getReferenceById(request.getArmorId()));
        role.setWeapon(this.itemRepo.getReferenceById(request.getWeaponId()));

        role.setBaseStats(request.getBaseStats());

        role.setSpells(spells);

        return this.repository.save(role);
    }

}

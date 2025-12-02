package com.projet.j_and_d.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.projet.j_and_d.api.request.CreateOrUpdateCharacterRequest;
import com.projet.j_and_d.exception.CharacterNotFoundException;
import com.projet.j_and_d.model.Character;
import com.projet.j_and_d.model.Role;
import com.projet.j_and_d.model.State;
import com.projet.j_and_d.repo.CharacterRepository;
import com.projet.j_and_d.repo.ItemRepository;
import com.projet.j_and_d.repo.RoleRepository;

@Service
public class CharacterService {

    private final CharacterRepository repository;
    private final RoleRepository roleRepo;
    private final ItemRepository itemRepo;

    public CharacterService(CharacterRepository repository, RoleRepository roleRepo, ItemRepository itemRepo) {
        this.repository = repository;
        this.roleRepo = roleRepo;
        this.itemRepo = itemRepo;
    }

    public List<Character> findAll() {
        return this.repository.findAll();
    }

    public Character findById(Integer id) {
        return this.repository.findById(id).orElseThrow(CharacterNotFoundException::new);
    }

    public Character save(CreateOrUpdateCharacterRequest request) {
        return this.save(new Character(), request);
    }

    public Character save(Integer id, CreateOrUpdateCharacterRequest request) {
        Character character = this.findById(id);

        return this.save(character, request);
    }

    public void deleteById(Integer id) {
        this.repository.deleteById(id);
    }

    private Character save(Character character, CreateOrUpdateCharacterRequest request) {
        Role role = this.roleRepo.getReferenceById(request.getRoleId());
        character.setRole(role);

        character.setName(request.getName());
        character.setRace(request.getRace());
        character.setAlive(request.isAlive());

        // Si création, initialisation avec rôle
        if (character.getId() == null) {
            character.setLevel(1);
            character.setHp(role.getBaseHp());
            character.setMp(role.getBaseMp());
            character.setSpeed(role.getBaseMs());
            character.setArmorClass(role.getBaseArmor());
            character.setInitiative(role.getBaseIni());
            character.setArmor(role.getArmor());
            character.setWeapon(role.getWeapon());
            character.setStats(role.getBaseStats());

        } else {
            // Pour les updates
            character.setLevel(request.getLevel());
            character.setHp(request.getHp());
            character.setMp(request.getMp());
            character.setSpeed(request.getSpeed());
            character.setArmorClass(request.getArmorClass());
            character.setInitiative(request.getInitiative());
        }

        if (request.getItemWornIds() != null) {
            character.setItemWorn(this.itemRepo.findAllById(request.getItemWornIds()));
        }

        if (request.getInventoryIds() != null) {
            character.setInventory(this.itemRepo.findAllById(request.getInventoryIds()));
        }

        if (request.getStates() != null) {
            character.setState(request.getStates().stream().map(State::valueOf).toList());
        }

        return this.repository.save(character);
    }
}

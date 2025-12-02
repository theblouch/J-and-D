package com.projet.j_and_d.service;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.projet.j_and_d.api.request.CreateOrUpdateCharacterRequest;
import com.projet.j_and_d.exception.CharacterNotFoundException;
import com.projet.j_and_d.model.Character;
import com.projet.j_and_d.model.Item;
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
        // List<Item> itemWorn = this.itemRepo.findAllById(request.getItemWornIds());
        // List<Item> inventory = this.itemRepo.findAllById(request.getInventoryIds());

        character.setName(request.getName());
        character.setLevel(request.getLevel());
        character.setHp(request.getHp());
        character.setMp(request.getMp());
        character.setSpeed(request.getSpeed());
        character.setAlive(request.isAlive());
        character.setArmorClass(request.getArmorClass());
        character.setInitiative(request.getInitiative());

        Item armor = null;
        if (request.getArmorId() != null) {
            armor = this.itemRepo.getReferenceById(request.getArmorId());
        }
        character.setArmor(armor);

        character.setWeapon(this.itemRepo.getReferenceById(request.getWeaponId()));

        List<Item> itemWorn = Collections.emptyList();
        if (request.getItemWornIds() != null) {
            itemWorn = this.itemRepo.findAllById(request.getItemWornIds());
        }
        character.setItemWorn(itemWorn);

        List<Item> inventory = Collections.emptyList();
        if (request.getInventoryIds() != null) {
            inventory = this.itemRepo.findAllById(request.getInventoryIds());
        }
        character.setInventory(inventory);
        character.setStats(request.getStats());

        character.setRole(this.roleRepo.getReferenceById(request.getRoleId()));

        character.setRace(request.getRace());

        List<State> states = Collections.emptyList();
        if (request.getStates() != null) {
            states = request.getStates()
                    .stream()
                    .map(State::valueOf)
                    .toList();
        }
        character.setState(states);

        return this.repository.save(character);
    }
}

package com.projet.j_and_d.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.projet.j_and_d.api.request.CreateOrUpdateCharacterRequest;
import com.projet.j_and_d.exception.CharacterNotFoundException;
import com.projet.j_and_d.model.Character;
import com.projet.j_and_d.repo.CharacterRepository;

@Service
public class CharacterService {

    private final CharacterRepository repository;
    private final RoleRepository roleRepository;

    public CharacterService(CharacterRepository repository, RoleRepository roleRepository) {
        this.repository = repository;
        this.roleRepository = roleRepository;
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
        character.setName(request.getName());
        character.setLevel(request.getLevel());
        character.setHp(request.getHp());
        character.setMp(request.getMp());
        character.setSpeed(request.getSpeed());
        character.setAlive(request.isAlive());
        character.setArmorClass(request.getArmorClass());
        character.setInitiative(request.getInitiative());

        character.setItemWorn(request.getItemWorn());
        character.setInventory(request.getInventory());
        character.setStats(request.getStats());

        character.setRole(this.roleRepository.getReferenceById(request.getRoleId()));

        character.setRace(request.getRace());

        return this.repository.save(character);
    }
}

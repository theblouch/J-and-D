package com.projet.j_and_d.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.projet.j_and_d.api.request.CreateOrUpdateSpellRequest;
import com.projet.j_and_d.exception.SpellNotFoundException;
import com.projet.j_and_d.model.Spell;
import com.projet.j_and_d.repo.SpellRepository;

@Service
public class SpellService {

    private final SpellRepository repository;

    public SpellService(SpellRepository repository) {
        this.repository = repository;
    }

    public List<Spell> findAll() {
        return this.repository.findAll();
    }

    public Spell findById(int id) {
        return this.repository.findById(id).orElseThrow(SpellNotFoundException::new);
    }

     public Spell findByName(String name) {
        return this.repository.findByName(name).orElseThrow(SpellNotFoundException::new);
    }

    public Spell save(CreateOrUpdateSpellRequest request) {
        return this.save(new Spell(), request);
    }

    public Spell save(int id, CreateOrUpdateSpellRequest request) {
        Spell spell = this.findById(id);

        return this.save(spell, request);
    }

    public void deleteById(int id) {
        this.repository.deleteById(id);
    }

    private Spell save(Spell spell, CreateOrUpdateSpellRequest request) {
        spell.setName(request.getName());
        spell.setDescription(request.getDescription());
        spell.setRole(request.getRole());
        spell.setSpellLevel(request.getSpellLevel());
        spell.setBaseDamage(request.getBaseDamage());

        return this.repository.save(spell);
    }

    //methode specifique


    public int[] getBaseDamage(String spellName) {
        Spell spell = repository.findByName(spellName)
                               .orElseThrow(SpellNotFoundException::new);
        return spell.getBaseDamage();
    }

    public Spell updateBaseDamage(String spellName, CreateOrUpdateSpellRequest request) {
        Spell spell = repository.findByName(spellName)
                                .orElseThrow(SpellNotFoundException::new);
        spell.setBaseDamage(request.getBaseDamage());
        return repository.save(spell);
    }

}

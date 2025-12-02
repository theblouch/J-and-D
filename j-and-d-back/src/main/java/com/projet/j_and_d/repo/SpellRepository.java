package com.projet.j_and_d.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projet.j_and_d.model.Spell;


public interface SpellRepository extends JpaRepository<Spell, Integer> {
     Optional<Spell> findByName(String name);
}




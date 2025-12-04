package com.projet.j_and_d.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projet.j_and_d.model.Character;

public interface CharacterRepository extends JpaRepository<Character, Integer> {
    Optional<Character> findByName(String name);
}

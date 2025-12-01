package com.projet.j_and_d.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projet.j_and_d.model.Character;

public interface CharacterRepository extends JpaRepository<Character, Integer> {

}

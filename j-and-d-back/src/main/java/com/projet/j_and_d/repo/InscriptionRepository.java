package com.projet.j_and_d.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.projet.j_and_d.model.Inscription;

import jakarta.transaction.Transactional;

public interface InscriptionRepository extends JpaRepository<Inscription, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Inscription i WHERE i.character.id = :characterId")
    void deleteAllByCharacterId(int characterId);
}

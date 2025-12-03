package com.projet.j_and_d.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.projet.j_and_d.model.Inscription;

import jakarta.transaction.Transactional;

public interface InscriptionRepository extends JpaRepository<Inscription, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Inscription i WHERE i.character.id = :characterId")
    void deleteAllByCharacterId(int characterId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Inscription i WHERE i.session.id = :sessionId")
    void deleteAllBySessionId(int sessionId);

    @Query("SELECT i FROM Inscription i WHERE i.character.name IN :names")
    List<Inscription> findAllByCharacterNameIn(@Param("names") List<String> names);
}

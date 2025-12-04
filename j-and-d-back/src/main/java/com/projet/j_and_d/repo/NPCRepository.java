package com.projet.j_and_d.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.projet.j_and_d.model.NPC;

import jakarta.transaction.Transactional;

public interface NPCRepository extends JpaRepository<NPC, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM NPC i WHERE i.session.id = :sessionId")
    void deleteAllBySessionId(int sessionId);

    List<NPC> findAllByNameIn(List<String> npcNames);

    Optional<NPC> findByName(String name);
}

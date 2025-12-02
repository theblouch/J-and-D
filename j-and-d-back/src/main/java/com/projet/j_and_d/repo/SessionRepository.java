package com.projet.j_and_d.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.projet.j_and_d.model.Session;

public interface SessionRepository extends JpaRepository<Session, Integer> {

    @Query("SELECT s FROM Session s LEFT JOIN FETCH s.gm WHERE s.id = :id")
    Optional<Session> findByIdWithGm(@Param("id") Integer id);
}

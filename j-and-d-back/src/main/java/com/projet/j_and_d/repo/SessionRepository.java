package com.projet.j_and_d.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projet.j_and_d.model.Session;

public interface SessionRepository extends JpaRepository<Session, Integer> {

}

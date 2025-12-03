package com.projet.j_and_d.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projet.j_and_d.model.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {

    Optional<Item> findByName(String name);
}

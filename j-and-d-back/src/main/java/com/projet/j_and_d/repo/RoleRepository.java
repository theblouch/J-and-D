package com.projet.j_and_d.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.projet.j_and_d.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    @Modifying
    @Query(value = "DELETE FROM role_spells WHERE spells_id = :spellId", nativeQuery = true)
    void deleteRoleSpellsBySpellId(@Param("spellId") Integer spellId);
}

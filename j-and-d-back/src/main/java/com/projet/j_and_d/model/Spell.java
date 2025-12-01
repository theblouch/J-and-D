package com.projet.j_and_d.model;

import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="spell")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Spell {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;

    @Column(length =180, nullable = false)
    private final String name;
    @Column(length =180, nullable = false)
    private final String description;
    @Column(nullable = false)
    private final int spellLevel;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public Spell(Integer id,String name, String description, int spellLevel) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.spellLevel = spellLevel;
    }

    public Spell(String name, String description, int spellLevel) {
        this.name = name;
        this.description = description;
        this.spellLevel = spellLevel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getSpellLevel() {
        return spellLevel;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    
    
}

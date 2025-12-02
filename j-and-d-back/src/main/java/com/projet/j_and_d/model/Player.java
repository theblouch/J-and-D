package com.projet.j_and_d.model;

import jakarta.persistence.Inheritance;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("Player")
public class Player extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @Column(nullable = false)
    protected String nom;

    @Column(length = 30, nullable = false)
    protected String prenom;

    @OneToMany
    @JoinColumn(name = "character_id", referencedColumnName = "id")
    private List<Character> characters;

    public Player() {
    }

    public Player(String login, String password, String nom, String prenom) {
        super(login, password);
        this.nom = nom;
        this.prenom = prenom;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public List<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(List<Character> characters) {
        this.characters = characters;
    }

    // Methodes
    public void createCharacter(String name, Role role, Race race) {
        Character myCharacter = new Character(name, 1, role.getBaseHp(), role.getBaseMp(), role.getBaseMs(), true,
                role.getBaseArmor(), role.getBaseIni(), role.getArmor(), role.getWeapon(), new ArrayList<Item>(),
                new ArrayList<Item>(), role.getBaseStats(), role, race, new ArrayList<State>());
        this.getCharacters().add(myCharacter);
    }

    public void joinSession() {
        // TODO
    }

}

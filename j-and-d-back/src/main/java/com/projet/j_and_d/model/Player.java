package com.projet.j_and_d.model;

import jakarta.persistence.Inheritance;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("Player")
public class Player extends User {

    @OneToMany
    @JoinColumn(name = "character_id", referencedColumnName = "id")
    private List<Character> characters;

    public Player() {
    }

    public Player(String login, String password) {
        super(login, password);
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

    public void joinSession(Session session, Character character) {
        Inscription inscription = new Inscription();
        inscription.setSession(session);
        inscription.setCharacter(character);
        session.getInscriptions().add(inscription);
    }

}

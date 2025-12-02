package com.projet.j_and_d.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
@DiscriminatorValue("GM")
public class GM extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @OneToMany
    private List<Session> sessions;

    public GM() {
    }

    public GM(String login, String password) {
        super(login, password);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    // methodes specifiques
    public void editHP(Creature creature, int damage) {
        creature.setHp(creature.getHp() - damage);
        System.out.println(creature.getName() + " perd " + damage + " HP. HP restant : " + creature.getHp());

    }

    public void createSession() {
        Session session = new Session(new ArrayList<>(), this, new ArrayList<>());
        System.out.println("Nouvelle session créée par le GM : " + this.login);
        this.sessions.add(session);
    }

    public void grantXp(Character character, NPC npc) {
        double xpGain = npc.getXP();
        character.setLevel(character.getLevel() + xpGain);
        System.out.println(character.getName() + " gagne " + xpGain + " XP. Total : " + character.getLevel());

    }

    public void reduceSpeed(Character character, double speedreduc) {
        character.setSpeed(character.getSpeed() - speedreduc);
        System.out.println(
                character.getName() + " perd " + speedreduc + " Speed. Speed restant : " + character.getSpeed());
    }

    public void endTurn() {
        System.out.println("Fin du tour.");
    }

}

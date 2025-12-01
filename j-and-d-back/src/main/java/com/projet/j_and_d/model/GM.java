package com.projet.j_and_d.model;



import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
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

    @Column(nullable = false,columnDefinition = "VARCHAR(30) default 'Doe'")
	protected String nom;

    @OneToMany
    private List<Session> sessions = new ArrayList<>();

    public GM() {}

    public GM(String login, String password, String nom) {
        super(login, password);
        this.nom = nom;
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

     public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    //methodes specifiques
    public void editHP  (Character character){
    int damage = 10; 
    character.setHp(character.getHp() - damage);
    System.out.println(character.getName() + " perd " + damage + " HP. HP restant : " + character.getHp());

    }

    public void editHP (NPC NPC){
        int damage = 5;
        NPC.setHp(NPC.getHp()- damage);
        System.out.println(NPC.getName() + " perd " + damage + " HP. HP restant : " + NPC.getHp());
        
    }
    public void createSession (){
        Session session = new Session();
        System.out.println("Nouvelle session créée par le GM : " + this.nom);
    }
    public void grantXp  (Character character){
        int xpGain = 50;
        character.setXp(character.getXp() + xpGain);
        System.out.println(character.getName() + " gagne " + xpGain + " XP. Total : " + character.getXp());
        
    }
    public void displayNPC  (NPC npc){
        System.out.println("NPC" +npc.getName()+" HP"+npc.getStats()+"Etat"+npc.alive);
    }

    public void reduceSpeed  (Character character){
    int speedreduc =2;
    character.setSpeed(character.getSpeed() - speedreduc);
    System.out.println(character.getName() + " perd " + speedreduc + " Speed. Speed restant : " + character.getSpeed());
    }
    public void endTurn  (){
        System.out.println("Fin du tour.");
    }
   
}

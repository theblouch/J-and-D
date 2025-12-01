package com.projet.j_and_d.model;



import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
@DiscriminatorValue("GM")
public class GM extends User {
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;

    @Column(nullable = false,columnDefinition = "VARCHAR(30) default 'Doe'")
	protected String nom;

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

    public void editHP  (Character character){

    }

    public void editHP (NPC NPC){
        
    }
    public void createSession (){
        
    }
    public void grantXp  (){
        
    }
    public void displayNPC  (NPC npc){
        
    }
    public void allowAttack  (){
        
    }
    public void reduceSpeed  (){
        
    }
    public void endTurn  (){
        
    }
   
}

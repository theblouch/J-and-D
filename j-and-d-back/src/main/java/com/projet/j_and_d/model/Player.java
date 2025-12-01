package com.projet.j_and_d.model;

import jakarta.persistence.Inheritance;




import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("Player")
public class Player extends User{
   
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;
	
	@Column(nullable = false)
	protected String nom;
	
	@Column(length = 30,nullable = false)
	protected String prenom;

    @OneToOne
    @JoinColumn(name = "character_id", referencedColumnName = "id")
    private Character character;
    
    public Player(){}
    

    public Player(String login, String password,String nom, String prenom) {
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

     public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }
	
    // Methodes
    public void createCharacter(){

    } 

    public void joinSession(){
        
    }

}

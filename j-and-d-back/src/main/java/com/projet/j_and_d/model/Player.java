package com.projet.j_and_d.model;

import jakarta.persistence.Inheritance;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.InheritanceType;
import j_and_d.view.Views;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("Player")
public class Player extends User{
   
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(Views.Common.class)
	protected Integer id;
	
	@Column(nullable = false)
	@JsonView(Views.Common.class)
	protected String nom;
	
	@Column(length = 30,nullable = false)
	@JsonView(Views.Common.class)
	protected String prenom;
    
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
	
    // Methodes
    public void createCharacter(){

    }

    public void joinSession(){
        
    }

}

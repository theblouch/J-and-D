package com.projet.j_and_d.model;

import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Inheritance;
import jakarta.persistence.Table;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Id;


@Entity
@Table(name="user")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type_User",columnDefinition = "ENUM('Player','GM')")

public abstract class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;
	
	@Column(length = 25,nullable = false,unique = true)
	protected String login;
	
	@Column(length = 180,nullable = false)
	protected String password;

	
	public User() {}
	
	public User(Integer id, String login, String password) {
		this.id = id;
		this.login = login;
		this.password = password;
	}
	
	public User(String login, String password) {
		this.login = login;
		this.password = password;
	}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}

    


package com.projet.j_and_d.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Random;

@Entity
@Table(name = "item")
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(length = 180, nullable = false)
	private String name;
	@Column(length = 180)
	private String description;

	private boolean basedOnStrength;

	private int[] baseDamage;

	public Item() {
	}

	public Item(String name, String description, boolean basedOnStrength, int[] baseDamage) {
		this.name = name;
		this.description = description;
		this.basedOnStrength = basedOnStrength;
		this.baseDamage = baseDamage;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isBasedOnStrength() {
		return this.basedOnStrength;
	}

	public void setBasedOnStrength(boolean basedOnStrength) {
		this.basedOnStrength = basedOnStrength;
	}

	public int[] getBaseDamage() {
		return this.baseDamage;
	}

	public void setBaseDamage(int[] baseDamage) {
		this.baseDamage = baseDamage;
	}

	public Item id(Integer id) {
		setId(id);
		return this;
	}

	@Override
	public String toString() {
		return "{" +
				" id='" + getId() + "'" +
				", name='" + getName() + "'" +
				", description='" + getDescription() + "'" +
				", basedOnStrength='" + isBasedOnStrength() + "'" +
				", baseDamage='" + getBaseDamage() + "'" +
				"}";
	}

	public int calculDamages() {
		Random rd = new Random();
		int sum = 0;
		for (int i = 0; i < this.baseDamage.length; i++) {
			for (int j = 0; j < this.baseDamage[i]; j++) {

				int intermValue = rd.nextInt((2 * i) + 4) + 1;
				sum += intermValue;
			}
		}
		return sum;
	}

}

package com.projet.j_and_d.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Stats {
	@Column(nullable = false)
	private int wisdom;
	@Column(nullable = false)
	private int strength;
	@Column(nullable = false)
	private int constitution;
	@Column(nullable = false)
	private int intelligence;
	@Column(nullable = false)
	private int dexterity;
	@Column(nullable = false)
	private int charisma;

	public Stats(int wisdom, int strength, int constitution, int intelligence, int dexterity, int charisma) {
		this.wisdom = wisdom;
		this.strength = strength;
		this.constitution = constitution;
		this.intelligence = intelligence;
		this.dexterity = dexterity;
		this.charisma = charisma;
	}

	public int getWisdom() {
		return wisdom;
	}

	public void setWisdom(int wisdom) {
		this.wisdom = wisdom;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getConstitution() {
		return constitution;
	}

	public void setConstitution(int constitution) {
		this.constitution = constitution;
	}

	public int getIntelligence() {
		return intelligence;
	}

	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}

	public int getDexterity() {
		return dexterity;
	}

	public void setDexterity(int dexterity) {
		this.dexterity = dexterity;
	}

	public int getCharisma() {
		return charisma;
	}

	public void setCharisma(int charisma) {
		this.charisma = charisma;
	}

	/*
	 * public int computationModifier(int nb) {
	 * // DANS LE CRUD
	 * int statModif = 0;// à changer, mis pour enlever l'erreur
	 * return statModif;
	 * }
	 */

	@Override
	public String toString() {
		return "Stats [wisdom=" + wisdom + ", strength=" + strength + ", constitution=" + constitution
				+ ", intelligence=" + intelligence + ", dexterity=" + dexterity + ", charisma=" + charisma + "]";
	}

}

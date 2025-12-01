package com.projet.j_and_d.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Character extends Creature {

	@ManyToOne
	@JoinColumn(nullable = false)
	private Role role;
	@Column(nullable = false)
	private Race race;

	public Character(String name, Integer level, int speed, int hp, int mp, boolean alive, int armorClass,
			int initiative, Stats stats, Role role, Race race) {
		super(name, level, hp, mp, speed, alive, armorClass, initiative, stats);
		this.role = role;
		this.race = race;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Race getRace() {
		return race;
	}

	public void setRace(Race race) {
		this.race = race;
	}

	public void levelUp() {
		// TODO
	}

	public void useItem(Item item) {
		if (!this.getInventory().contains(item)) {
			throw new IllegalArgumentException(
					"Le personnage " + this.getName() + " n'a pas l'item " + item + " dans son inventaire");
		}
		this.getInventory().remove(item);
	}

	public void equipItem(Item item) {
		if (!this.getInventory().contains(item)) {
			throw new IllegalArgumentException(
					"Le personnage " + this.getName() + " n'a pas l'item " + item + " dans son inventaire");
		}
		this.getInventory().remove(item);
		this.getItemWorn().add(item);
	}

	public void giveItem(Item item, Character target) {
		boolean removed = false;

		if (this.getInventory().contains(item)) {
			this.getInventory().remove(item);
			removed = true;
		}

		else if (!removed && this.getItemWorn().contains(item)) {
			this.getItemWorn().remove(item);
			removed = true;
		}

		if (!removed) {
			throw new IllegalArgumentException(
					"Le personnage " + this.getName() + " n'a pas l'item " + item + " dans son inventaire");
		}

		target.getInventory().add(item);
	}

	public void saveThrowVsDeath() {
		int success = 0;
		for (int i = 0; i < 5; i++) {
			if (diceThrow(10, 0, 0)) { // bien nommé le diceThrow quand on saura où le mettre
				success++;
			}
		}

		if (success < 3) {
			this.alive = false; // perso mort (sinon, si success >=3, il reste vivant: alive reste true)
		}
	}

}

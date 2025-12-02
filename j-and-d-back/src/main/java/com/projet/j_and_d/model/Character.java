package com.projet.j_and_d.model;

import java.util.List;

import com.projet.j_and_d.context.Singleton;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Character extends Creature {

	private Singleton singleton;

	@Column(nullable = false)
	private Race race;

	public Character() {

	}

	public Character(String name, double level, int hp, int mp, double speed, boolean alive, int armorClass,
			int initiative, Item armor, Item weapon, List<Item> itemWorn, List<Item> inventory, Stats stats,
			Role role, Race race, List<State> state) {
		super(name, level, hp, mp, speed, alive, armorClass,
				initiative, armor, weapon, itemWorn, inventory, stats, role, state);

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

	public void levelUp(double xpGain) {
		if (xpGain <=0){return;}
		this.level = this.level + xpGain;
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
			if (singleton.diceThrow(10, 0, 0)) { // bien nommé le diceThrow quand on saura où le mettre
				success++;
			}
		}

		if (success < 3) {
			this.alive = false; // perso mort (sinon, si success >=3, il reste vivant: alive reste true)
		}
	}

}

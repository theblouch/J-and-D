package com.projet.j_and_d.model;

import java.util.List;

import com.projet.j_and_d.context.Singleton;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "`character`")
public class Character extends Creature {

	@Column(nullable = false)
	private Race race;

	@OneToMany(mappedBy = "character")
	private List<Inscription> inscriptions;

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
		if (xpGain <= 0) {
			return;
		}
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
		Singleton singleton = Singleton.getInstance();
		int success = 0;
		for (int i = 0; i < 5; i++) {
			if (singleton.diceThrow(10, 0, 0)) {
				success++;
			}
		}

		if (success < 3) {
			this.alive = false; // perso mort (sinon, si success >=3, il reste vivant: alive reste true)
		}
	}

	public List<Inscription> getInscriptions() {
		return inscriptions;
	}

	public void setInscriptions(List<Inscription> inscriptions) {
		this.inscriptions = inscriptions;
	}

}

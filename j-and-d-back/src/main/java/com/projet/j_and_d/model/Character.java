package com.projet.j_and_d.model;

import jakarta.persistence.Entity;

@Entity
public class Character extends Creature {

	private Role role;
	private Race race;

	public Character(String name, Integer level, int speed, boolean alive, int armorClass, int initiative, Stats stats,
			Role role, Race race) {
		super(name, level, speed, alive, armorClass, initiative, stats);
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

	/*
	 * public void showInventory() {
	 * // Fonction inutile ici, ce sera le DAO qui fera ça
	 * }
	 */

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
		// TODO
	}

	@Override
	public String toString() {
		return "Character [name=" + name + ", level=" + level + ", speed=" + speed + ", alive=" + alive
				+ ", armorClass=" + armorClass + ", initiative=" + initiative + ", itemWorn=" + itemWorn
				+ ", inventory=" + inventory + ", stats=" + stats + "]";
	}

}

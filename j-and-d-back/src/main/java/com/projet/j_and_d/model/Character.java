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

	public ChatMessage levelUp(double xpGain) {
		ChatMessage log = new ChatMessage();
		if (xpGain <= 0) {
			return log;
		}
		log.add(this.name + " a gagné " + xpGain + "d'XP !");

		double oldLvl = Math.floor(this.level);
		this.level = this.level + xpGain;

		if (this.level - oldLvl >= 1) {
			log.add(this.name + " a gagné un niveau ! Il est maintenant niveau " + Math.floor(this.level));
		}

		// On pourra rajouter ici l'amélioration de stats

		return log;
	}

	public ChatMessage useItem(Item item) {
		ChatMessage log = new ChatMessage();

		if (!this.getInventory().contains(item)) {
			log.add("Le personnage " + this.getName() + " n'a pas l'item " + item + " dans son inventaire");
			return log;
		}
		this.getInventory().remove(item);
		log.add(this.getName() + "a utilisé l'item " + item.getName());
		return log;
	}

	public ChatMessage equipItem(Item item) {
		ChatMessage log = new ChatMessage();
		if (!this.getInventory().contains(item)) {
			log.add("Le personnage " + this.getName() + " n'a pas l'item " + item + " dans son inventaire");
			return log;
		}
		this.getInventory().remove(item);
		this.getItemWorn().add(item);
		log.add(this.getName() + "a utilisé l'item " + item.getName());
		return log;
	}

	public ChatMessage giveItem(Item item, Character target) {
		ChatMessage log = new ChatMessage();
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
			log.add("Le personnage " + this.getName() + " n'a pas l'item " + item + " dans son inventaire");
			return log;
		}

		target.getInventory().add(item);
		log.add(this.getName() + " a donné l'item " + item + " à " + target.getName());
		return log;
	}

	public ChatMessage saveThrowVsDeath() {
		ChatMessage log = new ChatMessage();
		Singleton singleton = Singleton.getInstance();
		int success = 0;
		for (int i = 0; i < 5; i++) {
			if (singleton.diceThrow(10, 0, 0)) {
				success++;
			}
		}

		if (success < 3) {
			this.alive = false; // perso mort (sinon, si success >=3, il reste vivant: alive reste true)
			log.add("Le personnage " + this.getName() + " n'a pas survévu...");
			return log;
		} else {
			this.setHp(1);
			log.add("Le personnage " + this.getName() + " a survécu à ses blessures");
			return log;
		}
	}

	public List<Inscription> getInscriptions() {
		return inscriptions;
	}

	public void setInscriptions(List<Inscription> inscriptions) {
		this.inscriptions = inscriptions;
	}

	@Override
	public String toString() {
		return name;
	}

}

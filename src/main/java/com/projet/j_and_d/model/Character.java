package com.projet.j_and_d.model;

public class Character extends Entity {

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

	public void useItem() {
		// TODO
	}

	public void equipItem() {
		// TODO
	}

	public void showInventory() {
		// TODO
	}

	public void giveItem(Item item, Character character) {
		// TODO
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

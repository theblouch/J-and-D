package model;

import java.util.ArrayList;
import java.util.List;

public abstract class Entity {

	protected String name;
	protected Integer level;
	protected int speed;
	protected boolean alive;
	protected int armorClass;
	protected int initiative;
	protected List<Item> itemWorn = new ArrayList();
	protected List<Item> inventory = new ArrayList();
	protected Stats stats;
	
	
	public Entity(String name, Integer level, int speed, boolean alive, int armorClass, int initiative, Stats stats) {
		this.name = name;
		this.level = level;
		this.speed = speed;
		this.alive = alive;
		this.armorClass = armorClass;
		this.initiative = initiative;
		this.stats = stats;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public int getArmorClass() {
		return armorClass;
	}

	public void setArmorClass(int armorClass) {
		this.armorClass = armorClass;
	}

	public int getInitiative() {
		return initiative;
	}

	public void setInitiative(int initiative) {
		this.initiative = initiative;
	}

	public List<Item> getItemWorn() {
		return itemWorn;
	}

	public void setItemWorn(List<Item> itemWorn) {
		this.itemWorn = itemWorn;
	}

	public List<Item> getInventory() {
		return inventory;
	}

	public void setInventory(List<Item> inventory) {
		this.inventory = inventory;
	}

	public Stats getStats() {
		return stats;
	}

	public void setStats(Stats stats) {
		this.stats = stats;
	}

	public void useSpell() {
		//TODO
	}

	@Override
	public String toString() {
		return "Entity [name=" + name + ", level=" + level + ", speed=" + speed + ", alive=" + alive + ", armorClass="
				+ armorClass + ", initiative=" + initiative + ", itemWorn=" + itemWorn + ", inventory=" + inventory
				+ ", stats=" + stats + "]";
	}
	
}

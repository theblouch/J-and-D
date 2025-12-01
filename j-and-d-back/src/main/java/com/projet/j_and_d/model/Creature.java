package com.projet.j_and_d.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Creature {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;
	@Column(length = 30, nullable = false)
	protected String name;
	@Column(nullable = false)
	protected double level;
	@Column(nullable = false)
	protected int hp;
	@Column(nullable = false)
	protected int mp;
	@Column(nullable = false)
	protected double speed;
	@Column
	protected boolean alive = true;
	@Column(nullable = false)
	protected int armorClass;
	@Column(nullable = false)
	protected int initiative;
	@OneToMany
	protected List<Item> itemWorn;
	@OneToMany
	protected List<Item> inventory;
	@Embedded
	protected Stats stats;

	public Creature(String name, double level, int hp, int mp, double speed, boolean alive, int armorClass,
			int initiative, Stats stats) {
		this.name = name;
		this.level = level;
		this.hp = hp;
		this.mp = mp;
		this.speed = speed;
		this.alive = alive;
		this.armorClass = armorClass;
		this.initiative = initiative;
		this.stats = stats;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getMp() {
		return mp;
	}

	public void setMp(int mp) {
		this.mp = mp;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getLevel() {
		return level;
	}

	public void setLevel(double level) {
		this.level = level;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
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
		// TODO
	}

}

package com.projet.j_and_d.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import com.projet.j_and_d.context.Singleton;

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
	@ManyToOne
	@JoinColumn(nullable = true)
	protected Item armor;
	@ManyToOne
	@JoinColumn(nullable = false)
	protected Item weapon;
	@OneToMany
	protected List<Item> itemWorn = new ArrayList<>();
	@OneToMany
	protected List<Item> inventory = new ArrayList<>();
	@Embedded
	protected Stats stats;

	@ManyToOne
	@JoinColumn(nullable = false)
	protected Role role;

	@ElementCollection(fetch = FetchType.EAGER)
	@Enumerated(EnumType.STRING)
	protected List<State> state = new ArrayList<>();

	@ManyToOne
	protected Creature tauntedBy = null;

	public Creature() {
	}

	public Creature(String name, double level, int hp, int mp, double speed, boolean alive, int armorClass,
			int initiative, Item armor, Item weapon, List<Item> itemWorn, List<Item> inventory, Stats stats,
			Role role, List<State> state) {
		this.name = name;
		this.level = level;
		this.hp = hp;
		this.mp = mp;
		this.speed = speed;
		this.alive = alive;
		this.armorClass = armorClass;
		this.initiative = initiative;
		this.armor = armor;
		this.weapon = weapon;
		this.itemWorn = itemWorn;
		this.inventory = inventory;
		this.stats = stats;
		this.role = role;
		this.state = state;
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

	public double getLevel() {
		return this.level;
	}

	public void setLevel(double level) {
		this.level = level;
	}

	public int getHp() {
		return this.hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getMp() {
		return this.mp;
	}

	public void setMp(int mp) {
		this.mp = mp;
	}

	public double getSpeed() {
		return this.speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public boolean isAlive() {
		return this.alive;
	}

	public boolean getAlive() {
		return this.alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public int getArmorClass() {
		return this.armorClass;
	}

	public void setArmorClass(int armorClass) {
		this.armorClass = armorClass;
	}

	public int getInitiative() {
		return this.initiative;
	}

	public void setInitiative(int initiative) {
		this.initiative = initiative;
	}

	public Item getArmor() {
		return this.armor;
	}

	public void setArmor(Item armor) {
		this.armor = armor;
	}

	public Item getWeapon() {
		return this.weapon;
	}

	public void setWeapon(Item weapon) {
		this.weapon = weapon;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Item> getItemWorn() {
		return this.itemWorn;
	}

	public void setItemWorn(List<Item> itemWorn) {
		this.itemWorn = itemWorn;
	}

	public List<Item> getInventory() {
		return this.inventory;
	}

	public void setInventory(List<Item> inventory) {
		this.inventory = inventory;
	}

	public Stats getStats() {
		return this.stats;
	}

	public void setStats(Stats stats) {
		this.stats = stats;
	}

	public List<State> getState() {
		return this.state;
	}

	public void setState(List<State> state) {
		this.state = state;
	}

	public void setTauntedBy(Creature creature) {
		this.tauntedBy = creature;
	}

	public Creature getTauntedBy() {
		return this.tauntedBy;
	}

	@Override
	public String toString() {
		return "{" +
				" id='" + getId() + "'" +
				", name='" + getName() + "'" +
				", level='" + getLevel() + "'" +
				", hp='" + getHp() + "'" +
				", mp='" + getMp() + "'" +
				", speed='" + getSpeed() + "'" +
				", alive='" + isAlive() + "'" +
				", armorClass='" + getArmorClass() + "'" +
				", initiative='" + getInitiative() + "'" +
				", armor='" + getArmor() + "'" +
				", weapon='" + getWeapon() + "'" +
				", itemWorn='" + getItemWorn() + "'" +
				", inventory='" + getInventory() + "'" +
				", stats='" + getStats() + "'" +
				", role='" + getRole() + "'" +
				", state='" + getState() + "'" +
				", tauntedBy='" + getTauntedBy() + "'" +
				"}";
	}

	public void attack(Creature target) {
		Singleton singleton = Singleton.getInstance();
		if (!this.getTauntedBy().equals(target)) {
			System.out.println("L'attaquand est provoqué par" + this.getTauntedBy().name + ", il ne peut pas attaquer "
					+ target.name + ".");
			return;
		}
		if (!this.canAttack(this) || !this.targetable(target)) {
			return;
		}
		// choix de la caractérisitique pour l'attaque (force ou dextérité)
		int caracteristic = (this.weapon.isBasedOnStrength()) ? this.stats.getStrength() : this.stats.getDexterity();

		int modifier = convertToModifier(caracteristic);

		boolean touch = singleton.diceThrow(target.armorClass, 0, modifier);

		if (touch) {

			// calcul des damages
			int baseDamage = this.weapon.calculDamages();

			int damage = baseDamage + modifier;

			this.getRole().applyDamageIfTouch(this, target, damage);
		}

	}

	public int convertToModifier(int value) {
		double intermValue = (double) value;
		return (int) Math.floor((intermValue / 2) - 5);
	}

	public void useSpell(Creature target, Spell spell) {
		Singleton singleton = Singleton.getInstance();

		if (spell.getSpellLevel() > this.getMp()) {
			System.out.println("Votre personnage n'a pas assez de points de magie");
			return;
		} else {
			this.setMp(this.getMp() - spell.getSpellLevel());

			int caracteristic;
			switch (spell.getRole().getName()) {
				case "rogue":
					caracteristic = this.getStats().getDexterity();
					break;
				case "mage":
					caracteristic = this.getStats().getIntelligence();
					break;
				case "warrior":
					caracteristic = this.getStats().getStrength();
					break;
				case "druid":
					caracteristic = this.getStats().getWisdom();
					break;
				default:
					caracteristic = 10;
			}

			int modifier = convertToModifier(caracteristic);

			boolean touch = singleton.diceThrow(target.armorClass, 0, modifier);

			if (touch) {

				// calcul des damages
				int baseDamage = spell.calculDamages();

				this.getRole().applyDamageIfTouch(this, target, baseDamage);
			}
		}

	}

	public boolean canAttack(Creature creature) {

		if (creature.getState().contains(State.Stunned)) {
			System.out.println("Personnage Stunned");
			return false;
		}

		if (creature.getState().contains(State.Asleep)) {
			System.out.println("Personnage Asleep");
			return false;
		}

		return true;
	}

	public boolean targetable(Creature creature) {
		if (creature.getState().contains(State.Invisible)) {
			System.out.println("Target is invisible");
			return false;
		} else {
			return true;
		}
	}

	public Creature getTauntedByCreature(Creature creature) {
		if (!creature.getState().contains(State.Taunted))
			return null;
		return creature.getTauntedBy(); // attribut à créer
	}

}

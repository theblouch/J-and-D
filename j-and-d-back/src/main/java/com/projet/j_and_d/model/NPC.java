package com.projet.j_and_d.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "npc")
public class NPC extends Creature {

	@Column(nullable = false)
	private double xP;

	@ManyToOne
	private Session session;

	public NPC() {
	}

	public NPC(String name, double level, int hp, int mp, double speed, boolean alive, int armorClass,
			int initiative, Item armor, Item weapon, List<Item> itemWorn, List<Item> inventory, Stats stats,
			Session session, double xP, Role role, List<State> state) {

		super(name, level, hp, mp, speed, alive, armorClass,
				initiative, armor, weapon, itemWorn, inventory, stats, role, state);

		this.xP = xP;
		this.session = session;
	}

	public double getXP() {
		return xP;
	}

	public void setXP(double xP) {
		this.xP = xP;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

}

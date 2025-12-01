package com.projet.j_and_d.model;

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

	public NPC(String name, double level, int hp, int mp, int speed, boolean alive, int armorClass, int initiative,
			Stats stats, double xP) {
		super(name, level, hp, mp, speed, alive, armorClass, initiative, stats);
		this.xP = xP;
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

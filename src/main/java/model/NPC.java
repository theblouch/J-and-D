package model;

public class NPC extends Entity {

	private double xP;

	public NPC(String name, Integer level, int speed, boolean alive, int armorClass, int initiative, Stats stats, double xP) {
		super(name, level, speed, alive, armorClass, initiative,stats);
		this.xP = xP;
	}

	public double getXP() {
		return xP;
	}

	public void setXP(double xP) {
		this.xP = xP;
	}
	
	public void display() {
		//TODO
	}
	
	public void remove() {
		//TODO
	}

	@Override
	public String toString() {
		return "NPC [xP=" + xP + ", name=" + name + ", level=" + level + ", speed=" + speed + ", alive=" + alive
				+ ", armorClass=" + armorClass + ", initiative=" + initiative + ", itemWorn=" + itemWorn
				+ ", inventory=" + inventory + ", stats=" + stats + "]";
	}
	
}

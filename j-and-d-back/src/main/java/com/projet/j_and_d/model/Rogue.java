package com.projet.j_and_d.model;

public class Rogue extends Role implements Damage {
    public Rogue(){
        super("Rogue");
    }
    @Override
    public void applyDamageIfTouch(Creature sender, Creature receiver) {}

    @Override
    public void applyDamageIfTestFailed(Creature sender, Creature receiver) {}
}

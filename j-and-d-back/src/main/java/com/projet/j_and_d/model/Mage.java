package com.projet.j_and_d.model;

public class Mage extends Role implements Damage, Control {
    public Mage(){
        super("Mage");
    }
    @Override
    public void applyDamageIfTouch(Creature sender, Creature receiver) {}

    @Override
    public void applyDamageIfTestFailed(Creature sender, Creature receiver) {}

    @Override
    public void applyControlIfTestFailed(Creature sender, Creature receiver) {}
}

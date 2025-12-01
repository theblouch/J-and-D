package com.projet.j_and_d.model;

public class Mage extends Role implements Damage, Control {
    public Mage(){
        super("Mage");
    }
    @Override
    public void applyDamageIfTouch(Entity sender, Entity receiver) {}

    @Override
    public void applyDamageIfTestFailed(Entity sender, Entity receiver) {}

    @Override
    public void applyControlIfTestFailed(Entity sender, Entity receiver) {}
}

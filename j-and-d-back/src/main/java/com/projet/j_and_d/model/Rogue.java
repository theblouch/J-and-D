package com.projet.j_and_d.model;

public class Rogue extends Role implements Damage {
    public Rogue(){
        super("Rogue");
    }
    @Override
    public void applyDamageIfTouch(Entity sender, Entity receiver) {}

    @Override
    public void applyDamageIfTestFailed(Entity sender, Entity receiver) {}
}

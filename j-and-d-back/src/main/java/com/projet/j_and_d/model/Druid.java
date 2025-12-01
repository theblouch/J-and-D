package com.projet.j_and_d.model;

public class Druid extends Role implements Damage, Control, Heal {
    public Druid(){
        super("Druid");
    }
    @Override
    public void applyDamageIfTouch(Entity sender, Entity receiver) {}

    @Override
    public void applyDamageIfTestFailed(Entity sender, Entity receiver) {}

    @Override
    public void applyHeal(Entity sender, Entity receiver) {}

    @Override
    public void applyControlIfTestFailed(Entity sender, Entity receiver) {}
}

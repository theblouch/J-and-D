package com.projet.j_and_d.model;

public class Druid extends Role implements Damage, Control, Heal {
    public Druid(){
        super("Druid");
    }
    @Override
    public void applyDamageIfTouch(Creature sender, Creature receiver) {}

    @Override
    public void applyDamageIfTestFailed(Creature sender, Creature receiver) {}

    @Override
    public void applyHeal(Creature sender, Creature receiver) {}

    @Override
    public void applyControlIfTestFailed(Creature sender, Creature receiver) {}
}

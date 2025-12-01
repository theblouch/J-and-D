package com.projet.j_and_d.model;

public class Warrior extends Role implements Damage, Control{
 
    public Warrior(){
        super("Warrior");
    }

    @Override
    public void applyDamageIfTouch(Entity sender, Entity receiver) {}

    @Override
    public void applyDamageIfTestFailed(Entity sender, Entity receiver) {}

    @Override
    public void applyControlIfTestFailed(Entity sender, Entity receiver) {}
}

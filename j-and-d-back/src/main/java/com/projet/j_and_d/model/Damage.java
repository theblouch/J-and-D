package com.projet.j_and_d.model;

public interface Damage {
    void applyDamageIfTouch(Creature sender, Creature receiver);
    void applyDamageIfTestFailed(Creature sender, Creature receiver);
}
    

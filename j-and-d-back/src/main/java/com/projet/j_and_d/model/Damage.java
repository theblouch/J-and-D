package com.projet.j_and_d.model;

public interface Damage {
    void applyDamageIfTouch(Entity sender, Entity receiver);
    void applyDamageIfTestFailed(Entity sender, Entity receiver);
}
    

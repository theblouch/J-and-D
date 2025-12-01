package com.projet.j_and_d.model;

public interface IHeal {
    default void applyHeal(Creature sender, Creature receiver, int value) {
        receiver.setHp(receiver.getHp() + value);
    }
}

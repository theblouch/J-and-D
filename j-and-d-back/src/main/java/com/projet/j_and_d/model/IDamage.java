package com.projet.j_and_d.model;

public interface IDamage {
    default void applyDamageIfTouch(Creature sender, Creature receiver, int value) {
        receiver.setHp(receiver.getHp() - value);

        if (receiver.getHp() < 0) {
            receiver.setHp(0);
            receiver.setAlive(false);
        }
    }

}

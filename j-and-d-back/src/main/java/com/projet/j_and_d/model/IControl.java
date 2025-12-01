package com.projet.j_and_d.model;

public interface IControl {
    default void applyControlIfTestFailed(Creature sender, Creature receiver, State state) {

        receiver.getState().add(state);
    }
}

package com.projet.j_and_d.service;

import org.springframework.stereotype.Service;

import com.projet.j_and_d.model.Creature;
import com.projet.j_and_d.model.State;

@Service
public class StateService {

    public boolean canAttack(Creature creature) {

        if (creature.getState().contains(State.Stunned)) {
            System.out.println("Personnage Stunned");
            return false;
        }

        if (creature.getState().contains(State.Asleep)) {
            System.out.println("Personnage Asleep");
            return false;
        }

        return true;
    }

    public boolean targetable(Creature creature) {
        if (creature.getState().contains(State.Invisible)) {
            System.out.println("Target is invisible");
            return false;
        } else {
            return true;
        }
    }

    public Creature getTauntedByCreature(Creature creature) {
        if (!creature.getState().contains(State.Taunted))
            return null;
        return creature.getTauntedBy(); // attribut à créer
    }
}

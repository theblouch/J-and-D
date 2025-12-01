package com.projet.j_and_d.service;

import com.projet.j_and_d.model.Creature;
import com.projet.j_and_d.model.State;

public class StateService {

    public boolean canAttack(Creature creature) {

        if (creature.getState(State.Stunned)){
            return false;
            System.out.println("Personnage Stunned");
        }

        if (creature.getState(State.Asleep)){
            return false;
            System.out.println("Personnage Asleep");
        }

        return true;
    }

    public boolean targetable(Creature creature){
        if (creature.getState(State.Invisible)){
            return false;
            System.out.println("Non ciblable");
        }
        else {return true;}
    }

    public Creature getTauntedByCreature(Creature creature) {
        if (!creature.getState(State.Taunted)) return null;
        return creature.getTauntedBy(); // attribut à créer
    }
}

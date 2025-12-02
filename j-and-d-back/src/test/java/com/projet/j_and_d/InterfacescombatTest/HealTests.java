package com.projet.j_and_d.InterfacescombatTest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.projet.j_and_d.model.Character;
import com.projet.j_and_d.model.IHeal;
import com.projet.j_and_d.model.NPC;

public class HealTests {

    @Test
    void heal_increases_hp_by_value() {
        Character receiver = new Character();
        receiver.setHp(7);
        receiver.setAlive(true);

        // Act
        new IHeal(){}.applyHeal(null, receiver, 4);

        // Assert
        assertEquals(11, receiver.getHp());
        assertTrue(receiver.getAlive());
    }

        @Test
    void healDoesNotResurrect() {
        NPC wolf = new NPC();
        wolf.setHp(0);
        wolf.setAlive(false);

        new IHeal(){}.applyHeal(null, wolf, 10);

        assertEquals(10, wolf.getHp());
        assertFalse(wolf.getAlive()); // reste mort
    }
}

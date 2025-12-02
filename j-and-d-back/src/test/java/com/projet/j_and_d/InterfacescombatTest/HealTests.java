package com.projet.j_and_d.InterfacescombatTest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.projet.j_and_d.model.Character;
import com.projet.j_and_d.model.IHeal;

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
}

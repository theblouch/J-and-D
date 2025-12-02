package com.projet.j_and_d.InterfacescombatTest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;



import com.projet.j_and_d.model.Character;
import com.projet.j_and_d.model.NPC;
import com.projet.j_and_d.model.IDamage;

public class DamageTests {

    @Test
    void damage_reduces_hp_and_keeps_alive_when_positive() {
        Character hero = new Character();
        hero.setHp(10);
        hero.setAlive(true);
        hero.setLevel(1.0);

        NPC wolf = new NPC();
        wolf.setHp(8);
        wolf.setAlive(true);
        wolf.setXP(0.25);


        new IDamage(){}.applyDamageIfTouch(hero, wolf, 3);

        assertEquals(5, wolf.getHp());
        assertTrue(wolf.getAlive());
        assertEquals(1.0, hero.getLevel()); 
    }

    @Test
    void lethal_damage_clamps_to_zero_and_sets_dead_and_awards_xp_to_character() {
        Character hero = new Character();
        hero.setHp(10);
        hero.setAlive(true);
        hero.setLevel(1.0);

        NPC wolf = new NPC();
        wolf.setHp(5);
        wolf.setAlive(true);
        wolf.setXP(0.25);


        new IDamage(){}.applyDamageIfTouch(hero, wolf, 6);


        assertEquals(0, wolf.getHp());
        assertFalse(wolf.getAlive());


        assertEquals(1.25, hero.getLevel());
    }

    @Test
    void exact_lethal_damage_sets_hp_zero_and_alive_false() {
        NPC wolf = new NPC();
        wolf.setHp(5);
        wolf.setAlive(true);
    
        wolf.setXP(0.25);

        Character hero = new Character();
        hero.setLevel(1.0);
        hero.setAlive(true);

 
        new IDamage(){}.applyDamageIfTouch(hero, wolf, 5);


        assertEquals(0, wolf.getHp());
        assertFalse(wolf.getAlive(), "Alive should be false when HP reaches 0");
        assertEquals(1.25, hero.getLevel(), "Hero should gain XP when NPC dies");
}
        @Test
    void damageZeroDoesNothing() {
        Character hero = new Character();
        hero.setHp(10);
        hero.setAlive(true);

        NPC wolf = new NPC();
        wolf.setHp(5);
        wolf.setAlive(true);

        new IDamage(){}.applyDamageIfTouch(hero, wolf, 0);

        assertEquals(5, wolf.getHp());
        assertTrue(wolf.getAlive());
    }

    @Test
    void negativeDamageDoesNotHeal() {
        Character hero = new Character();
        NPC wolf = new NPC();
        wolf.setHp(5);
        wolf.setAlive(true);

        new IDamage(){}.applyDamageIfTouch(hero, wolf, -3);

        // HP ne doit pas augmenter
        assertTrue(wolf.getHp() <= 5);
    }

    @Test
        void exactLethalSetsAliveFalse() {
        Character hero = new Character();
        NPC wolf = new NPC();
        wolf.setHp(5);
        wolf.setAlive(true);
        wolf.setXP(0.25);

        hero.setLevel(1.0);

        new IDamage(){}.applyDamageIfTouch(hero, wolf, 5);

        assertEquals(0, wolf.getHp());
        assertFalse(wolf.getAlive()); 
        assertEquals(1.25, hero.getLevel()); 
    }
    }

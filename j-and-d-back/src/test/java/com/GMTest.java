package com;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.projet.j_and_d.model.Character;
import com.projet.j_and_d.model.Druid;
import com.projet.j_and_d.model.GM;
import com.projet.j_and_d.model.NPC;
import com.projet.j_and_d.model.Race;
import com.projet.j_and_d.model.Role;
import com.projet.j_and_d.model.Session;
import com.projet.j_and_d.model.Stats;

public class GMTest {

    private GM gm;
    private Character hero;
    private NPC npc;

    @BeforeEach
    public void setup() {
        gm = new GM("gm1", "password", "Master");
        gm.setSessions(new ArrayList<>());
        
        // Création d'un rôle concret
        Role druid = new Druid();

        // Création du personnage
        hero = new Character(
                "Hero",
                1.0,
                10,
                6,
                5.0,
                true,
                10,
                11,
                null,
                null,
                new ArrayList<>(),
                new ArrayList<>(),
                new Stats(),
                druid,
                Race.ELF,
                new ArrayList<>()
        );

        // Création d'un NPC donnant de l'XP
        npc = new NPC(
                "Wolf",
                1.0,
                8,
                0,
                3.0,
                true,
                10,
                5,
                null,
                null,
                new ArrayList<>(),
                new ArrayList<>(),
                new Stats(),
                null,
                0.25,
                druid,
                new ArrayList<>()
        );
    }

    // ───────────────────────────────
    // TEST 1 : editHP()
    // ───────────────────────────────
    @Test
    public void testEditHP() {
        gm.editHP(hero, 3);
        assertEquals(7, hero.getHp());

        gm.editHP(hero, 20);
        assertEquals(-13, hero.getHp());  // pas géré ici (normal, c’est dans IDamage qu'on clamp à 0)
    }

    // ───────────────────────────────
    // TEST 2 : createSession()
    // ───────────────────────────────
    @Test
    public void testCreateSession() {
        gm.createSession();

        assertEquals(1, gm.getSessions().size());
        assertNotNull(gm.getSessions().get(0));
        assertEquals(gm, gm.getSessions().get(0).getGm());
    }

    // ───────────────────────────────
    // TEST 3 : grantXp()
    // ───────────────────────────────
    @Test
    public void testGrantXp() {
        assertEquals(1.0, hero.getLevel());

        gm.grantXp(hero, npc);

        assertEquals(1.25, hero.getLevel());
    }

    // ───────────────────────────────
    // TEST 4 : reduceSpeed()
    // ───────────────────────────────
    @Test
    public void testReduceSpeed() {
        assertEquals(5.0, hero.getSpeed());

        gm.reduceSpeed(hero, 1.5);

        assertEquals(3.5, hero.getSpeed());
    }
}


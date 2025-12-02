package com.projet.j_and_d;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.projet.j_and_d.model.Character;
import com.projet.j_and_d.model.Druid;
import com.projet.j_and_d.model.GM;
import com.projet.j_and_d.model.Player;
import com.projet.j_and_d.model.Race;
import com.projet.j_and_d.model.Role;
import com.projet.j_and_d.model.Session;

public class PlayerTest {

    private Player player;

    @BeforeEach
    public void setup() {
        player = new Player("player1", "password", "Doe", "John");
        player.setCharacters(new ArrayList<>());
    }

    @Test
    public void testPlayerCreation() {
        assertEquals("player1", player.getLogin());
        assertEquals("Doe", player.getNom());
        assertEquals("John", player.getPrenom());
    }

    @Test
    public void testCreateCharacter() {
        Role druid = new Druid(); // Rôle concret
        Race race = Race.HUMAN; // Enum direct

        player.createCharacter("Hero", druid, race);

        assertEquals(1, player.getCharacters().size());
        Character c = player.getCharacters().get(0);

        assertEquals("Hero", c.getName());
        assertEquals(10, c.getHp()); // baseHp du Druid
        assertEquals(6, c.getMp()); // baseMp du Druid
        assertEquals(druid, c.getRole());
        assertEquals(race, c.getRace());
    }

    @Test
    public void testJoinSession() {
        GM gm = new GM("gm", "pw", "Master");
        Session session = new Session(new ArrayList<>(), gm, new ArrayList<>());

        Character c = new Character();
        c.setName("TestChar");

        player.joinSession(session, c);

        assertEquals(1, session.getInscriptions().size());
        assertEquals(c, session.getInscriptions().get(0).getCharacter());
    }
}

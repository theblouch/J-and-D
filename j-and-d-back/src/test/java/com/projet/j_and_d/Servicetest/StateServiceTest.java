package com.projet.j_and_d.Servicetest;

import com.projet.j_and_d.model.Creature;
import com.projet.j_and_d.model.State;
import com.projet.j_and_d.service.StateService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StateServiceTest {

    private final StateService stateService = new StateService();

    @Mock
    private Creature creature;

    // ---------------- canAttack ----------------

    @Test
    void testCanAttack_WhenStunned_ReturnsFalse() {
        when(creature.getState()).thenReturn(List.of(State.Stunned));

        boolean result = stateService.canAttack(creature);

        assertFalse(result);
    }

    @Test
    void testCanAttack_WhenAsleep_ReturnsFalse() {
        when(creature.getState()).thenReturn(List.of(State.Asleep));

        boolean result = stateService.canAttack(creature);

        assertFalse(result);
    }

    @Test
    void testCanAttack_WhenNoBlockingState_ReturnsTrue() {
        when(creature.getState()).thenReturn(List.of());

        boolean result = stateService.canAttack(creature);

        assertTrue(result);
    }

    // ---------------- targetable ----------------

    @Test
    void testTargetable_WhenInvisible_ReturnsFalse() {
        when(creature.getState()).thenReturn(List.of(State.Invisible));

        boolean result = stateService.targetable(creature);

        assertFalse(result);
    }

    @Test
    void testTargetable_WhenVisible_ReturnsTrue() {
        when(creature.getState()).thenReturn(List.of());

        boolean result = stateService.targetable(creature);

        assertTrue(result);
    }

    // ---------------- getTauntedByCreature ----------------

    @Test
    void testGetTauntedByCreature_WhenNotTaunted_ReturnsNull() {
        when(creature.getState()).thenReturn(List.of());

        Creature result = stateService.getTauntedByCreature(creature);

        assertNull(result);
    }

    @Test
    void testGetTauntedByCreature_WhenTaunted_ReturnsTaunter(@Mock Creature taunter) {
        when(creature.getState()).thenReturn(List.of(State.Taunted));
        when(creature.getTauntedBy()).thenReturn(taunter);

        Creature result = stateService.getTauntedByCreature(creature);

        assertEquals(taunter, result);
    }
}

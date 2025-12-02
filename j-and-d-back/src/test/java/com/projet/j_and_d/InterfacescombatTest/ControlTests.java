package com.projet.j_and_d.InterfacescombatTest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import com.projet.j_and_d.model.Character;
import com.projet.j_and_d.model.IControl;
import com.projet.j_and_d.model.State;

public class ControlTests {

    @Test
    void control_adds_state_to_receiver() {
        Character sender = new Character();
        Character receiver = new Character();

        receiver.setState(new ArrayList<>()); // ensure list exists

        // Act: apply a control state (e.g., STUNNED)
        new IControl(){}.applyControlIfTestFailed(sender, receiver, State.Stunned);

        // Assert
        assertEquals(1, receiver.getState().size());
        assertTrue(receiver.getState().contains(State.Stunned));
    }
}

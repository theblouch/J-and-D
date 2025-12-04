package com.projet.j_and_d.Servicetest;

import com.projet.j_and_d.api.request.CreateOrUpdateSessionRequest;
import com.projet.j_and_d.exception.SessionNotFoundException;
import com.projet.j_and_d.model.GM;
import com.projet.j_and_d.model.Inscription;
import com.projet.j_and_d.model.NPC;
import com.projet.j_and_d.model.Session;
import com.projet.j_and_d.repo.InscriptionRepository;
import com.projet.j_and_d.repo.NPCRepository;
import com.projet.j_and_d.repo.SessionRepository;
import com.projet.j_and_d.repo.UserRepository;
import com.projet.j_and_d.service.SessionService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SessionServiceTest {

    @Mock
    private SessionRepository sessionRepo;

    @Mock
    private NPCRepository npcRepo;

    @Mock
    private UserRepository gmRepo;

    @Mock
    private InscriptionRepository inscriptionRepo;

    @InjectMocks
    private SessionService sessionService;

    // ---------------- findAll ----------------
    @Test
    void testFindAll_ReturnsAllSessions() {
        Session s1 = new Session();
        Session s2 = new Session();
        when(sessionRepo.findAll()).thenReturn(Arrays.asList(s1, s2));

        List<Session> result = sessionService.findAll();

        assertEquals(2, result.size());
        verify(sessionRepo).findAll();
    }

    // ---------------- findById ----------------
    @Test
    void testFindById_WhenExists_ReturnsSession() {
        Session session = new Session();
        when(sessionRepo.findById(1)).thenReturn(Optional.of(session));

        Session result = sessionService.findById(1);

        assertEquals(session, result);
    }

    @Test
    void testFindById_WhenNotFound_ThrowsException() {
        when(sessionRepo.findById(1)).thenReturn(Optional.empty());

        assertThrows(SessionNotFoundException.class, () -> sessionService.findById(1));
    }

    // ---------------- save (new session) ----------------
    @Test
    void testSave_NewSession_WithNPCsAndInscriptions() {
        CreateOrUpdateSessionRequest request = new CreateOrUpdateSessionRequest();
        request.setGmLogin("null");
        request.setInscriptionCharacters(Arrays.asList("200", "201"));
        request.setName("null");
        request.setNpcNames(Arrays.asList("100", "101"));

        GM gm = new GM();
        NPC npc1 = new NPC();
        NPC npc2 = new NPC();
        Inscription ins1 = new Inscription();
        Inscription ins2 = new Inscription();

        when(gmRepo.findById(1)).thenReturn(Optional.of(gm));
        when(npcRepo.findAllById(Arrays.asList(100, 101))).thenReturn(Arrays.asList(npc1, npc2));
        when(inscriptionRepo.findAllById(Arrays.asList(200, 201))).thenReturn(Arrays.asList(ins1, ins2));

        Session savedSession = new Session();
        when(sessionRepo.save(any(Session.class))).thenReturn(savedSession);

        Session result = sessionService.save(request);

        assertEquals(savedSession, result);
        verify(sessionRepo).save(any(Session.class));
    }

    @Test
    void testSave_NewSession_WhenGmNotFound_ThrowsException() {
        CreateOrUpdateSessionRequest request = new CreateOrUpdateSessionRequest();
        request.setGmLogin("null");
        request.setInscriptionCharacters(Arrays.asList("200", "201"));
        request.setName("null");
        request.setNpcNames(Arrays.asList("100", "101"));

        when(gmRepo.findById(1)).thenReturn(Optional.empty());

        assertThrows(Exception.class, () -> sessionService.save(request));
    }

    // ---------------- save (update existing session) ----------------
    @Test
    void testSave_UpdateExistingSession() {
        CreateOrUpdateSessionRequest request = new CreateOrUpdateSessionRequest();
        request.setGmLogin("null");
        request.setInscriptionCharacters(Arrays.asList("200", "201"));
        request.setName("null");
        request.setNpcNames(Arrays.asList("100", "101"));

        Session existingSession = new Session();
        GM gm = new GM();
        NPC npc = new NPC();
        Inscription ins = new Inscription();

        when(sessionRepo.findById(1)).thenReturn(Optional.of(existingSession));
        when(gmRepo.findById(1)).thenReturn(Optional.of(gm));
        when(npcRepo.findAllById(Arrays.asList(100))).thenReturn(Arrays.asList(npc));
        when(inscriptionRepo.findAllById(Arrays.asList(200))).thenReturn(Arrays.asList(ins));
        when(sessionRepo.save(existingSession)).thenReturn(existingSession);

        Session result = sessionService.save(1, request);

        assertEquals(existingSession, result);
        verify(sessionRepo).save(existingSession);
    }

    @Test
    void testSave_UpdateSession_WhenSessionNotFound_ThrowsException() {
        CreateOrUpdateSessionRequest request = new CreateOrUpdateSessionRequest();
        when(sessionRepo.findById(1)).thenReturn(Optional.empty());

        assertThrows(SessionNotFoundException.class, () -> sessionService.save(1, request));
    }

    // ---------------- deleteById ----------------
    @Test
    void testDeleteById_CallsRepositoryDelete() {
        sessionService.deleteById(1);
        verify(sessionRepo).deleteById(1);
    }
}

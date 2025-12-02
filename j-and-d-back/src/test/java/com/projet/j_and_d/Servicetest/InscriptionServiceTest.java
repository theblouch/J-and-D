package com.projet.j_and_d.Servicetest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.projet.j_and_d.api.request.CreateOrUpdateInscriptionRequest;
import com.projet.j_and_d.exception.InscriptionNotFoundException;
import com.projet.j_and_d.model.Character;
import com.projet.j_and_d.model.Inscription;
import com.projet.j_and_d.model.Session;
import com.projet.j_and_d.repo.CharacterRepository;
import com.projet.j_and_d.repo.InscriptionRepository;
import com.projet.j_and_d.repo.SessionRepository;
import com.projet.j_and_d.service.InscriptionService;

public class InscriptionServiceTest {

    private InscriptionService service;

    private InscriptionRepository inscriptionRepo;
    private CharacterRepository characterRepo;
    private SessionRepository sessionRepo;

    @BeforeEach
    public void setup() {
        inscriptionRepo = mock(InscriptionRepository.class);
        characterRepo = mock(CharacterRepository.class);
        sessionRepo = mock(SessionRepository.class);

        service = new InscriptionService(inscriptionRepo, characterRepo, sessionRepo);
    }

    // -------------------------------------------------------
    // TEST 1 : findAll()
    // -------------------------------------------------------
    @Test
    public void testFindAll() {
        List<Inscription> list = Arrays.asList(new Inscription(), new Inscription());

        when(inscriptionRepo.findAll()).thenReturn(list);

        List<Inscription> result = service.findAll();

        assertEquals(2, result.size());
        verify(inscriptionRepo, times(1)).findAll();
    }

    // -------------------------------------------------------
    // TEST 2 : findById() OK
    // -------------------------------------------------------
    @Test
    public void testFindById_OK() {
        Inscription ins = new Inscription();
        ins.setId(10);

        when(inscriptionRepo.findById(10)).thenReturn(Optional.of(ins));

        Inscription result = service.findById(10);

        assertEquals(10, result.getId());
    }

    // -------------------------------------------------------
    // TEST 3 : findById() NOT FOUND
    // -------------------------------------------------------
    @Test
    public void testFindById_NotFound() {
        when(inscriptionRepo.findById(10)).thenReturn(Optional.empty());

        assertThrows(
                InscriptionNotFoundException.class,
                () -> service.findById(10)
        );
    }

    // -------------------------------------------------------
    // TEST 4 : deleteById()
    // -------------------------------------------------------
    @Test
    public void testDeleteById() {
        service.deleteById(5);

        verify(inscriptionRepo, times(1)).deleteById(5);
    }

    // -------------------------------------------------------
    // TEST 5 : save(request) = Création
    // -------------------------------------------------------
    @Test
    public void testSaveCreate() {
        CreateOrUpdateInscriptionRequest req = new CreateOrUpdateInscriptionRequest();
        req.setCharacterId(100);
        req.setSessionId(200);

        Character character = new Character();
        Session session = new Session();

        when(characterRepo.getReferenceById(100)).thenReturn(character);
        when(sessionRepo.getReferenceById(200)).thenReturn(session);
        when(inscriptionRepo.save(any(Inscription.class)))
                .thenAnswer(inv -> inv.getArgument(0));

        Inscription saved = service.save(req);

        assertEquals(character, saved.getCharacter());
        assertEquals(session, saved.getSession());
        verify(inscriptionRepo, times(1)).save(any(Inscription.class));
    }

    // -------------------------------------------------------
    // TEST 6 : save(id, request) = Mise à jour
    // -------------------------------------------------------
    @Test
    public void testSaveUpdateExisting() {
        Inscription existing = new Inscription();
        existing.setId(50);

        CreateOrUpdateInscriptionRequest req = new CreateOrUpdateInscriptionRequest();
        req.setCharacterId(101);
        req.setSessionId(202);

        Character character = new Character();
        Session session = new Session();

        when(inscriptionRepo.findById(50)).thenReturn(Optional.of(existing));
        when(characterRepo.getReferenceById(101)).thenReturn(character);
        when(sessionRepo.getReferenceById(202)).thenReturn(session);
        when(inscriptionRepo.save(any(Inscription.class)))
                .thenAnswer(inv -> inv.getArgument(0));

        Inscription updated = service.save(50, req);

        assertEquals(character, updated.getCharacter());
        assertEquals(session, updated.getSession());
        verify(inscriptionRepo, times(1)).save(existing);
    }
}


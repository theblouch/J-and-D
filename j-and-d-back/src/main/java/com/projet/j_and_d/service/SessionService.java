package com.projet.j_and_d.service;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

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

@Service
public class SessionService {
    private final SessionRepository repository;
    private final NPCRepository npcRepo;
    private final UserRepository gmRepo;
    private final InscriptionRepository inscriptionRepo;

    public SessionService(SessionRepository repository, NPCRepository npcRepo, UserRepository gmRepo,
            InscriptionRepository inscriptionRepo) {
        this.repository = repository;
        this.npcRepo = npcRepo;
        this.gmRepo = gmRepo;
        this.inscriptionRepo = inscriptionRepo;
    }

    public List<Session> findAll() {
        return this.repository.findAll();
    }

    public Session findById(Integer id) {
        return this.repository.findByIdWithGm(id).orElseThrow(SessionNotFoundException::new);
    }

    public Session save(CreateOrUpdateSessionRequest request) {
        return this.save(new Session(), request);
    }

    public Session save(Integer id, CreateOrUpdateSessionRequest request) {
        Session session = this.findById(id);

        return this.save(session, request);
    }

    public void deleteById(Integer id) {
        inscriptionRepo.deleteAllBySessionId(id);
        npcRepo.deleteAllBySessionId(id);
        this.repository.deleteById(id);
    }

    private Session save(Session session, CreateOrUpdateSessionRequest request) {

        GM gm = (GM) gmRepo.findByLogin(request.getGmLogin()).orElseThrow();
        // List<NPC> npcs = npcRepo.findAllById(request.getNpcNames());

        session.setGm(gm);

        List<Inscription> inscriptions = Collections.emptyList();
        if (request.getInscriptionCharacters() != null) {
            inscriptions = inscriptionRepo.findAllByCharacterNameIn(request.getInscriptionCharacters());
        }
        for (Inscription i : inscriptions) {
            i.setSession(session);
        }
        session.setInscriptions(inscriptions);

        List<NPC> npcs = Collections.emptyList();
        if (request.getNpcNames() != null) {
            npcs = npcRepo.findAllByNameIn(request.getNpcNames());
        }

        session.setNpcs(npcs);

        return this.repository.save(session);
    }
}

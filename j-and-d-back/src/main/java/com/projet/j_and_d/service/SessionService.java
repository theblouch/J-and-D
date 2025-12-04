package com.projet.j_and_d.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.projet.j_and_d.api.request.CreateOrUpdateSessionRequest;
import com.projet.j_and_d.exception.SessionNotFoundException;
import com.projet.j_and_d.model.GM;
import com.projet.j_and_d.model.Inscription;
import com.projet.j_and_d.model.NPC;
import com.projet.j_and_d.model.Character;
import com.projet.j_and_d.model.Session;
import com.projet.j_and_d.repo.CharacterRepository;
import com.projet.j_and_d.repo.InscriptionRepository;
import com.projet.j_and_d.repo.NPCRepository;
import com.projet.j_and_d.repo.SessionRepository;
import com.projet.j_and_d.repo.UserRepository;

@Service
public class SessionService {
    private final SessionRepository repository;
    private final NPCRepository npcRepo;
    private final UserRepository gmRepo;
    private final CharacterRepository characterRepo;
    private final InscriptionRepository inscriptionRepo;

    public SessionService(SessionRepository repository, NPCRepository npcRepo, UserRepository gmRepo,
            InscriptionRepository inscriptionRepo, CharacterRepository characterRepo) {
        this.repository = repository;
        this.npcRepo = npcRepo;
        this.gmRepo = gmRepo;
        this.inscriptionRepo = inscriptionRepo;
        this.characterRepo = characterRepo;
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

        session.setName(request.getName());

        GM gm = (GM) gmRepo.findByLogin(request.getGmLogin()).orElseThrow();
        // List<NPC> npcs = npcRepo.findAllById(request.getNpcNames());

        session.setGm(gm);
        session = repository.save(session);
        List<Inscription> finalInscriptions = new ArrayList<>();
        if (request.getInscriptionCharacters() != null) {
            for (String characterName : request.getInscriptionCharacters()) {
                Character character = characterRepo.findByName(characterName)
                        .orElseThrow(() -> new RuntimeException("Character introuvable : " + characterName));

                Inscription ins = inscriptionRepo.findByCharacter(character)
                        .orElseGet(() -> {
                            Inscription newIns = new Inscription();
                            newIns.setCharacter(character);
                            return newIns;
                        });

                // Associer la session désormais persistée
                ins.setSession(session);
                ins = inscriptionRepo.save(ins);

                finalInscriptions.add(ins);
            }
        }
        session.setInscriptions(finalInscriptions);

        List<NPC> finalNpcs = new ArrayList<>();
        if (request.getNpcNames() != null) {
            for (String npcName : request.getNpcNames()) {
                NPC npc = npcRepo.findByName(npcName)
                        .orElseGet(() -> {
                            NPC newNpc = new NPC();
                            newNpc.setName(npcName);
                            return newNpc;
                        });

                npc.setSession(session);
                npc = npcRepo.save(npc);
                finalNpcs.add(npc);
            }
        }
        session.setNpcs(finalNpcs);

        return this.repository.save(session);
    }
}

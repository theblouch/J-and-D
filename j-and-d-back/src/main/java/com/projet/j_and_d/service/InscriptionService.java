package com.projet.j_and_d.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.projet.j_and_d.api.request.CreateOrUpdateInscriptionRequest;
import com.projet.j_and_d.exception.InscriptionNotFoundException;
import com.projet.j_and_d.model.Inscription;
import com.projet.j_and_d.repo.CharacterRepository;
import com.projet.j_and_d.repo.InscriptionRepository;
import com.projet.j_and_d.repo.SessionRepository;

@Service
public class InscriptionService {

    private final InscriptionRepository repository;
    private final CharacterRepository characterRepo;
    private final SessionRepository sessionRepo;

    public InscriptionService(InscriptionRepository repository, CharacterRepository characterRepo,
            SessionRepository sessionRepo) {
        this.repository = repository;
        this.characterRepo = characterRepo;
        this.sessionRepo = sessionRepo;
    }

    public List<Inscription> findAll() {
        return this.repository.findAll();
    }

    public Inscription findById(Integer id) {
        return this.repository.findById(id).orElseThrow(InscriptionNotFoundException::new);
    }

    public Inscription save(CreateOrUpdateInscriptionRequest request) {
        return this.save(new Inscription(), request);
    }

    public Inscription save(Integer id, CreateOrUpdateInscriptionRequest request) {
        Inscription inscription = this.findById(id);

        return this.save(inscription, request);
    }

    public void deleteById(Integer id) {
        this.repository.deleteById(id);
    }

    private Inscription save(Inscription inscription, CreateOrUpdateInscriptionRequest request) {

        inscription.setCharacter(this.characterRepo.getReferenceById(request.getCharacterId()));
        inscription.setSession(this.sessionRepo.getReferenceById(request.getSessionId()));

        return this.repository.save(inscription);
    }

}

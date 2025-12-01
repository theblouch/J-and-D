package com.projet.j_and_d.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.projet.j_and_d.api.request.CreateOrUpdateNPCRequest;
import com.projet.j_and_d.exception.NPCNotFoundException;
import com.projet.j_and_d.model.NPC;
import com.projet.j_and_d.repo.NPCRepository;

@Service
public class NPCService {
    private final NPCRepository repository;

    public NPCService(NPCRepository repository) {
        this.repository = repository;
    }

    public List<NPC> findAll() {
        return this.repository.findAll();
    }

    public NPC findById(Integer id) {
        return this.repository.findById(id).orElseThrow(NPCNotFoundException::new);
    }

    public NPC save(CreateOrUpdateNPCRequest request) {
        return this.save(new NPC(), request);
    }

    public NPC save(Integer id, CreateOrUpdateNPCRequest request) {
        NPC npc = this.findById(id);

        return this.save(npc, request);
    }

    public void deleteById(Integer id) {
        this.repository.deleteById(id);
    }

    private NPC save(NPC npc, CreateOrUpdateNPCRequest request) {
        npc.setName(request.getName());
        npc.setLevel(request.getLevel());
        npc.setHp(request.getHp());
        npc.setMp(request.getMp());
        npc.setSpeed(request.getSpeed());
        npc.setAlive(request.isAlive());
        npc.setArmorClass(request.getArmorClass());
        npc.setInitiative(request.getInitiative());

        npc.setItemWorn(request.getItemWorn());
        npc.setInventory(request.getInventory());
        npc.setStats(request.getStats());

        npc.setXP(request.getXP());
        npc.setSession(request.getSession());

        return this.repository.save(npc);
    }
}

package com.projet.j_and_d.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.projet.j_and_d.api.request.CreateOrUpdateNPCRequest;
import com.projet.j_and_d.exception.NPCNotFoundException;
import com.projet.j_and_d.model.Item;
import com.projet.j_and_d.model.NPC;
import com.projet.j_and_d.model.State;
import com.projet.j_and_d.repo.ItemRepository;
import com.projet.j_and_d.repo.NPCRepository;
import com.projet.j_and_d.repo.SessionRepository;

@Service
public class NPCService {
    private final NPCRepository repository;
    private final ItemRepository itemRepo;
    private final SessionRepository sessionRepo;
    private final RoleRepository roleRepo;

    public NPCService(NPCRepository repository, ItemRepository itemRepo, SessionRepository sessionRepo,
            RoleRepository roleRepo) {
        this.repository = repository;
        this.itemRepo = itemRepo;
        this.sessionRepo = sessionRepo;
        this.roleRepo = roleRepo;
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
        List<Item> itemWorn = this.itemRepo.findAllById(request.getItemWornIds());
        List<Item> inventory = this.itemRepo.findAllById(request.getInventoryIds());

        npc.setName(request.getName());
        npc.setLevel(request.getLevel());
        npc.setHp(request.getHp());
        npc.setMp(request.getMp());
        npc.setSpeed(request.getSpeed());
        npc.setAlive(request.isAlive());
        npc.setArmorClass(request.getArmorClass());
        npc.setInitiative(request.getInitiative());

        npc.setArmor(this.itemRepo.getReferenceById(request.getArmorId()));
        npc.setWeapon(this.itemRepo.getReferenceById(request.getWeaponId()));

        npc.setItemWorn(itemWorn);
        npc.setInventory(inventory);
        npc.setStats(request.getStats());

        npc.setXP(request.getXP());
        npc.setSession(this.sessionRepo.getReferenceById(request.getSessionId()));

        npc.setRole(this.roleRepo.getReferenceById(request.getRoleId()));

        List<State> states = request.getStates()
                .stream()
                .map(State::valueOf)
                .toList();

        npc.setState(states);

        return this.repository.save(npc);
    }
}

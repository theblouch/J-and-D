package com.projet.j_and_d.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.projet.j_and_d.api.request.CreateOrUpdateNPCRequest;
import com.projet.j_and_d.exception.NPCNotFoundException;
import com.projet.j_and_d.model.Item;
import com.projet.j_and_d.model.NPC;
import com.projet.j_and_d.model.Role;
import com.projet.j_and_d.model.State;
import com.projet.j_and_d.repo.ItemRepository;
import com.projet.j_and_d.repo.NPCRepository;
import com.projet.j_and_d.repo.RoleRepository;
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

        Role role = this.roleRepo.getReferenceById(request.getRoleId());

        npc.setName(request.getName());
        npc.setRole(role);

        npc.setXP(request.getXP());
        npc.setSession(this.sessionRepo.getReferenceById(request.getSessionId()));

        // si création, récupéraion des données du rôle
        if (npc.getId() == null) {
            npc.setLevel(1);
            npc.setHp(role.getBaseHp());
            npc.setMp(role.getBaseMp());
            npc.setSpeed(role.getBaseMs());
            npc.setArmorClass(role.getBaseArmor());
            npc.setInitiative(role.getBaseIni());
            npc.setArmor(role.getArmor());
            npc.setWeapon(role.getWeapon());
            npc.setStats(role.getBaseStats());
        } else {
            // Pour les updates
            npc.setLevel(request.getLevel());
            npc.setHp(request.getHp());
            npc.setMp(request.getMp());
            npc.setSpeed(request.getSpeed());
            npc.setArmorClass(request.getArmorClass());
            npc.setInitiative(request.getInitiative());
            npc.setStats(request.getStats());

            // arme obligatoire
            npc.setWeapon(this.itemRepo.getReferenceById(request.getWeaponId()));

            Item armor = null;
            if (request.getArmorId() != null) {
                armor = this.itemRepo.getReferenceById(request.getArmorId());
            }
            npc.setArmor(armor);
        }

        if (request.getItemWornIds() != null) {
            npc.setItemWorn(this.itemRepo.findAllById(request.getItemWornIds()));
        }

        if (request.getInventoryIds() != null) {
            npc.setInventory(this.itemRepo.findAllById(request.getInventoryIds()));
        }

        if (request.getStates() != null) {
            npc.setState(request.getStates()
                    .stream()
                    .map(State::valueOf)
                    .toList());
        }

        return this.repository.save(npc);
    }
}

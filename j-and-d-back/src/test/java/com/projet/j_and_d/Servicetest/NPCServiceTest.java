package com.projet.j_and_d.Servicetest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.projet.j_and_d.api.request.CreateOrUpdateNPCRequest;
import com.projet.j_and_d.exception.NPCNotFoundException;
import com.projet.j_and_d.model.Item;
import com.projet.j_and_d.model.NPC;
import com.projet.j_and_d.model.Role;
import com.projet.j_and_d.model.Session;
import com.projet.j_and_d.model.Stats;
import com.projet.j_and_d.repo.ItemRepository;
import com.projet.j_and_d.repo.NPCRepository;
import com.projet.j_and_d.repo.RoleRepository;
import com.projet.j_and_d.repo.SessionRepository;
import com.projet.j_and_d.service.NPCService;

public class NPCServiceTest {

    private NPCService service;

    private NPCRepository npcRepo;
    private ItemRepository itemRepo;
    private SessionRepository sessionRepo;
    private RoleRepository roleRepo;

    @BeforeEach
    public void setup() {
        npcRepo = mock(NPCRepository.class);
        itemRepo = mock(ItemRepository.class);
        sessionRepo = mock(SessionRepository.class);
        roleRepo = mock(RoleRepository.class);

        service = new NPCService(npcRepo, itemRepo, sessionRepo, roleRepo);
    }

    // ------------------------------------------
    // findAll()
    // ------------------------------------------
    @Test
    public void testFindAll() {
        List<NPC> list = Arrays.asList(new NPC(), new NPC());
        when(npcRepo.findAll()).thenReturn(list);

        List<NPC> result = service.findAll();

        assertEquals(2, result.size());
        verify(npcRepo, times(1)).findAll();
    }

    // ------------------------------------------
    // findById() OK
    // ------------------------------------------
    @Test
    public void testFindById_OK() {
        NPC npc = new NPC();
        npc.setName("Goblin");

        when(npcRepo.findById(1)).thenReturn(Optional.of(npc));

        NPC result = service.findById(1);

        assertEquals("Goblin", result.getName());
    }

    // ------------------------------------------
    // findById() NOT FOUND
    // ------------------------------------------
    @Test
    public void testFindById_NotFound() {
        when(npcRepo.findById(1)).thenReturn(Optional.empty());

        assertThrows(NPCNotFoundException.class, () -> service.findById(1));
    }

    // ------------------------------------------
    // deleteById()
    // ------------------------------------------
    @Test
    public void testDeleteById() {
        service.deleteById(99);

        verify(npcRepo, times(1)).deleteById(99);
    }

    // ------------------------------------------
    // save(request) => création
    // ------------------------------------------
    @Test
    public void testSaveCreateNewNPC() {

        CreateOrUpdateNPCRequest req = buildRequest();

        Item armor = mock(Item.class);
        Item weapon = mock(Item.class);
        Role role = mock(Role.class);
        Session session = mock(Session.class);

        when(itemRepo.getReferenceById(10)).thenReturn(armor);
        when(itemRepo.getReferenceById(20)).thenReturn(weapon);
        when(itemRepo.findAllById(List.of(100, 101))).thenReturn(List.of(new Item(), new Item()));
        when(sessionRepo.getReferenceById(2)).thenReturn(session);
        when(roleRepo.getReferenceById(3)).thenReturn(role);

        when(npcRepo.save(any(NPC.class))).thenAnswer(inv -> inv.getArgument(0));

        NPC result = service.save(req);

        assertEquals("Wolf", result.getName());
        assertEquals(15, result.getHp());
        assertEquals(0.50, result.getXP());
        assertEquals(role, result.getRole());
        assertEquals(session, result.getSession());
        assertEquals(2, result.getItemWorn().size());
    }

    // ------------------------------------------
    // save(id, request) => MAJ
    // ------------------------------------------
    @Test
    public void testSaveUpdateNPC() {
        NPC existing = new NPC();
        existing.setName("OldNPC");
        existing.setHp(5);

        CreateOrUpdateNPCRequest req = buildRequest();

        when(npcRepo.findById(10)).thenReturn(Optional.of(existing));
        when(itemRepo.getReferenceById(10)).thenReturn(mock(Item.class));
        when(itemRepo.getReferenceById(20)).thenReturn(mock(Item.class));
        when(itemRepo.findAllById(List.of(100, 101))).thenReturn(List.of(new Item(), new Item()));
        when(sessionRepo.getReferenceById(2)).thenReturn(mock(Session.class));
        when(roleRepo.getReferenceById(3)).thenReturn(mock(Role.class));

        when(npcRepo.save(any(NPC.class))).thenAnswer(inv -> inv.getArgument(0));

        NPC result = service.save(10, req);

        assertEquals("Wolf", result.getName());
        assertEquals(15, result.getHp());
        assertEquals(0.50, result.getXP());
    }

    // ------------------------------------------
    // Helper
    // ------------------------------------------
    private CreateOrUpdateNPCRequest buildRequest() {
        CreateOrUpdateNPCRequest req = new CreateOrUpdateNPCRequest();

        req.setName("Wolf");
        req.setLevel(1.0);
        req.setHp(15);
        req.setMp(0);
        req.setSpeed(3.0);
        req.setAlive(true);
        req.setArmorClass(12);
        req.setInitiative(5);

        req.setArmorId(10);
        req.setWeaponId(20);

        req.setItemWornIds(List.of(100, 101));
        req.setInventoryIds(List.of());

        req.setStats(new Stats(10, 10, 10, 10, 10, 10));

        req.setXP(0.50);
        req.setSessionId(2);
        req.setRoleId(3);

        req.setStateIds(List.of("Poisonned", "Asleep"));

        return req;
    }
}

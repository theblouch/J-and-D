package com.projet.j_and_d.Servicetest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.projet.j_and_d.api.request.CreateOrUpdateCharacterRequest;
import com.projet.j_and_d.exception.CharacterNotFoundException;
import com.projet.j_and_d.model.*;
import com.projet.j_and_d.model.Character;
import com.projet.j_and_d.repo.CharacterRepository;
import com.projet.j_and_d.repo.ItemRepository;
import com.projet.j_and_d.repo.RoleRepository;
import com.projet.j_and_d.service.CharacterService;

public class CharacterTest {
    private CharacterService service;

    private CharacterRepository characterRepo;
    private RoleRepository roleRepo;
    private ItemRepository itemRepo;

    @BeforeEach
    public void setup() {
        characterRepo = mock(CharacterRepository.class);
        roleRepo = mock(RoleRepository.class);
        itemRepo = mock(ItemRepository.class);

        service = new CharacterService(characterRepo, roleRepo, itemRepo);
    }

    // -------------------------------------------------------
    // findAll()
    // -------------------------------------------------------
    @Test
    public void testFindAll() {
        List<Character> characters = Arrays.asList(new Character(), new Character());
        when(characterRepo.findAll()).thenReturn(characters);

        List<Character> result = service.findAll();

        assertEquals(2, result.size());
        verify(characterRepo, times(1)).findAll();
    }

    // -------------------------------------------------------
    // findById()
    // -------------------------------------------------------
    @Test
    public void testFindById_OK() {
        Character c = new Character();
        c.setName("Hero");

        when(characterRepo.findById(1)).thenReturn(Optional.of(c));

        Character result = service.findById(1);

        assertEquals("Hero", result.getName());
    }

    @Test
    public void testFindById_NotFound() {
        when(characterRepo.findById(1)).thenReturn(Optional.empty());

        assertThrows(CharacterNotFoundException.class, () -> service.findById(1));
    }

    // -------------------------------------------------------
    // deleteById()
    // -------------------------------------------------------
    @Test
    public void testDeleteById() {
        service.deleteById(10);

        verify(characterRepo, times(1)).deleteById(10);
    }

    // -------------------------------------------------------
    // save(request) => création d'un Character
    // -------------------------------------------------------
    @Test
    public void testSaveCreateNewCharacter() {
        CreateOrUpdateCharacterRequest req = buildRequest();

        Role fakeRole = mock(Role.class);
        Item fakeWeapon = mock(Item.class);

        when(itemRepo.getReferenceById(55)).thenReturn(fakeWeapon);
        when(roleRepo.getReferenceById(3)).thenReturn(fakeRole);
        when(characterRepo.save(any(Character.class))).thenAnswer(inv -> inv.getArgument(0));

        Character result = service.save(req);

        assertEquals("TestHero", result.getName());
        assertEquals(10, result.getHp());
        assertEquals(3.0, result.getSpeed());
        assertEquals(fakeRole, result.getRole());
        assertEquals(Race.ELF, result.getRace());
        verify(characterRepo, times(1)).save(any(Character.class));
    }

    // -------------------------------------------------------
    // save(id, request) => édition d'un Character déjà existant
    // -------------------------------------------------------
    @Test
    public void testSaveUpdateExistingCharacter() {
        Character existing = new Character();
        existing.setName("OldName");
        existing.setLevel(1.0);

        CreateOrUpdateCharacterRequest req = buildRequest();

        when(characterRepo.findById(10)).thenReturn(Optional.of(existing));
        when(characterRepo.save(any(Character.class))).thenAnswer(inv -> inv.getArgument(0));
        when(roleRepo.getReferenceById(3)).thenReturn(mock(Role.class));
        when(itemRepo.getReferenceById(55)).thenReturn(mock(Item.class));

        Character updated = service.save(10, req);

        assertEquals("TestHero", updated.getName());
        assertEquals(10, updated.getHp());
        assertEquals(5, updated.getMp());
    }

    // -------------------------------------------------------
    // Helper pour construire une requête de test
    // -------------------------------------------------------
    private CreateOrUpdateCharacterRequest buildRequest() {
        CreateOrUpdateCharacterRequest req = new CreateOrUpdateCharacterRequest();

        req.setName("TestHero");
        req.setLevel(1.0);
        req.setHp(10);
        req.setMp(5);
        req.setSpeed(3.0);
        req.setAlive(true);
        req.setArmorClass(12);
        req.setInitiative(5);

        req.setWeaponId(55);
        req.setRoleId(3);
        req.setRace(Race.ELF);

        req.setStats(new Stats(10, 10, 10, 10, 10, 10));

        req.setItemWornIds(List.of());
        req.setInventoryIds(List.of());
        req.setStateIds(List.of("Poisonned", "Asleep"));

        return req;
    }
}

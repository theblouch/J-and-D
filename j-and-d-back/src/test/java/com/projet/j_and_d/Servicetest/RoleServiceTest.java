package com.projet.j_and_d.Servicetest;

import com.projet.j_and_d.api.request.CreateOrUpdateRoleRequest;
import com.projet.j_and_d.exception.RoleNotFoundException;
import com.projet.j_and_d.model.*;
import com.projet.j_and_d.repo.ItemRepository;
import com.projet.j_and_d.repo.RoleRepository;
import com.projet.j_and_d.repo.SpellRepository;
import com.projet.j_and_d.service.RoleService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RoleServiceTest {

    @Mock
    private RoleRepository roleRepo;

    @Mock
    private SpellRepository spellRepo;

    @Mock
    private ItemRepository itemRepo;

    @InjectMocks
    private RoleService roleService;

    // ---------------- findAll ----------------
    @Test
    void testFindAll_ReturnsAllRoles() {
        Role r1 = new Warrior();
        Role r2 = new Mage();
        when(roleRepo.findAll()).thenReturn(Arrays.asList(r1, r2));

        List<Role> result = roleService.findAll();

        assertEquals(2, result.size());
        verify(roleRepo).findAll();
    }

    // ---------------- findById ----------------
    @Test
    void testFindById_WhenExists_ReturnsRole() {
        Role role = new Warrior();
        when(roleRepo.findById(1)).thenReturn(Optional.of(role));

        Role result = roleService.findById(1);

        assertEquals(role, result);
    }

    @Test
    void testFindById_WhenNotFound_ThrowsException() {
        when(roleRepo.findById(1)).thenReturn(Optional.empty());

        assertThrows(RoleNotFoundException.class, () -> roleService.findById(1));
    }

    // ---------------- save (new role) ----------------
    @Test
    void testSave_NewRole_Warrior() {
        CreateOrUpdateRoleRequest request = new CreateOrUpdateRoleRequest();
        request.setName("WARRIOR");
        request.setSpellIds(Collections.emptyList());
        request.setWeaponId(1);
        request.setArmorId(null);

        Item weapon = new Item();
        when(itemRepo.getReferenceById(1)).thenReturn(weapon);
        when(spellRepo.findAllById(Collections.emptyList())).thenReturn(Collections.emptyList());
        when(roleRepo.save(any(Role.class))).thenAnswer(i -> i.getArgument(0));

        Role result = roleService.save(request);

        assertTrue(result instanceof Warrior);
        assertEquals("WARRIOR", result.getName());
        assertEquals(weapon, result.getWeapon());
        assertNull(result.getArmor());
        assertEquals(100, result.getBaseHp());
        verify(roleRepo).save(result);
    }

    @Test
    void testSave_NewRole_Mage() {
        CreateOrUpdateRoleRequest request = new CreateOrUpdateRoleRequest();
        request.setName("MAGE");
        request.setSpellIds(Collections.emptyList());
        request.setWeaponId(2);
        request.setArmorId(3);

        Item weapon = new Item();
        Item armor = new Item();
        when(itemRepo.getReferenceById(2)).thenReturn(weapon);
        when(itemRepo.getReferenceById(3)).thenReturn(armor);
        when(spellRepo.findAllById(Collections.emptyList())).thenReturn(Collections.emptyList());
        when(roleRepo.save(any(Role.class))).thenAnswer(i -> i.getArgument(0));

        Role result = roleService.save(request);

        assertTrue(result instanceof Mage);
        assertEquals(weapon, result.getWeapon());
        assertEquals(armor, result.getArmor());
        assertEquals("MAGE", result.getName());
        verify(roleRepo).save(result);
    }

    @Test
    void testSave_NewRole_UnknownRole_ThrowsException() {
        CreateOrUpdateRoleRequest request = new CreateOrUpdateRoleRequest();
        request.setName("PALADIN"); // non géré

        assertThrows(IllegalArgumentException.class, () -> roleService.save(request));
    }

    // ---------------- save (update existing role) ----------------
    @Test
    void testSave_UpdateExistingRole() {
        CreateOrUpdateRoleRequest request = new CreateOrUpdateRoleRequest();
        request.setName("ROGUE");
        request.setSpellIds(Collections.emptyList());
        request.setWeaponId(1);
        request.setArmorId(null);

        Role existingRole = new Rogue();
        Item weapon = new Item();
        when(roleRepo.findById(1)).thenReturn(Optional.of(existingRole));
        when(itemRepo.getReferenceById(1)).thenReturn(weapon);
        when(spellRepo.findAllById(Collections.emptyList())).thenReturn(Collections.emptyList());
        when(roleRepo.save(existingRole)).thenReturn(existingRole);

        Role result = roleService.save(1, request);

        assertEquals(existingRole, result);
        assertEquals(weapon, result.getWeapon());
        assertTrue(result instanceof Rogue);
        verify(roleRepo).save(existingRole);
    }

    @Test
    void testSave_UpdateRole_NotFound_ThrowsException() {
        CreateOrUpdateRoleRequest request = new CreateOrUpdateRoleRequest();
        when(roleRepo.findById(1)).thenReturn(Optional.empty());

        assertThrows(RoleNotFoundException.class, () -> roleService.save(1, request));
    }

    // ---------------- deleteById ----------------
    @Test
    void testDeleteById_CallsRepositoryDelete() {
        roleService.deleteById(1);
        verify(roleRepo).deleteById(1);
    }
}

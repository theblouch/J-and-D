package com.projet.j_and_d.Servicetest;

import com.projet.j_and_d.api.request.CreateOrUpdateSpellRequest;
import com.projet.j_and_d.exception.SpellNotFoundException;
import com.projet.j_and_d.model.Mage;
import com.projet.j_and_d.model.Spell;
import com.projet.j_and_d.repo.SpellRepository;
import com.projet.j_and_d.service.SpellService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpellServiceTest {

    @Mock
    private SpellRepository repository;

    @InjectMocks
    private SpellService spellService;

    // ---------------- findAll ----------------

    @Test
    void testFindAll_ReturnsAllSpells() {
        Spell spell1 = new Spell();
        Spell spell2 = new Spell();
        when(repository.findAll()).thenReturn(Arrays.asList(spell1, spell2));

        List<Spell> result = spellService.findAll();

        assertEquals(2, result.size());
        verify(repository, times(1)).findAll();
    }

    // ---------------- findById ----------------

    @Test
    void testFindById_WhenExists_ReturnsSpell() {
        Spell spell = new Spell();
        when(repository.findById(1)).thenReturn(Optional.of(spell));

        Spell result = spellService.findById(1);

        assertEquals(spell, result);
    }

    @Test
    void testFindById_WhenNotFound_ThrowsException() {
        when(repository.findById(1)).thenReturn(Optional.empty());

        assertThrows(SpellNotFoundException.class, () -> spellService.findById(1));
    }

    // ---------------- findByName ----------------

    @Test
    void testFindByName_WhenExists_ReturnsSpell() {
        Spell spell = new Spell();
        when(repository.findByName("Fireball")).thenReturn(Optional.of(spell));

        Spell result = spellService.findByName("Fireball");

        assertEquals(spell, result);
    }

    @Test
    void testFindByName_WhenNotFound_ThrowsException() {
        when(repository.findByName("Fireball")).thenReturn(Optional.empty());

        assertThrows(SpellNotFoundException.class, () -> spellService.findByName("Fireball"));
    }

    // ---------------- save (new spell) ----------------

    @Test
    void testSave_NewSpell_CallsRepositorySave() {
        CreateOrUpdateSpellRequest request = new CreateOrUpdateSpellRequest();
        request.setName("Fireball");
        request.setDescription("Powerful spell");
        request.setRole(new Mage());
        request.setSpellLevel(3);
        request.setBaseDamage(new int[] { 10, 20 });

        Spell savedSpell = new Spell();
        when(repository.save(any(Spell.class))).thenReturn(savedSpell);

        Spell result = spellService.save(request);

        assertEquals(savedSpell, result);
        verify(repository, times(1)).save(any(Spell.class));
    }

    // ---------------- save (update existing spell) ----------------

    @Test
    void testSave_UpdateExistingSpell_CallsRepositorySave() {
        CreateOrUpdateSpellRequest request = new CreateOrUpdateSpellRequest();
        Spell existingSpell = new Spell();
        when(repository.findById(1)).thenReturn(Optional.of(existingSpell));
        when(repository.save(existingSpell)).thenReturn(existingSpell);

        Spell result = spellService.save(1, request);

        assertEquals(existingSpell, result);
        verify(repository, times(1)).save(existingSpell);
    }

    // ---------------- deleteById ----------------

    @Test
    void testDeleteById_CallsRepositoryDelete() {
        spellService.deleteById(1);
        verify(repository, times(1)).deleteById(1);
    }

    // ---------------- getBaseDamage ----------------

    @Test
    void testGetBaseDamage_WhenSpellExists_ReturnsBaseDamage() {
        Spell spell = new Spell();
        spell.setBaseDamage(new int[] { 10, 20 });
        when(repository.findByName("Fireball")).thenReturn(Optional.of(spell));

        int[] result = spellService.getBaseDamage("Fireball");

        assertArrayEquals(new int[] { 10, 20 }, result);
    }

    @Test
    void testGetBaseDamage_WhenNotFound_ThrowsException() {
        when(repository.findByName("Fireball")).thenReturn(Optional.empty());
        assertThrows(SpellNotFoundException.class, () -> spellService.getBaseDamage("Fireball"));
    }

    // ---------------- updateBaseDamage ----------------

    @Test
    void testUpdateBaseDamage_WhenSpellExists_UpdatesAndSaves() {
        Spell spell = new Spell();
        spell.setBaseDamage(new int[] { 5, 10 });
        CreateOrUpdateSpellRequest request = new CreateOrUpdateSpellRequest();
        request.setBaseDamage(new int[] { 15, 25 });

        when(repository.findByName("Fireball")).thenReturn(Optional.of(spell));
        when(repository.save(spell)).thenReturn(spell);

        Spell result = spellService.updateBaseDamage("Fireball", request);

        assertArrayEquals(new int[] { 15, 25 }, result.getBaseDamage());
        verify(repository, times(1)).save(spell);
    }

    @Test
    void testUpdateBaseDamage_WhenNotFound_ThrowsException() {
        CreateOrUpdateSpellRequest request = new CreateOrUpdateSpellRequest();
        when(repository.findByName("Fireball")).thenReturn(Optional.empty());

        assertThrows(SpellNotFoundException.class, () -> spellService.updateBaseDamage("Fireball", request));
    }
}

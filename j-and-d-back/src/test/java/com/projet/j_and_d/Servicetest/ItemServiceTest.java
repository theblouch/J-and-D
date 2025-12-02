package com.projet.j_and_d.Servicetest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.projet.j_and_d.api.request.CreateOrUpdateItemRequest;
import com.projet.j_and_d.exception.ItemNotFoundException;
import com.projet.j_and_d.model.Item;
import com.projet.j_and_d.repo.ItemRepository;
import com.projet.j_and_d.service.ItemService;

public class ItemServiceTest {

    private ItemService service;
    private ItemRepository itemRepo;

    @BeforeEach
    public void setup() {
        itemRepo = mock(ItemRepository.class);
        service = new ItemService(itemRepo);
    }

    // -------------------------------------------------------
    // TEST 1 : findAll()
    // -------------------------------------------------------
    @Test
    public void testFindAll() {
        List<Item> items = Arrays.asList(new Item(), new Item());

        when(itemRepo.findAll()).thenReturn(items);

        List<Item> result = service.findAll();

        assertEquals(2, result.size());
        verify(itemRepo, times(1)).findAll();
    }

    // -------------------------------------------------------
    // TEST 2 : findById() OK
    // -------------------------------------------------------
    @Test
    public void testFindById_OK() {
        Item item = new Item();
        item.setName("Sword");

        when(itemRepo.findById(5)).thenReturn(Optional.of(item));

        Item result = service.findById(5);

        assertEquals("Sword", result.getName());
    }

    // -------------------------------------------------------
    // TEST 3 : findById() NOT FOUND
    // -------------------------------------------------------
    @Test
    public void testFindById_NotFound() {
        when(itemRepo.findById(5)).thenReturn(Optional.empty());

        assertThrows(ItemNotFoundException.class, () -> service.findById(5));
    }

    // -------------------------------------------------------
    // TEST 4 : deleteById()
    // -------------------------------------------------------
    @Test
    public void testDeleteById() {
        service.deleteById(9);

        verify(itemRepo, times(1)).deleteById(9);
    }

    // -------------------------------------------------------
    // TEST 5 : save(request) => création d'un Item
    // -------------------------------------------------------
    @Test
    public void testSaveCreateNewItem() {
        CreateOrUpdateItemRequest req = new CreateOrUpdateItemRequest();
        req.setName("Shield");
        req.setDescription("A sturdy wooden shield.");

        when(itemRepo.save(any(Item.class))).thenAnswer(inv -> inv.getArgument(0));

        Item result = service.save(req);

        assertEquals("Shield", result.getName());
        assertEquals("A sturdy wooden shield.", result.getDescription());
    }

    // -------------------------------------------------------
    // TEST 6 : save(id, request) => update
    // -------------------------------------------------------
    @Test
    public void testSaveUpdateExistingItem() {
        Item existing = new Item();
        existing.setName("OldName");
        existing.setDescription("OldDesc");

        CreateOrUpdateItemRequest req = new CreateOrUpdateItemRequest();
        req.setName("NewName");
        req.setDescription("NewDesc");

        when(itemRepo.findById(10)).thenReturn(Optional.of(existing));
        when(itemRepo.save(any(Item.class))).thenAnswer(inv -> inv.getArgument(0));

        Item updated = service.save(10, req);

        assertEquals("NewName", updated.getName());
        assertEquals("NewDesc", updated.getDescription());
    }
}

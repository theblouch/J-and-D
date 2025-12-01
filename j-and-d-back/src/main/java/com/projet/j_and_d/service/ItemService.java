package com.projet.j_and_d.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.projet.j_and_d.api.request.CreateOrUpdateItemRequest;
import com.projet.j_and_d.exception.ItemNotFoundException;
import com.projet.j_and_d.model.Item;
import com.projet.j_and_d.repo.ItemRepository;

@Service
public class ItemService {

    private final ItemRepository repository;

    public ItemService(ItemRepository repository) {
        this.repository = repository;
    }

    public List<Item> findAll() {
        return this.repository.findAll();
    }

    public Item findById(int id) {
        return this.repository.findById(id).orElseThrow(ItemNotFoundException::new);
    }

    public Item save(CreateOrUpdateItemRequest request) {
        return this.save(new Item(), request);
    }

    public Item save(int id, CreateOrUpdateItemRequest request) {
        Item item = this.findById(id);

        return this.save(item, request);
    }

    public void deleteById(int id) {
        this.repository.deleteById(id);
    }

    private Item save(Item item, CreateOrUpdateItemRequest request) {
        item.setName(request.getName());
        item.setDescription(request.getDescription());

        return this.repository.save(item);
    }
}

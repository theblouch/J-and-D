package com.projet.j_and_d.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.projet.j_and_d.api.request.CreateOrUpdateSpellRequest;
import com.projet.j_and_d.api.response.EntityCreatedResponse;
import com.projet.j_and_d.api.response.EntityUpdatedResponse;
import com.projet.j_and_d.api.response.SpellResponse;
import com.projet.j_and_d.service.SpellService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/spell")
public class SpellApiController {
    private final SpellService service;

    public SpellApiController(SpellService service) {
        this.service = service;
    }

    @GetMapping
    public List<SpellResponse> findAll() {
        return this.service.findAll().stream().map(SpellResponse::convert).toList();
    }

    @GetMapping("/{id:[0-9]+}")
    public SpellResponse findById(@PathVariable int id) {
        return SpellResponse.convert(this.service.findById(id));
    }

    @GetMapping("/{name:[a-zA-Z]+}")
    public SpellResponse findByName(@PathVariable String name) {
        return SpellResponse.convert(this.service.findByName(name));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntityCreatedResponse create(@Valid @RequestBody CreateOrUpdateSpellRequest request) {
        return new EntityCreatedResponse(this.service.save(request).getId());
    }

    @PutMapping("/{id}")
    public EntityUpdatedResponse update(@PathVariable int id, @Valid @RequestBody CreateOrUpdateSpellRequest request) {
        this.service.save(id, request);

        return new EntityUpdatedResponse(id, true);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable int id) {
        this.service.deleteById(id);
    }
}

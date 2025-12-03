package com.projet.j_and_d.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.projet.j_and_d.api.request.CreateOrUpdateCharacterRequest;
import com.projet.j_and_d.api.response.CharacterResponse;
import com.projet.j_and_d.api.response.EntityCreatedResponse;
import com.projet.j_and_d.api.response.EntityUpdatedResponse;
import com.projet.j_and_d.model.ChatMessage;
import com.projet.j_and_d.model.Creature;
import com.projet.j_and_d.model.Character;
import com.projet.j_and_d.service.CharacterService;
import com.projet.j_and_d.service.NPCService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/character")
public class CharacterApiController {
    private final CharacterService service;
    private final NPCService npcService;

    public CharacterApiController(CharacterService service, NPCService npcService) {
        this.service = service;
        this.npcService = npcService;
    }

    @GetMapping
    public List<CharacterResponse> findAll() {
        return this.service.findAll().stream().map(CharacterResponse::convert).toList();
    }

    @GetMapping("/{id}")
    public CharacterResponse findById(@PathVariable Integer id) {
        return CharacterResponse.convert(this.service.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntityCreatedResponse create(@Valid @RequestBody CreateOrUpdateCharacterRequest request) {
        return new EntityCreatedResponse(this.service.save(request).getId());
    }

    @PutMapping("/{id}")
    public EntityUpdatedResponse update(@PathVariable Integer id,
            @Valid @RequestBody CreateOrUpdateCharacterRequest request) {
        this.service.save(id, request);

        return new EntityUpdatedResponse(id, true);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Integer id) {
        this.service.deleteById(id);
    }

    @PostMapping("/character/{attackerId}/attack/{targetId}")
    public ResponseEntity<ChatMessage> characterAttacks(
            @PathVariable int attackerId,
            @PathVariable int targetId,
            @RequestParam(defaultValue = "0") int advantage,
            @RequestParam(defaultValue = "0") int additionalModifier) {

        Character attacker = service.findById(attackerId);
        Creature target = npcService.exists(targetId) ? npcService.findById(targetId)
                : service.findById(targetId);

        ChatMessage log = attacker.attack(target, advantage, additionalModifier);
        return ResponseEntity.ok(log);
    }
}

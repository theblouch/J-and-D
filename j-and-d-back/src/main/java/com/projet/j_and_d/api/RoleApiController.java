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

import com.projet.j_and_d.api.request.CreateOrUpdateRoleRequest;
import com.projet.j_and_d.api.response.RoleResponse;
import com.projet.j_and_d.api.response.EntityCreatedResponse;
import com.projet.j_and_d.api.response.EntityUpdatedResponse;
import com.projet.j_and_d.service.RoleService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/role")
public class RoleApiController {
    private final RoleService service;

    public RoleApiController(RoleService service) {
        this.service = service;
    }

    @GetMapping
    public List<RoleResponse> findAll() {
        return this.service.findAll().stream().map(RoleResponse::convert).toList();
    }

    @GetMapping("/{id}")
    public RoleResponse findById(@PathVariable Integer id) {
        return RoleResponse.convert(this.service.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntityCreatedResponse create(@Valid @RequestBody CreateOrUpdateRoleRequest request) {
        return new EntityCreatedResponse(this.service.save(request).getId());
    }

    @PutMapping("/{id}")
    public EntityUpdatedResponse update(@PathVariable Integer id,
            @Valid @RequestBody CreateOrUpdateRoleRequest request) {
        this.service.save(id, request);

        return new EntityUpdatedResponse(id, true);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Integer id) {
        this.service.deleteById(id);
    }
}

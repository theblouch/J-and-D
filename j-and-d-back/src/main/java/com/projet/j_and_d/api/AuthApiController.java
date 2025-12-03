package com.projet.j_and_d.api;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projet.j_and_d.api.request.AuthRequest;
import com.projet.j_and_d.api.response.AuthResponse;
import com.projet.j_and_d.security.service.SecurityService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/auth")
public class AuthApiController {
    private final SecurityService service;

    public AuthApiController(SecurityService service) {
        this.service = service;
    }

    @PostMapping
    public AuthResponse auth(@Valid @RequestBody AuthRequest request) {
        return this.service.auth(request);
    }
}

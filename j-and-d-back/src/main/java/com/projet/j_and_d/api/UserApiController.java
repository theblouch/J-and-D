package com.projet.j_and_d.api;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projet.j_and_d.api.request.SubscriptionRequest;
import com.projet.j_and_d.api.response.EntityCreatedResponse;
import com.projet.j_and_d.model.GM;
import com.projet.j_and_d.model.Player;
import com.projet.j_and_d.model.User;
import com.projet.j_and_d.repo.UserRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserApiController {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserApiController(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/inscription")
    public EntityCreatedResponse subscribe(@Valid @RequestBody SubscriptionRequest request) {
        User user;

        if (request.isGM()) {
            user = new GM();
        } else {
            user = new Player();
        }

        user.setLogin(request.getLogin());
        user.setPassword(this.passwordEncoder.encode(request.getPassword()));

        this.repository.save(user);

        return new EntityCreatedResponse(user.getId());
    }
}

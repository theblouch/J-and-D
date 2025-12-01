package com.projet.j_and_d.security.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.projet.j_and_d.repo.UserRepository;




@Service
public class JpaUserDetailsService implements UserDetailsService {
    private final UserRepository repository;

    public JpaUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = this.repository
            .findByLogin(login)
            .orElseThrow(() -> new LoginNotFoundException("Utilisateur non trouvé"));

        return User.withLogin(login)
            .password(user.getPassword())
            .roles("USER")
            .build();
    }
}

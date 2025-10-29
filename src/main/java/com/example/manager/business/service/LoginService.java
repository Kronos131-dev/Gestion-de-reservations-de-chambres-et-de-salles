package com.example.manager.business.service;

import com.example.manager.persistence.entity.Utilisateur;
import com.example.manager.presentation.dto.LoginRequestDTO;
import com.example.manager.persistence.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public LoginService(UtilisateurRepository utilisateurRepository, PasswordEncoder passwordEncoder) {
        this.utilisateurRepository = utilisateurRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean verifyCredentials(LoginRequestDTO loginRequest) {
        return utilisateurRepository.findByEmail(loginRequest.email())
                .map(user -> passwordEncoder.matches(loginRequest.password(), user.getPassword()))
                .orElse(false);
    }
}


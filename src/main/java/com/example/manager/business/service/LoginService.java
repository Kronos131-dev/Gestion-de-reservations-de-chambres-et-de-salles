package com.example.manager.business.service;

import com.example.manager.persistence.entity.Utilisateur;
import com.example.manager.presentation.dto.LoginRequestDTO;
import com.example.manager.persistence.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final UtilisateurRepository utilisateurRepository;

    @Autowired
    public LoginService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    public boolean verifyCredentials(LoginRequestDTO loginRequest) {
        return utilisateurRepository.findByEmail(loginRequest.email())
                .map(user -> user.getPassword().equals(loginRequest.password()))
                .orElse(false);
    }
}


package com.example.manager.business.service;

import com.example.manager.config.JwtUtils;
import com.example.manager.presentation.dto.LoginRequestDTO;
import com.example.manager.persistence.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    @Autowired
    public LoginService(UtilisateurRepository utilisateurRepository, PasswordEncoder passwordEncoder, JwtUtils jwtUtils) {
        this.utilisateurRepository = utilisateurRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }

    public String verifyCredentials(LoginRequestDTO loginRequest) {

        if (loginRequest == null || loginRequest.email() == null || loginRequest.password() == null) {
            return null;
        }

        return utilisateurRepository.findByEmail(loginRequest.email())
                .filter(user -> passwordEncoder.matches(loginRequest.password(), user.getPassword()))
                .map(jwtUtils::generateToken)
                .orElse(null);
    }
}


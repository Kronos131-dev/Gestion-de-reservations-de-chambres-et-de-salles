package com.example.manager.business.service;

import com.example.manager.config.JwtUtils;
import com.example.manager.presentation.dto.LoginRequestDTO;
import com.example.manager.persistence.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

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

    public Optional<String> verifyCredentials(LoginRequestDTO loginRequest) {
        return utilisateurRepository.findByEmail(loginRequest.email())
                .filter(user -> passwordEncoder.matches(loginRequest.password(), user.getPassword()))
                .map(jwtUtils::generateToken);
    }


}


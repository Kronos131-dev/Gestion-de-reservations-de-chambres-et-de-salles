package com.example.manager.business.service;

import com.example.manager.config.JwtUtils;
import com.example.manager.presentation.dto.LoginRequestDTO;
import com.example.manager.persistence.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

// Service responsable de l'authentification (login)
@Service
public class LoginService {

    // Accès aux utilisateurs en base de données
    private final UtilisateurRepository utilisateurRepository;

    // Encodeur pour vérifier le mot de passe
    private final PasswordEncoder passwordEncoder;

    // Utilitaire pour générer les tokens JWT
    private final JwtUtils jwtUtils;

    @Autowired
    public LoginService(UtilisateurRepository utilisateurRepository,
                        PasswordEncoder passwordEncoder,
                        JwtUtils jwtUtils) {
        this.utilisateurRepository = utilisateurRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }

    // Vérifie les identifiants et retourne un token JWT si valides
    public String verifyCredentials(LoginRequestDTO loginRequest) {

        // Vérifie que la requête contient bien les informations nécessaires
        if (loginRequest == null
                || loginRequest.email() == null
                || loginRequest.password() == null) {
            return null;
        }

        return utilisateurRepository.findByEmail(loginRequest.email())
                // Vérifie le mot de passe
                .filter(user ->
                        passwordEncoder.matches(
                                loginRequest.password(),
                                user.getPassword()
                        )
                )
                // Génère le token JWT
                .map(jwtUtils::generateToken)
                // Retourne null si l'authentification échoue
                .orElse(null);
    }
}

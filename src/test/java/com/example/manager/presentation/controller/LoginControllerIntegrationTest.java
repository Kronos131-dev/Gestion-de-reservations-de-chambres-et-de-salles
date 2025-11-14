package com.example.manager.presentation.controller;

import com.example.manager.persistence.entity.Role;
import com.example.manager.persistence.entity.Utilisateur;
import com.example.manager.persistence.repository.RoleRepository;
import com.example.manager.persistence.repository.UtilisateurRepository;
import com.example.manager.config.JwtUtils;
import com.example.manager.presentation.dto.LoginRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test") // utilise application-test.properties
class LoginControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private ObjectMapper objectMapper;

    private Utilisateur user;
    private String token;

    @BeforeEach
    void setUp() {
        utilisateurRepository.deleteAll();

        // Crée un role et un utilisateur pour les tests
        Role role = new Role();
        role.setNom("CLIENT");
        roleRepository.save(role);

        user = new Utilisateur();
        user.setEmail("test@test.com");
        user.setPassword(new BCryptPasswordEncoder().encode("pwd"));
        user.setRole(role);
        user.setNom("TestNom");
        user.setPrenom("TestPrenom");
        user.setTel("0123456789");
        user.setDateNaissance(LocalDate.of(1990,1,1));
        utilisateurRepository.save(user);

        token = jwtUtils.generateToken(user);
    }

    @Test
    void testLoginWithValidCredentials() throws Exception {
        LoginRequestDTO loginRequest = new LoginRequestDTO("test@test.com", "pwd");

        mockMvc.perform(post("/api/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Connexion réussie"))
                .andExpect(jsonPath("$.token").exists());
    }

    @Test
    void testLoginWithInvalidCredentials() throws Exception {
        LoginRequestDTO loginRequest = new LoginRequestDTO("test@test.com", "wrongpwd");

        mockMvc.perform(post("/api/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.message").value("Identifiants invalides"));
    }

    @Test
    void testGetUtilisateurWithJwt() throws Exception {
        mockMvc.perform(get("/api/utilisateurs/" + user.getId())
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("test@test.com"));
    }
}


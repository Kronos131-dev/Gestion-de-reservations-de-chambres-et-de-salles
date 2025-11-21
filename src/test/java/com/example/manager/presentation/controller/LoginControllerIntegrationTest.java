package com.example.manager.presentation.controller;

import com.example.manager.persistence.entity.Role;
import com.example.manager.persistence.entity.Utilisateur;
import com.example.manager.persistence.repository.RoleRepository;
import com.example.manager.persistence.repository.UtilisateurRepository;
import com.example.manager.config.JwtUtils;
import com.example.manager.presentation.dto.LoginRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
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
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//Tests du login
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test") // application-test.properties
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

    // Test de connexion avec email mdp valides
    @Test
    void testLoginWithValidCredentials_shouldReturn200() throws Exception {
        LoginRequestDTO loginRequest = new LoginRequestDTO("test@test.com", "pwd");

        mockMvc.perform(post("/api/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Connexion réussie"))
                .andExpect(jsonPath("$.token").exists());
    }

    // Test avec email invalide
    @Test
    void testLoginWithWrongEmail_shouldReturn401() throws Exception {
        LoginRequestDTO loginRequest = new LoginRequestDTO("wrongtest@test.com", "pwd");

        mockMvc.perform(post("/api/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.message").value("Identifiants invalides"));
    }

    // Test avec mdp invalide
    @Test
    void testLoginWithWrongPassword_shouldReturn401() throws Exception {
        LoginRequestDTO loginRequest = new LoginRequestDTO("test@test.com", "wrongpwd");

        mockMvc.perform(post("/api/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.message").value("Identifiants invalides"));
    }

    //Test vérifiant la bonne reception du JSON
    @Test
    void testLoginWithEmptyBody_shouldReturn400() throws Exception {
        mockMvc.perform(post("/api/login")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    //Test vérifiant qu'on ne peut pas accéder à une autre page sans login
    @Test
    void testAccessWithoutToken_shouldReturn401() throws Exception {
        mockMvc.perform(get("/api/utilisateurs/" + user.getId()))
                .andExpect(status().isUnauthorized());
    }


    // Création et test d'un mauvais token
    private String generateExpiredToken(Utilisateur user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis() - 1000 * 60 * 10)) // il y a 10 mn
                .setExpiration(new Date(System.currentTimeMillis() - 1000 * 60 * 5)) // expiré depuis 5 mn
                .signWith(SignatureAlgorithm.HS256, "MysecretKeyNeededForThisTestIsSoShortSoIWriteThisToMakeItBetter")
                .compact();
    }

    @Test
    void testExpiredToken_shouldReturn401() throws Exception {
        String expiredToken = generateExpiredToken(user);
        mockMvc.perform(get("/api/utilisateurs/" + user.getId())
                        .header("Authorization", "Bearer " + expiredToken))
                .andExpect(status().isUnauthorized());
    }

    //Test login sans mdp
    @Test
    void testLoginWithMissingPassword_shouldReturn400() throws Exception {
        String requestJson = """
        {
           "email": "test@test.com"
        }
    """;

        mockMvc.perform(post("/api/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isBadRequest());
    }


}


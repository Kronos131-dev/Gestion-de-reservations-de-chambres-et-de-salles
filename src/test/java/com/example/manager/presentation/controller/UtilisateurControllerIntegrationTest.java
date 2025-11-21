package com.example.manager.presentation.controller;

import com.example.manager.persistence.entity.Role;
import com.example.manager.persistence.entity.Utilisateur;
import com.example.manager.persistence.repository.RoleRepository;
import com.example.manager.persistence.repository.UtilisateurRepository;
import com.example.manager.config.JwtUtils;
import com.example.manager.presentation.dto.UtilisateurDTO;
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
@ActiveProfiles("test")
class UtilisateurControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JwtUtils jwtUtils;

    private Utilisateur userClient, userAdmin;
    private String tokenClient, tokenAdmin;

    @BeforeEach
    void setUp() {
        utilisateurRepository.deleteAll();
        roleRepository.deleteAll();

        Role roleClient = new Role();
        roleClient.setNom("CLIENT");
        roleRepository.save(roleClient);
        Role roleAdmin = new Role();
        roleAdmin.setNom("ADMIN");
        roleRepository.save(roleAdmin);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        // Client
        userClient = new Utilisateur();
        userClient.setEmail("client@test.com");
        userClient.setPassword(encoder.encode("pwd"));
        userClient.setRole(roleClient);
        userClient.setPrenom("PrenomClient");
        userClient.setNom("NomClient");
        userClient.setTel("0606060606");
        userClient = utilisateurRepository.save(userClient);
        tokenClient = jwtUtils.generateToken(userClient);

        // Admin
        userAdmin = new Utilisateur();
        userAdmin.setEmail("admin@test.com");
        userAdmin.setPassword(encoder.encode("pwd"));
        userAdmin.setRole(roleAdmin);
        userAdmin.setPrenom("PrenomAdmin");
        userAdmin.setNom("NomAdmin");
        userAdmin.setTel("0606060606");
        userAdmin = utilisateurRepository.save(userAdmin);
        tokenAdmin = jwtUtils.generateToken(userAdmin);
    }

    // ===== GET =====

    //Test de nécessité du token JWT
    @Test
    void testAccessWithoutJwt_shouldReturn401() throws Exception {
        mockMvc.perform(get("/api/utilisateurs/" + userClient.getId()))
                .andExpect(status().isUnauthorized());
    }

    //Test qu'un utilisateur puisse accéder à ses propres données
    @Test
    void testGetOwnUserWithJwt_shouldReturn200() throws Exception {
        mockMvc.perform(get("/api/utilisateurs/" + userClient.getId())
                        .header("Authorization", "Bearer " + tokenClient))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("client@test.com"));
    }

    // Test qu'un utilisateur ne puisse pas accéder aux données des autres utilisateurs
    @Test
    void testClientSeeOtherUsers_shouldReturn403() throws Exception {
        mockMvc.perform(get("/api/utilisateurs")
                        .header("Authorization", "Bearer " + tokenClient))
                .andExpect(status().isForbidden());
    }

    //Test qu'un admin a le droit de voir les données de tous les utilisateurs
    @Test
    void testAdminSeeOtherUsers_shouldReturn200() throws Exception {
        mockMvc.perform(get("/api/utilisateurs")
                        .header("Authorization", "Bearer " + tokenAdmin))
                .andExpect(status().isOk());
    }

    // Test qu'un utilisateur ne puisse pas accéder aux données d'un autre utilisateur
    @Test
    void testClientAccessOtherUser_shouldReturn403() throws Exception {
        mockMvc.perform(get("/api/utilisateurs/" + userAdmin.getId())
                        .header("Authorization", "Bearer " + tokenClient))
                .andExpect(status().isForbidden());
    }

    //Test qu'un admin a le droit de voir les donées d'un autre utilisateur
    @Test
    void testAdminAccessOtherUser_shouldReturn200() throws Exception {
        mockMvc.perform(get("/api/utilisateurs/" + userClient.getId())
                        .header("Authorization", "Bearer " + tokenAdmin))
                .andExpect(status().isOk());
    }

    //Test accès utilisateur inexistant
    @Test
    void testAccessUtilisateurInexistant_shouldReturn404() throws Exception {
        mockMvc.perform(get("/api/utilisateurs/9999")
                        .header("Authorization", "Bearer " + tokenAdmin))
                .andExpect(status().isNotFound());
    }
    // ===== POST =====

    //Test admin peut créer un autre utilisateur
    @Test
    void testAdminCreateUser_shouldReturn201() throws Exception {
        Utilisateur newUser = new Utilisateur();
        newUser.setEmail("new@test.com");
        newUser.setPassword("pwd");
        newUser.setPrenom("NewPrenom");
        newUser.setNom("NewNom");
        newUser.setTel("0101010101");
        newUser.setRole(userClient.getRole()); // client role

        mockMvc.perform(post("/api/utilisateurs")
                        .header("Authorization", "Bearer " + tokenAdmin)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newUser)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.email").value("new@test.com"));
    }

    //Test client ne peut pas en créer
    @Test
    void testClientCreateUser_shouldReturn403() throws Exception {
        mockMvc.perform(post("/api/utilisateurs")
                        .header("Authorization", "Bearer " + tokenClient)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userClient)))
                .andExpect(status().isForbidden());
    }

    // ===== PUT =====
    //Test qu'un client puisse modifier ses données
    @Test
    void testClientModifyOwnData_shouldReturn200() throws Exception {
        UtilisateurDTO dto = new UtilisateurDTO(
                "NomModifie",
                "PrenomModifie",
                "client@test.com",
                "newPwd",
                "0707070707",
                LocalDate.of(1990,1,1),
                userClient.getRole().getId(),
                null
        );

        mockMvc.perform(put("/api/utilisateurs/" + userClient.getId())
                        .header("Authorization", "Bearer " + tokenClient)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom").value("NomModifie"))
                .andExpect(jsonPath("$.prenom").value("PrenomModifie"))
                .andExpect(jsonPath("$.tel").value("0707070707"));
    }

    //Test qu'un client ne puisse pas modifier les données d'un autre client
    @Test
    void testClientModifyOtherUser_shouldReturn403() throws Exception {
        UtilisateurDTO dto = new UtilisateurDTO(
                "NomModifie",
                "PrenomModifie",
                "admin@test.com",
                "newPwd",
                "0707070707",
                LocalDate.of(1980,1,1),
                userAdmin.getRole().getId(),
                null
        );

        mockMvc.perform(put("/api/utilisateurs/" + userAdmin.getId())
                        .header("Authorization", "Bearer " + tokenClient)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isForbidden());
    }

    //Test qu'un admin puisse modifier les données d'un client
    @Test
    void testAdminModifyOtherUser_shouldReturn200() throws Exception {
        UtilisateurDTO dto = new UtilisateurDTO(
                "NomClientModifie",
                "PrenomClientModifie",
                "client@test.com",
                "newPwd",
                "0707070707",
                LocalDate.of(1990,1,1),
                userClient.getRole().getId(),
                null
        );

        mockMvc.perform(put("/api/utilisateurs/" + userClient.getId())
                        .header("Authorization", "Bearer " + tokenAdmin)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom").value("NomClientModifie"));
    }

    //Test modification utilisateur inexistant
    @Test
    void testModifyUserInexistant_shouldReturn404() throws Exception {
        UtilisateurDTO dto = new UtilisateurDTO(
                "NomClientModifie",
                "PrenomClientModifie",
                "client@test.com",
                "newPwd",
                "0707070707",
                LocalDate.of(1990,1,1),
                userClient.getRole().getId(),
                null
        );

        mockMvc.perform(put("/api/utilisateurs/9999")
                        .header("Authorization", "Bearer " + tokenAdmin)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isNotFound());
    }
    // ===== DELETE =====
    //Test qu'un client puisse supprimer ses données
    @Test
    void testClientDeleteOwnData_shouldReturn200() throws Exception {
        mockMvc.perform(delete("/api/utilisateurs/" + userClient.getId())
                        .header("Authorization", "Bearer " + tokenClient))
                .andExpect(status().isOk());
    }

    //Test qu'un client ne puisse pas supprimer un autre client
    @Test
    void testClientDeleteOtherUser_shouldReturn403() throws Exception {
        mockMvc.perform(delete("/api/utilisateurs/" + userAdmin.getId())
                        .header("Authorization", "Bearer " + tokenClient))
                .andExpect(status().isForbidden());
    }

    //Test qu'un admin puisse supprimer un client
    @Test
    void testAdminDeleteUser_shouldReturn200() throws Exception {
        mockMvc.perform(delete("/api/utilisateurs/" + userClient.getId())
                        .header("Authorization", "Bearer " + tokenAdmin))
                .andExpect(status().isOk());
    }

    //Test suppression utilisateur inexistant
    @Test
    void testDeleteUtilisateurInexistant_shouldReturn404() throws Exception {
        mockMvc.perform(delete("/api/utilisateurs/9999")
                        .header("Authorization", "Bearer " + tokenAdmin))
                .andExpect(status().isNotFound());
    }

}



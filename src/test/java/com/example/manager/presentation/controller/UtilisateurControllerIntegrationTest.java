package com.example.manager.presentation.controller;

import com.example.manager.persistence.entity.Adresse;
import com.example.manager.persistence.entity.Role;
import com.example.manager.persistence.entity.Utilisateur;
import com.example.manager.persistence.repository.AdresseRepository;
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
    private AdresseRepository adresseRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JwtUtils jwtUtils;

    private Utilisateur userClient, userAdmin;
    private String tokenClient, tokenAdmin;

    @BeforeEach
    void setUp() {
        // Nettoyage des tables
        utilisateurRepository.deleteAll();
        roleRepository.deleteAll();
        adresseRepository.deleteAll();

        // Création des rôles
        Role roleClient = new Role();
        roleClient.setNom("CLIENT");
        roleRepository.save(roleClient);

        Role roleAdmin = new Role();
        roleAdmin.setNom("ADMIN");
        roleRepository.save(roleAdmin);

        // Création d'une adresse
        Adresse adresse = new Adresse();
        adresse.setNum("1");
        adresse.setRue("RueTest");
        adresse.setVille("VilleTest");
        adresse.setCodePostal("12345");
        adresse.setPays("France");
        adresseRepository.save(adresse);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        // Création d'un utilisateur client
        userClient = new Utilisateur();
        userClient.setEmail("client@test.com");
        userClient.setPassword(encoder.encode("pwd"));
        userClient.setPrenom("PrenomClient");
        userClient.setNom("NomClient");
        userClient.setTel("0606060606");
        userClient.setRole(roleClient);
        userClient.setAdresse(adresse);
        userClient = utilisateurRepository.save(userClient);
        tokenClient = jwtUtils.generateToken(userClient);

        // Création d'un utilisateur admin
        userAdmin = new Utilisateur();
        userAdmin.setEmail("admin@test.com");
        userAdmin.setPassword(encoder.encode("pwd"));
        userAdmin.setPrenom("PrenomAdmin");
        userAdmin.setNom("NomAdmin");
        userAdmin.setTel("0606060606");
        userAdmin.setRole(roleAdmin);
        userAdmin.setAdresse(adresse);
        userAdmin = utilisateurRepository.save(userAdmin);
        tokenAdmin = jwtUtils.generateToken(userAdmin);
    }

    // ===== GET =====

    // Test d'accès à une route protégée sans JWT : doit renvoyer 401
    @Test
    void testAccessWithoutJwt_shouldReturn401() throws Exception {
        mockMvc.perform(get("/api/utilisateurs/" + userClient.getId()))
                .andExpect(status().isUnauthorized());
    }

    // Test d'accès d'un utilisateur à ses propres données : doit renvoyer 200
    @Test
    void testGetOwnUserWithJwt_shouldReturn200() throws Exception {
        mockMvc.perform(get("/api/utilisateurs/" + userClient.getId())
                        .header("Authorization", "Bearer " + tokenClient))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("client@test.com"));
    }

    // Test qu'un client ne peut pas accéder à la liste de tous les utilisateurs : 403
    @Test
    void testClientSeeOtherUsers_shouldReturn403() throws Exception {
        mockMvc.perform(get("/api/utilisateurs")
                        .header("Authorization", "Bearer " + tokenClient))
                .andExpect(status().isForbidden());
    }

    // Test qu'un admin peut accéder à la liste de tous les utilisateurs : 200
    @Test
    void testAdminSeeOtherUsers_shouldReturn200() throws Exception {
        mockMvc.perform(get("/api/utilisateurs")
                        .header("Authorization", "Bearer " + tokenAdmin))
                .andExpect(status().isOk());
    }

    // Test qu'un client ne peut pas accéder aux données d'un autre utilisateur : 403
    @Test
    void testClientAccessOtherUser_shouldReturn403() throws Exception {
        mockMvc.perform(get("/api/utilisateurs/" + userAdmin.getId())
                        .header("Authorization", "Bearer " + tokenClient))
                .andExpect(status().isForbidden());
    }

    // Test qu'un admin peut accéder aux données d'un autre utilisateur : 200
    @Test
    void testAdminAccessOtherUser_shouldReturn200() throws Exception {
        mockMvc.perform(get("/api/utilisateurs/" + userClient.getId())
                        .header("Authorization", "Bearer " + tokenAdmin))
                .andExpect(status().isOk());
    }

    // Test d'accès à un utilisateur inexistant : 404
    @Test
    void testAccessUtilisateurInexistant_shouldReturn404() throws Exception {
        mockMvc.perform(get("/api/utilisateurs/9999")
                        .header("Authorization", "Bearer " + tokenAdmin))
                .andExpect(status().isNotFound());
    }

    // ===== POST =====

    // Test qu'un admin peut créer un utilisateur : 201
    @Test
    void testAdminCreateUser_shouldReturn201() throws Exception {
        UtilisateurDTO newUserDTO = new UtilisateurDTO(
                "NewNom",
                "NewPrenom",
                "new@test.com",
                "pwd",
                "0101010101",
                LocalDate.of(1990, 1, 1),
                userClient.getRole().getId(),
                userClient.getAdresse().getId()
        );

        mockMvc.perform(post("/api/utilisateurs")
                        .header("Authorization", "Bearer " + tokenAdmin)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newUserDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.email").value("new@test.com"));
    }

    // Test qu'un client ne peut pas créer un utilisateur : 403
    @Test
    void testClientCreateUser_shouldReturn403() throws Exception {
        mockMvc.perform(post("/api/utilisateurs")
                        .header("Authorization", "Bearer " + tokenClient)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userClient)))
                .andExpect(status().isForbidden());
    }

    // ===== PUT =====

    // Test qu'un client peut modifier ses propres données : 200
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
                userClient.getAdresse().getId()
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

    // Test qu'un client ne peut pas modifier les données d'un autre utilisateur : 403
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

    // Test qu'un admin peut modifier les données d'un utilisateur : 200
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

    // Test de modification d'un utilisateur inexistant : 404
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

    // Test qu'un client peut supprimer ses propres données : 200
    @Test
    void testClientDeleteOwnData_shouldReturn200() throws Exception {
        mockMvc.perform(delete("/api/utilisateurs/" + userClient.getId())
                        .header("Authorization", "Bearer " + tokenClient))
                .andExpect(status().isOk());
    }

    // Test qu'un client ne peut pas supprimer un autre utilisateur : 403
    @Test
    void testClientDeleteOtherUser_shouldReturn403() throws Exception {
        mockMvc.perform(delete("/api/utilisateurs/" + userAdmin.getId())
                        .header("Authorization", "Bearer " + tokenClient))
                .andExpect(status().isForbidden());
    }

    // Test qu'un admin peut supprimer un utilisateur : 200
    @Test
    void testAdminDeleteUser_shouldReturn200() throws Exception {
        mockMvc.perform(delete("/api/utilisateurs/" + userClient.getId())
                        .header("Authorization", "Bearer " + tokenAdmin))
                .andExpect(status().isOk());
    }

    // Test de suppression d'un utilisateur inexistant : 404
    @Test
    void testDeleteUtilisateurInexistant_shouldReturn404() throws Exception {
        mockMvc.perform(delete("/api/utilisateurs/9999")
                        .header("Authorization", "Bearer " + tokenAdmin))
                .andExpect(status().isNotFound());
    }

}
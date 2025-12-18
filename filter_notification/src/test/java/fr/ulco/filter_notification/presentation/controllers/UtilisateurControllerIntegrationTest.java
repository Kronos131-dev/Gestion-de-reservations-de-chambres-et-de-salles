package fr.ulco.filter_notification.presentation.controllers;

import fr.ulco.filter_notification.persistence.entities.Notification;
import fr.ulco.filter_notification.persistence.entities.Utilisateur;
import fr.ulco.filter_notification.persistence.repositories.UtilisateurRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UtilisateurControllerIntegrationTest {

    @BeforeEach
    void setup() {
        utilisateurRepository.deleteAll();

        List<Notification> emptyList = new ArrayList<>();

        Utilisateur u1 = new Utilisateur();
        u1.setNom("Tim");
        u1.setPrenom("Vincent");
        u1.setEmail("Vincent.Tim@gmail.com");
        u1.setPassword("toto");
        u1.setTel("0102030406");
        u1.setDateNaissance(LocalDate.MIN);
        u1.setNotifications(emptyList);

        utilisateurRepository.save(u1);

        Utilisateur u2 = new Utilisateur();
        u2.setNom("Admin");
        u2.setPrenom("Super");
        u2.setEmail("admin@site.com");
        u2.setPassword("tata");
        u2.setTel("0600000000");
        u2.setDateNaissance(LocalDate.MIN);
        u2.setNotifications(emptyList);

        utilisateurRepository.save(u2);
    }

    @Test   // GET: /api/utilisateurs
    void testGetAllUtilisateurs() throws Exception {
        mockMvc.perform(get("/api/utilisateurs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2)); // [u1, u2]
    }

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private MockMvc mockMvc;
}

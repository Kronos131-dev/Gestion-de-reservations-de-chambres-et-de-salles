package fr.ulco.filter_notification.presentation.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.ulco.filter_notification.business.subjects.concrete.EspaceStatusSubject;
import fr.ulco.filter_notification.persistence.entities.Espace;
import fr.ulco.filter_notification.persistence.entities.Notification;
import fr.ulco.filter_notification.persistence.entities.TypeEspace;
import fr.ulco.filter_notification.persistence.entities.Utilisateur;
import fr.ulco.filter_notification.persistence.repositories.EspaceRepository;
import fr.ulco.filter_notification.persistence.repositories.TypeEspaceRepository;
import fr.ulco.filter_notification.persistence.repositories.UtilisateurRepository;
import fr.ulco.filter_notification.presentation.dto.EspaceFilterDTO;
import fr.ulco.filter_notification.presentation.dto.EspaceUpdateDTO;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class EspaceControllerIntegrationTest {

    @BeforeEach
    void setup() {
        espaceRepository.deleteAll();
        typeEspaceRepository.deleteAll();
        utilisateurRepository.deleteAll();

        t = new TypeEspace();
        t.setDescription("chambre");
        t.setNomEspace("chambre");
        typeEspaceRepository.save(t);

        e1 = new Espace();
        e1.setNbPlaces(5L);
        e1.setDescription("chambre");
        e1.setPrixBase(30F);
        e1.setStatus(Espace.Status.DISPONIBLE);
        e1.setTypeEspace(t);
        espaceRepository.save(e1);

        e2 = new Espace();
        e2.setNbPlaces(3L);
        e2.setDescription("chambre");
        e2.setPrixBase(200F);
        e2.setStatus(Espace.Status.DISPONIBLE);
        e2.setTypeEspace(t);
        espaceRepository.save(e2);

        u = new Utilisateur();
        u.setNom("Tim");
        u.setPrenom("Vincent");
        u.setEmail("Vincent.Tim@gmail.com");
        u.setPassword("toto");
        u.setTel("0102030406");
        u.setDateNaissance(LocalDate.MIN);
        u.setNotifications(new ArrayList<>());

        utilisateurRepository.save(u);
    }

    @Test   // GET: /api/espaces
    void testGetAllEspaces() throws Exception {
        mockMvc.perform(get("/api/espaces"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2)); // DTO(e1), DTO(e2)
    }

    @Test   // GET: /api/espaces avec body contenant maxPrixBase = 50.0
    void testGetFilteredEspaces() throws Exception {
        EspaceFilterDTO filter = new EspaceFilterDTO(
                null,
                null,
                null,
                50F,
                null,
                null,
                null,
                null);

        mockMvc.perform(get("/api/espaces")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(filter)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].prixBase").value(30F)); // DTO(e1)
    }

    @Test   // PUT: /api/espaces/{id} avec body contenant status = OCCUPE
    void testUpdateEspace() throws Exception {
        EspaceUpdateDTO dto = new EspaceUpdateDTO(Espace.Status.OCCUPE);

        mockMvc.perform(put("/api/espaces/" + e1.getIdEspace())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("OCCUPE"));
    }

    @Autowired
    private EspaceRepository espaceRepository;

    @Autowired
    private TypeEspaceRepository typeEspaceRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Espace e1, e2;
    private TypeEspace t;
    private Utilisateur u;
}

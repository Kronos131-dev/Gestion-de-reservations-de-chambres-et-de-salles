package fr.ulco.filter_notification.presentation.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.ulco.filter_notification.persistence.entities.Espace;
import fr.ulco.filter_notification.persistence.entities.TypeEspace;
import fr.ulco.filter_notification.persistence.repositories.EspaceRepository;
import fr.ulco.filter_notification.persistence.repositories.TypeEspaceRepository;
import fr.ulco.filter_notification.presentation.dto.EspaceFilterDTO;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

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

        TypeEspace t = new TypeEspace();
        t.setDescription("chambre");
        t.setNomEspace("chambre");
        typeEspaceRepository.save(t);

        Espace e1 = new Espace();
        e1.setNbPlaces(5L);
        e1.setDescription("chambre");
        e1.setPrixBase(30F);
        e1.setStatus(Espace.Status.DISPONIBLE);
        e1.setTypeEspace(t);
        espaceRepository.save(e1);

        Espace e2 = new Espace();
        e2.setNbPlaces(3L);
        e2.setDescription("chambre");
        e2.setPrixBase(200F);
        e2.setStatus(Espace.Status.DISPONIBLE);
        e2.setTypeEspace(t);
        espaceRepository.save(e2);
    }

    @Test   // GET: /api/espaces
    void testGetAllEspaces() throws Exception {
        mockMvc.perform(get("/api/espaces"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2)); // e1, e2
    }

    @Test   // GET: /api/espaces avec body contenant maxPrixBase = 50.0
    void testGetFilteredEspaces() throws Exception {
        EspaceFilterDTO filter = new EspaceFilterDTO(
                null,
                null,
                null,
                50F,
                null,
                false);

        mockMvc.perform(get("/api/espaces")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(filter)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].prixBase").value(30F)); // e1
    }

    @Autowired
    private EspaceRepository espaceRepository;

    @Autowired
    private TypeEspaceRepository typeEspaceRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
}

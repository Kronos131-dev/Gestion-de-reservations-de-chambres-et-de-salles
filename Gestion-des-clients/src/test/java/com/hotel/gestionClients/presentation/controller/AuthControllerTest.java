package com.hotel.gestionClients.presentation.controller;

import com.hotel.gestionClients.business.dto.UtilisateurDTO;
import com.hotel.gestionClients.business.service.UtilisateurService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UtilisateurService utilisateurService;
    @Test
    void testInscription_SubmitForm_DoitCreerClient_EtRediriger() throws Exception {

        mockMvc.perform(post("/inscription")
                        .param("nom", "Dupont")
                        .param("prenom", "Jean")
                        .param("email", "jean.dupont@test.com")
                        .param("password", "123456")
                        .param("tel", "0606060606")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login?success"));

        verify(utilisateurService, times(1)).createClient(any(UtilisateurDTO.class));
    }
}
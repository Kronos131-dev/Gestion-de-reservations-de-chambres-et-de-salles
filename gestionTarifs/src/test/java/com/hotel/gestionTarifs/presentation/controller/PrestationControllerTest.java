package com.hotel.gestionTarifs.presentation.controller;

import com.hotel.gestionTarifs.business.dto.PrestationDTO;
import com.hotel.gestionTarifs.business.service.IPrestationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PrestationController.class)
class PrestationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private IPrestationService prestationService;

    @Test
    @DisplayName("GET /prestations : Doit afficher la liste des prestations")
    void shouldShowList() throws Exception {
        PrestationDTO p1 = new PrestationDTO();
        p1.setIdPrestation(1);
        p1.setPrix(new BigDecimal("150.00"));
        when(prestationService.getAll()).thenReturn(Arrays.asList(p1));
        mockMvc.perform(get("/prestations")).andExpect(status().isOk()).andExpect(view().name("prestations/liste")).andExpect(model().attributeExists("prestations"));
    }

    @Test
    @DisplayName("GET /prestations/create : Doit afficher le formulaire vide")
    void shouldShowCreateForm() throws Exception {
        mockMvc.perform(get("/prestations/create")).andExpect(status().isOk()).andExpect(view().name("prestations/form")).andExpect(model().attributeExists("prestation"));
    }

    @Test
    @DisplayName("POST /prestations/create : Doit sauvegarder et rediriger")
    void shouldSavePrestation() throws Exception {
        mockMvc.perform(post("/prestations/create").param("prix", "250.00").param("idReservation", "10")).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/prestations"));
        verify(prestationService, times(1)).create(any(PrestationDTO.class));
    }

    @Test
    @DisplayName("GET /prestations/edit/{id} : Doit afficher le formulaire d'édition")
    void shouldShowEditForm() throws Exception {
        Integer id = 1;
        PrestationDTO dto = new PrestationDTO();
        dto.setIdPrestation(id);
        dto.setPrix(new BigDecimal("300.00"));
        when(prestationService.getById(id)).thenReturn(dto);
        mockMvc.perform(get("/prestations/edit/{id}", id)).andExpect(status().isOk()).andExpect(view().name("prestations/form")).andExpect(model().attribute("prestation", dto));
    }

    @Test
    @DisplayName("GET /prestations/delete/{id} : Doit supprimer et rediriger")
    void shouldDeletePrestation() throws Exception {
        Integer id = 1;
        mockMvc.perform(get("/prestations/delete/{id}", id)).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/prestations"));
        verify(prestationService, times(1)).delete(id);
    }
}
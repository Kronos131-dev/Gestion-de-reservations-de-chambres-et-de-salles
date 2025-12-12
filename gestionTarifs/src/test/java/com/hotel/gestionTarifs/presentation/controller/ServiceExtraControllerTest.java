package com.hotel.gestionTarifs.presentation.controller;

import com.hotel.gestionTarifs.business.dto.ServiceExtraDTO;
import com.hotel.gestionTarifs.business.service.ServiceExtraService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ServiceExtraController.class)
class ServiceExtraControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ServiceExtraService service;

    @Test
    @DisplayName("GET /service-extras : Doit afficher la liste")
    void shouldShowList() throws Exception {
        when(service.getAllServiceExtras()).thenReturn(Arrays.asList(
                new ServiceExtraDTO(1, "Wifi", "Desc", 100),
                new ServiceExtraDTO(2, "TV", "Desc", 200)
        ));
        mockMvc.perform(get("/service-extras"))
                .andExpect(status().isOk())
                .andExpect(view().name("serviceExtra/list"))
                .andExpect(model().attributeExists("serviceExtras"));
    }

    @Test
    @DisplayName("GET /service-extras/new : Doit afficher le formulaire")
    void shouldShowCreateForm() throws Exception {
        mockMvc.perform(get("/service-extras/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("serviceExtra/form"))
                .andExpect(model().attributeExists("serviceExtra"))
                .andExpect(model().attribute("title", "Créer un service extra"));
    }

    @Test
    @DisplayName("POST /service-extras/save : Doit sauvegarder et rediriger")
    void shouldSaveServiceExtra() throws Exception {
        mockMvc.perform(post("/service-extras/save")
                        .param("nom", "Piscine")
                        .param("description", "Accès illimité")
                        .param("prix", "2000"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/service-extras"))
                .andExpect(flash().attributeExists("success"));
        verify(service, times(1)).createServiceExtra(any(ServiceExtraDTO.class));
    }

    @Test
    @DisplayName("GET /service-extras/{id}/edit : Doit afficher le formulaire pré-rempli")
    void shouldShowEditForm() throws Exception {
        Integer id = 1;
        ServiceExtraDTO dto = new ServiceExtraDTO(id, "Gym", "Sport", 500);
        when(service.getServiceExtraById(id)).thenReturn(dto);
        mockMvc.perform(get("/service-extras/{id}/edit", id))
                .andExpect(status().isOk())
                .andExpect(view().name("serviceExtra/form"))
                .andExpect(model().attribute("serviceExtra", dto));
    }

    @Test
    @DisplayName("GET /service-extras/{id}/delete : Doit supprimer et rediriger")
    void shouldDeleteServiceExtra() throws Exception {
        mockMvc.perform(get("/service-extras/{id}/delete", 1))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/service-extras"))
                .andExpect(flash().attributeExists("success"));
        verify(service, times(1)).deleteServiceExtra(1);
    }
}
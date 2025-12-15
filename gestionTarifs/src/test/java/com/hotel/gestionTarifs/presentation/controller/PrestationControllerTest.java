package com.hotel.gestionTarifs.presentation.controller;
import com.hotel.gestionTarifs.business.dto.PrestationDTO;
import com.hotel.gestionTarifs.business.service.IPrestationService;
import com.hotel.gestionTarifs.business.service.ServiceExtraService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
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
    @MockitoBean
    private ServiceExtraService serviceExtraService;
    @Test
    @DisplayName("GET /prestations : Doit afficher la liste des prestations")
    void shouldShowList() throws Exception {
        PrestationDTO p1 =new PrestationDTO();
        p1.setIdPrestation(1);
        p1.setPrix(new BigDecimal("150.00"));
        when(prestationService.getAll()).thenReturn(Arrays.asList(p1));
        mockMvc.perform(get("/prestations")).andExpect(status().isOk()).andExpect(view().name("prestations/liste")).andExpect(model().attributeExists("prestations"));
    }

    @Test
    @DisplayName("GET /prestations/create : Doit afficher le formulaire avec la liste des services")
    void shouldShowCreateForm() throws Exception {
        when(serviceExtraService.getAllServiceExtras()).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/prestations/create")).andExpect(status().isOk()).andExpect(view().name("prestations/form"))
                .andExpect(model().attributeExists("prestation"))
                .andExpect(model().attributeExists("servicesAvailable"));
    }

    @Test
    @DisplayName("POST /prestations/create : Doit sauvegarder (cas standard) et rediriger")
    void shouldSavePrestation() throws Exception {
        mockMvc.perform(post("/prestations/create").param("prix", "250.00")).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/prestations"))
                .andExpect(flash().attributeExists("success"));
        verify(prestationService, times(1)).create(any(PrestationDTO.class));
    }

    @Test
    @DisplayName("GET /prestations/edit/{id} : Doit afficher le formulaire d'édition avec les services")
    void shouldShowEditForm() throws Exception {
        Integer id =1;
        PrestationDTO dto= new PrestationDTO();
        dto.setIdPrestation(id);
        dto.setPrix(new BigDecimal("300.00"));
        when(prestationService.getById(id)).thenReturn(dto);
        when(serviceExtraService.getAllServiceExtras()).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/prestations/edit/{id}", id)).andExpect(status().isOk()).andExpect(view().name("prestations/form"))
                .andExpect(model().attribute("prestation", dto))
                .andExpect(model().attributeExists("servicesAvailable"));}

    @Test
    @DisplayName("GET /prestations/delete/{id} : Doit supprimer et rediriger")
    void shouldDeletePrestation() throws Exception {
        Integer id =1;
        mockMvc.perform(get("/prestations/delete/{id}", id)).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/prestations"))
                .andExpect(flash().attributeExists("success"));
        verify(prestationService, times(1)).delete(id);}
}
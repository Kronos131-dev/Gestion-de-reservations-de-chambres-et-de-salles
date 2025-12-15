package com.hotel.gestionTarifs.business.service;

import com.hotel.gestionTarifs.business.dto.PrestationDTO;
import com.hotel.gestionTarifs.business.service.impl.PrestationServiceImpl;
import com.hotel.gestionTarifs.persistence.entity.*;
import com.hotel.gestionTarifs.persistence.repository.ExtraRepository;
import com.hotel.gestionTarifs.persistence.repository.PrestationRepository;
import com.hotel.gestionTarifs.persistence.repository.ReservationRepository;
import com.hotel.gestionTarifs.persistence.repository.ServiceExtraRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PrestationServiceImplTest {

    @Mock
    private PrestationRepository prestationRepository;
    @Mock
    private ServiceExtraRepository serviceExtraRepository;
    @Mock
    private ExtraRepository extraRepository;
    @Mock
    private ReservationRepository reservationRepository;
    @InjectMocks
    private PrestationServiceImpl service;
    @Test
    @DisplayName("Devrait retourner toutes les prestations triées")
    void shouldReturnAllPrestations() {
        Prestation p1 =new Prestation();
        p1.setIdPrestation(1);
        p1.setPrix(new BigDecimal("100.00"));
        Prestation p2= new Prestation();
        p2.setIdPrestation(2);
        p2.setPrix(new BigDecimal("250.50"));

        when(prestationRepository.findAll(any(Sort.class))).thenReturn(Arrays.asList(p1,p2));
        List<PrestationDTO> result =service.getAll();
        assertEquals(2, result.size());
        assertEquals(new BigDecimal("100.00"), result.get(0).getPrix());
        verify(prestationRepository).findAll(any(Sort.class));
    }

    @Test
    @DisplayName("Devrait créer une prestation simple")
    void shouldCreatePrestation() {
        PrestationDTO dto =new PrestationDTO();
        dto.setPrix(new BigDecimal("150.00"));
        dto.setIdReservation(10);
        Prestation savedEntity= new Prestation();
        savedEntity.setIdPrestation(55);
        savedEntity.setPrix(new BigDecimal("150.00"));

        when(prestationRepository.save(any(Prestation.class))).thenReturn(savedEntity);
        PrestationDTO result =service.create(dto);
        assertNotNull(result.getIdPrestation());
        assertEquals(55, result.getIdPrestation());
        assertEquals(new BigDecimal("150.00"), result.getPrix());
        verify(prestationRepository, times(1)).save(any(Prestation.class));}

    @Test
    @DisplayName("Devrait créer une prestation liée à un Service Extra")
    void shouldCreatePrestationWithService() {
        PrestationDTO dto = new PrestationDTO();
        dto.setQuantite(2);
        Integer idService =10;
        ServiceExtra mockService = new ServiceExtra();
        mockService.setIdService(idService);
        mockService.setPrix(new BigDecimal("50.00"));

        when(serviceExtraRepository.findById(idService)).thenReturn(Optional.of(mockService));
        Prestation savedPrestation = new Prestation();
        savedPrestation.setIdPrestation(100);
        savedPrestation.setPrix(new BigDecimal("100.00"));
        when(prestationRepository.save(any(Prestation.class))).thenReturn(savedPrestation);
        PrestationDTO result =service.createPrestationWithService(dto, idService);
        assertNotNull(result);
        assertEquals(new BigDecimal("100.00"), result.getPrix());
        verify(extraRepository, times(1)).save(any(Extra.class));}

    @Test
    @DisplayName("Devrait lancer une exception si ID introuvable lors de l'update")
    void shouldThrowExceptionWhenUpdatingUnknownId() {
        Integer unknownId =999;
        PrestationDTO updateInfo= new PrestationDTO();
        updateInfo.setPrix(new BigDecimal("500.00"));
        when(prestationRepository.findById(unknownId)).thenReturn(Optional.empty());
        Exception exception = assertThrows(RuntimeException.class, () -> {service.update(unknownId, updateInfo);});
        assertTrue(exception.getMessage().contains("non trouvée"));}

    @Test
    @DisplayName("Devrait supprimer une prestation")
    void shouldDeletePrestation() {
        Integer id = 1;
        service.delete(id);
        verify(prestationRepository, times(1)).deleteById(id);}}
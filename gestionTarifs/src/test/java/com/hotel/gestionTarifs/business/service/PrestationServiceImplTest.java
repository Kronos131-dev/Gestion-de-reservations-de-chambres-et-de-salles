package com.hotel.gestionTarifs.business.service;

import com.hotel.gestionTarifs.business.dto.PrestationDTO;
import com.hotel.gestionTarifs.business.service.impl.PrestationServiceImpl;
import com.hotel.gestionTarifs.persistence.entity.Prestation;
import com.hotel.gestionTarifs.persistence.repository.PrestationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
    private PrestationRepository repository;

    @InjectMocks
    private PrestationServiceImpl service;

    @Test
    void shouldReturnAllPrestations() {
        Prestation p1 = new Prestation();
        p1.setIdPrestation(1);
        p1.setPrix(new BigDecimal("100.00"));
        Prestation p2 = new Prestation();
        p2.setIdPrestation(2);
        p2.setPrix(new BigDecimal("250.50"));
        when(repository.findAll()).thenReturn(Arrays.asList(p1, p2));

        List<PrestationDTO> result = service.getAll();

        assertEquals(2, result.size());
        assertEquals(new BigDecimal("100.00"), result.get(0).getPrix());
    }

    @Test
    void shouldCreatePrestation() {
        PrestationDTO dto = new PrestationDTO();
        dto.setPrix(new BigDecimal("150.00"));
        dto.setIdReservation(10);
        Prestation savedEntity = new Prestation();
        savedEntity.setIdPrestation(55);
        savedEntity.setPrix(new BigDecimal("150.00"));

        when(repository.save(any(Prestation.class))).thenReturn(savedEntity);
        PrestationDTO result = service.create(dto);
        assertNotNull(result.getIdPrestation());
        assertEquals(55, result.getIdPrestation());
        assertEquals(new BigDecimal("150.00"), result.getPrix());
        verify(repository, times(1)).save(any(Prestation.class));
    }

    @Test
    void shouldThrowExceptionWhenUpdatingUnknownId() {
        Integer unknownId = 999;
        PrestationDTO updateInfo = new PrestationDTO();
        updateInfo.setPrix(new BigDecimal("500.00"));
        when(repository.findById(unknownId)).thenReturn(Optional.empty());
        Exception exception = assertThrows(RuntimeException.class, () -> {
            service.update(unknownId, updateInfo);
        });

        assertTrue(exception.getMessage().contains("non trouvée"));
    }

    @Test
    void shouldDeletePrestation() {
        Integer id = 1;
        service.delete(id);
        verify(repository, times(1)).deleteById(id);
    }
}
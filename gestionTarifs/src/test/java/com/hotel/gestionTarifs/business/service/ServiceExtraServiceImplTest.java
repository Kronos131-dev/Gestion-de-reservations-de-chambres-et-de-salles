package com.hotel.gestionTarifs.business.service;

import com.hotel.gestionTarifs.business.dto.ServiceExtraDTO;
import com.hotel.gestionTarifs.business.service.impl.ServiceExtraServiceImpl;
import com.hotel.gestionTarifs.persistence.entity.ServiceExtra;
import com.hotel.gestionTarifs.persistence.repository.ServiceExtraRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ServiceExtraServiceImplTest {

    @Mock
    private ServiceExtraRepository repository;

    @InjectMocks
    private ServiceExtraServiceImpl service;

    @Test
    void shouldReturnAllServiceExtras() {
        ServiceExtra s1 = new ServiceExtra("Wifi", "Haut débit", 10);
        s1.setIdService(1);
        ServiceExtra s2 = new ServiceExtra("Petit dej", "Continental", 15);
        s2.setIdService(2);

        when(repository.findAll()).thenReturn(Arrays.asList(s1, s2));
        List<ServiceExtraDTO> result = service.getAllServiceExtras();

        assertEquals(2, result.size());
        assertEquals("Wifi", result.get(0).getNom());
    }

    @Test
    void shouldCreateServiceExtra() {
        ServiceExtraDTO dto = new ServiceExtraDTO(null, "Spa", "Accès 1h", 50);
        ServiceExtra savedEntity = new ServiceExtra("Spa", "Accès 1h", 50);
        savedEntity.setIdService(10); // simule l'ID généré par la BDD

        when(repository.save(any(ServiceExtra.class))).thenReturn(savedEntity);
        ServiceExtraDTO result = service.createServiceExtra(dto);

        assertNotNull(result.getId());
        assertEquals(10, result.getId());
        assertEquals("Spa", result.getNom());
        verify(repository, times(1)).save(any(ServiceExtra.class));
    }

    @Test
    void shouldThrowExceptionWhenUpdatingUnknownId() {
        Integer unknownId = 999;
        ServiceExtraDTO updateInfo = new ServiceExtraDTO(unknownId, "Test", "Desc", 100);

        when(repository.findById(unknownId)).thenReturn(Optional.empty());
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            service.updateServiceExtra(unknownId, updateInfo);
        });

        assertTrue(exception.getMessage().contains("introuvable"));
    }

    @Test
    void shouldDeleteServiceExtra() {
        Integer id = 1;
        when(repository.existsById(id)).thenReturn(true);
        service.deleteServiceExtra(id);
        verify(repository, times(1)).deleteById(id);
    }
}
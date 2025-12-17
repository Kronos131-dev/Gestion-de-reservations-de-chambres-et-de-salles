package com.ulco.hotel.gestion.gestion_chambre_salle.business;

import com.ulco.hotel.gestion.gestion_chambre_salle.persistence.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EspaceServiceTest {

    @Mock
    private EspaceRepository espaceRepo;

    @InjectMocks
    private EspaceService service;

    @Test
    void testFindAllEspaces() {
        Espace e1 = new Espace();
        e1.setId_espace(1L);
        e1.setDescription("Chambre 101");
        Espace e2 = new Espace();
        e2.setId_espace(2L);
        e2.setDescription("Chambre 102");
        when(espaceRepo.findAll()).thenReturn(Arrays.asList(e1, e2));
        List<Espace> result = service.findAll();
        assertEquals(2, result.size());
        assertEquals("Chambre 101", result.get(0).getDescription());
    }

    @Test
    void testFindEspaceById() {
        Espace espace = new Espace();
        espace.setId_espace(1L);
        espace.setDescription("Chambre 101");
        when(espaceRepo.findById(1L)).thenReturn(Optional.of(espace));
        Espace result = service.findById(1L);
        assertEquals("Chambre 101", result.getDescription());
    }

    @Test
    void testSaveEspace() {
        Espace espace = new Espace();
        espace.setDescription("Chambre 101");
        espace.setNb_place(2L);
        espace.setPrix_base(100.0f);
        when(espaceRepo.save(espace)).thenReturn(espace);
        Espace result = service.save(espace);
        assertEquals("Chambre 101", result.getDescription());
        verify(espaceRepo, times(1)).save(espace);
    }

    @Test
    void testDeleteEspace() {
        service.deleteById(1L);
        verify(espaceRepo, times(1)).deleteById(1L);
    }

    @Test
    void testUpdateEspace() {
        Espace existing = new Espace();
        existing.setId_espace(1L);
        existing.setDescription("Ancienne description");
        Espace updated = new Espace();
        updated.setDescription("Nouvelle description");
        updated.setNb_place(3L);
        when(espaceRepo.findById(1L)).thenReturn(Optional.of(existing));
        when(espaceRepo.save(any(Espace.class))).thenAnswer(inv -> inv.getArgument(0));
        Espace result = service.update(1L, updated);
        assertEquals("Nouvelle description", result.getDescription());
        assertEquals(3L, result.getNb_place());
    }
}
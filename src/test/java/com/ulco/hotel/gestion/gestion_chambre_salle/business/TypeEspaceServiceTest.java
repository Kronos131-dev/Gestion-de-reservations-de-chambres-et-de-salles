package com.ulco.hotel.gestion.gestion_chambre_salle.business;

import com.ulco.hotel.gestion.gestion_chambre_salle.persistence.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TypeEspaceServiceTest {

    @Mock
    private TypeEspaceRepository typeRepo;

    @Mock
    private EspaceRepository espaceRepo;

    @InjectMocks
    private TypeEspaceService service;

    @Test
    void testFindAll() {
        TypeEspace type1 = new TypeEspace();
        type1.setId_type(1L);
        type1.setNom_espace("Standard");
        TypeEspace type2 = new TypeEspace();
        type2.setId_type(2L);
        type2.setNom_espace("Suite");
        when(typeRepo.findAll()).thenReturn(Arrays.asList(type1, type2));
        List<TypeEspace> result = service.findAll();
        assertEquals(2, result.size());
        assertEquals("Standard", result.get(0).getNom_espace());
    }

    @Test
    void testFindByIdExist() {
        TypeEspace type = new TypeEspace();
        type.setId_type(1L);
        type.setNom_espace("Standard");
        when(typeRepo.findById(1L)).thenReturn(Optional.of(type));
        TypeEspace result = service.findById(1L);
        assertEquals("Standard", result.getNom_espace());
    }

    @Test
    void testFindByIdNotExist() {
        when(typeRepo.findById(99L)).thenReturn(Optional.empty());
        Exception exception = assertThrows(RuntimeException.class, () -> {
            service.findById(99L);
        });
        assertTrue(exception.getMessage().contains("99"));
    }

    @Test
    void testSave() {
        TypeEspace type = new TypeEspace();
        type.setNom_espace("Standard");
        when(typeRepo.save(type)).thenReturn(type);
        TypeEspace result = service.save(type);
        assertEquals("Standard", result.getNom_espace());
        verify(typeRepo, times(1)).save(type);
    }

    @Test
    void testDeleteSuccess() {
        when(espaceRepo.findByTypeEspaceId(1L)).thenReturn(new ArrayList<>());
        service.deleteById(1L);
        verify(typeRepo, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteFailWithEspaces() {
        Espace espace = new Espace();
        espace.setId_espace(10L);
        when(espaceRepo.findByTypeEspaceId(1L)).thenReturn(Arrays.asList(espace));
        Exception exception = assertThrows(RuntimeException.class, () -> {
            service.deleteById(1L);
        });
        assertTrue(exception.getMessage().contains("utilisé"));
    }
}
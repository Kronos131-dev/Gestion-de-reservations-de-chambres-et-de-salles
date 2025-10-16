package com.ulco.hotel.reservation_service.business;

import com.ulco.hotel.reservation_service.persistence.Reservation;
import com.ulco.hotel.reservation_service.persistence.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReservationServiceTests {

    private ReservationRepository repo;
    private ReservationService service;

    @BeforeEach
    void setup() {
        repo = Mockito.mock(ReservationRepository.class); // Mockito va imiter ce Repo et capturer tous les appels
        service = new ReservationService(repo);
    }

    @Test
    void testGetAll() {
        // Vérifier qu'il est possible de récupérer toutes les données depuis la BDD
        Reservation r = new Reservation();
        r.setIdReservation(1L);
        r.setDureeJours(3);
        when(repo.findAll()).thenReturn(List.of(r)); // Quand finAll est appelé, retourner une liste avec r via Mockito

        List<Reservation> list = service.getAll();

        assertEquals(1, list.size());
        verify(repo, times(1)).findAll();
    }

    @Test
    void testGetById() {
        // Vérifier qu'il est possible de récupérer un élément de la BDD
        Reservation r = new Reservation();
        r.setIdReservation(1L);
        when(repo.findById(1L)).thenReturn(Optional.of(r));

        Optional<Reservation> result = service.getById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getIdReservation());
    }

    @Test
    void testCreate() {
        // Créer un nouvel élément dans la BDD
        Reservation r = new Reservation();
        r.setIdReservation(1L);
        when(repo.save(r)).thenReturn(r); // Mockito intercepte les appels à save et retourne r

        Reservation saved = service.create(r);

        assertEquals(1L, saved.getIdReservation());
        verify(repo, times(1)).save(r); // Save a bien été appelé une fois
    }

    @Test
    void testDelete() {
        // Supprimer un élément dans le BDD
        service.delete(1L);
        verify(repo, times(1)).deleteById(1L);
    }
}

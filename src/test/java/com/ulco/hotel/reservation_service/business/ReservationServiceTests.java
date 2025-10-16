package com.ulco.hotel.reservation_service.business;

import com.ulco.hotel.reservation_service.persistence.Reservation;
import com.ulco.hotel.reservation_service.persistence.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReservationServiceTests {

    private ReservationRepository repo;
    private ReservationService service;

    @BeforeEach
    void setup() {
        repo = Mockito.mock(ReservationRepository.class);
        service = new ReservationService(repo);
    }

    @Test
    void testGetAll() {
        Reservation r = new Reservation();
        r.setIdReservation(1L);
        r.setDureeJours(3);
        when(repo.findAll()).thenReturn(List.of(r));

        List<Reservation> list = service.getAll();

        assertEquals(1, list.size());
        verify(repo, times(1)).findAll();
    }

}
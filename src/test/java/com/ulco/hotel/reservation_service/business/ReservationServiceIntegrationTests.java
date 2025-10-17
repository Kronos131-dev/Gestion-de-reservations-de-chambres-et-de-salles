package com.ulco.hotel.reservation_service.business;

import com.ulco.hotel.reservation_service.persistence.Reservation;
import com.ulco.hotel.reservation_service.persistence.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // Charge tout le contexte Spring
@ActiveProfiles("test") // Charge explicitement le test properties
class ReservationServiceIntegrationTests {

    @Autowired
    private ReservationService service;

    @Autowired
    private ReservationRepository repository;

    @BeforeEach
    void setup() {
        // Pour les tests, je supprime toutes les données de la BDD locale, et je remets deux réservations dedans
        repository.deleteAll();

        Reservation r1 = Reservation.builder()
                .idClient(1L)
                .idEspace(2L)
                .idSaison(3L)
                .dureeJours(5)
                .dateArrivee(LocalDate.of(2025, 10, 18))
                .remarqueClient("Test réservation H2")
                .build();
        repository.save(r1);

        Reservation r2 = Reservation.builder()
                .idClient(2L)
                .idEspace(3L)
                .idSaison(58745L)
                .dureeJours(365)
                .dateArrivee(LocalDate.of(2025, 10, 30))
                .remarqueClient("Test réservation H2")
                .build();
        repository.save(r2);
    }

    @Test
    void testGetAll() {
        List<Reservation> list = service.getAll();
        assertEquals(2, list.size(), "La base H2 devrait contenir 2 réservations");
    }

    @Test
    void testGetById() {
        Reservation existing = repository.findAll().get(0);
        Optional<Reservation> result = service.getById(existing.getIdReservation());
        assertTrue(result.isPresent());
        assertEquals(existing.getDateArrivee(), result.get().getDateArrivee());
    }

    @Test
    void testCreate() {
        Reservation r3 = new Reservation(); // Au lieu d'utiliser le builder, je vérifie que les méthodes sont bien générées par Lombok
        r3.setDateArrivee(LocalDate.of(2025, 12, 1));
        r3.setDureeJours(685465);
        r3.setIdClient(442154654L);
        r3.setIdEspace(467451564L);
        r3.setIdSaison(89L);
        r3.setDureeJours(5);
        r3.setDateArrivee(LocalDate.of(2025, 10, 18));

        Reservation saved = service.create(r3);
        assertNotNull(saved.getIdReservation());
        assertEquals(3, repository.count());
    }

    @Test
    void testDelete() {
        Reservation toDelete = repository.findAll().get(0);
        service.delete(toDelete.getIdReservation());
        assertEquals(1, repository.count());
        assertFalse(repository.findById(toDelete.getIdReservation()).isPresent());
    }
}
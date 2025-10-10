package com.ulco.hotel.reservation_service.presentation;

import com.ulco.hotel.reservation_service.business.ReservationService;
import com.ulco.hotel.reservation_service.persistence.Reservation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationRestController {

    private final ReservationService service;

    public ReservationRestController(ReservationService service) {
        this.service = service;
    }

    @GetMapping
    public List<Reservation> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Reservation getById(@PathVariable Long id) {
        return service.getById(id).orElse(null);
    }

    @PostMapping
    public Reservation create(@RequestBody Reservation reservation) {
        return service.create(reservation);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

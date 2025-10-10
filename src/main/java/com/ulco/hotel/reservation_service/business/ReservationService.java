package com.ulco.hotel.reservation_service.business;

import com.ulco.hotel.reservation_service.persistence.Reservation;
import com.ulco.hotel.reservation_service.persistence.ReservationRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    private final ReservationRepository repo;

    public ReservationService(ReservationRepository repo) {
        this.repo = repo;
    }

    public List<Reservation> getAll() {
        return repo.findAll();
    }

    public Optional<Reservation> getById(Long id) {
        return repo.findById(id);
    }

    public Reservation create(Reservation reservation) {
        return repo.save(reservation);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}

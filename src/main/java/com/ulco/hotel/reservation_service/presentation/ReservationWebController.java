package com.ulco.hotel.reservation_service.presentation;

import com.ulco.hotel.reservation_service.business.ReservationService;
import com.ulco.hotel.reservation_service.persistence.Reservation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/web/reservations")
public class ReservationWebController {

    private final ReservationService service;

    public ReservationWebController(ReservationService service) {
        this.service = service;
    }

    @GetMapping
    public String listReservations(Model model) {
        model.addAttribute("reservations", service.getAll());
        return "reservations/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("reservation", new Reservation());
        return "reservations/create";
    }

    @PostMapping("/create")
    public String createReservation(@ModelAttribute Reservation reservation) {
        service.create(reservation);
        return "redirect:/web/reservations";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Reservation r = service.getById(id).orElseThrow();
        model.addAttribute("reservation", r);
        return "reservations/edit";
    }

    @PostMapping("/edit/{id}")
    public String editReservation(@PathVariable Long id, @ModelAttribute Reservation reservation) {
        reservation.setIdReservation(id);
        service.create(reservation);
        return "redirect:/web/reservations";
    }

    @GetMapping("/delete/{id}")
    public String deleteReservation(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/web/reservations";
    }
}

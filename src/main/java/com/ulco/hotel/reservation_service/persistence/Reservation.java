package com.ulco.hotel.reservation_service.persistence;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "reservations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reservation")
    private Long idReservation;

    @Column(name = "id_client", nullable = false)
    private Long idClient;

    @Column(name = "id_espace", nullable = false)
    private Long idEspace;

    @Column(name = "id_saison", nullable = false)
    private Long idSaison;

    @Column(name = "duree_jours", nullable = false)
    private Integer dureeJours;

    @Column(name = "date_arrivee", nullable = false)
    private LocalDate dateArrivee;

    @Column(name = "remarque_client", columnDefinition = "TEXT")
    private String remarqueClient;
}
